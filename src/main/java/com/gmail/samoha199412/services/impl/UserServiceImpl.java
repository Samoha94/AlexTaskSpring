package com.gmail.samoha199412.services.impl;

import com.gmail.samoha199412.dao.UserRepository;
import com.gmail.samoha199412.entity.Project;
import com.gmail.samoha199412.entity.User;
import com.gmail.samoha199412.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

//    @Override
//    public Optional<User> refreshUser(User user) {
//        Optional<User> findUser = userRepository.findById(user.getId());
//        return findUser;
//    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }


    @Override
    public User refreshUser(Long id, User user) {
        User userFromDB = userRepository.getOne(id);
        if (userFromDB != null) {
            user.setId(id);
            User saveAndFlush = userRepository.saveAndFlush(user);
            return saveAndFlush;
        }

        return user;
    }

    @Override
    public List<Project> getUserProjects(Long id) {

        Optional<User> byId = userRepository.findById(id);
        return byId.get().getProjects();
    }

    @Override
    public void deleteUserProjects(Long id) {
        Optional<User> userFroDB=userRepository.findById(id);
        List<Project> projects = userFroDB.get().getProjects();
        projects.clear();
    }
}
