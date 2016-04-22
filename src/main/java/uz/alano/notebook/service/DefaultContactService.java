package uz.alano.notebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.alano.notebook.dao.ContactsDAO;
import uz.alano.notebook.model.Contact;

import java.util.List;

@Service
public class DefaultContactService implements ContactService {

    @Autowired
    private ContactsDAO contactsDAO;

    @Override
    public List<Contact> getContacts() {
        return contactsDAO.getContacts();
    }

    @Override
    public Contact getContactByName(String entryName) {
        return contactsDAO.getContactByName(entryName);
    }

    @Override
    public void saveContact(Contact contact) {
        contactsDAO.saveContact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactsDAO.updateContact(contact);
    }

    @Override
    public void removeContactByName(String entryName) {
        contactsDAO.removeContactByName(entryName);
    }
}