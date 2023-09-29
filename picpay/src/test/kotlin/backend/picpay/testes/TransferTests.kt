package backend.picpay.testes

import backend.picpay.controllers.TransferController
import backend.picpay.controllers.UserController
import backend.picpay.dtos.TransferDTO
import backend.picpay.repositories.TransferRepository
import backend.picpay.services.TransferService
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
class TransferTests {

    @Autowired
    private lateinit var transferRepository: TransferRepository

    @Autowired
    private lateinit var transferService: TransferService

    @Autowired
    private val mvc: MockMvc? = null

    var objectMapper = ObjectMapper()

    @Test
    @Order(0)
    @DisplayName("teste service create")
    fun testCreateService() {
        val transfer = TransferDTO(3,1, BigDecimal.ONE)
        transferService.createTransfer(transfer)
        val lastTransfer = transferRepository.findAll().last()
        Assertions.assertEquals(transfer.sender, lastTransfer.sender)
        Assertions.assertEquals(transfer.receiver, lastTransfer.receiver)
        Assertions.assertEquals(transfer.amount, lastTransfer.amount.setScale(0))
    }

    @Test
    @Order(1)
    @DisplayName("teste service findById")
    fun testFindByIdService() {
        val transfer1 = transferService.findById(1)
        val transfer2 = transferRepository.findById(1).get()
        Assertions.assertEquals(transfer1!!.amount,transfer2.amount)
        Assertions.assertEquals(transfer1!!.sender_id,transfer2.sender)
        Assertions.assertEquals(transfer1!!.receiver_id,transfer2.receiver)

    }

    @Test
    @Order(2)
    @DisplayName("Http Method: POST - status Created")
    fun postTransfer_CREATED() {
        val transfer = TransferDTO(3,1, BigDecimal.ONE)

        mvc?.perform(
            MockMvcRequestBuilders.post(TransferController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transfer))
                .accept(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isCreated())
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.sender").value(transfer.sender))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.receiver").value(transfer.receiver))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(transfer.amount))
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }
    @Test
    @Order(3)
    @DisplayName("Http Method: POST - status BAD REQUEST")
    fun postTransfer_BADREQUEST() {
        val transfer = TransferDTO(2,3, BigDecimal.ONE)
        mvc?.perform(
            MockMvcRequestBuilders.post(TransferController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transfer))
                .accept(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isBadRequest())
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(4)
    @DisplayName("Http Method: GET - status NOTFOUND")
    fun getTransfer_NOTFOUND() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${TransferController.ENDPOINT}/999999")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isNotFound())
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(5)
    @DisplayName("Http Method: GET - status OK")
    fun getUser_OK() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${TransferController.ENDPOINT}/1")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isOk())
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.sender_id").value(transferRepository.findById(1).get().sender))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.receiver_id").value(transferRepository.findById(1).get().receiver))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(transferRepository.findById(1).get().amount.setScale(1)))
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }

    @Test
    @Order(6)
    @DisplayName("Http Method: GET - status BADREQUEST")
    fun getUser_BADREQUEST() {
        mvc?.perform(
            MockMvcRequestBuilders.get("${TransferController.ENDPOINT}/apicela")
                .contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().isBadRequest())
            ?.andDo(MockMvcResultHandlers.print())?.andReturn()
    }
}