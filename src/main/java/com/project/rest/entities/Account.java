package com.project.rest.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "o nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser nulo")
    private String username;

    @NotNull(message = "O login não pode ser nulo!")
    @NotEmpty(message = "o login não pode estar vazio!")
    private String login;

    @NotNull(message = "A senha não pode ser nulo!")
    @NotEmpty(message = "A senha não pode estar vazio!")
    private String password;

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Phone> phoneList;

    public Account(){}

    public Account(Long id, String username, String login, String password, List<Phone> phoneList) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
        this.phoneList = phoneList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
