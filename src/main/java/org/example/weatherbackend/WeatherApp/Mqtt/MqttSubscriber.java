package org.example.weatherbackend.WeatherApp.Mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.example.weatherbackend.WeatherApp.DTO.IncomingWeather;
import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.example.weatherbackend.WeatherApp.Service.WeatherService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MqttSubscriber {

    private static final String BROKER = "tcp://192.168.61.111:1883";
    private static final String CLIENT_ID = "spring-mqtt-client";
    private static final String TOPIC = "/room_meas";

    private final WeatherService weatherService;
    private MqttClient client;

    public MqttSubscriber(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostConstruct
    public void connectAndSubscribe() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false); // 🔁 Behåll session
            options.setKeepAliveInterval(60); // 🟢 Håll anslutningen levande
            options.setAutomaticReconnect(true); // 🔄 Automatisk återanslutning

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.err.println("⚠️ MQTT-anslutning förlorad: " + cause.getMessage());
                    // Återanslutning hanteras automatiskt
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    try {
                        String payload = new String(message.getPayload());
                        System.out.println("📩 Mottaget från MQTT: " + payload);

                        ObjectMapper mapper = new ObjectMapper();
                        IncomingWeather incoming = mapper.readValue(payload, IncomingWeather.class);

                        Weather weather = new Weather();

                        // Temperatur i Celsius, trunkerad till 2 decimaler
                        double temp = incoming.getTemperature() / 100.0;
                        temp = Math.floor(temp * 100) / 100.0;
                        weather.setTemp(temp);

                        // Tryck i hPa, trunkerad till 2 decimaler
                        double pressure = (incoming.getPressure() / 256.0) / 100.0;
                        pressure = Math.floor(pressure * 100) / 100.0;
                        weather.setPressure(pressure);

                        // Luftfuktighet i %, trunkerad till 2 decimaler
                        double humidity = incoming.getHumidity() / 1024.0;
                        humidity = Math.floor(humidity * 100) / 100.0;
                        weather.setHumidity(humidity);

                        weatherService.saveWeather(weather);
                        System.out.println("✅ Sparad till DB via service");

                    } catch (Exception e) {
                        System.err.println("❌ Fel vid hantering av meddelande: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Inget att göra här för en subscriber – används bara för publish
                }


            });

            client.connect(options);
            client.subscribe(TOPIC);
            System.out.println("✅ Prenumererar på topic: " + TOPIC);

        } catch (MqttException e) {
            System.err.println("❌ Misslyckades att ansluta till MQTT-broker: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
