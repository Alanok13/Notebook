package uz.alano.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.alano.notebook.service.ContactService;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ContactService contactService;

    @RequestMapping({"/","/home", "contacts"})
    public String showHomePage(Map<String, Object> model) {
        model.put("contacts", contactService.getContacts());

        return "contacts";
    }
}
