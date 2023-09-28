package backend.picpay.services

import backend.picpay.dtos.TransferDTO
import backend.picpay.exceptions.TransferNotFound
import backend.picpay.exceptions.UserNotFound
import backend.picpay.models.Transfer
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.repositories.TransferRepository
import backend.picpay.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
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
        val transfer: Transfer =
            Transfer(null, transferDTO.sender, transferDTO.receiver, transferDTO.amount, LocalDateTime.now())
        transferRepository.save(transfer)
        return "A transferência de R$${transfer.amount} foi realizada com sucesso!\n" +
                "Pagador: ${(sender.fullName)}\n" +
                "Favorecido: ${(receiver).fullName}"

    }

    fun listAllTransfers(): MutableList<TransferProjectionImpl> {
        return transferRepository.findAll().map { it ->
            val senderName: String = userRepository.findById(it.sender).get().fullName
            val receiverName: String = userRepository.findById(it.receiver).get().fullName
            TransferProjectionImpl(
                it.id!!, it.sender, senderName, it.receiver,
                receiverName, it.amount, it.date
            )
        }.toMutableList()
    }

    fun findById(id: Long): TransferProjectionImpl? {
        val transfer = (transferRepository.findByIdOrNull(id))
            ?: throw TransferNotFound("A transferência $id não existe! Verifique se o campo está preenchido corretamente.")
        val senderName: String = userService.findById(transfer.sender)!!.fullName
        val receiverName: String = userService.findById(transfer.sender)!!.fullName
        return TransferProjectionImpl(
            transfer.id!!, transfer.sender, senderName, transfer.receiver,
            receiverName, transfer.amount, transfer.date
        )

    }

    fun findTransfersByUserId(userId: Long): MutableList<TransferProjectionImpl> {
        println(transferRepository.findTransfersByUserId(userId).size)
        return transferRepository.findTransfersByUserId(userId).map {
            val senderName: String = userRepository.findById(it.sender).get().fullName
            val receiverName: String = userRepository.findById(it.receiver).get().fullName
            TransferProjectionImpl(
                it.id!!, it.sender, senderName, it.receiver,
                receiverName, it.amount, it.date
            )
        }.toMutableList()


    }
}