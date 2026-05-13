package jpa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends People {

    @Column(nullable = false)
    private String adresse;



    private Integer maxBookings;

    private Integer dureeBooking;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Borrow> borrowings = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_wishlist",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "element_id"))
    @JsonIgnore
    private List<Element> wishlist = new ArrayList<>();

    public User() {
    }

    @Transient
    @Override
    public Role getRole() {
        return Role.USER;
    }

    public User(String nom, String prenom, String password, String adresse, String email, Integer maxBookings, Integer dureeBooking) {
        super(nom, prenom, password, email);
        this.adresse = adresse;
        this.maxBookings = maxBookings;
        this.dureeBooking = dureeBooking;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public List<Element> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Element> wishlist) {
        this.wishlist = wishlist;
    }
}
