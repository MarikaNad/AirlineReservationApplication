package model;

import java.time.LocalTime;

public class ArrivalDetails {
    private String arrivalCity;

    private LocalTime arrivalTime;

    public ArrivalDetails(String arrivalCity, LocalTime arrivalTime) {
        this.arrivalCity = arrivalCity;
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "ArrivalDetails{" +
                "arrivalCity='" + arrivalCity + '\'' +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
