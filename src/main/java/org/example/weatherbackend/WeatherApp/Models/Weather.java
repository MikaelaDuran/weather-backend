package org.example.weatherbackend.WeatherApp.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    //När data hämtas från API:et
    //@CreationTimestamp
    //@Column(updatable = false)
    private LocalDateTime timestamp;

}
