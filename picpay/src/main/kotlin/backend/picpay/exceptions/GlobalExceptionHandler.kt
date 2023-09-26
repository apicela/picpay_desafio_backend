package backend.picpay.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalance::class)
    fun handleInsufficientBalance(ex : InsufficientBalance) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(UnauthorizedTransfer::class)
    fun handleUnauthorizedTransfer(ex : UnauthorizedTransfer) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(UserNotFound::class)
    fun handleUserNotFound(ex : UserNotFound) : ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}