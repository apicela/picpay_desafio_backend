package backend.picpay

import backend.picpay.repositories.UserRepository
import backend.picpay.services.UserService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PicpayApplication(
    @Autowired
    val user22: UserRepository,
    @Autowired
    val user: UserService
) {
    @PostConstruct
    fun saveUsers() {
        user.saveUser()
    }
}

fun main(args: Array<String>) {
    runApplication<PicpayApplication>(*args)
}
