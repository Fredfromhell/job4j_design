package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        Map<String, String> rsl = config.getValues();
        assertThat(rsl.size(), is(1));

    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBadFile()  {
        String path = "./data/IllegalArgumentException.properties";
        Config config = new Config(path);
        config.load();
    }
}
