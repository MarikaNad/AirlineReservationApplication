package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DepartureDetails {
    private String departureCity;
    private Date departureDate;
    private LocalTime departureTime;

    public DepartureDetails(String departureCity, Date departureDate, LocalTime departureTime) {
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    
    public String getDepartureCity() {
        return departureCity;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }


    @Override
    public String toString() {
        return "DepartureDetails{" +
                "departureCity='" + departureCity + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                '}';
    }
}
