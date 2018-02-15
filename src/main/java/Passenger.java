/**
 * Represents a passenger
 */
public class Passenger {
    private String surname;
    private String lastname;
    private Integer age;

    /**
     * @param surname
     * @param lastname
     * @param age
     */
    public Passenger(String surname, String lastname, Integer age) {
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
    }

    /**
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param passenger's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return passenger's lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return passenger's age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
