package by.rekuts.tattoosalon.subject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalonUser extends SalonEntity implements Serializable {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private boolean male;
    private int userRole;
    private LocalDateTime registration;
    private LocalDate birth;
    private boolean blocked;

    public enum UserRole {
        ADMIN,
        MASTER,
        USER
    }

    public SalonUser() {
    }

    public SalonUser(int id, String login, String name, String surname) {
        super(id);
        this.login = login;
        this.name = name;
        this.surname = surname;
    }

    public SalonUser(int id, String login, String password, String name, String surname, String email, boolean male,
                     int userRole, LocalDateTime registration, LocalDate birth, boolean blocked) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userRole = userRole;
        this.male =  male;
        this.registration = registration;
        this.birth = birth;
        this.blocked = blocked;
    }

    public SalonUser(String login, String password, String name, String surname, String email, boolean male, LocalDate birth) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.male = male;
        this.birth = birth;
    }

    public SalonUser(int userId, String login, String name, String surname, String email, boolean male, int userRole, LocalDateTime registration, LocalDate birth, boolean blocked) {
        super(userId);
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.male = male;
        this.userRole = userRole;
        this.registration = registration;
        this.birth = birth;
        this.blocked = blocked;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
