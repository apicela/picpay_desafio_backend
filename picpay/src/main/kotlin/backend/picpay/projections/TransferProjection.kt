package backend.picpay.projections

import java.math.BigDecimal
import java.time.LocalDateTime

interface TransferProjection {
    val sender_id: Long
    val sender_name: String
    val receiver_id: Long
    val receiver_name: String
    val amount: BigDecimal
    val date: LocalDateTime
}