package backend.picpay.projections

import backend.picpay.models.AccountType
import backend.picpay.models.Transfer
import java.math.BigDecimal


interface UserProjection {
    val fullName: String
    val document: String
    val email: String
    var receivedTransfers: MutableList<TransferProjectionImpl>?
    var sendedTransfers: MutableList<TransferProjectionImpl>?
    var balance: BigDecimal
    val accountType: AccountType

}
