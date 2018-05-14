package by.rekuts.tattoosalon.db.dao;

public enum QueryToDatabase {
    CHECK_LOGIN_AND_PASSWORD("SELECT login, password FROM users WHERE login = ? AND password = ?"),
    INSERT_NEW_USER("INSERT INTO users(login, password, email, gender, birth) VALUES (?, ?, ?, ?, ?)"),
    SELECT_ALL_NEWS("SELECT id_publication, title, content, content_type, login AS author, publish_time, publication.blocked FROM publication LEFT JOIN users ON author_id=userid"),
    SELECT_PUBLICATION_BY_ID("SELECT id_publication, title, content, content_type, login AS author, publish_time, publication.blocked FROM publication LEFT JOIN users ON author_id=userid WHERE id_publication = ?"),
    SELECT_USER_DATA("SELECT userid, login, email, gender, userrole, register, birth, blocked FROM tattooparlor.users WHERE login = ?"),
    CHECK_USER_ROLE("SELECT userrole FROM users WHERE login = ?"),
    CHECK_LOGIN("SELECT count(login) FROM users WHERE login = ?"),
    CHECK_EMAIL("SELECT count(email) FROM users WHERE email = ?"),
    UPDATE_USER_BLOCKED("UPDATE users SET blocked = ? WHERE userid = ?"),
    UPDATE_USER_ROLE("UPDATE users SET userrole = ? WHERE userid = ?"),
    SELECT_MASTERS("SELECT login FROM users where userrole = ?"),
    SELECT_ALL_USERS("SELECT userid, login, email, gender, userrole, register, birth, blocked FROM users"),
    SELECT_SUM_AUTHORS_PUBLICATION("SELECT count(id_publication) FROM publication LEFT JOIN users ON author_id = userid WHERE login = ?"),
    INSERT_NEW_PUBLICATION("INSERT INTO publication(title, content, content_type, author_id) VALUES (?, ?, ?, (SELECT userid FROM users WHERE login = ?))"),
    UPDATE_PUBLICATION_BLOCKED("UPDATE publication SET blocked = ? WHERE id_publication = ?");

    private final String query;
    QueryToDatabase(String query) {
        this.query = query;
    }
    public String getQuery() {
        return this.query;
    }
}
