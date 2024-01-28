package mk.ukim.finki.diansvinarii.controller;

import mk.ukim.finki.diansvinarii.model.Review;
import mk.ukim.finki.diansvinarii.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
class ReviewControllerTest {

    @Mock
    private ReviewServiceImpl reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;

    @Test
    void getReviewsByWineryId() throws Exception {
        Mockito.when(reviewService.findAllByWinery_Id(1L)).thenReturn(Collections.emptyList());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/all/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBestNReviewsByWineryId() throws Exception {
        Mockito.when(reviewService.getNBestByWineryId(1L, 5)).thenReturn(Collections.emptyList());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/best/1/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getWineryAverageScore() throws Exception {
        Mockito.when(reviewService.getWineryAverageScoreById(1L)).thenReturn(4.5);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/score/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("4.5"));
    }

    @Test
    void addReview() throws Exception {
        // Mocking the service method
        Mockito.when(reviewService.create(1L, 5, "Test review", LocalDateTime.now(), 1L)).thenReturn(new Review());

        // Setting up the controller for testing
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        // Formatting the current timestamp in the required format
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        // Performing the POST request and asserting the result
        mockMvc.perform(MockMvcRequestBuilders.post("/api/review/add/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"score\":5, \"desc\":\"Test review\", \"timestamp\":\"" + timestamp + "\", \"userId\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getReview() throws Exception {
        Mockito.when(reviewService.findById(1L)).thenReturn(new Review());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}