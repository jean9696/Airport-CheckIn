package model;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

import static model.BaggageSize.createRandomBaggage;

/**
 * Represents a passenger
 */
public class Passenger {
    private String surname;
    private String lastname;
    private Integer age;
    private BaggageSize baggage;

    /**
     * @param surname
     * @param lastname
     * @param age
     */
    public Passenger(String surname, String lastname, Integer age, BaggageSize baggage) throws Exception {
        if (surname.length() < 2 || lastname.length() < 2 || age < 1 || !baggage.isValidSize()) {
            throw new Exception("Invalid passenger constructor inputs");
        }
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.baggage = baggage;
    }

    public static Passenger createFakeRandomPassenger() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int age = ThreadLocalRandom.current().nextInt(10, 100);
        try {
            return new Passenger(firstName, lastName, age, createRandomBaggage());
        } catch (Exception e) {
            return createFakeRandomPassenger();
        }
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
