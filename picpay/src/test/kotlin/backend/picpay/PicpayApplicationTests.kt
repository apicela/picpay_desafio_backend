package backend.picpay

import backend.picpay.models.AccountType
import backend.picpay.models.Transfer
import backend.picpay.models.User
import backend.picpay.repositories.TransferRepository
import backend.picpay.repositories.UserRepository
import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
import org.hibernate.Hibernate
import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime
import org.hibernate.collection.spi.PersistentCollection
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@SpringBootTest
class PicpayApplicationTests {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var transferRepository: TransferRepository


    @PostConstruct
    @Transactional
    fun initialize(){
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

    }

}
