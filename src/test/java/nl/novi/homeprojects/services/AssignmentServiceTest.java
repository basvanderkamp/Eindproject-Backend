package nl.novi.homeprojects.services;

import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.repositorys.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

    @Mock
    AssignmentRepository assignmentRepository;
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
    void shouldCreateAssignment() {
        //Arrange


        //Act


        //Assert
    }

    @Test
    void shouldGetCorrectAssignments() {
        //Arrange


        //Act


        //Assert
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
    void shouldTransferToAssignmentDto() {
        //Arrange
        Assignment assignment = new Assignment();
        assignment.setTitle("Test Titel");
        assignment.setDescription("Test Description");
        assignment.setEssentials("Test Essentials");
        assignment.setDemands("Test Demands");
        assignment.setReward("Test Reward");

        //Act
        //AssignmentOutputDto assignmentOutputDto = AssignmentService


        //Assert
    }
}