package com.serviceone.challange.service;

import com.serviceone.challange.model.Team;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Optimizer {
    public Team getOptimumTeam(int job, int seniorSpeed, int juniorSpeed) {
        if (job <= 0 || seniorSpeed <= 0 || juniorSpeed <= 0) {
            throw new IllegalArgumentException("all arguments must be positive");
        }
        HashMap<Integer, Work> memo = new HashMap<>();
        Work result = getOptimumTeam(applyOneSeniorRule(job, seniorSpeed), seniorSpeed, juniorSpeed, memo);
        return new Team(result.seniorCount, result.juniorCount);
    }

    private Work applyOneSeniorRule(int job, int seniorSpeed) {
        return new Work(job - seniorSpeed, 1, 0);
    }


    private Work getOptimumTeam(Work work, int seniorSpeed, int juniorSpeed, HashMap<Integer, Work> memo) {
        if (work.remainingJobAmount <= 0) {
            return work;
        }
        if (memo.get(work.remainingJobAmount) != null) {
            return memo.get(work.remainingJobAmount);
        }
        Work useSenior = getOptimumTeam(new Work(work.remainingJobAmount - seniorSpeed, work.seniorCount + 1, work.juniorCount), seniorSpeed, juniorSpeed, memo);
        Work useJunior = getOptimumTeam(new Work(work.remainingJobAmount - juniorSpeed, work.seniorCount, work.juniorCount + 1), seniorSpeed, juniorSpeed, memo);
        Work result = Math.abs(useSenior.remainingJobAmount) > Math.abs(useJunior.remainingJobAmount) ? useJunior : useSenior;
        memo.put(work.remainingJobAmount, result);
        return result;
    }

    class Work {
        int remainingJobAmount;
        int seniorCount;
        int juniorCount;

        public Work(int remainingJobAmount, int seniorCount, int juniorCount) {
            this.remainingJobAmount = remainingJobAmount;
            this.seniorCount = seniorCount;
            this.juniorCount = juniorCount;
        }
    }
}