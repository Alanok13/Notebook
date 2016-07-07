package uz.alano.notebook.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.alano.notebook.dao.UsersDAO;
import uz.alano.notebook.model.Leader;
import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
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
        List<User> users = getUsers();
        List<Leader> leaders = users
                .stream()
                .filter(x -> x.getClass() == Leader.class)
                .map(x -> (Leader)x)
                .collect(Collectors.toList());

        List<Request> requests = leaders
                .stream()
                .map(x -> x.getRequests())
                .reduce(leaders.get(0).getRequests(), (x, y) -> GetIntersection(x, y));

        return requests;
    }

    private List<Request> GetIntersection(List<Request> f, List<Request> s){
        List<Request> intersections = f
                .stream()
                .map(r -> GetIntersection(r, s))
                .filter(x -> x != null)
                .collect(Collectors.toList());

        return intersections;
    }

    private Request GetIntersection(Request r, List<Request> requests){
        if (!requests.stream().anyMatch(x -> x.isMatch(r))){
            return null;
        }

        List<Request> matchRequests = requests
                .stream()
                .filter(request -> request.isMatch(r))
                .map(request -> request.Intersection(r))
                .collect(Collectors.toList());

        Request optRequest = matchRequests
                .stream()
                .max((x,y) -> x.getEndDay() - x.getStartDay() > y.getEndDay() - y.getStartDay()? 1 : -1)
                .get();

        return optRequest;
    }
}