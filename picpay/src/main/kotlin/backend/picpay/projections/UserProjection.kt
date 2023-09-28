package backend.picpay.projections

import backend.picpay.models.AccountType
import java.math.BigDecimal


interface UserProjection {
    val fullName: String
    val document: String
    val email: String
    var balance: BigDecimal
    val accountType: AccountType

}
