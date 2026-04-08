package jpa.domain;

import jakarta.persistence.*;

@Entity(name="tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ticket_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class Ticket {
    private Long id;
    private String description;
    public Ticket() {
    }
    public Ticket(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

}
