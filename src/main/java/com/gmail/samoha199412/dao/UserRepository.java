package com.gmail.samoha199412.dao;

import com.gmail.samoha199412.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByLastName (String lastName);
    Optional<User> findById (Long id);
    void deleteById(User user);
    void deleteAll();
//    User findById(Long id);
}
