package backend.picpay.controllers

import backend.picpay.dtos.TransferDTO
import backend.picpay.projections.TransferProjectionImpl
import backend.picpay.services.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
class TransferController(
    @Autowired
    val transferService: TransferService
) {

    @PostMapping
    fun createTransfer(transferDTO: TransferDTO): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(transferDTO))
    }

    @GetMapping
    fun listAllTransfers(): ResponseEntity<List<TransferProjectionImpl>> {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.listAllTransfers())
    }

//    @GetMapping("/{id}")
//    fun listAllTransfers(@PathVariable (value = "id") id: Long) : ResponseEntity<Transfer>{
//        return ResponseEntity.status(HttpStatus.OK).body(transferService.findById(id))
//    }

}