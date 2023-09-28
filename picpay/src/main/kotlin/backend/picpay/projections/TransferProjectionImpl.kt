package backend.picpay.projections

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

class TransferProjectionImpl(
    override val id: Long,
    override val sender_id: Long,
    override val sender_name: String,
    override val receiver_id: Long,
    override val receiver_name: String,
    override val amount: BigDecimal,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    override val date: LocalDateTime

) : TransferProjection