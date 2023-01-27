package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private Client client;
    @Mock
    ClientRepository clientRepository;
    @Mock
    AssignmentRepository assignmentRepository;
    @Mock
    ExecutorRepository executorRepository;
    @InjectMocks
    ClientService clientService;

    ClientInputDto inputDto1;
    ClientInputDto inputDto2;
    ClientOutputDto outputDto1;
    ClientOutputDto outputDto2;
    Client client1;
    Client client2;
    Executor executor;
    Assignment assignment1;
    Assignment assignment2;
    List<Assignment> assignmentList;

    @BeforeEach
    void setUp() {
        inputDto1 = new ClientInputDto();
        inputDto2 = new ClientInputDto();
        outputDto1 = new ClientOutputDto();
        outputDto2 = new ClientOutputDto();
        client1 = new Client();
        client2 = new Client();
        executor = new Executor();

        inputDto1.setUsername("Test Username");
        inputDto1.setFirstname("Test Firstname");
        inputDto1.setLastname("Test Lastname");
        inputDto1.setMobile("Test Mobile");
        inputDto1.setAdres("Test Adres");
        inputDto1.setPlace("Test Place");
        inputDto1.setZipcode("Test ZipCode");
        inputDto1.setEmail("Test Email");
        inputDto1.setStory("Test Story");

        inputDto2.setUsername("Test Username");
        inputDto2.setFirstname("Test Firstname");
        inputDto2.setLastname("Test Lastname Updated");
        inputDto2.setMobile("Test Mobile");
        inputDto2.setAdres("Test Adres");
        inputDto2.setPlace("Test Place Updated");
        inputDto2.setZipcode("Test ZipCode");
        inputDto2.setEmail("Test Email Updated");
        inputDto2.setStory("Test Story Updated");

        outputDto1.setUsername("Test Username");
        outputDto1.setFirstname("Test Firstname");
        outputDto1.setLastname("Test Lastname");
        outputDto1.setMobile("Test Mobile");
        outputDto1.setAdres("Test Adres");
        outputDto1.setPlace("Test Place");
        outputDto1.setZipcode("Test ZipCode");
        outputDto1.setEmail("Test Email");
        outputDto1.setStory("Test Story");
        outputDto1.setAssignments(assignmentList);
        outputDto1.setExecutor(executor);

        outputDto2.setUsername("Test Username");
        outputDto2.setFirstname("Test Firstname");
        outputDto2.setLastname("Test Lastname");
        outputDto2.setMobile("Test Mobile");
        outputDto2.setAdres("Test Adres");
        outputDto2.setPlace("Test Place");
        outputDto2.setZipcode("Test ZipCode");
        outputDto2.setEmail("Test Email");
        outputDto2.setStory("Test Story");
        outputDto2.setAssignments(assignmentList);
        outputDto2.setExecutor(executor);

        client1.setUsername("Test Username");
        client1.setFirstname("Test Firstname");
        client1.setLastname("Test Lastname");
        client1.setMobile("Test Mobile");
        client1.setAdres("Test Adres");
        client1.setPlace("Test Place");
        client1.setZipcode("Test ZipCode");
        client1.setEmail("Test Email");
        client1.setStory("Test Story");
        client1.setAssignments(assignmentList);

        client2.setUsername("Test Username");
        client2.setFirstname("Test Firstname");
        client2.setLastname("Test Lastname");
        client2.setMobile("Test Mobile");
        client2.setAdres("Test Adres");
        client2.setPlace("Test Place");
        client2.setZipcode("Test ZipCode");
        client2.setEmail("Test Email");
        client2.setStory("Test Story");
        client2.setAssignments(assignmentList);

        executor.setName("Test Name");
        executor.setClient(client1);
        executor.setAssignments(assignmentList);

        assignment1 = new Assignment("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE, client1, executor);
        assignment2 = new Assignment("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE, client1, executor);
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
    }



    @Test
    void testCreateClient() {

        when(clientRepository.save(any(Client.class))).thenReturn(client1);
        when(executorRepository.save(any(Executor.class))).thenReturn(executor);
        when(clientRepository.findById(inputDto1.getUsername())).thenReturn(Optional.of(client1));
        when(executorRepository.findById(inputDto1.getUsername())).thenReturn(Optional.of(executor));

        // Act
        String result = clientService.createClient(inputDto1);

        // Assert
        assertEquals("Test Username" + "Test Name", result);

    }

    @Test
    void testGetClients() {
        //Assert
        List<Client> expectedClientList = new ArrayList<>();
        expectedClientList.add(client1);
        expectedClientList.add(client2);

        List<ClientOutputDto> ExpectedOutputList = new ArrayList<>();
        ExpectedOutputList.add(outputDto1);
        ExpectedOutputList.add(outputDto2);

        when(clientRepository.findAll()).thenReturn(expectedClientList);

        // Act
        List<ClientOutputDto> actualOutputList = (List<ClientOutputDto>) clientService.getClients();

        // Assert
        assertEquals(ExpectedOutputList.get(0).getUsername(), actualOutputList.get(0).getUsername());
        assertEquals(ExpectedOutputList.get(0).getFirstname(), actualOutputList.get(0).getFirstname());
        assertEquals(ExpectedOutputList.get(0).getLastname(), actualOutputList.get(0).getLastname());
        assertEquals(ExpectedOutputList.get(0).getMobile(), actualOutputList.get(0).getMobile());
        assertEquals(ExpectedOutputList.get(0).getAdres(), actualOutputList.get(0).getAdres());
        assertEquals(ExpectedOutputList.get(0).getPlace(), actualOutputList.get(0).getPlace());
        assertEquals(ExpectedOutputList.get(0).getZipcode(), actualOutputList.get(0).getZipcode());
        assertEquals(ExpectedOutputList.get(0).getEmail(), actualOutputList.get(0).getEmail());
        assertEquals(ExpectedOutputList.get(0).getStory(), actualOutputList.get(0).getStory());

    }

    @Test
    void testGetOneClient() {

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));

        // Act
        clientService.getOneClient("testId");

        // Assert
        assertEquals("Test Username", outputDto1.getUsername());
        assertEquals("Test Firstname", outputDto1.getFirstname());
        assertEquals("Test Lastname", outputDto1.getLastname());
        assertEquals("Test Mobile", outputDto1.getMobile());
        assertEquals("Test Adres", outputDto1.getAdres());
        assertEquals("Test Place", outputDto1.getPlace());
        assertEquals("Test ZipCode", outputDto1.getZipcode());
        assertEquals("Test Email", outputDto1.getEmail());
        assertEquals("Test Story", outputDto1.getStory());
        assertEquals(executor, outputDto1.getExecutor());

    }

    @Test
    void testDeleteClient() {

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));

        // Act
        String result = clientService.deleteClient("testId");

        // Assert
        assertEquals("Client with id: testId is deleted!", result);
    }

    @Test
    void testPatchClient() {

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));
        when(clientRepository.save(any(Client.class))).thenReturn(client1);


        // Act
        ClientOutputDto outputDto = clientService.patchClient("testId", inputDto2);

        // Assert
        assertEquals("Test Username", outputDto.getUsername());
        assertEquals("Test Firstname", outputDto.getFirstname());
        assertEquals("Test Lastname Updated", outputDto.getLastname());
        assertEquals("Test Mobile", outputDto.getMobile());
        assertEquals("Test Adres", outputDto.getAdres());
        assertEquals("Test Place Updated", outputDto.getPlace());
        assertEquals("Test ZipCode", outputDto.getZipcode());
        assertEquals("Test Email Updated", outputDto.getEmail());
        assertEquals("Test Story Updated", outputDto.getStory());


    }

    @Test
    void testTransferToClientDto() {
        // Arrange
        when(client.getUsername()).thenReturn("Test Username");
        when(client.getFirstname()).thenReturn("Test Firstname");
        when(client.getLastname()).thenReturn("Test Lastname");
        when(client.getMobile()).thenReturn("Test Mobile");
        when(client.getAdres()).thenReturn("Test Adres");
        when(client.getPlace()).thenReturn("Test Place");
        when(client.getZipcode()).thenReturn("Test ZipCode");
        when(client.getStory()).thenReturn("Test Story");

        // Act
        ClientOutputDto outputDto = clientService.transferToClientDto(client);

        // Assert
        assertEquals("Test Username", outputDto.getUsername());
        assertEquals("Test Firstname", outputDto.getFirstname());
        assertEquals("Test Lastname", outputDto.getLastname());
        assertEquals("Test Mobile", outputDto.getMobile());
        assertEquals("Test Adres", outputDto.getAdres());
        assertEquals("Test Place", outputDto.getPlace());
        assertEquals("Test ZipCode", outputDto.getZipcode());
        assertEquals("Test Story", outputDto.getStory());
    }

    @Test
    void testAssignAssignmentToClient() {
        // Arrange
        Client client1 = new Client();
        client1.setUsername("Test Username");
        client1.setFirstname("Test Firstname");
        client1.setLastname("Test Lastname");
        client1.setMobile("Test Mobile");
        client1.setAdres("Test Adres");
        client1.setPlace("Test Place");
        client1.setZipcode("Test ZipCode");
        client1.setEmail("Test Email");
        client1.setStory("Test Story");


        Assignment assignment = new Assignment();
        assignment.setTitle("Test Title");
        assignment.setDescription("Test Description");
        assignment.setEssentials("Test Essentials");
        assignment.setDemands("Test Demands");
        assignment.setReward("Test Geld");
        assignment.setAssignmentStatus(AVAILABLE);

        when(clientRepository.findById("clientId")).thenReturn(Optional.of(client1));
        when(assignmentRepository.findById("assignmentId")).thenReturn(Optional.of(assignment));

        // Act
        clientService.assignAssignmentToClient("clientId", "assignmentId");

        // Assert
        assertEquals("Test Username", assignment.getClient().getUsername());
    }
}
