package com.serviceone.challange.web.controller;

import com.serviceone.challange.model.Task;
import com.serviceone.challange.model.Team;
import com.serviceone.challange.service.TeamOptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamOptimizerController {

    @Autowired
    private TeamOptimizationService teamOptimizationService;

    @PostMapping("/optimizework")
    public List<Team> optimizeTeams(@RequestBody Task task) {
        if (task == null || task.getRooms() == null || Arrays.stream(task.getRooms()).anyMatch(a -> a <= 0)
                || task.getSeniorSpeed() <= 0 || task.getJuniorSpeed() <= 0) {
            throw new ResourceNotFoundException("all parameters must be positive number");
        }
        if (task.getRooms().length > 100 || Arrays.stream(task.getRooms()).anyMatch(a -> a > 100)) {
            throw new ResourceNotFoundException("rooms can not bigger than 100 and structure can not bigger than 100");
        }
        return teamOptimizationService.optimizeTeam(task);
    }
}
