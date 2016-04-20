package uz.alano.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.alano.notebook.model.Contact;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS public.contacts" +
                "(email character varying(20)," +
                "phonenumber character varying(10)," +
                "entryName character varying(30) NOT NULL," +
                "datebirth date)" +
                "WITH (OIDS = FALSE)";

        jdbcTemplate.update(query);
    }

    public List<Contact> getContacts() {
        String query = "SELECT * FROM public.contacts";
        List<Contact> result = jdbcTemplate.queryForList(query).stream().map(m -> {
            String entryName = (String) m.get("entryName");
            String email = (String) m.get("email");
            String phoneNumber = (String) m.get("phonenumber");
            Date dateBirth = (Date) m.get("datebirth");

            Contact contact = new Contact(entryName, email, dateBirth.toString(), phoneNumber);

            return contact;
        }).collect(Collectors.toList());

        return result;
    }

    public Contact getContactByName(String entryName) {
        String query = "SELECT * FROM public.contacts WHERE entryName = ?";
        try {
            Contact result = jdbcTemplate.queryForObject(query, new Object[]{entryName}, (resultSet, i) -> {

                Contact contact = new Contact(
                        resultSet.getString("entryName"),
                        resultSet.getString("email"),
                        resultSet.getString("datebirth"),
                        resultSet.getString("phonenumber"));

                return contact;
            });

            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveContact(Contact contact) {
        final String insertQuery = "INSERT INTO public.contacts (entryName, email, datebirth, phonenumber) " +
                "VALUES (?, ?, ?, ?)";

        if (getContactByName(contact.getEntryName()) == null) {
            jdbcTemplate.update(insertQuery,
                    contact.getEntryName(),
                    contact.getEmail(),
                    contact.getBirthDateAsDate(),
                    contact.getPhoneNumber());
        } else {
            updateContact(contact);
        }
    }

    public void updateContact(Contact contact) {
        final String updateQuery = "UPDATE public.contacts " +
                "SET entryName = ?, email = ?, datebirth = ?, phonenumber = ? " +
                "WHERE entryName = '" + contact.getEntryName() + "'";
        jdbcTemplate.update(updateQuery,
                contact.getEntryName(),
                contact.getEmail(),
                contact.getBirthDateAsDate(),
                contact.getPhoneNumber());
    }

    public void removeContactByName(String entryName) {
        String query = "DELETE FROM contacts WHERE entryName ='" + entryName + "'";
        jdbcTemplate.update(query);
    }
}