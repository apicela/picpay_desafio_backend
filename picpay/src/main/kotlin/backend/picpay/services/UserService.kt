package backend.picpay.services

import backend.picpay.dtos.UserDTO
import backend.picpay.exceptions.InsufficientBalance
import backend.picpay.exceptions.UnauthorizedTransfer
import backend.picpay.exceptions.UserNotFound
import backend.picpay.models.AccountType
import backend.picpay.models.User
import backend.picpay.projections.UserProjectionImpl
import backend.picpay.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal

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
            user.balance,
            user.accountType
        )
    }

    fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
            ?: throw UserNotFound("O usuário $id não existe! Verifique se o campo está preenchido corretamente.")
    }

    fun createUser(userDTO: UserDTO): User {
        val user = User(userDTO)
        return userRepository.save(user)
    }


    fun listAllUsers(): List<UserProjectionImpl> {
        val users = userRepository.findAll()

        return users.map { user ->
            UserProjectionImpl(
                user.fullName, user.document, user.email,
                user.balance, user.accountType
            )
        }
    }


}