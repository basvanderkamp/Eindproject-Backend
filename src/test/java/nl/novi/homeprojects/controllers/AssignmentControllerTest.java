package nl.novi.homeprojects.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.homeprojects.dtos.input.AssignmentInputDto;
import nl.novi.homeprojects.dtos.output.AssignmentOutputDto;
import nl.novi.homeprojects.filter.JwtRequestFilter;
import nl.novi.homeprojects.models.Assignment;
import nl.novi.homeprojects.models.Executor;
import nl.novi.homeprojects.services.AssignmentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssignmentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    JwtRequestFilter jwtRequestFilter;
    @MockBean
    private AssignmentService assignmentService;


    Assignment assignment1;
    Assignment assignment2;
    Assignment assignment3;
    AssignmentInputDto assignmentInputDto1;
    AssignmentInputDto assignmentInputDto2;
    AssignmentOutputDto assignmentOutputDto1;
    AssignmentOutputDto assignmentOutputDto2;
    AssignmentOutputDto assignmentOutputDto3;
    AssignmentOutputDto assignmentOutputDto4;
    Executor executor;


    @BeforeEach
    void setUp() {

        assignment1 = new Assignment("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);
        assignment2 = new Assignment("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE);
        assignment3 = new Assignment("Titel 3", "Description 3", "Essentials 3", "Demands 3", "Reward 3", AVAILABLE);

        assignmentOutputDto1 = new AssignmentOutputDto("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);
        assignmentOutputDto2 = new AssignmentOutputDto("Titel 2", "Description 2", "Essentials 2", "Demands 2", "Reward 2", AVAILABLE);
        assignmentOutputDto3 = new AssignmentOutputDto("Titel 3", "Description 3", "Essentials 3", "Demands 3", "Reward 3", AVAILABLE);
        assignmentOutputDto4 = new AssignmentOutputDto("Titel 4 ", "Description 2", "Essentials 2", "Demands 2", "Reward 4", AVAILABLE);

        assignmentInputDto1 = new AssignmentInputDto("Titel 1", "Description 1", "Essentials 1", "Demands 1", "Reward 1", AVAILABLE);
        assignmentInputDto2 = new AssignmentInputDto("Titel 4", "Description 2", "Essentials 2", "Demands 2", "Reward 4", AVAILABLE);

        executor = new Executor("executor");
    }


    @Test
    void testGetAssignments() throws Exception {
        given(assignmentService.getAssignments()).willReturn(List.of(assignmentOutputDto1, assignmentOutputDto2, assignmentOutputDto3));

        mockMvc.perform(get("/assignments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Titel 1"))
                .andExpect(jsonPath("$[1].title").value("Titel 2"))
        ;
    }


    @Test
    void testGetOneAssignment() throws Exception {
        given(assignmentService.getOneAssignment(anyString())).willReturn(assignmentOutputDto1);

        mockMvc.perform(get("/assignments/Titel 1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Titel 1"))
                .andExpect(jsonPath("description").value("Description 1"))
        ;
    }


    @Test
    public void testCreateAssignment() throws Exception {
        given(assignmentService.createAssignment(assignmentInputDto1)).willReturn("Assignment with title: Titel 1 created!");

        mockMvc.perform(post("/assignments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(assignmentInputDto1)))
                .andExpect(status().isCreated())
        ;
    }


    @Test
    void testDeleteAssignment() throws Exception {
        given(assignmentService.deleteAssignment(anyString())).willReturn("Assignment with title: titel 1 is deleted!");

        mockMvc.perform(delete("/assignments/Titel 1"))
                .andExpect(status().isOk())
        ;
    }


    @Test
    void testPatchAssignment() throws Exception {
        given(assignmentService.patchAssignment("Titel 4", assignmentInputDto2)).willReturn(assignmentOutputDto4);

        mockMvc.perform(patch("/assignments/Titel 4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(assignmentInputDto2)))
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