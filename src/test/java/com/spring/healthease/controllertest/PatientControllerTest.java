package com.spring.healthease.controllertest;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.healthease.controller.PatientController;
import com.spring.healthease.model.PatientInputModel;
import com.spring.healthease.model.PatientOutputModel;
import com.spring.healthease.serviceimpl.PatientServiceImpl;
import com.spring.healthease.services.PatientService;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;
    
    @Test
    public void testAddPatients() throws Exception {

        PatientInputModel inputModel = new PatientInputModel();
        inputModel.setName("John Doe");
        inputModel.setAge(30);
        inputModel.setEmail("johndoe@example.com");
        inputModel.setPassword("P@ssw0rd");

        PatientOutputModel outputModel = new PatientOutputModel();
        outputModel.setPatientId(1);
        outputModel.setName(inputModel.getName());
        outputModel.setAge(inputModel.getAge());
        outputModel.setEmail(inputModel.getEmail());

        when(patientService.addPatients(any(PatientInputModel.class))).thenReturn(outputModel);

        mockMvc.perform(MockMvcRequestBuilders.post("/addpatient/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inputModel)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientId", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("John Doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", equalTo(30)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", equalTo("johndoe@example.com")));

        verify(patientService, times(1)).addPatients(any(PatientInputModel.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}