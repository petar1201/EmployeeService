package com.example.EmployeeService.config;

import com.example.EmployeeService.entities.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents a UserDetails object which contains the user's email, password and authorities.
 * It implements the UserDetails interface, providing methods to retrieve the user's details.
 * It is used in MyUserDetailsService
 * @author petar
 */
public class MyUserDetails implements UserDetails {

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The authorities of the user.
     */
    private List<GrantedAuthority> authorities;

    /**
     * Constructor to create a new MyUserDetails object from an Employee object.
     * @param employee the Employee object to create the MyUserDetails object from.
     */
    public MyUserDetails(Employee employee){
        this.email = employee.getEmail();
        this.password =employee.getPassword();
        this.authorities = new ArrayList<>();
    }

    /**
     * Returns the authorities of the user.
     * @return the authorities of the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Returns whether the user account is non-expired.
     * @return true if the user account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns whether the user account is non-locked.
     * @return true if the user account is non-locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns whether the user credentials are non-expired.
     * @return true if the user credentials are non-expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * Returns whether the user account is enabled.
     * @return true if the user account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
