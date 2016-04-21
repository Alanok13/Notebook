package uz.alano.notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.alano.notebook.model.Contact;

import java.util.List;

@Component
public class ContactService {

    private ContactsDAO contactsDAO;
    @Autowired
    public void setContactsDAO(ContactsDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
    }

    public List<Contact> getContacts() {
        return contactsDAO.getContacts();
    }

    public Contact getContactByName(String entryName) {
        return contactsDAO.getContactByName(entryName);
    }

    public void saveContact(Contact contact) {
        contactsDAO.saveContact(contact);
    }

    public void updateContact(Contact contact) {
        contactsDAO.updateContact(contact);
    }
    public void removeContactByName(String entryName) {
        contactsDAO.removeContactByName(entryName);
    }
}