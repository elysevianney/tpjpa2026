package jpa.domain;

import jakarta.persistence.Entity;

@Entity
public class TicketPremium extends Ticket{
    private  String premium;
    public String getPremium() {
        return premium;
    }
    public void setPremium(String premium) {
        this.premium = premium;
    }

    public TicketPremium() {
        super();
    }
    public TicketPremium(String premium) {
        super();
        this.premium =  premium;
    }
}
