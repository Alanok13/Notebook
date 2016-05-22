package uz.alano.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.alano.notebook.service.ContactService;
import uz.alano.notebook.model.Contact;

import javax.validation.Valid;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping({"/","/home"})
    public String showHomePage(Map<String, Object> model) {
        model.put("contacts", contactService.getContacts());

        return "contacts";
    }

    @RequestMapping(value = "/add", method=GET)
    public String addContactPage(Model model){
        Contact contact = new Contact();
        contact.setBirthDate("0001-01-01");
        model.addAttribute(contact);

        return "add";
    }

    @RequestMapping(value = "/add", method=POST)
    public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "add";
        }

        contactService.saveContact(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method=GET)
    public String editContact(@RequestParam("name") String name, Model model){

        Contact contact = contactService.getContactByName(name);
        model.addAttribute(contact);

        return "edit";
    }

    @RequestMapping(value = "/edit", method=POST)
    public String addContactFromForm(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit";
        }

        contactService.updateContact(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method=GET)
    public String deleteContactRequest(@RequestParam("name") String name){
        contactService.removeContactByName(name);

        return "redirect:/";
    }
}