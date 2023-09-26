package backend.picpay.projections

import java.math.BigDecimal
import java.time.LocalDateTime

class TransferProjectionImpl(
    override val sender_id: Long,
    override val sender_name: String,
    override val receiver_id: Long,
    override val receiver_name: String,
    override val amount: BigDecimal,
    override val date: LocalDateTime
) : TransferProjection