package com.golub.servlet.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    private long id;
    private LocalTime time_start;
    private LocalTime time_end;
    private LocalDate date;
    private Hall hall;
    private Exposition exposition;

    public Schedule() {
    }

    public Schedule(long id, LocalTime time_start, LocalTime time_end, LocalDate date, Hall hall, Exposition exposition) {
        this.id = id;
        this.time_start = time_start;
        this.time_end = time_end;
        this.date = date;
        this.hall = hall;
        this.exposition = exposition;
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public static class ScheduleBuilder {
        private long id;
        private LocalTime time_start;
        private LocalTime time_end;
        private LocalDate date;
        private Hall hall;
        private Exposition exposition;

        private ScheduleBuilder() {
        }

        public static ScheduleBuilder aSchedule() {
            return new ScheduleBuilder();
        }

        public ScheduleBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder withTime_start(LocalTime time_start) {
            this.time_start = time_start;
            return this;
        }

        public ScheduleBuilder withTime_end(LocalTime time_end) {
            this.time_end = time_end;
            return this;
        }

        public ScheduleBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public ScheduleBuilder withHall(Hall hall) {
            this.hall = hall;
            return this;
        }

        public ScheduleBuilder withExposition(Exposition exposition) {
            this.exposition = exposition;
            return this;
        }

        public Schedule build() {
            Schedule schedule = new Schedule();
            schedule.setId(id);
            schedule.setTime_start(time_start);
            schedule.setTime_end(time_end);
            schedule.setDate(date);
            schedule.setHall(hall);
            schedule.setExposition(exposition);
            return schedule;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getTime_start() {
        return time_start;
    }

    public void setTime_start(LocalTime time_start) {
        this.time_start = time_start;
    }

    public LocalTime getTime_end() {
        return time_end;
    }

    public void setTime_end(LocalTime time_end) {
        this.time_end = time_end;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id && time_start.equals(schedule.time_start) && time_end.equals(schedule.time_end) && date.equals(schedule.date) && hall.equals(schedule.hall) && exposition.equals(schedule.exposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time_start, time_end, date, hall, exposition);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", time_start=" + time_start +
                ", time_end=" + time_end +
                ", date=" + date +
                ", hall=" + hall +
                ", exposition=" + exposition +
                '}';
    }
}
