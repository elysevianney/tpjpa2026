package jpa.domain;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends People {

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer maxBookings;

    private Integer dureeBooking;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrowings = new ArrayList<>();

    public User() {
    }

    public User(String nom, String prenom, String adresse, String email, Integer maxBookings, Integer dureeBooking) {
        super(nom, prenom);
        this.adresse = adresse;
        this.email = email;
        this.maxBookings = maxBookings;
        this.dureeBooking = dureeBooking;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMaxBookings() {
        return maxBookings;
    }

    public void setMaxBookings(Integer maxBookings) {
        this.maxBookings = maxBookings;
    }

    public Integer getDureeBooking() {
        return dureeBooking;
    }

    public void setDureeBooking(Integer dureeBooking) {
        this.dureeBooking = dureeBooking;
    }

    public List<Borrow> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrow> borrowings) {
        this.borrowings = borrowings;
    }
}
