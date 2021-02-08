package com.golub.servlet.model.entity;

import java.util.Objects;

public class Ticket {
    private long ticket_id;
    private Exposition exposition;
    private User user;

    public Ticket() {
    }

    public Ticket(long ticket_id, Exposition exposition, User user) {
        this.ticket_id = ticket_id;
        this.exposition = exposition;
        this.user = user;
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public static class TicketBuilder {
        private long ticket_id;
        private Exposition exposition;
        private User user;

        private TicketBuilder() {
        }

        public static TicketBuilder aTicket() {
            return new TicketBuilder();
        }

        public TicketBuilder withTicket_id(long ticket_id) {
            this.ticket_id = ticket_id;
            return this;
        }

        public TicketBuilder withExposition(Exposition exposition) {
            this.exposition = exposition;
            return this;
        }

        public TicketBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();
            ticket.setTicket_id(ticket_id);
            ticket.setExposition(exposition);
            ticket.setUser(user);
            return ticket;
        }
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticket_id == ticket.ticket_id && exposition.equals(ticket.exposition) && user.equals(ticket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, exposition, user);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", exposition=" + exposition +
                ", user=" + user +
                '}';
    }
}
