package jpa.service;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;
import jpa.dao.generic.UserDao;
import jpa.domain.User;
import jpa.dto.UserCreateDto;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public long create(final UserCreateDto userCreateDto) throws ClientErrorException {
        // Contrôle métier

        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());

        userDao.save(user);
        return user.getId();
    }
}


