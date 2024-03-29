package org.example.client;

import org.knowm.xchart.XYChart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Sensor sensor = registerSensor();
//        addMeasurements(sensor);
        getAllMeasurements().forEach(System.out::println);
    }

    public static Sensor registerSensor() {
        Sensor sensor = new Sensor("RDD4");

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/sensors/registration";
        String response = restTemplate.postForObject(url, sensor, String.class);
        System.out.println(response);

        return sensor;
    }

    public static void addMeasurements(Sensor sensor) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements/add";

        for (int i = 0; i < 1000; i++) {
            double random = -100 + (Math.random() * 200);   // from -100 to 100
            BigDecimal value = BigDecimal.valueOf(random).setScale(2, RoundingMode.HALF_UP);
            boolean raining = Double.compare(Math.random(), 0.5) >= 0;  // 50/50
            Measurement measurement = new Measurement(value, raining, sensor, null);

            String response = restTemplate.postForObject(url, measurement, String.class);
            System.out.println(response);
        }
    }

    public static List<Measurement> getAllMeasurements() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements";
        ParameterizedTypeReference<List<Measurement>> typeReference = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }

    public static void saveMeasurementsChart(List<Measurement> measurements) {
        XYChart chart = new XYChart(800, 600);
    }
}