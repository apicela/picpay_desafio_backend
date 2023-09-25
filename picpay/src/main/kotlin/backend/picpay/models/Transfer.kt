package backend.picpay.models

//import lombok.ToString
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Transfer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonManagedReference
    val sender: User,
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonManagedReference
    val receiver: User,
    val amount: BigDecimal,
    val date: LocalDateTime
) {

}

