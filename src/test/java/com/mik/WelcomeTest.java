package com.mik;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

@RunWith(value = Parameterized.class)
public class WelcomeTest {

    private Welcome welcomeRu;
    private ResourceBundle resourceMessageRu;

    public WelcomeTest(Locale locale) {
        welcomeRu  = new Welcome(locale);
        resourceMessageRu = ResourceBundle.getBundle("message", locale);
    }

    // определяем значениея локали которые будем тестировать
    @Parameterized.Parameters()
    public static Collection getParameters() {
        return Arrays.asList(Locale.ENGLISH, Locale.forLanguageTag("RU"));
    }

    // Проверка попадания в утро
    @Test
    public void testMorningStart() {
        Assert.assertEquals(resourceMessageRu.getString("morning"),welcomeRu.getMessage(LocalTime.of(6,0,1)));
    }

    @Test
    public void testMorningMiddle() {
        Assert.assertEquals(resourceMessageRu.getString("morning"),welcomeRu.getMessage(LocalTime.of(8,30)));
    }

    @Test
    public void testMorningEnd() {
        Assert.assertEquals(resourceMessageRu.getString("morning"),welcomeRu.getMessage(LocalTime.of(9,0)));
    }

    // Проверка попадания в день
    @Test
    public void testDayStart() {
        Assert.assertEquals(resourceMessageRu.getString("day"),welcomeRu.getMessage(LocalTime.of(9,0,1)));
    }

    @Test
    public void testDayMiddle() {
        Assert.assertEquals(resourceMessageRu.getString("day"),welcomeRu.getMessage(LocalTime.of(14,30)));
    }

    @Test
    public void testDayEnd() {
        Assert.assertEquals(resourceMessageRu.getString("day"),welcomeRu.getMessage(LocalTime.of(19,0)));
    }

    // Проверка попадания в вечер
    @Test
    public void testEveningStart() {
        Assert.assertEquals(resourceMessageRu.getString("evening"),welcomeRu.getMessage(LocalTime.of(19,0,1)));
    }

    @Test
    public void testEveningMiddle() {
        Assert.assertEquals(resourceMessageRu.getString("evening"),welcomeRu.getMessage(LocalTime.of(21,30)));
    }

    @Test
    public void testEveningEnd() {
        Assert.assertEquals(resourceMessageRu.getString("evening"),welcomeRu.getMessage(LocalTime.of(23,0)));
    }

    // Проверка попадания в ночь
    @Test
    public void testNightStart() {
        Assert.assertEquals(resourceMessageRu.getString("night"),welcomeRu.getMessage(LocalTime.of(23,0,1)));
    }

    @Test
    public void testNightMiddle() {
        Assert.assertEquals(resourceMessageRu.getString("night"),welcomeRu.getMessage(LocalTime.of(3,30)));
    }

    @Test
    public void testNightEnd() {
        Assert.assertEquals(resourceMessageRu.getString("night"),welcomeRu.getMessage(LocalTime.of(6,0)));
    }

}
