package jpa.service;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;
import jpa.dao.generic.UserDao;
import jpa.domain.User;
import jpa.dto.UserCreateDto;
import jpa.utils.PasswordUtil;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public User findById(Long id) {
        return userDao.findOne(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public long create(final UserCreateDto userCreateDto) throws ClientErrorException {
        User user = new User();
        user.setNom(userCreateDto.getNom());
        user.setPrenom(userCreateDto.getPrenom());
        user.setPassword(PasswordUtil.hash(userCreateDto.getPassword()));
        user.setAdresse(userCreateDto.getAdresse());
        user.setEmail(userCreateDto.getEmail());
        user.setMaxBookings(5); // valeur par défaut
        user.setDureeBooking(30); // valeur par défaut

        userDao.save(user);
        return user.getId();
    }
}

