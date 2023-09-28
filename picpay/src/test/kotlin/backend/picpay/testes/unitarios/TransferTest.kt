package backend.picpay.testes.unitarios

import backend.picpay.repositories.UserRepository
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@Log4j2
class TransferTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    @Order(0)
    @DisplayName("teste service findById")
    fun testFindByIdService() {
        println(userRepository.findAll())
    }
}

//// test unitario
//@Test
//@Order(0)
//@DisplayName("TESTE SERVICE - save, findby")
//void testService() throws Exception {
//    // creating a model
//    var equip = equipmentRepository.findAll().get(0);
//    var saved = equipmentService.findById(equipmentRepository.findAll().get(0).getId()).get();
//    Assertions.assertNotNull(saved);
//    Assertions.assertEquals(equip.getName(), saved.getName());
//    log.info(equipmentRepository.findAll());
//
//}
//
//
//@Test()
//@Order(1)
//@DisplayName("TESTE SERVICE -  save, delete")
//void testServiceDelete_status200() throws Exception {
//
//    var saved = equipmentRepository.findAll().get(0);
//    Assertions.assertNotNull(saved);
//    log.info("saved {}", saved);
//    equipmentService.delete(saved);
//    assertThrows(IndexOutOfBoundsException.class, () -> equipmentRepository.findAll().get(0));
//
//}
//
//
//// test chamadas http
//@Test
//@Order(2)
//@DisplayName("POST - CREATED")
//void postEquipment_http201() throws Exception {
//    var equip = builder.createEquipment();
//
//    mvc.perform(MockMvcRequestBuilders.post(EquipmentController.ENDPOINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(equip))
//        .accept(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.status().isCreated())
//        .andDo(print());
//}
//
//@Test
//@Order(6)
//@DisplayName("POST - BAD REQUEST")
//void postEquipment_http400() throws Exception {
//    EquipmentPositionHistory equip = new EquipmentPositionHistory();
//    log.info(equip.getClass());
//    mvc.perform(MockMvcRequestBuilders.post(EquipmentController.ENDPOINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(equip))
//        .accept(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.status().isBadRequest())
//        .andDo(print());
//}
//
//@Test
//@Order(3)
//@DisplayName("GET - OK")
//void getEquipment_http200() throws Exception {
//    equipmentRepository.save(builder.createEquipment());
//    var equip = equipmentRepository.findAll().get(0);
//    mvc.perform(MockMvcRequestBuilders.get(EquipmentController.ENDPOINT.concat("/" + equip.getId().toString()))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(equip.getName()))
//        .andExpect(MockMvcResultMatchers.jsonPath("$.equipment_model_id").value(equip.getEquipment_model_id().toString()))
//        .andDo(print())
//        .andReturn();
//}
//
//@Test
//@Order(7)
//@DisplayName("GET - BAD REQUEST")
//void getEquipment_http400() throws Exception {
//    int id = 25;
//    mvc.perform(MockMvcRequestBuilders.get(EquipmentController.ENDPOINT.concat("/" + id))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.status().isBadRequest())
//        .andDo(print())
//        .andReturn();
//}
//
//@Test
//@Order(11)
//@DisplayName("GET - NOT FOUND")
//void getEquipment_http404() throws Exception {
//    var id = UUID.randomUUID();
//    mvc.perform(MockMvcRequestBuilders.get(EquipmentController.ENDPOINT.concat("/" + id))
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(MockMvcResultMatchers.status().isNotFound());
//}
