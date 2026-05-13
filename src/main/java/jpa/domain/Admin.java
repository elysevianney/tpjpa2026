package jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "admins")
public class Admin extends People {

    public Admin() {
    }

    public Admin(String nom, String prenom, String password, String email) {
        super(nom, prenom, password, email);
    }

    @Transient
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}

