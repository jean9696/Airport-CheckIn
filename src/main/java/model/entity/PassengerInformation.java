package model.entity;

/**
 * Represents a passenger
 */
public class PassengerInformation {
    protected String firstname;
    protected String lastname;
    protected Integer age;

    /**
     * @param firstname
     * @param lastname
     * @param age
     */
    public PassengerInformation(String firstname, String lastname, Integer age) throws Exception {
        if (firstname.length() < 2 || lastname.length() < 2 || age < 1) {
            throw new Exception("Invalid passenger constructor inputs");
        }
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    /**
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param passenger's firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
        return firstname + " " + lastname;
    }
}
