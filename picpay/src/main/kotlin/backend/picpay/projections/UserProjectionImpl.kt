package backend.picpay.projections

import backend.picpay.models.AccountType
import backend.picpay.models.Transfer
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

class UserProjectionImpl(
    override val fullName: String,
    override val document: String,
    override val email: String,
    override var receivedTransfers: MutableList<TransferProjectionImpl>?,
    override var sendedTransfers: MutableList<TransferProjectionImpl>?,
    override var balance: BigDecimal,
    override val accountType: AccountType
) : UserProjection{

}