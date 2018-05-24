package by.rekuts.tattoosalon.db.dao;

public enum QueryToDatabase {
    CHECK_LOGIN_AND_PASSWORD("SELECT login, password FROM users WHERE login = ? AND password = ?"),
    INSERT_NEW_USER("INSERT INTO users(login, password, name, surname, email, gender, birth) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    SELECT_ALL_NEWS("SELECT id_publication, title, content, content_type, login AS author, publish_time, publication.blocked FROM publication LEFT JOIN users ON author_id=userid"),
    SELECT_PUBLICATION_BY_ID("SELECT id_publication, title, content, content_type, login AS author, publish_time, publication.blocked FROM publication LEFT JOIN users ON author_id=userid WHERE id_publication = ?"),
    SELECT_USER_DATA("SELECT userid, login, email, name, surname, gender, userrole, register, birth, blocked FROM tattooparlor.users WHERE login = ?"),
    CHECK_USER_ROLE("SELECT userrole FROM users WHERE login = ?"),
    CHECK_LOGIN("SELECT count(login) FROM users WHERE login = ?"),
    CHECK_EMAIL("SELECT count(email) FROM users WHERE email = ?"),
    UPDATE_USER_BLOCKED("UPDATE users SET blocked = ? WHERE userid = ?"),
    UPDATE_USER_ROLE("UPDATE users SET userrole = ? WHERE userid = ?"),
    SELECT_MASTERS("SELECT userid, login, name, surname FROM users where userrole = ?"),
    SELECT_ALL_USERS("SELECT userid, login, name, surname, email, gender, userrole, register, birth, blocked FROM users"),
    SELECT_SUM_AUTHORS_PUBLICATION("SELECT count(id_publication) FROM publication LEFT JOIN users ON author_id = userid WHERE login = ?"),
    INSERT_NEW_PUBLICATION("INSERT INTO publication(title, content, content_type, author_id) VALUES (?, ?, ?, (SELECT userid FROM users WHERE login = ?))"),
    UPDATE_PUBLICATION_BLOCKED("UPDATE publication SET blocked = ? WHERE id_publication = ?"),
    INSERT_NEW_APPOINTMENT("INSERT INTO appointment (appointment_type, master_id, client_id, begining_time, ending_time, ordering_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)"),
    SELECT_APPOINTMENTS_BY_MASTER_ID("SELECT appointment_id, appointment_type, master_id, client_id, begining_time, ending_time, ordering_time, status FROM appointment WHERE master_id = ?"),
    SELECT_APPOINTMENTS_BY_MASTER_ID_FOR_NEX_14_DAYS("SELECT appointment_id, appointment_type, master_id, client_id, begining_time, ending_time, ordering_time, status FROM appointment WHERE master_id = ? AND begining_time BETWEEN current_timestamp AND date_add(current_timestamp,interval 14 day) ORDER BY begining_time"),
    SELECT_APPOINTMENTS_BY_CLIENT_ID_FOR_NEX_14_DAYS("SELECT appointment_id, appointment_type, master_id, client_id, begining_time, ending_time, ordering_time, status FROM appointment WHERE client_id = ? AND begining_time BETWEEN current_timestamp AND date_add(current_timestamp,interval 14 day) ORDER BY begining_time");

    private final String query;
    QueryToDatabase(String query) {
        this.query = query;
    }
    public String getQuery() {
        return this.query;
    }
}
