package com.serviceone.challange.service;

import com.serviceone.challange.model.Task;
import com.serviceone.challange.model.Team;

import java.util.List;

public interface TeamOptimizationService {
    List<Team> optimizeTeam(Task task);
}
