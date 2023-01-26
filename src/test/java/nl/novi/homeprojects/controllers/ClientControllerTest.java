package nl.novi.homeprojects.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.filter.JwtRequestFilter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.services.ClientService;
import nl.novi.homeprojects.services.DatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    JwtRequestFilter jwtRequestFilter;
    @MockBean
    private ClientService clientService;
    @MockBean
    private DatabaseService databaseService;

    Client client1;
    Client client2;
    Client client3;
    ClientInputDto clientInputDto1;
    ClientInputDto clientInputDto2;
    ClientOutputDto clientOutputDto1;
    ClientOutputDto clientOutputDto2;
    ClientOutputDto clientOutputDto3;
    ClientOutputDto clientOutputDto4;
    Executor executor;
    Assignment assignment;


    @BeforeEach
    void setUp() {

        client1 = new Client("Username 1", "Firstname 1", "Lastname 1", "Mobile 1", "Adres 1", "Place 1", "ZipCode 1", "Email 1", "Story 1");
        client2 = new Client("Username 2", "Firstname 2", "Lastname 2", "Mobile 2", "Adres 2", "Place 2", "ZipCode 2", "Email 2", "Story 2");
        client3 = new Client("Username 3", "Firstname 3", "Lastname 3", "Mobile 3", "Adres 3", "Place 3", "ZipCode 3", "Email 3", "Story 3");

        clientOutputDto1 = new ClientOutputDto("Username 1", "Firstname 1", "Lastname 1", "Mobile 1", "Adres 1", "Place 1", "ZipCode 1", "Email 1", "Story 1");
        clientOutputDto2 = new ClientOutputDto("Username 2", "Firstname 2", "Lastname 2", "Mobile 2", "Adres 2", "Place 2", "ZipCode 2", "Email 2", "Story 2");
        clientOutputDto3 = new ClientOutputDto("Username 3", "Firstname 3", "Lastname 3", "Mobile 3", "Adres 3", "Place 3", "ZipCode 3", "Email 3", "Story 3");
        clientOutputDto4 = new ClientOutputDto("Username 4", "Firstname 2", "Lastname 4", "Mobile 2", "Adres 2", "Place 4", "ZipCode 2", "Email 4", "Story 4");

        clientInputDto1 = new ClientInputDto("Username 1", "Firstname 1", "Lastname 1", "Mobile 1", "Adres 1", "Place 1", "ZipCode 1", "Email 1", "Story 1");
        clientInputDto2 = new ClientInputDto("Username 4", "Firstname 2", "Lastname 4", "Mobile 2", "Adres 2", "Place 4", "ZipCode 2", "Email 4", "Story 4");

        executor = new Executor("executor");

        assignment = new Assignment("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);
    }


    @Test
    void testGetClients() throws Exception {
        given(clientService.getClients()).willReturn(List.of(clientOutputDto1, clientOutputDto2, clientOutputDto3));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Username 1"))
                .andExpect(jsonPath("$[1].username").value("Username 2"))
        ;
        verify(clientService).getClients();
    }


    @Test
    void testGetOneClient() throws Exception {
        given(clientService.getOneClient(anyString())).willReturn(clientOutputDto1);

        mockMvc.perform(get("/clients/Username 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("Username 1"))
                .andExpect(jsonPath("firstname").value("Firstname 1"))
        ;
        verify(clientService).getOneClient("Username 1");
    }


    @Test
    public void testCreateClient() throws Exception {
        given(clientService.createClient(clientInputDto1)).willReturn("Test Username" + "Test Name");

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientInputDto1)))
                .andExpect(status().isCreated())
        ;
    }


    @Test
    void testDeleteClient() throws Exception {
        given(clientService.deleteClient(anyString())).willReturn("Client with id: Username 1 is deleted!");

        mockMvc.perform(delete("/clients/Username 1"))
                .andExpect(status().isOk())
        ;
                verify(clientService).deleteClient("Username 1");
    }

    @Test
    void testPatchClient() throws Exception {
        given(clientService.patchClient("Username 4", clientInputDto2)).willReturn(clientOutputDto4);

        mockMvc.perform(patch("/clients/Username 4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientInputDto2)))
                .andExpect(status().isOk())
        ;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}