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
    private final static LocalTime SIX_HOUR = LocalTime.of(6, 0);
    private final static LocalTime NINE_HOUR = LocalTime.of(9, 0);
    private final static LocalTime NINETEEN_HOUR = LocalTime.of(19, 0);
    private final static LocalTime TWENTY_THREE_HOUR = LocalTime.of(23, 0);
    private final static LocalTime NIGHT_HOUR = LocalTime.of(0, 0);
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

        if (localTime.isAfter(SIX_HOUR) && isBeforeEquals(localTime, NINE_HOUR))
            retMessage = resourceMessage.getString("morning");

        else if (localTime.isAfter(NINE_HOUR) && isBeforeEquals(localTime, NINETEEN_HOUR))
            retMessage = resourceMessage.getString("day");

        else if (localTime.isAfter(SIX_HOUR) && isBeforeEquals(localTime, TWENTY_THREE_HOUR))
            retMessage = resourceMessage.getString("evening");

        else if (localTime.isAfter(TWENTY_THREE_HOUR)
                || localTime.compareTo(NIGHT_HOUR) == 0
                || isBeforeEquals(localTime, SIX_HOUR))
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
