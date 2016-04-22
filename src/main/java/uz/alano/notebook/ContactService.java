package uz.alano.notebook;

import uz.alano.notebook.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts();

    Contact getContactByName(String entryName);

    void saveContact(Contact contact);

    void updateContact(Contact contact);

    void removeContactByName(String entryName);
}
