package net.helloworld.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Date: 15/06/13
 * Time: 7:04 PM
 */

/**
 * The persistent class for the USERS database table.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    //Original props

    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ENABLED")
    private String enabled;

    @Column(name = "PASSWORD")
    private String password;

    //bi-directional one-to-one association to Authority
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private Authority authority;

    // Getters & Setters for original props

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters and setters for relation property

    public Authority getAuthority() {
        return this.authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    //Spring Security props

    private transient Collection<GrantedAuthority> authorities;

    //UserDetails methods

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    public boolean isEnabled() {
        return getEnabled().equals("true");
    }

    @Transient
    public void setUserAuthorities(List<String> authorities) {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
        for (String role : authorities) {
            listOfAuthorities.add(new SimpleGrantedAuthority(role));
        }
        this.authorities = (Collection<GrantedAuthority>) listOfAuthorities;
    }

    //Constructors

    public User() {
    }
}
