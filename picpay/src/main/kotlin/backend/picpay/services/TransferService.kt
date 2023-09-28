package backend.picpay.services

import backend.picpay.dtos.TransferDTO
import backend.picpay.exceptions.TransferNotFound
import backend.picpay.exceptions.UserNotFound
import backend.picpay.models.AccountType
import backend.picpay.models.Transfer
import backend.picpay.models.User
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.repositories.TransferRepository
import backend.picpay.repositories.UserRepository
import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransferService(
    @Autowired
    val transferRepository: TransferRepository,
    @Autowired
    val userRepository: UserRepository,
    @Autowired
    val userService: UserService
) {

    @PostConstruct
    @Transactional
    fun initialize(){
//        val session = sessionFactory.openSession();
        // criando usuarios pro teste
        val user1: User = User(
            null,
            "Jamil Apicela",
            "123456789",
            "trab.jamilsouza@gmail.com",
            "123",
            BigDecimal.valueOf(0),
            AccountType.COMMON
        )
        val user2: User = User(
            null,
            "Lobo de Wall Street",
            "987654321",
            "freemoney@lobowallstreet.com",
            "000",
            BigDecimal.valueOf(1_000_000_000),
            AccountType.VENDOR
        )
        val user3: User = User(
            null,
            "Jhin Carrey",
            "444444444",
            "jhin4444@jhin.com",
            "4444",
            BigDecimal.valueOf(8_888),
            AccountType.COMMON
        )
        userRepository.save(user1)
        userRepository.save(user2)
        userRepository.save(user3)

        val transfer1 =  Transfer(null, 3, 1, BigDecimal.TEN, LocalDateTime.now())
        val transfer2 =  Transfer(null, 3, 2, BigDecimal.ONE, LocalDateTime.now())
        val transfer3 =  Transfer(null, 1, 3, BigDecimal.ONE, LocalDateTime.now())

        transferRepository.save(transfer1)
        transferRepository.save(transfer2)
        transferRepository.save(transfer3)

//        session.close()
    }


    @Transactional
    fun createTransfer(transferDTO: TransferDTO): String? {
        val sender = checkNotNull(userService.findById(transferDTO.sender))
        { throw UserNotFound("O usuário ${transferDTO.sender} não existe! Verifique se o campo está preenchido corretamente.") }
        val receiver = checkNotNull(userService.findById(transferDTO.receiver))
        { throw UserNotFound("O usuário ${transferDTO.receiver} não existe! Verifique se o campo está preenchido corretamente.") }
        userService.canTransfer(sender)
        userService.hasBalance(sender, transferDTO.amount)
        sender.balance = sender.balance.subtract(transferDTO.amount)
        receiver.balance = receiver.balance.plus(transferDTO.amount)
        val transfer: Transfer = Transfer(null, transferDTO.sender,transferDTO.receiver,transferDTO.amount, LocalDateTime.now())
        transferRepository.save(transfer)
        return "A transferência de R$${transfer.amount} foi realizada com sucesso!\n" +
                "Pagador: ${(sender.fullName)}\n" +
                "Favorecido: ${(receiver).fullName}"

    }

    fun listAllTransfers(): MutableList<TransferProjectionImpl> {
       return transferRepository.findAll().map{it ->
           val senderName : String = userRepository.findById(it.sender).get().fullName
           val receiverName : String = userRepository.findById(it.receiver).get().fullName
           TransferProjectionImpl(
               it.id!!, it.sender, senderName, it.receiver,
               receiverName, it.amount, it.date
           )
       }.toMutableList()
    }

    fun findById(id: Long): TransferProjectionImpl? {
        val transfer = (transferRepository.findByIdOrNull(id))
            ?: throw TransferNotFound("A transferência $id não existe! Verifique se o campo está preenchido corretamente.")
        val senderName : String = userService.findById(transfer.sender)!!.fullName
        val receiverName : String = userService.findById(transfer.sender)!!.fullName
        return TransferProjectionImpl(
            transfer.id!!, transfer.sender, senderName, transfer.receiver,
            receiverName, transfer.amount, transfer.date
        )

    }


}