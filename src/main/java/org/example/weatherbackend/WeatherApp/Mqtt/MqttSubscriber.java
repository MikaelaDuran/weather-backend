package org.example.weatherbackend.WeatherApp.Mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.example.weatherbackend.WeatherApp.DTO.IncomingWeather;
import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.example.weatherbackend.WeatherApp.Service.WeatherService;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {

    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "spring-mqtt-client";
    private static final String TOPIC = "weather/data";

    private final WeatherService weatherService;

    public MqttSubscriber(WeatherService weatherService) {
        this.weatherService = weatherService;

        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);

            client.subscribe(TOPIC, (topic, message) -> {
                String payload = new String(message.getPayload());
                System.out.println("Mottaget från MQTT: " + payload);

                ObjectMapper mapper = new ObjectMapper();
                IncomingWeather incoming = mapper.readValue(payload, IncomingWeather.class);

                Weather weather = new Weather();
                weather.setTemp(incoming.getTemp());
                weather.setPressure(incoming.getPressure());
                weather.setHumidity(incoming.getHumidity());

                weatherService.saveWeather(weather);  // ⬅️ Via service-lagret
                System.out.println("Sparad till DB via service");

            });

            System.out.println("Prenumererar på topic: " + TOPIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
