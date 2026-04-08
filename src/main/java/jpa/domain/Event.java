package jpa.domain;

import jakarta.persistence.*;

@Entity
public class Event {
    private Long id;
    private String name;
    private String description;
    private User user;

    public Event(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
    public Event() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {}
}
