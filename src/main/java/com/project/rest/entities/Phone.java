package com.project.rest.entities;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("deprecation")
@Entity
public class Phone implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "O telefone não pode estar vazio!")
    @NotNull(message = "O telefone não pode estar nulo!")
    private String phoneNumber;

    @NotEmpty(message = "O tipo do telefone não pode estar vazio!")
    @NotNull(message = "O tipo do telefone não pode estar nulo!")
    private String phoneType;

    @ManyToOne
    @ForeignKey(name = "cod_account_fk")
    private Account account;

    public Phone(){}

    public Phone(Long id, String phoneNumber, String phoneType, Account account) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
