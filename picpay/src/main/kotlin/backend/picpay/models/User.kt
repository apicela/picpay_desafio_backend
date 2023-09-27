package backend.picpay.models

import backend.picpay.dtos.UserDTO
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val fullName: String,
    @Column(unique = true)
    val document: String,
    @Column(unique = true)
    val email: String,
    var password: String,
    @OneToMany(mappedBy = "receiver")
    @JsonBackReference
    var receivedTransfers: MutableList<Transfer>? = null,
    @OneToMany(mappedBy = "sender")
    @JsonBackReference
    var sendedTransfers: MutableList<Transfer>? = null,
    var balance: BigDecimal,
    @Enumerated(EnumType.STRING)
    val accountType: AccountType
) {
    constructor(userDTO: UserDTO) : this(
        fullName = userDTO.fullName,
        document = userDTO.document,
        email = userDTO.email,
        password = userDTO.password,
        balance = userDTO.balance,
        accountType = userDTO.accountType
    )

    override fun toString(): String {
        return "User(id=$id, fullName='$fullName'," +
                " document='$document', email='$email', password='$password', " +
                "receivedTransfers=$receivedTransfers, sendedTransfers=$sendedTransfers," +
                " balance=$balance, accountType=$accountType)"
    }

}