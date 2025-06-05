package org.example.weatherbackend.WeatherApp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long temp;
    private long pressure;
    private long humidity;


}
