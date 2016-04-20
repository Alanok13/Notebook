package uz.alano.notebook.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class Contact {

    @Size(min = 2, max = 20, message = "Name must be less than 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
    private String entryName;

    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address.")
    private String email;
    private Calendar birthDate;

    @Pattern(regexp = "\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}",
            message = "Invalid phone number.")
    private String phoneNumber;

    public Contact() {
    }

    public Contact(String entryName, String email, String birthDate, String phoneNumber) {
        this.entryName = entryName;
        this.email = email;
        this.setBirthDate(birthDate);
        this.phoneNumber = phoneNumber;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format((birthDate.getTime()));

        return formattedDate;
    }

    public Date getBirthDateAsDate() {
        return birthDate.getTime();
    }

    public void setBirthDate(String birthDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = dateFormat.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(parsed);
        this.birthDate = newCalendar;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
