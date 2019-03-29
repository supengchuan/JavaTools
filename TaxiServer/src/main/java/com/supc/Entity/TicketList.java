package com.supc.Entity;

import java.io.Serializable;
import java.util.List;

public class TicketList implements Serializable {
    private int totalValue;
    private List<Ticket> tickets;

    public TicketList() {

    }

    public TicketList(int totalValue, List<Ticket> tickets) {
        this.totalValue = totalValue;
        this.tickets = tickets;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
