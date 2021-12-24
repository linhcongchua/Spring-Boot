package com.learn.meme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.meme.security.UserRole;
import com.learn.meme.validation.ValidPhone;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @NotEmpty
    @Size(min = 2, message = "username should have at least 2 character")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Transient
    @JsonIgnore
    private Set<? extends GrantedAuthority> grantedAuthorities;
    @Column(columnDefinition = "boolean default true")
    private boolean isAccountNonExpired;
    @Column(columnDefinition = "boolean default true")
    private boolean isAccountNonLocked;
    @Column(columnDefinition = "boolean default true")
    private boolean isCredentialsNonExpired;
    @Column(columnDefinition = "boolean default true")
    private boolean isEnabled;

    @Column(name = "fullname")
    private String fullname;
    @Column(name = "address")
    private String address;
    @ValidPhone
    @Column(name = "phone")
    private String phone;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;


    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    @JsonIgnore
    public Set<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
