package by.rekuts.tattoosalon.subject;

import java.io.Serializable;

public abstract class SalonEntity implements Serializable {
    private int id;
    public SalonEntity() {
    }
    public SalonEntity(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
