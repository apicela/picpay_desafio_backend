package backend.picpay.projections

import backend.picpay.models.AccountType
import java.math.BigDecimal

class UserProjectionImpl(
    override val fullName: String,
    override val document: String,
    override val email: String,
    override var balance: BigDecimal,
    override val accountType: AccountType
) : UserProjection