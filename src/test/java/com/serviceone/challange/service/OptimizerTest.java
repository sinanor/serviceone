package com.serviceone.challange.service;


import com.serviceone.challange.model.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class OptimizerTest {

    static Stream<Arguments> exampleOptimumTeamDistribution() {
        return Stream.of(
                Arguments.of(35, 10, 6, 3, 1),
                Arguments.of(21, 10, 6, 1, 2),
                Arguments.of(17, 10, 6, 2, 0),
                Arguments.of(24, 11, 6, 2, 1),
                Arguments.of(28, 11, 6, 2, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("exampleOptimumTeamDistribution")
    public void optimumTeamTest(int job, int seniorSpeed, int juniorSpeed, Integer seniorCount, Integer juniorCount) {
        Optimizer optimizer = new Optimizer();
        Team team = optimizer.getOptimumTeam(job, seniorSpeed, juniorSpeed);
        assertEquals(seniorCount, team.getSeniorCount());
        assertEquals(juniorCount, team.getJuniorCount());
    }

}
