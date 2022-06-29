package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Petr Arsentev";
        int age = 33;
        boolean mentor = true;
        float flot = 1.12431241f;
        double dob = 1.124214121252151251251251;
        byte byt = 127;
        short shot = 32767;
        char male = 'm';
        long lon = 9223372036854775807L;
        LOG.debug("User info name : {}, age : {}, mentor : {}, flot : {}, dob : {}, byt : {}, shot "
                + ": {},male : {}, lon : {}", name, age, mentor, flot, dob, byt, shot, male, lon);
    }
}
