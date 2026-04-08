package jpa.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements Serializable {
    private Long id;
    private String username;
    private String email;

    private List<Event> events  = new ArrayList<Event>();

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) { this.events = events; }

    // helper method (super important)
    public void addEvent(Event e) {
        events.add(e);
        e.setUser(this);
    }

}
