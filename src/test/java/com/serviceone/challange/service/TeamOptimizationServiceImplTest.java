package com.serviceone.challange.service;

import com.serviceone.challange.model.Task;
import com.serviceone.challange.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class TeamOptimizationServiceImplTest {

    @Autowired
    private TeamOptimizationService teamOptimizationService;

    @MockBean
    private Optimizer optimizer;

    @Test
    public void optimizeTeamTest() {
        Task task = new Task();
        task.setRooms(new Integer[]{35, 21, 17});
        task.setSeniorSpeed(10);
        task.setJuniorSpeed(6);
        Team team1 = new Team(3, 1);
        Team team2 = new Team(1, 2);
        Team team3 = new Team(2, 0);
        given(optimizer.getOptimumTeam(35, task.getSeniorSpeed(), task.getJuniorSpeed())).willReturn(team1);
        given(optimizer.getOptimumTeam(21, task.getSeniorSpeed(), task.getJuniorSpeed())).willReturn(team2);
        given(optimizer.getOptimumTeam(17, task.getSeniorSpeed(), task.getJuniorSpeed())).willReturn(team3);
        List<Team> teams = teamOptimizationService.optimizeTeam(task);
        assertEquals(3, teams.size());
        assertEquals(team1, teams.get(0));
        assertEquals(team2, teams.get(1));
        assertEquals(team3, teams.get(2));
    }

    @TestConfiguration
    static class DeveloperServiceImplTestContextConfiguration {
        @Bean
        public TeamOptimizationService optimizationService() {
            return new TeamOptimizationServiceImpl();
        }
    }
}
