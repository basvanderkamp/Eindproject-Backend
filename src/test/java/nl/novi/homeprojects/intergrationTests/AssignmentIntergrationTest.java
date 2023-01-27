package nl.novi.homeprojects.intergrationTests;

import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.repositorys.AssignmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static nl.novi.homeprojects.controllers.AssignmentControllerTest.asJsonString;
import static nl.novi.homeprojects.models.AssignmentStatus.AVAILABLE;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class AssignmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AssignmentRepository assignmentRepository;

    Assignment assignment1;
    Assignment assignment2;

    AssignmentInputDto assignmentInputDto1;


    @BeforeEach
    void setUp() {

        assignment1 = new Assignment("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE, null, null);
        assignment2 = new Assignment("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE, null, null);

        assignmentRepository.save(assignment1);
        assignmentRepository.save(assignment2);

        assignmentInputDto1 = new AssignmentInputDto("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);

    }


    @Test
    void shouldGetAssignments() throws Exception {

        mockMvc.perform(get("/assignments"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", is("Autowassen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", is("Grasmaaien")))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.assignmentStatus", is("AVAILABLE")))
        ;
    }


    @Test
    void shouldCreateAssignment() throws Exception {

        mockMvc.perform(post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(assignmentInputDto1)))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldDeleteAssignment() throws Exception {

        mockMvc.perform(delete("/assignments/" + assignment2.getTitle()))
                .andExpect(status().isOk());
    }
}