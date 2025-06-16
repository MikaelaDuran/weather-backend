package org.example.weatherbackend.WeatherApp.Enums;

public class WeatherConstants {

    /**
     * Delningsfaktor för temperaturvärde från sensorn.
     * Sensorn skickar temperatur i hundradels grader.
     */
    public static final double TEMPERATURE_DIVISOR = 100.0;

    /**
     * Delningsfaktor för tryckvärde från sensorn.
     * Sensorn skickar värdet i enhet x256, sedan måste det konverteras till hPa och skalas.
     */
    public static final double PRESSURE_DIVISOR = 256.0;

    /**
     * Skalningsfaktor för tryck från sensorenhet till hPa.
     * Sensorvärdet delas med detta tal för att omvandlas till standardenhet.
     */
    public static final double PRESSURE_SCALE = 100.0;

    /**
     * Delningsfaktor för luftfuktighet i procent.
     */
    public static final double HUMIDITY_DIVISOR = 1024.0;

    /**
     * Minsta rimliga temperatur i Celsius.
     */
    public static final double MIN_TEMP = -50.0;

    /**
     * Högsta rimliga temperatur i Celsius.
     */
    public static final double MAX_TEMP = 60.0;

    /**
     * Minsta rimliga lufttryck i hPa.
     */
    public static final double MIN_PRESSURE = 800.0;

    /**
     * Högsta rimliga lufttryck i hPa.
     */
    public static final double MAX_PRESSURE = 1200.0;

    /**
     * Minsta rimliga luftfuktighet i procent.
     */
    public static final double MIN_HUMIDITY = 0.0;

    /**
     * Högsta rimliga luftfuktighet i procent.
     */
    public static final double MAX_HUMIDITY = 100.0;

}

