package nl.novi.homeprojects.services;

import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
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
import static nl.novi.homeprojects.models.AssignmentStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecutorServiceTest {

    @Mock
    ExecutorRepository executorRepository;
    @Mock
    AssignmentRepository assignmentRepository;
    @InjectMocks
    ExecutorService executorService;
    Executor executor;
    Assignment assignment;
    List<Assignment> assignmentList;

    @BeforeEach
    void setUp() {

        executor = new Executor();
        assignment = new Assignment();
        assignmentList = new ArrayList<>();

        assignment.setTitle("Test Title");
        assignment.setDescription("Test Description");
        assignment.setEssentials("Test Essentials");
        assignment.setDemands("Test Demands");
        assignment.setReward("Test Geld");
        assignment.setAssignmentStatus(ACCEPTED);
        assignment.setExecutor(executor);

        assignmentList.add(assignment);

        executor.setName("Test Name");
        executor.setClient(null);
        executor.setAssignments(assignmentList);
    }

    @Test
    void finishAssignment() {

        when(executorRepository.findById("executorId")).thenReturn(Optional.of(executor));
        when(assignmentRepository.findById("assignmentId")).thenReturn(Optional.of(assignment));

        // Act
        executorService.finishAssignment("executorId", "assignmentId");

        // Assert
        assertEquals(0 , executor.getAssignments().size());
        assertEquals(FINISHED, assignment.getAssignmentStatus());
    }

    @Test
    void cancelAssignment() {

        when(executorRepository.findById("executorId")).thenReturn(Optional.of(executor));
        when(assignmentRepository.findById("assignmentId")).thenReturn(Optional.of(assignment));

        // Act
        executorService.cancelAssignment("executorId", "assignmentId");

        // Assert
        assertEquals(0 , executor.getAssignments().size());
        assertEquals(AVAILABLE, assignment.getAssignmentStatus());
    }
}