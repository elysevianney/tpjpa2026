package jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends Person {

    public Admin() {
    }

    public Admin(String nom, String prenom) {
        super(nom, prenom);
    }
}

