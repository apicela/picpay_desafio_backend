package backend.picpay.controllers

import backend.picpay.dtos.UserDTO
import backend.picpay.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    @Autowired
    val userService: UserService
) {

    @PostMapping
    fun createUser(userDTO: UserDTO): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO))
    }

    @GetMapping
    fun listAllUsers(): ResponseEntity<List<Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers())
    }

    @GetMapping("/{id}")
    fun listAllUsers(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id))
    }
}