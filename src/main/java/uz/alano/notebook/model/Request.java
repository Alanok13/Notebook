package uz.alano.notebook.model;


import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;
import java.util.Objects;

public class Request {
    public void setCountry(String country) {
        this.country = country;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

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

    public Boolean isMatch(Request other)
    {
        boolean isMatch = Objects.equals(this.country, other.country) &&
                this.month == other.month &&
                this.startDay <= other.endDay &&
                this.endDay >= other.startDay;

        return isMatch;
    }

    public static Request fromMap(Map<String, Object> map){
        String country = (String) map.get("country");
        int month = (int)map.get("month");
        int startDay = (int)map.get("startday");
        int endDay = (int)map.get("endday");
        Request request = new Request(country, month, startDay, endDay);

        return request;
    }

    public Request Intersection(Request r) {
        Request request = new Request();
        request.country = r.getCountry();
        request.month = r.getMonth();
        request.startDay = r.getStartDay() > this.startDay ? r.getStartDay() : this.startDay;
        request.endDay = r.getEndDay() < this.endDay ? r.getEndDay() : this.endDay;

        return request;
    }
}
