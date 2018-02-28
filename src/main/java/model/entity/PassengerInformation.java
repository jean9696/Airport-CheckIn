package model.entity;

/**
 * Represents a passenger
 */
public class PassengerInformation {
    protected String surname;
    protected String lastname;
    protected Integer age;

    /**
     * @param surname
     * @param lastname
     * @param age
     */
    public PassengerInformation(String surname, String lastname, Integer age) throws Exception {
        if (surname.length() < 2 || lastname.length() < 2 || age < 1) {
            throw new Exception("Invalid passenger constructor inputs");
        }
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

    /**
     * @return passenger's name
     */
    @Override
    public String toString() {
        return surname + " " + lastname;
    }
}
