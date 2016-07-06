package uz.alano.notebook.dao;

import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getUsers();

    User getUserByLogin(String name);

    void saveUser(User user);

    void removeAllRequests();

    void saveRequest(Request request, String login);

    List<Request> getRequests();
}