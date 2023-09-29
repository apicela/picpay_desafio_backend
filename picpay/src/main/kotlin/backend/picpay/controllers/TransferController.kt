package backend.picpay.controllers

import backend.picpay.dtos.TransferDTO
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.services.TransferService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/transactions")
class TransferController(
    @Autowired
    val transferService: TransferService,
) {
    @PostMapping
    @Operation(summary = "Criar transferência", description = "API que realiza transferência entre usuários.")
    fun createTransfer(@RequestBody @Valid transferDTO: TransferDTO): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(transferDTO))
    }

    @GetMapping
    @Operation(
        summary = "Listar todas transferências",
        description = "API que retorna todas transferência entre usuários."
    )
    fun listAllTransfers(): ResponseEntity<List<TransferProjectionImpl>> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.listAllTransfers())
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar transferência por ID",
        description = "API que busca uma transferência já existente baseado no ID"
    )
    fun listAllTransfers(@PathVariable(value = "id") id: Long): ResponseEntity<TransferProjectionImpl> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findById(id))
    }

    @GetMapping("/user/{userId}")
    @Operation(
        summary = "Buscar transferências do  usuário",
        description = "API que busca TODAS transferência vinculadas ao usuário"
    )
    fun findTransfersByUserId(@PathVariable(value = "userId") userId: Long): ResponseEntity<List<TransferProjectionImpl>> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findTransfersByUserId(userId))
    }
    companion object {
        const val ENDPOINT = "/transactions"
    }
}