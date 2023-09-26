package backend.picpay.dtos

import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

class TransferDTO(
    @NotNull
    val sender: Long,
    @NotNull
    val receiver: Long,
    @NotNull
    val amount: BigDecimal
)