package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.AssignmentStatus;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {
    @Mock
    private Assignment assignment;
    @Mock
    AssignmentRepository assignmentRepository;
    @Mock
    private ExecutorRepository executorRepository;
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    AssignmentService assignmentService;

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void testCreateAssignment() {
        // Arrange
        AssignmentInputDto inputDto = new AssignmentInputDto();
        inputDto.setTitle("Test Title");
        inputDto.setDescription("Test Description");
        inputDto.setEssentials("Test Essentials");
        inputDto.setDemands("Test Demands");
        inputDto.setReward("Test Geld");
        Client client = new Client();
        inputDto.setClient(client);

        Assignment savedAssignment = new Assignment();
        savedAssignment.setTitle("Test Title");
        savedAssignment.setDescription("Test Description");
        savedAssignment.setEssentials("Test Essentials");
        savedAssignment.setDemands("Test Demands");
        savedAssignment.setReward("Test Geld");
        savedAssignment.setClient(client);
        savedAssignment.setAssignmentStatus(AVAILABLE);

        when(assignmentRepository.save(any(Assignment.class))).thenReturn(savedAssignment);

        // Act
        String result = assignmentService.createAssignment(inputDto);

        // Assert
        assertEquals("Assignment with title: Test Title created!", result);

    }

    @Test
    void testGetAssignments() {
        // Arrange
        Assignment assignment1 = new Assignment();
        Client client = new Client();
        assignment1.setTitle("Test Title 1");
        assignment1.setDescription("Test Description 1");
        assignment1.setEssentials("Test Essentials 1");
        assignment1.setDemands("Test Demands 1");
        assignment1.setReward("Test Geld 1");
        assignment1.setClient(client);
        assignment1.setAssignmentStatus(AVAILABLE);

        Assignment assignment2 = new Assignment();
        assignment2.setTitle("Test Title 1");
        assignment2.setDescription("Test Description 1");
        assignment2.setEssentials("Test Essentials 1");
        assignment2.setDemands("Test Demands 1");
        assignment2.setReward("Test Geld 1");
        assignment2.setClient(client);
        assignment2.setAssignmentStatus(ACCEPTED);


        AssignmentOutputDto assignmentOutputDto1 = new AssignmentOutputDto();
        assignmentOutputDto1.setTitle("Test Title 1");
        assignmentOutputDto1.setDescription("Test Description 1");
        assignmentOutputDto1.setEssentials("Test Essentials 1");
        assignmentOutputDto1.setDemands("Test Demands 1");
        assignmentOutputDto1.setReward("Test Geld 1");
        assignmentOutputDto1.setClient(client);
        assignmentOutputDto1.setAssignmentStatus(AVAILABLE);

        AssignmentOutputDto assignmentOutputDto2 = new AssignmentOutputDto();
        assignmentOutputDto2.setTitle("Test Title 1");
        assignmentOutputDto2.setDescription("Test Description 1");
        assignmentOutputDto2.setEssentials("Test Essentials 1");
        assignmentOutputDto2.setDemands("Test Demands 1");
        assignmentOutputDto2.setReward("Test Geld 1");
        assignmentOutputDto2.setClient(client);
        assignmentOutputDto2.setAssignmentStatus(ACCEPTED);


        List<Assignment> expectedAssignmentList = new ArrayList<>();
        expectedAssignmentList.add(assignment1);
        expectedAssignmentList.add(assignment2);

        List<AssignmentOutputDto> expectedAssignmentOutputDtoList = new ArrayList<>();
        expectedAssignmentOutputDtoList.add(assignmentOutputDto1);
        expectedAssignmentOutputDtoList.add(assignmentOutputDto2);

        Mockito.when(assignmentRepository.findAll()).thenReturn(expectedAssignmentList);

        // Act
        Iterable<AssignmentOutputDto> actualAssignmentOutputDtoList = assignmentService.getAssignments();

        // Assert
        assertEquals(expectedAssignmentOutputDtoList, actualAssignmentOutputDtoList);
    }

    @Test
    void shouldGetOneCorrectAssignment() {
        //Arrange


        //Act


        //Assert
    }

    @Test
    void shouldDeleteOneAssignment() {
        //Arrange


        //Act


        //Assert
    }

    @Test
    void shouldOverrideOneAssignment() {
        //Arrange


        //Act


        //Assert
    }

    @Test
    void testTransferToAssignmentDto() {
        // Arrange
        Client client = new Client();
        Executor executor = new Executor();
        when(assignment.getTitle()).thenReturn("Test Title");
        when(assignment.getDescription()).thenReturn("Test Description");
        when(assignment.getEssentials()).thenReturn("Test Essentials");
        when(assignment.getDemands()).thenReturn("Test Demands");
        when(assignment.getReward()).thenReturn("Test Geld");
        when(assignment.getClient()).thenReturn(client);
        when(assignment.getAssignmentStatus()).thenReturn(AssignmentStatus.FINISHED);
        when(assignment.getExecutor()).thenReturn(executor);

        // Act
        AssignmentOutputDto outputDto = assignmentService.transferToAssignmentDto(assignment);

        // Assert
        assertEquals("Test Title", outputDto.getTitle());
        assertEquals("Test Description", outputDto.getDescription());
        assertEquals("Test Essentials", outputDto.getEssentials());
        assertEquals("Test Demands", outputDto.getDemands());
        assertEquals("Test Geld", outputDto.getReward());
        assertEquals(client, outputDto.getClient());
        assertEquals(AssignmentStatus.FINISHED, outputDto.getAssignmentStatus());
        assertEquals(executor, outputDto.getExecutor());
    }

    @Test
    void testAssignExecutorToAssignment() {
        // Arrange
        Assignment assignment = new Assignment();
        assignment.setTitle("Test Title");

        Executor executor = new Executor();
        executor.setName("Test Executor Name");

        when(assignmentRepository.findById("testId")).thenReturn(Optional.of(assignment));
        when(executorRepository.findById("testExecutorId")).thenReturn(Optional.of(executor));

        // Act
        assignmentService.assignExecutorToAssignment("testId", "testExecutorId");

        // Assert
        assertEquals("Test Executor Name", assignment.getExecutor().getName());
    }
}