package com.app.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.naming.ConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.CampusPlacementPortalApplication;
import com.app.pojos.Batch;
import com.app.pojos.Course;
import com.app.pojos.CourseName;
import com.app.pojos.Credential;
import com.app.pojos.Role;
import com.app.pojos.Student;
import com.app.service.IStudentService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK,classes = CampusPlacementPortalApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IStudentService studentService;
    @Value("${spring.datasource.url}")
    String url;
    
    @BeforeEach
    public void abortTestIfMysql() throws ConfigurationException {
    	System.out.println("DB Url"+url);
    	if(url.contains("mysq")) {
    		throw new ConfigurationException("Please check application.properties, the mysql was going to be written!!");
    	}
    }
        
    @Test
    public void testValidateLogin_ValidCredentials() throws Exception {
    	// Given
    	System.out.println();
    	Course course = new Course(CourseName.DAC, Batch.JAN, 2020);
        Credential credential = new Credential("testtest", "test@123", Role.STUDENT);
        Student s = Student.builder()
        		.address("test")
        		.course(course)
        		.credential(credential)
        		.dob(LocalDate.of(1990, 1, 1))
        		.email("test@test.com")
        		.firstName("test")
        		.gitLink("https://www.github.com/test")
        		.lastName("test")
        		.linkedIn("https://www.linkedin.com/test")
        		.mark10th(75)
        		.mark12th(75)
        		.markCCEE(75)
        		.markGrad(75)
        		.mobNo(1234567890L)
        		.passingYear10th(LocalDate.of(2010, 1, 1))
        		.passingYear12th(LocalDate.of(2012, 1, 1))
        		.passingYearGrad(LocalDate.of(2016, 1, 1))
        		.prn(1234567890L)
        		.build();
        
        studentService.studentRegister(s);
        
        mockMvc.perform(MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\": \"testtest\", \"password\": \"test@123\"}"))
        // Then
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jwt").isString())
                .andExpect(jsonPath("$.user").exists());

    }

  
}
