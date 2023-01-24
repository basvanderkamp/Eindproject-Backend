package nl.novi.homeprojects.intergrationTests;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Client;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.models.FileDocument;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import nl.novi.homeprojects.services.AssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class AssignmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    AssignmentRepository assignmentRepository;



    Assignment assignment1;
    Assignment assignment2;
    Assignment assignment3;
    AssignmentInputDto assignmentInputDto1;
    AssignmentInputDto assignmentInputDto2;
    AssignmentOutputDto assignmentOutputDto1;
    AssignmentOutputDto assignmentOutputDto2;
    AssignmentOutputDto assignmentOutputDto3;
    AssignmentOutputDto assignmentOutputDto4;
    //FileDocument file;
    Executor executor;
    Client client;


    @BeforeEach
    void setUp() {

        assignment1 = new Assignment("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE, null, null);
        assignment2 = new Assignment("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE, null, null);
        assignment3 = new Assignment("Titel 3", "Description 3", "Essentials 3", "Demands 3", "Reward 3", AVAILABLE, null, null);

        assignmentOutputDto1 = new AssignmentOutputDto("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE, null, null);
        assignmentOutputDto2 = new AssignmentOutputDto("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE, null, null);
        assignmentOutputDto3 = new AssignmentOutputDto("Titel 3", "Description 3", "Essentials 3", "Demands 3", "Reward 3", AVAILABLE, null, null);
        assignmentOutputDto4 = new AssignmentOutputDto("Titel 4 ", "Description 2", "Essentials 2", "Demands 2", "Reward 4", AVAILABLE, null, null);

        assignmentInputDto1 = new AssignmentInputDto("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);
        assignmentInputDto2 = new AssignmentInputDto("Titel 4", "Description 2", "Essentials 2", "Demands 2", "Reward 4", AVAILABLE);

//        file = new FileDocument();
//        executor = new Executor("Test Username");
//        client = new Client("Test Username", "Test Firstname", "Test Lastname", "Test Mobile", "Test Adres", "Test Place", "Test ZipCode", "Test Email", "Test Story", null, null, null, executor);
    }


    @Test
    void shouldGetAssignments() throws Exception {


        mockMvc.perform(get("/assignments"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value(assignment1.getTitle().toString()))
//                .andExpect(jsonPath("$[1].title").value(assignment2.getTitle().toString()))
        ;
    }


    @Test
    void shouldGetOneAssignment() throws Exception {


        mockMvc.perform(get("/assignments/" + assignment1.getTitle()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is("Titel 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("Description 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.essentials", is("Essentials 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.demands", is("Demands 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reward", is("Reward 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentStatus", is(AVAILABLE)))


        ;
    }


}