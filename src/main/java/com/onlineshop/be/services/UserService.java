package com.onlineshop.be.services;

import com.onlineshop.be.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public interface UserService {
    User findByName(String email);

    Collection<User> findByRole(String role);
    User save(User user);
    User update(User user);
}
