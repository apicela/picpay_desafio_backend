package backend.picpay.models

import backend.picpay.dtos.UserDTO
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
}