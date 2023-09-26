package backend.picpay.services

import backend.picpay.dtos.TransferDTO
import backend.picpay.exceptions.UserNotFound
import backend.picpay.models.Transfer
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.repositories.TransferRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransferService(
    @Autowired
    val transferRepository: TransferRepository,
    @Autowired
    val userService: UserService
) {
    @Transactional
    fun createTransfer(transferDTO: TransferDTO): Transfer? {
        val sender = checkNotNull(userService.findById(transferDTO.sender))
        { throw UserNotFound("O usuário ${transferDTO.sender} não existe! Verifique se o campo está preenchido corretamente.") }
        val receiver = checkNotNull(userService.findById(transferDTO.receiver))
        { throw UserNotFound("O usuário ${transferDTO.receiver} não existe! Verifique se o campo está preenchido corretamente.") }
        userService.canTransfer(sender)
        userService.hasBalance(sender, transferDTO.amount)
        sender.balance = sender.balance.subtract(transferDTO.amount)
        receiver.balance = receiver.balance.plus(transferDTO.amount)
        val transfer: Transfer = Transfer(null, sender, receiver, transferDTO.amount, LocalDateTime.now())
        return transferRepository.save(transfer)
    }

    fun listAllTransfers(): MutableList<TransferProjectionImpl> {
        val transfers = transferRepository.findAll()

        return transfers.map { transfer ->
            TransferProjectionImpl(
                sender_id = transfer.sender.id!!,
                sender_name = transfer.sender.fullName,
                receiver_id = transfer.receiver.id!!,
                receiver_name = transfer.receiver.fullName,
                amount = transfer.amount,
                date = transfer.date
            )
        }.toMutableList()
    }


    fun findById(id: Long): TransferProjectionImpl? {
        val transfer = transferRepository.findById(id)
        return transfer.map { transfer ->
            TransferProjectionImpl(
                sender_id = transfer.sender.id!!,
                sender_name = transfer.sender.fullName,
                receiver_id = transfer.receiver.id!!,
                receiver_name = transfer.receiver.fullName,
                amount = transfer.amount,
                date = transfer.date
            )
        }.orElse(null)
    }

}