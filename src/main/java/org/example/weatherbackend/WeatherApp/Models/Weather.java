package org.example.weatherbackend.WeatherApp.Models;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Weather {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double temp;
    private double pressure;
    private double humidity;


    private LocalDateTime timestamp;

}
