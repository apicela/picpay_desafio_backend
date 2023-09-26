package backend.picpay.dtos

import backend.picpay.models.AccountType
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

class UserDTO(
    @NotNull
    val fullName: String,
    @NotNull
    val document: String,
    @NotNull
    val email: String,
    @NotNull
    var password: String,
    @NotNull
    var balance: BigDecimal,
    @NotNull
    val accountType: AccountType
) {
    companion object {
        fun getDefault(): UserDTO {
            return UserDTO(
                fullName = "412421",
                document = "string",
                email = "string",
                password = "string",
                balance = BigDecimal.ZERO,
                accountType = AccountType.COMMUN
            )
        }
    }
}