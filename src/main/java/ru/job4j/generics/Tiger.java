package ru.job4j.generics;

public class Tiger extends Predator {
    private String color;
    private String weight;

    public Tiger(String type, String weapon, String ship, Boolean killSchwarzenegger, String color, String weight) {
        super(type, weapon, ship, killSchwarzenegger);
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "color='" + color + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
