package org.example.weatherbackend.WeatherApp.Service;

import lombok.Getter;

@Getter
public enum DecimalConverter {
    NONE(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private final int decimals;

    DecimalConverter(int decimals) {
        this.decimals = decimals;
    }

    /**
     * Trunkerar ett tal till angivet antal decimaler.
     */
    public double truncate(double value) {
        double factor = Math.pow(10, decimals);
        return Math.floor(value * factor) / factor;
    }

    /**
     * Avrundar ett tal till angivet antal decimaler.
     */
    public double round(double value) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }
}
