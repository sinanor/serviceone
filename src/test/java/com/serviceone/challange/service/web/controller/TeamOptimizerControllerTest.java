package com.serviceone.challange.service.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.serviceone.challange.model.Task;
import com.serviceone.challange.model.Team;
import com.serviceone.challange.service.TeamOptimizationService;
import com.serviceone.challange.web.controller.TeamOptimizerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamOptimizerController.class)
public class TeamOptimizerControllerTest {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TeamOptimizationService service;

    @Test
    public void optimizeTeamsTest() throws Exception {
        Task task = new Task();
        task.setSeniorSpeed(10);
        task.setJuniorSpeed(6);
        task.setRooms(new Integer[]{35, 21, 17});
        Team team1 = new Team(3, 1);
        Team team2 = new Team(1, 2);
        Team team3 = new Team(2, 0);
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        given(service.optimizeTeam(task)).willReturn(teams);
        mvc.perform(post("/api/optimizework/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].seniorCount", is(3)))
                .andExpect(jsonPath("$[0].juniorCount", is(1)))
                .andExpect(jsonPath("$[1].seniorCount", is(1)))
                .andExpect(jsonPath("$[1].juniorCount", is(2)))
                .andExpect(jsonPath("$[2].seniorCount", is(2)))
                .andExpect(jsonPath("$[2].juniorCount", is(0)));
        Mockito.verify(service).optimizeTeam(task);
    }
}
