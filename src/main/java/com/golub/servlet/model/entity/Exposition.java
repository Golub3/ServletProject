package com.golub.servlet.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Exposition {
    private long id_exp;
    private String theme;
    private BigDecimal price;
    private Ticket ticket;
    private List<Schedule> daySchedule;

    public Exposition() {
    }

    public Exposition(long id_exp, String theme, BigDecimal price, Ticket ticket, List<Schedule> daySchedule) {
        this.id_exp = id_exp;
        this.theme = theme;
        this.price = price;
        this.ticket = ticket;
        this.daySchedule = daySchedule;
    }

    public static ExpositionBuilder builder() {
        return new ExpositionBuilder();
    }

    public static class ExpositionBuilder {
        private long id_exp;
        private String theme;
        private BigDecimal price;
        private Ticket ticket;
        private List<Schedule> daySchedule;

        private ExpositionBuilder() {
        }

        public static ExpositionBuilder anExposition() {
            return new ExpositionBuilder();
        }

        public ExpositionBuilder withId_exp(long id_exp) {
            this.id_exp = id_exp;
            return this;
        }

        public ExpositionBuilder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public ExpositionBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ExpositionBuilder withTicket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public ExpositionBuilder withDaySchedule(List<Schedule> daySchedule) {
            this.daySchedule = daySchedule;
            return this;
        }

        public Exposition build() {
            Exposition exposition = new Exposition();
            exposition.setId_exp(id_exp);
            exposition.setTheme(theme);
            exposition.setPrice(price);
            exposition.setTicket(ticket);
            exposition.setDaySchedule(daySchedule);
            return exposition;
        }
    }

    public long getId_exp() {
        return id_exp;
    }

    public void setId_exp(long id_exp) {
        this.id_exp = id_exp;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
        Exposition that = (Exposition) o;
        return id_exp == that.id_exp && theme.equals(that.theme) && price.equals(that.price) && ticket.equals(that.ticket) && daySchedule.equals(that.daySchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_exp, theme, price, ticket, daySchedule);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id_exp=" + id_exp +
                ", theme='" + theme + '\'' +
                ", price=" + price +
                ", ticket=" + ticket +
                ", daySchedule=" + daySchedule +
                '}';
    }
}
