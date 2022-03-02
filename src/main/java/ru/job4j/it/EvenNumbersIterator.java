package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = true;
                index = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next()  {
        if (hasNext()) {
            return data[index++];

        } else {
            throw new NoSuchElementException("Нет четных элементов");
        }
    }

    public static void main(String[] args) {
        EvenNumbersIterator test = new EvenNumbersIterator(new int[]{1, 3, 5, 7, 200});
        System.out.println(test.hasNext());
        System.out.println(test.next());
        System.out.println(test.hasNext());

    }
}
