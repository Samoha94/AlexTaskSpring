package com.gmail.samoha199412.services;

import com.gmail.samoha199412.entity.Project;
import com.gmail.samoha199412.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findByLastName(String lastName);

    void deleteUser(Long id);

    Optional<User> findUser(Long id);

    void deleteAllUser();

    User refreshUser(Long id, User user);

    List<Project> getUserProjects(Long id);

    void deleteUserProjects(Long id);
}
