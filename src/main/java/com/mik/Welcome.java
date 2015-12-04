package com.mik;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mikitjuk on 01.12.15.
 */
public class Welcome {

    //Переменные границы периода дня
    private final static LocalTime BEGIN_MORNING = LocalTime.of(6, 0);
    private final static LocalTime BEGIN_DAY = LocalTime.of(9, 0);
    private final static LocalTime BEGIN_EVENING = LocalTime.of(19, 0);
    private final static LocalTime BEGIN_NIGHT = LocalTime.of(23, 0);
    //Переменная имя resource bundle
    private final static String defaultProrerties = "message";
    private ResourceBundle resourceMessage;

    private static final Logger logger = LogManager.getLogger(Welcome.class);

    public Welcome() {
        this(Locale.getDefault());
    }

    public Welcome(Locale locale) {
        this(defaultProrerties, locale);
    }

    public Welcome(String baseName, Locale locale) {
        this.resourceMessage = ResourceBundle.getBundle(baseName, locale);
        logger.debug("now locale = " + locale);
    }

    // метод определения какое сообщение выводить по времени
    public String getMessage(LocalTime localTime) {

        String retMessage = null;
        logger.debug("now local time = " + localTime);

        if (localTime.isAfter(BEGIN_MORNING) && isBeforeEquals(localTime, BEGIN_DAY))
            retMessage = resourceMessage.getString("morning");

        else if (localTime.isAfter(BEGIN_DAY) && isBeforeEquals(localTime, BEGIN_EVENING))
            retMessage = resourceMessage.getString("day");

        else if (localTime.isAfter(BEGIN_EVENING) && isBeforeEquals(localTime, BEGIN_NIGHT))
            retMessage = resourceMessage.getString("evening");

        else
            retMessage = resourceMessage.getString("night");

        logger.debug("message for local time = " + retMessage);

        return retMessage;
    }

    // метод для определения крайне большего времени с включением границы
    private boolean isBeforeEquals(LocalTime localTime, LocalTime other) {
        return (localTime.isBefore(other) || localTime.compareTo(other) == 0);
    }

    public ResourceBundle getResourceMessage() {
        return resourceMessage;
    }

    public void setResourceMessage(ResourceBundle resourceMessage) {
        this.resourceMessage = resourceMessage;
    }
}
