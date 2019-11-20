package modelDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class LectorDto {
    @Valid
    @Pattern(message = "Bad formed lector firstName", regexp = "[a-zA-Z]{1,16}")
    @Size(max = 15, min = 3)
    @NotNull
    private String firstName;

    @Valid
    @Pattern(message = "Bad formed lector lastName", regexp = "[a-zA-Z]{1,16}")
    @Size(max = 15, min = 3)
    @NotNull
    private String lastName;
    private long id;

    public LectorDto() {}
    public LectorDto(long id) {
        this.id = id;
    }

    public LectorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return firstName +" " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectorDto lectorDto = (LectorDto) o;
        return id == lectorDto.id &&
                firstName.equals(lectorDto.firstName) &&
                lastName.equals(lectorDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}
