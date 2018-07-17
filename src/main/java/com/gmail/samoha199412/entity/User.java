package com.gmail.samoha199412.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    public interface ShowUser {};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(ShowUser.class)
    private Long id;

    @Column(name = "FitstName")
    @JsonView(ShowUser.class)
    private String firstName;

    @Column(name = "LastName")
    @JsonView(ShowUser.class)
    private String lastName;

    @Column
    @JsonView(ShowUser.class)
    private String email;


    @Column
    @JsonView(ShowUser.class)
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonView(ShowUser.class)
    private List<Project> projects;

    public User(String firstName, String lastName, List<Project> projects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = projects;
    }

    public User(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projects=" + projects +
                '}';
    }
}
