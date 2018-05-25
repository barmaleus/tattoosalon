package by.rekuts.tattoosalon.subject;

import by.rekuts.tattoosalon.resource.ConfigurationManager;
import by.rekuts.tattoosalon.resource.MessageManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class SalonUser extends SalonEntity implements Serializable, HttpSessionBindingListener {
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
    private boolean loggedIn = false;

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
        this.loggedIn = false;
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
        this.loggedIn = false;
    }

    public SalonUser(String login, String password, String name, String surname, String email, boolean male, LocalDate birth) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.male = male;
        this.birth = birth;
        this.loggedIn = false;
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
        this.loggedIn = false;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalonUser salonUser = (SalonUser) o;
        return male == salonUser.male &&
                userRole == salonUser.userRole &&
                blocked == salonUser.blocked &&
                Objects.equals(login, salonUser.login) &&
                Objects.equals(password, salonUser.password) &&
                Objects.equals(name, salonUser.name) &&
                Objects.equals(surname, salonUser.surname) &&
                Objects.equals(email, salonUser.email) &&
                Objects.equals(registration, salonUser.registration) &&
                Objects.equals(birth, salonUser.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname, email, male, userRole, registration, birth, blocked);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext sc = session.getServletContext();
        @SuppressWarnings("unchecked")
        HashSet<String> logins = (HashSet<String>) sc.getAttribute("logins");
        if (logins.isEmpty() || !logins.contains(this.getLogin())) {
            System.out.println("A user session has been created");
            logins.add(this.getLogin());
            sc.setAttribute("logins", logins);
            System.out.println("1. Logins: " + session.getServletContext().getAttribute("logins"));
            this.loggedIn = false;
        } else {
            this.loggedIn = true;
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
    }
}
