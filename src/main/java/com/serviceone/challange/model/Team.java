package com.serviceone.challange.model;

import java.util.Objects;

public class Team {

    private Integer seniorCount;
    private Integer juniorCount;

    public Team(Integer seniorCount, Integer juniorCount) {
        this.seniorCount = seniorCount;
        this.juniorCount = juniorCount;
    }

    public Team() {
    }

    public Integer getSeniorCount() {
        return seniorCount;
    }

    public void setSeniorCount(Integer seniorCount) {
        this.seniorCount = seniorCount;
    }

    public Integer getJuniorCount() {
        return juniorCount;
    }

    public void setJuniorCount(Integer juniorCount) {
        this.juniorCount = juniorCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(seniorCount, team.seniorCount) &&
                Objects.equals(juniorCount, team.juniorCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seniorCount, juniorCount);
    }

    @Override
    public String toString() {
        return "Team{" +
                "seniorCount=" + seniorCount +
                ", juniorCount=" + juniorCount +
                '}';
    }
}
