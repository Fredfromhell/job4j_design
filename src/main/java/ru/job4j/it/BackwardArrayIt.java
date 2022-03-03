package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;
    private int count = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;

    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point = data.length - 1 - count;
        count++;
        return data[point--];

    }

    public static void main(String[] args) {
        BackwardArrayIt test = new BackwardArrayIt(new int[]{1, 2, 3});
        System.out.println(test.next());
        System.out.println(test.next());
        System.out.println(test.next());

    }
}