package jpa.domain;

import jakarta.persistence.Entity;

@Entity
public class TicketLastMinute  extends Ticket{
    private Long lastMinute;
    public Long getLastMinute() {
        return lastMinute;
    }
    public void setLastMinute(Long lastMinute) {
        this.lastMinute = lastMinute;
    }

    public TicketLastMinute(Long lastMinute) {
        super();
        this.lastMinute = lastMinute;
    }
    public TicketLastMinute() {
        super();
    }

}
