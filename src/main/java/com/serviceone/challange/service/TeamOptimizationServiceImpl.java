package com.serviceone.challange.service;

import com.serviceone.challange.model.Task;
import com.serviceone.challange.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamOptimizationServiceImpl implements TeamOptimizationService {

    @Autowired
    private Optimizer optimizer;

    @Override
    public List<Team> optimizeTeam(Task task) {
        return Arrays.stream(task.getRooms()).
                map(roomSize -> optimizer.getOptimumTeam(roomSize, task.getSeniorSpeed(), task.getJuniorSpeed())).
                collect(Collectors.toList());
    }
}
