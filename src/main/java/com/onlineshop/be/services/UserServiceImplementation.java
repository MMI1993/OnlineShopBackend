package com.onlineshop.be.services;

import com.onlineshop.be.enums.Results;
import com.onlineshop.be.exceptions.CustomizedExceptions;
import com.onlineshop.be.model.User;
import com.onlineshop.be.repository.CartRepo;
import com.onlineshop.be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    CartRepo cartRepository;

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
        try {
            User savedUser = userRepository.save(user);
            return userRepository.save(savedUser);
        } catch (Exception e){
            throw  new CustomizedExceptions(Results.VALID_ERROR);
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        User initialUser= userRepository.findByEmail(user.getEmail());
        initialUser.setName(user.getName());
        initialUser.setEmail(user.getEmail());
        initialUser.setAddress(user.getAddress());
        initialUser.setPhone(user.getPhone());
        return userRepository.save(initialUser);
    }
}
