package uz.alano.notebook.model;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;
import java.util.Objects;

public class Request {
    private String country;

    private int month;

    private int startDay;

    private int endDay;

    public Request(String country, int month, int startDay, int endDay) {
        this.country = country;
        this.month = month;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public Request() {

    }

    public String getCountry() {
        return country;
    }

    public int getMonth() {
        return month;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public Boolean equals(Request other)
    {
        return Objects.equals(this.country, other.country) &&
                this.month == other.month &&
                this.startDay == other.startDay &&
                this.endDay == other.endDay;
    }

    public static Request fromMap(Map<String, Object> map){
        String country = (String) map.get("country");
        int month = (int)map.get("monthNumber");
        int startDay = (int)map.get("startDay");
        int endDay = (int)map.get("endDay");
        Request request = new Request(country, month, startDay, endDay);

        return request;
    }
}
