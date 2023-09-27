package backend.picpay.exceptions

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import shared.StringRegex

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalance::class)
    fun handleInsufficientBalance(ex: InsufficientBalance): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(UnauthorizedTransfer::class)
    fun handleUnauthorizedTransfer(ex: UnauthorizedTransfer): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(UserNotFound::class)
    fun handleUserNotFound(ex: UserNotFound): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex : DataIntegrityViolationException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Já existe um usuário com os dados cadastrados!")
    }
    @ExceptionHandler(TransferNotFound::class)
    fun handleTransferNotFound(ex: TransferNotFound): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentInvalid(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val validationErrors = ex.allErrors.forEach { err ->
            val s : String = err.defaultMessage.toString() // captura a mensagem do erro
           val lastWord = StringRegex.lastWord.find(s)?.value
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("")
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleNotReadableMessage(ex: HttpMessageNotReadableException): ResponseEntity<Any> {
        val errors = ArrayList<String>()
        errors.add("Erro na leitura da mensagem! ${ex.message}\n" +
                "verifique se os campos estão preenchidos corretamente")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
}
