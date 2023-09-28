package backend.picpay.models

//import lombok.ToString
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Transfer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val sender: Long,
    val receiver: Long,
    val amount: BigDecimal,
    val date: LocalDateTime
)

