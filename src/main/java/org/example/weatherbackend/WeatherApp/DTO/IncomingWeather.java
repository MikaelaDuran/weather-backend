package org.example.weatherbackend.WeatherApp.DTO;

import lombok.Getter;
import lombok.Setter;

// Matchar MQTT JSON-strukturen
@Getter
@Setter
public class IncomingWeather {


    private long temp;
    private long pressure;
    private long humidity;

    }
