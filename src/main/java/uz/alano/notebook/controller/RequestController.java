package uz.alano.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.alano.notebook.model.Leader;
import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;
import uz.alano.notebook.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller()
public class RequestController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/","/home"})
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value = "registration", method=GET)
    public String registration(Model model){
        model.addAttribute(new User());

        return "registration";
    }

    @RequestMapping(value = "registration", method=POST)
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "registration";
        }

        if (userService.getUserByLogin(user.getLogin()) == null) {
            userService.saveUser(new Leader(user.getUserName(), user.getLogin(), user.getPassword()));
        } else {
            return "registration";
        }

        return "requestList";
    }

    @RequestMapping(value = "requests/requestList", method=GET)
    public String showRequestList(Map<String, Object> model){
        List<Request> requests = userService.getRequests();
        model.put("requests", requests);

        return "requestList";
    }

    @RequestMapping(value = "requests/addRequest", method=GET)
    public String addRequest(Model model){
        model.addAttribute(new Request());
        return "addRequest";
    }

    @RequestMapping(value = "requests/addRequest", method=POST)
    public String addRequest(Request request, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "addRequest";
        }

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.saveRequest(request, user.getUsername());

        return "requestList";
    }

    @RequestMapping(value = "requests/processRequests", method=GET)
    public String processRequests(Map<String, Object> model){
        List<Request> requests =  userService.processRequests();
        if (!requests.isEmpty()){
            model.put("requests", requests);
            userService.removeAllRequests();

            return "validRequests";
        }

        return "requestList";
    }
}