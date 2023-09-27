package backend.picpay.services

import backend.picpay.dtos.UserDTO
import backend.picpay.exceptions.InsufficientBalance
import backend.picpay.exceptions.UnauthorizedTransfer
import backend.picpay.exceptions.UserNotFound
import backend.picpay.models.AccountType
import backend.picpay.models.Transfer
import backend.picpay.models.User
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.projections.UserProjectionImpl
import backend.picpay.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class UserService(
    @Autowired
    val userRepository: UserRepository
) {

    fun canTransfer(sender: User) {
        if (sender.accountType != AccountType.COMMON) {
            throw UnauthorizedTransfer("Contas do tipo ${sender.accountType} não podem enviar transferências!")
        }
    }

    fun hasBalance(sender: User, amount: BigDecimal) {
        if (sender.balance < amount) throw InsufficientBalance("Saldo insuficiente para realizar a transferência de R$$amount!")
    }

    fun findUserProjectionImplById(id: Long): UserProjectionImpl? {
        val user = userRepository.findByIdOrNull(id)
            ?: throw UserNotFound("O usuário $id não existe! Verifique se o campo está preenchido corretamente.")

        return UserProjectionImpl(
            user.fullName,
            user.document,
            user.email,
            user.receivedTransfers?.map{ convertTransferToProjection(it) }?.toMutableList(),
            user.sendedTransfers?.map { convertTransferToProjection(it) }?.toMutableList(),
            user.balance,
            user.accountType
        )
    }

    fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
            ?: throw UserNotFound("O usuário $id não existe! Verifique se o campo está preenchido corretamente.")
    }

    fun createUser(userDTO: UserDTO): String {
        val user = User(userDTO)
        userRepository.save(user)
        return "O usuário de nome:${userDTO.fullName} e documento: ${userDTO.document} foi criado!"
    }


    fun listAllUsers(): List<UserProjectionImpl> {
        val users = userRepository.findAll()

        return users.map { user ->
            UserProjectionImpl(
                user.fullName, user.document, user.email,
                user.receivedTransfers?.map{ convertTransferToProjection(it) }?.toMutableList(),
                user.sendedTransfers?.map{ convertTransferToProjection(it) }?.toMutableList()
                , user.balance, user.accountType
            )
        }
    }


    val user1: User = User(
        null,
        "Jamil Souza",
        "123456789",
        "trab.jamilsouza@gmail.com",
        "123",
        null,
        null,
        BigDecimal.valueOf(0),
        AccountType.COMMON
    )
    val user2: User = User(
        null,
        "Lobo de Wall Street",
        "987654321",
        "freemoney@lobowallstreet.com",
        "000",
        null,
        null,
        BigDecimal.valueOf(1_000_000),
        AccountType.VENDOR
    )
    val user3: User = User(
        null,
        "Jhin Carry",
        "444444444",
        "jh1n4444@jhin.com",
        "4444",
        null,
        null,
        BigDecimal.valueOf(4_444),
        AccountType.COMMON
    )

    fun convertTransferToProjection(transfer: Transfer): TransferProjectionImpl {
        return TransferProjectionImpl(
            transfer.id!!,
            transfer.sender.id!!,
            transfer.sender.fullName,
            transfer.receiver.id!!,
            transfer.receiver.fullName,
            transfer.amount,
            transfer.date
        )
    }

    fun saveUser() {
        userRepository.save(user1)
        userRepository.save(user2)
        userRepository.save(user3)
    }
}