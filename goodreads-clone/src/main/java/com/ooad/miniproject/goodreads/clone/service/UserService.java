package com.ooad.miniproject.goodreads.clone.service;
import org.springframework.stereotype.Service;
import com.ooad.miniproject.goodreads.clone.repository.UserRepository;
import com.ooad.miniproject.goodreads.clone.entity.User;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public boolean checkUser(String username, String password){
        return userRepository.checkUser(username, password);
    }

    public void updateUser(User user){
        userRepository.updateUser(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }

    public Long getUserIdByUsername(String username) {
        return userRepository.getUserIdByUsername(username);
    }
}