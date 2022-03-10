package ru.job4j.generics;

import ru.job4j.generics.Animal;

public class Predator extends Animal {
    private String weapon;
    private String ship;
    private Boolean killSchwarzenegger;

    public Predator(String type, String weapon, String ship, Boolean killSchwarzenegger) {
        super(type);
        this.weapon = weapon;
        this.ship = ship;
        this.killSchwarzenegger = killSchwarzenegger;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public Boolean getkillSchwarzenegger() {
        return killSchwarzenegger;
    }

    public void setkillSchwarzenegger(Boolean killSchwarzenegger) {
        this.killSchwarzenegger = killSchwarzenegger;
    }

    @Override
    public String toString() {
        return "Predator{" +
                "weapon='" + weapon + '\'' +
                ", ship='" + ship + '\'' +
                ", killSchwarzenegger=" + killSchwarzenegger +
                '}';
    }
}
