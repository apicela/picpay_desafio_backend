package backend.picpay.dtos

import backend.picpay.models.AccountType
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

class UserDTO(
    @NotBlank
    val fullName: String,
    @NotBlank
    val document: String,
    @NotBlank
    val email: String,
    @NotBlank
    var password: String,
    @NotNull
    var balance: BigDecimal,
    @NotNull
    val accountType: AccountType
) {

}