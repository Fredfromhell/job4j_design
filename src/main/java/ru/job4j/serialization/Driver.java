package ru.job4j.serialization;

import java.util.Arrays;

public class Driver {
    private final boolean sex;
    private final int age;
    private final Car car;
    private final String[] statuses;

    public Driver(boolean sex, int age, Car car, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.car = car;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "sex="
                + sex
                + ", age="
                + age
                + ", car="
                + car
                + ", statuses="
                + Arrays.toString(statuses)
                + '}';
    }
}
