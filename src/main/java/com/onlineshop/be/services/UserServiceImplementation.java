package com.onlineshop.be.services;

import com.onlineshop.be.model.User;
import com.onlineshop.be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public User findByName(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    @Transactional
    public User save(User user) {
        return null;
    }

    @Override
    @Transactional
    public User update(User user) {
        return null;
    }
}
