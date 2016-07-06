package uz.alano.notebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.alano.notebook.dao.UsersDAO;
import uz.alano.notebook.model.Leader;
import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public List<User> getUsers() {
        return usersDAO.getUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return usersDAO.getUserByLogin(login);
    }

    @Override
    public void saveUser(User user) {
        usersDAO.saveUser(user);
    }

    @Override
    public void removeAllRequests() {
        usersDAO.removeAllRequests();
    }

    @Override
    public void saveRequest(Request request, String login) {
        usersDAO.saveRequest(request, login);
    }

    @Override
    public List<Request> getRequests() {
        return usersDAO.getRequests();
    }

    @Override
    public List<Request> processRequests() {
        List<Leader> leaders = getUsers()
                .stream()
                .filter(x -> x.getClass() == Leader.class)
                .map(x -> (Leader)x)
                .collect(Collectors.toList());
        Leader l = leaders.get(0);
        List<Request> validRequests = l.getRequests()
                .stream()
                .filter(request -> leaders
                        .stream()
                        .allMatch(x -> x.getRequests().stream().anyMatch(r -> r.equals(request))))
                .collect(Collectors.toList());

        return validRequests;
    }
}