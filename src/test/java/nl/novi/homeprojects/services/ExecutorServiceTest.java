package nl.novi.homeprojects.services;

import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ExecutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static nl.novi.homeprojects.models.AssignmentStatus.ACCEPTED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExecutorServiceTest {

    @Mock
    Executor executor;
    @Mock
    ExecutorRepository executorRepository;
    @Mock
    AssignmentRepository assignmentRepository;
    @InjectMocks
    ExecutorService executorService;

    @Test
    void finishAssignment() {
        //Arrange
        Executor executor1 = new Executor();
        Assignment assignment = new Assignment();
        List<Assignment> assignmentList = new ArrayList<>();
        assignment.setTitle("Test Title");
        assignment.setDescription("Test Description");
        assignment.setEssentials("Test Essentials");
        assignment.setDemands("Test Demands");
        assignment.setReward("Test Geld");
        assignment.setAssignmentStatus(ACCEPTED);
        assignment.setExecutor(executor1);

        assignmentList.add(assignment);

        executor1.setName("Test Name");
        executor1.setClient(null);
        executor1.setAssignments(assignmentList);

        when(executorRepository.findById("executorId")).thenReturn(Optional.of(executor1));
        when(assignmentRepository.findById("assignmentId")).thenReturn(Optional.of(assignment));

        // Act
        executorService.finishAssignment("executorId", "assignmentId");

        // Assert
        assertEquals(0 , executor1.getAssignments().size());
    }
}