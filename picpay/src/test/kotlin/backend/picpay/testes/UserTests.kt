package backend.picpay.testes.unitarios

import backend.picpay.controllers.UserController
import backend.picpay.dtos.UserDTO
import backend.picpay.models.AccountType
import backend.picpay.models.User
import backend.picpay.repositories.UserRepository
import backend.picpay.services.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserTests {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private val mvc: MockMvc? = null

    var objectMapper = ObjectMapper()
    @Test
    @Order(0)
    @DisplayName("teste service create")
    fun testCreateService() {
        val userDTO = UserDTO("Mekon Trata",
            "123987456",
            "tech@recruiter.com",
            "3213421",
            BigDecimal.TEN,
            AccountType.VENDOR)

        userService.createUser(userDTO)
        val lastUser = userRepository.findAll().last()
        Assertions.assertEquals(userDTO.email, lastUser.email)
        Assertions.assertEquals(userDTO.document, lastUser.document)
    }

    @Test
    @Order(1)
    @DisplayName("teste service findById")
    fun testFindByIdService() {
        val user1 = userService.findById(1)
        val user2 = userRepository.findById(1).get()
        Assertions.assertEquals(user1,user2)
    }

    @Test
    @Order(2)
    @DisplayName("Http Method: POST - status Created")
    fun postUser_CREATED() {
        val usuario = UserDTO(
            "HelloWorld",
            "12333",
            "hello@world.com",
            "123",
            BigDecimal("8888"),
            AccountType.COMMON
        )

         mvc?.perform(
            MockMvcRequestBuilders.post(UserController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario))
                .accept(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isCreated())
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value(usuario.fullName))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.document").value(usuario.document))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty)
             ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }
    @Test
    @Order(3)
    @DisplayName("Http Method: POST - status BAD REQUEST")
    fun postUser_BADREQUEST() {
        mvc?.perform(
            MockMvcRequestBuilders.post(UserController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                      """{
                          "document": "string",
                          "email": "string",
                          "password": "string",
                          "balance": 0,
                          "accountType": "COMMON"
                      }""")
                .accept(MediaType.APPLICATION_JSON))
                ?.andExpect(MockMvcResultMatchers.status().isBadRequest())
                ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(4)
    @DisplayName("Http Method: GET - status NOTFOUND")
    fun getUser_NOTFOUND() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${UserController.ENDPOINT}/999999")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isNotFound())
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(5)
    @DisplayName("Http Method: GET - status OK")
    fun getUser_OK() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${UserController.ENDPOINT}/1")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isOk())
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value(userRepository.findById(1).get().fullName))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.document").value(userRepository.findById(1).get().document))
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(6)
    @DisplayName("Http Method: GET - status BADREQUEST")
    fun getUser_BADREQUEST() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${UserController.ENDPOINT}/apicela")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isBadRequest())
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }
}

