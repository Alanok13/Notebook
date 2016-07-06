package uz.alano.notebook.service;

import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserByLogin(String login);

    void saveUser(User user);

    void removeAllRequests();

    void saveRequest(Request request, String login);

    List<Request> getRequests();

    List<Request> processRequests();
}
