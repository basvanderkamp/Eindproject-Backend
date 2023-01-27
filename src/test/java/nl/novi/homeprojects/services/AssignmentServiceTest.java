package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.input.ClientInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.dtos.output.ClientOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.AssignmentStatus;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static nl.novi.homeprojects.models.AssignmentStatus.ACCEPTED;
import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {
    @Mock
    private Assignment assignment;
    @Mock
    AssignmentRepository assignmentRepository;
    @Mock
    private ExecutorRepository executorRepository;

    @InjectMocks
    AssignmentService assignmentService;

    AssignmentInputDto inputDto1;
    AssignmentInputDto inputDto2;
    AssignmentOutputDto outputDto1;
    AssignmentOutputDto outputDto2;
    Client client;
    Executor executor;
    Assignment assignment1;
    Assignment assignment2;
    List<Assignment> assignmentList;

    @BeforeEach
    void setUp() {
        inputDto1 = new AssignmentInputDto();
        inputDto2 = new AssignmentInputDto();
        outputDto1 = new AssignmentOutputDto();
        outputDto2 = new AssignmentOutputDto();
        client = new Client();
        executor = new Executor();

        inputDto1.setTitle("Title 1");
        inputDto1.setDescription("Description 1");
        inputDto1.setEssentials("Essentials 1");
        inputDto1.setDemands("Demands 1");
        inputDto1.setReward("Reward 1");
        inputDto1.setAssignmentStatus(AVAILABLE);
        inputDto1.setClient(client);

        inputDto2.setTitle("Title 1");
        inputDto2.setDescription("Description 1 Updated");
        inputDto2.setEssentials("Essentials 1 Updated");
        inputDto2.setDemands("Demands 1");
        inputDto2.setReward("Reward 1 Updated");
        inputDto2.setAssignmentStatus(AVAILABLE);
        inputDto2.setClient(client);

        outputDto1.setTitle("Title 1");
        outputDto1.setDescription("Description 1");
        outputDto1.setEssentials("Essentials 1");
        outputDto1.setDemands("Demands 1");         
        outputDto1.setReward("Reward 1");
        outputDto1.setAssignmentStatus(AVAILABLE);
        outputDto1.setClient(client);
        outputDto1.setExecutor(executor);


        outputDto2.setTitle("Title 1");
        outputDto2.setDescription("Description 1 Updated");
        outputDto2.setEssentials("Essentials 1 Updated");
        outputDto2.setDemands("Demands 1");
        outputDto2.setReward("Reward 1 Updated");
        outputDto2.setAssignmentStatus(AVAILABLE);
        outputDto2.setClient(client);
        outputDto2.setExecutor(executor);

        client.setUsername("Test Username");
        client.setFirstname("Test Firstname");
        client.setLastname("Test Lastname");
        client.setMobile("Test Mobile");
        client.setAdres("Test Adres");
        client.setPlace("Test Place");
        client.setZipcode("Test ZipCode");
        client.setEmail("Test Email");
        client.setStory("Test Story");
        client.setAssignments(assignmentList);


        executor.setName("Test Name");
        executor.setClient(client);
        executor.setAssignments(assignmentList);

        assignment1 = new Assignment("Title 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE, client, executor);
        assignment2 = new Assignment("Title 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE, client, executor);
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
    }

    @Test
    void testCreateAssignment() {

        when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment1);

        // Act
        String result = assignmentService.createAssignment(inputDto1);

        // Assert
        assertEquals("Assignment with title: Title 1 created!", result);

    }


    @Test
    void testGetAssignments() {
        //Arrange
        List<Assignment> expectedAssignmentList = new ArrayList<>();
        expectedAssignmentList.add(assignment1);
        expectedAssignmentList.add(assignment2);

        List<AssignmentOutputDto> expectedAssignmentOutputDtoList = new ArrayList<>();
        expectedAssignmentOutputDtoList.add(outputDto1);
        expectedAssignmentOutputDtoList.add(outputDto2);

        Mockito.when(assignmentRepository.findAll()).thenReturn(expectedAssignmentList);

        // Act
        List<AssignmentOutputDto> actualAssignmentOutputDtoList = (List<AssignmentOutputDto>) assignmentService.getAssignments();

        // Assert
        assertEquals(expectedAssignmentOutputDtoList.get(0).getTitle(), actualAssignmentOutputDtoList.get(0).getTitle());
        assertEquals(expectedAssignmentOutputDtoList.get(0).getDescription(), actualAssignmentOutputDtoList.get(0).getDescription());
        assertEquals(expectedAssignmentOutputDtoList.get(0).getEssentials(), actualAssignmentOutputDtoList.get(0).getEssentials());
        assertEquals(expectedAssignmentOutputDtoList.get(0).getDemands(), actualAssignmentOutputDtoList.get(0).getDemands());
        assertEquals(expectedAssignmentOutputDtoList.get(0).getReward(), actualAssignmentOutputDtoList.get(0).getReward());
        assertEquals(expectedAssignmentOutputDtoList.get(0).getAssignmentStatus(), actualAssignmentOutputDtoList.get(0).getAssignmentStatus());
    }

    @Test
    void testGetOneAssignment() {

        when(assignmentRepository.findById("testId")).thenReturn(Optional.of(assignment));

        // Act
        assignmentService.getOneAssignment("testId");

        // Assert
        assertEquals("Title 1", outputDto1.getTitle());
        assertEquals("Description 1", outputDto1.getDescription());
        assertEquals("Essentials 1", outputDto1.getEssentials());
        assertEquals("Demands 1", outputDto1.getDemands());
        assertEquals("Reward 1", outputDto1.getReward());
        assertEquals(client, outputDto1.getClient());
        assertEquals(AVAILABLE, outputDto1.getAssignmentStatus());
    }

    @Test
    void testDeleteAssignment() {

        when(assignmentRepository.findById("testId")).thenReturn(Optional.of(assignment1));

        // Act
        String result = assignmentService.deleteAssignment("testId");

        // Assert
        assertEquals("Assignment with title: testId is deleted!", result);
    }

    @Test
    void testPatchAssignment() {

        when(assignmentRepository.findById("testId")).thenReturn(Optional.of(assignment1));
        when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment1);

        // Act
        assignmentService.patchAssignment("testId", inputDto2);

        // Assert
        assertEquals("Title 1", outputDto2.getTitle());
        assertEquals("Description 1 Updated", outputDto2.getDescription());
        assertEquals("Essentials 1 Updated", outputDto2.getEssentials());
        assertEquals("Demands 1", outputDto2.getDemands());
        assertEquals("Reward 1 Updated", outputDto2.getReward());
        assertEquals(AVAILABLE, outputDto2.getAssignmentStatus());
    }

        @Test
    void testTransferToAssignmentDto() {

        when(assignment.getTitle()).thenReturn("Title 1");
        when(assignment.getDescription()).thenReturn("Description 1");
        when(assignment.getEssentials()).thenReturn("Essentials 1");
        when(assignment.getDemands()).thenReturn("Demands 1");
        when(assignment.getReward()).thenReturn("Reward 1");
        when(assignment.getClient()).thenReturn(client);
        when(assignment.getAssignmentStatus()).thenReturn(AVAILABLE);
        when(assignment.getExecutor()).thenReturn(executor);

        // Act
        assignmentService.transferToAssignmentDto(assignment);

        // Assert
        assertEquals("Title 1", outputDto1.getTitle());
        assertEquals("Description 1", outputDto1.getDescription());
        assertEquals("Essentials 1", outputDto1.getEssentials());
        assertEquals("Demands 1", outputDto1.getDemands());
        assertEquals("Reward 1", outputDto1.getReward());
        assertEquals(client, outputDto1.getClient());
        assertEquals(AVAILABLE, outputDto1.getAssignmentStatus());
        assertEquals(executor, outputDto1.getExecutor());
    }

    @Test
    void testAssignExecutorToAssignment() {

        when(assignmentRepository.findById("testId")).thenReturn(Optional.of(assignment1));
        when(executorRepository.findById("testExecutorId")).thenReturn(Optional.of(executor));

        // Act
        assignmentService.assignExecutorToAssignment("testId", "testExecutorId");

        // Assert
        assertEquals("Test Name", assignment1.getExecutor().getName());
    }
}