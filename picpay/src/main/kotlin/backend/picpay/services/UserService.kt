package backend.picpay.services

import backend.picpay.dtos.UserDTO
import backend.picpay.exceptions.InsufficientBalance
import backend.picpay.exceptions.UnauthorizedTransfer
import backend.picpay.models.AccountType
import backend.picpay.models.User
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
        if (sender.accountType != AccountType.COMMUN) {
            throw UnauthorizedTransfer("Contas do tipo ${sender.accountType} não podem enviar transferências!")
        }
    }

    fun hasBalance(sender: User, amount: BigDecimal){
        if(sender.balance < amount) throw InsufficientBalance("Saldo insuficiente para realizar a transferência de R$$amount!")
    }

    fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    fun createUser(userDTO: UserDTO): User {
        val user = User(userDTO)
        return userRepository.save(user)
    }

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun listAllUsers(): List<User> {
        return userRepository.findAll()
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
        AccountType.COMMUN
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
        AccountType.COMMUN
    )

    fun saveUser() {
        userRepository.save(user1)
        userRepository.save(user2)
        userRepository.save(user3)

    }
}