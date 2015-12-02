package com.mik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;

/**
 * Hello world!
 *
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main( String[] args ){
        logger.info("start application");
        System.out.println(new Welcome().getMessage(LocalTime.now()));
    }
}
