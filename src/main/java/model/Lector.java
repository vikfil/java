package model;

import java.util.Objects;

public class Lector {

    private String firstName;
    private String lastName;
    private long id;

    public Lector() {}

    public Lector( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Lector(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }

        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Lector other = (Lector) otherObject;
        return  Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName)
                && id == other.id;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}

