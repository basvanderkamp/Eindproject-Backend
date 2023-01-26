package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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



    @Test
    void testCreateClient() {
        // Arrange
        ClientInputDto inputDto = new ClientInputDto();
        inputDto.setUsername("Test Username");
        inputDto.setFirstname("Test Firstname");
        inputDto.setLastname("Test Lastname");
        inputDto.setMobile("Test Mobile");
        inputDto.setAdres("Test Adres");
        inputDto.setPlace("Test Place");
        inputDto.setZipcode("Test ZipCode");
        inputDto.setEmail("TestEmail");
        inputDto.setStory("Test Story");

        Client savedClient = new Client();
        savedClient.setUsername("Test Username");
        savedClient.setFirstname("Test Firstname");
        savedClient.setLastname("Test Lastname");
        savedClient.setMobile("Test Mobile");
        savedClient.setAdres("Test Adres");
        savedClient.setPlace("Test Place");
        savedClient.setZipcode("Test ZipCode");
        savedClient.setEmail("TestEmail");
        savedClient.setStory("Test Story");

        Executor savedExecutor = new Executor();
        savedExecutor.setName("Test Name");

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);
        when(executorRepository.save(any(Executor.class))).thenReturn(savedExecutor);
        when(clientRepository.findById(inputDto.getUsername())).thenReturn(Optional.of(savedClient));
        when(executorRepository.findById(inputDto.getUsername())).thenReturn(Optional.of(savedExecutor));

        // Act
        String result = clientService.createClient(inputDto);

        // Assert
        assertEquals("Test Username" + "Test Name", result);

    }

    @Test
    void testGetClients() {
        // Arrange
        Client client1 = new Client();
        Executor executor = new Executor();
        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);

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
        client1.setExecutor(executor);

        Client client2 = new Client();
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
        client2.setExecutor(executor);


        ClientOutputDto outputDto1 = new ClientOutputDto();
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

        ClientOutputDto outputDto2 = new ClientOutputDto();
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

        List<Client> expectedClientList = new ArrayList<>();
        expectedClientList.add(client1);
        expectedClientList.add(client2);

        List<ClientOutputDto> ExpectedOutputList = new ArrayList<>();
        ExpectedOutputList.add(outputDto1);
        ExpectedOutputList.add(outputDto2);

        Mockito.when(clientRepository.findAll()).thenReturn(expectedClientList);

        // Act
        Iterable<ClientOutputDto> actualOutputList = clientService.getClients();

        // Assert
        assertEquals(ExpectedOutputList, actualOutputList);
    }

    @Test
    void testGetOneClient() {
        // Arrange
        Client client1 = new Client();
        Executor executor = new Executor();
        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
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
        client1.setExecutor(executor);

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));

        // Act
        ClientOutputDto clientOutputDto = clientService.getOneClient("testId");

        // Assert
        assertEquals("Test Username", clientOutputDto.getUsername());
        assertEquals("Test Firstname", clientOutputDto.getFirstname());
        assertEquals("Test Lastname", clientOutputDto.getLastname());
        assertEquals("Test Mobile", clientOutputDto.getMobile());
        assertEquals("Test Adres", clientOutputDto.getAdres());
        assertEquals("Test Place", clientOutputDto.getPlace());
        assertEquals("Test ZipCode", clientOutputDto.getZipcode());
        assertEquals("Test Email", clientOutputDto.getEmail());
        assertEquals("Test Story", clientOutputDto.getStory());
        assertEquals(List.of(assignment1, assignment2), clientOutputDto.getAssignments());
        assertEquals(executor, clientOutputDto.getExecutor());

    }

    @Test
    void testDeleteClient() {
        // Arrange
        Client client1 = new Client();
        Executor executor = new Executor();
        Assignment assignment1 = new Assignment();
        Assignment assignment2 = new Assignment();
        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
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
        client1.setExecutor(executor);

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));

        // Act
        String result = clientService.deleteClient("testId");

        // Assert
        assertEquals("Client with id: testId is deleted!", result);
    }

    @Test
    void testPatchClient() {
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

        when(clientRepository.findById("testId")).thenReturn(Optional.of(client1));
        when(clientRepository.save(any(Client.class))).thenReturn(client1);

        ClientInputDto inputDto = new ClientInputDto();
        inputDto.setStory("Test Story Updated");
        inputDto.setEmail("Test Email Updated");
        inputDto.setLastname("Test Lastname Updated");
        inputDto.setPlace("Test Place Updated");

        // Act
        ClientOutputDto outputDto = clientService.patchClient("testId", inputDto);

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
