package com.golub.servlet.model.entity;

import java.util.List;
import java.util.Objects;

public class Hall {
    private long id_hall;
    private String name;
    private List<Schedule> daySchedule;

    public Hall() {
    }

    public Hall(long id_hall, String name, List<Schedule> daySchedule) {
        this.id_hall = id_hall;
        this.name = name;
        this.daySchedule = daySchedule;
    }

    public static HallBuilder builder() {
        return new HallBuilder();
    }

    public static class HallBuilder {
        private long id_hall;
        private String name;
        private List<Schedule> daySchedule;

        private HallBuilder() {
        }

        public static HallBuilder aHall() {
            return new HallBuilder();
        }

        public HallBuilder withId_hall(long id_hall) {
            this.id_hall = id_hall;
            return this;
        }

        public HallBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HallBuilder withDaySchedule(List<Schedule> daySchedule) {
            this.daySchedule = daySchedule;
            return this;
        }

        public Hall build() {
            Hall hall = new Hall();
            hall.setId_hall(id_hall);
            hall.setName(name);
            hall.setDaySchedule(daySchedule);
            return hall;
        }
    }

    public long getId_hall() {
        return id_hall;
    }

    public void setId_hall(long id_hall) {
        this.id_hall = id_hall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schedule> getDaySchedule() {
        return daySchedule;
    }

    public void setDaySchedule(List<Schedule> daySchedule) {
        this.daySchedule = daySchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id_hall == hall.id_hall && name.equals(hall.name) && daySchedule.equals(hall.daySchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_hall, name, daySchedule);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id_hall=" + id_hall +
                ", name='" + name + '\'' +
                ", daySchedule=" + daySchedule +
                '}';
    }
}
