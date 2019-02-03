package com.serviceone.challange.model;

import java.util.Arrays;
import java.util.Objects;

public class Task {
    private Integer[] rooms;
    private Integer seniorSpeed;
    private Integer juniorSpeed;

    public Integer[] getRooms() {
        return rooms;
    }

    public void setRooms(Integer[] rooms) {
        this.rooms = rooms;
    }

    public Integer getSeniorSpeed() {
        return seniorSpeed;
    }

    public void setSeniorSpeed(Integer seniorSpeed) {
        this.seniorSpeed = seniorSpeed;
    }

    public Integer getJuniorSpeed() {
        return juniorSpeed;
    }

    public void setJuniorSpeed(Integer juniorSpeed) {
        this.juniorSpeed = juniorSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Arrays.equals(rooms, task.rooms) &&
                Objects.equals(seniorSpeed, task.seniorSpeed) &&
                Objects.equals(juniorSpeed, task.juniorSpeed);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(seniorSpeed, juniorSpeed);
        result = 31 * result + Arrays.hashCode(rooms);
        return result;
    }
}
