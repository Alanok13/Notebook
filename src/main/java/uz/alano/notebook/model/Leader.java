package uz.alano.notebook.model;

import java.util.ArrayList;
import java.util.List;

public class Leader extends User {

    List<Request> requests;
    public List<Request> getRequests() {
        return requests;
    }

    public Leader(String name, String login, String password) {
        super(name, login, password);
        this.requests = new ArrayList<>();
    }

    public Leader(String name, String login, String password, List<Request> requests) {
        super(name, login, password);
        this.requests = requests;
    }

    public void addRequest(Request request){
        requests.add(request);
    }
}
