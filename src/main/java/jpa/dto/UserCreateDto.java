package jpa.dto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jpa.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class UserCreateDto {

    @NotEmpty
    @NotNull
    private String username;

    @NotEmpty
    @NotNull
    private String email;




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

}
