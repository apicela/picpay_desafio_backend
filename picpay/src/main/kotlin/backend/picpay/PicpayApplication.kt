package backend.picpay

import backend.picpay.repositories.UserRepository
import backend.picpay.services.UserService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PicpayApplication(
) {

}

fun main(args: Array<String>) {
    runApplication<PicpayApplication>(*args)
}
