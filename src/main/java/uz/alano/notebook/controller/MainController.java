package uz.alano.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.alano.notebook.ContactService;

import java.util.Map;

@Controller
public class MainController {

    private ContactService contactService;
    @Autowired
    public MainController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping({"/","/home", "contacts"})
    public String showHomePage(Map<String, Object> model) {
        model.put("contacts", contactService.getContacts());

        return "contacts";
    }
}
