package uz.alano.notebook.dao;

import uz.alano.notebook.model.Contact;

import java.util.List;

public interface ContactsDAO {
    List<Contact> getContacts();

    Contact getContactByName(String entryName);

    void saveContact(Contact contact);

    void updateContact(Contact contact);

    void removeContactByName(String entryName);
}