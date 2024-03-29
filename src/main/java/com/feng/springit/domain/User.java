package com.feng.springit.domain;

import com.feng.springit.domain.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@PasswordsMatch
public class User implements UserDetails {
    //user class is authenticated side of spring security

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Size(min = 8, max = 20)
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    @Column(length = 100)
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    //many to many due to a user can have many roles and a role can assign to many users
    @ManyToMany(fetch = FetchType.EAGER) //grab all the roles for the users
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )

    // user can have many roles
    private Set<Role> roles = new HashSet<>();

    @NonNull
    @NotEmpty(message = "You must enter First Name")
    private String firstName;

    @NonNull
    @NotEmpty(message = "You must enter Last Name")
    private String lastName;

    @Transient //marks that this data is not going to persist in db
    @Setter(AccessLevel.NONE)
    private String fullName;

    @NonNull
    @NotEmpty(message = "Please enter userid")
    @Column(nullable = false, unique = true)
    private String alias;

    @Transient
    @NotEmpty(message = "Please reenter password")
    private String confirmPassword;

    private String activationCode;

    public String getFullName() {
        return firstName +" "+ lastName;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//        this is equivalent as below
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
