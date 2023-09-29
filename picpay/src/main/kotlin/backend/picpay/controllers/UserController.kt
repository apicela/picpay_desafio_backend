package backend.picpay.controllers

import backend.picpay.dtos.UserDTO
import backend.picpay.models.User
import backend.picpay.projections.UserProjectionImpl
import backend.picpay.services.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    @Autowired
    val userService: UserService
) {
    @PostMapping
    @Operation(summary = "Criar usuário", description = "API que cria um novo usuário.")
    fun createUser(@RequestBody @Valid userDTO: UserDTO): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO))
    }

    @GetMapping
    @Operation(summary = "Listar todos usuários", description = "API que retorna todos usuários.")
    fun listAllUsers(): ResponseEntity<List<UserProjectionImpl>> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listAllUsers())
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar usuário através da ID",
        description = "API que retorna o usuário com o ID especificado.."
    )
    fun findUserById(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserProjectionImplById(id))
    }

    companion object {
        const val ENDPOINT = "/users"
    }
}