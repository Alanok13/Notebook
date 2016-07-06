package uz.alano.notebook.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.alano.notebook.model.Leader;
import uz.alano.notebook.model.Request;
import uz.alano.notebook.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DefaultUsersDAO implements UsersDAO {

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        createTable();
    }

    private JdbcTemplate jdbcTemplate;

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS public.users" +
                " (userName character varying(20) NOT NULL," +
                " login character varying(10) NOT NULL," +
                " password character varying(30) NOT NULL," +
                " userType character varying(20) NOT NULL)" +
                " WITH (OIDS = FALSE);" +
                " CREATE TABLE IF NOT EXISTS public.requests" +
                " (country character varying(20) NOT NULL," +
                " month integer NOT NULL," +
                " startDay integer NOT NULL," +
                " endDay integer NOT NULL," +
                " login character varying(20) NOT NULL) " +
                " WITH (OIDS = FALSE);";

        jdbcTemplate.update(query);
    }

    @Override
    public List<User> getUsers() {
        String query = "SELECT * FROM public.users";
        List<User> result = jdbcTemplate.queryForList(query).stream().map(m -> {
            String name = (String) m.get("userName");
            String login = (String) m.get("login");
            String pass = (String) m.get("password");
            if (m.get("userType") == "Leader"){
                String q = "SELECT * FROM public.requests WHERE login = ?";
                List<Request> requests = jdbcTemplate.queryForList(q, name)
                        .stream()
                        .map(Request::fromMap)
                        .collect(Collectors.toList());

                Leader leader = new Leader(name, login, pass, requests);

                return leader;
            }

            User user = new User(name, login, pass);

            return user;
        }).collect(Collectors.toList());

        return result;
    }

    public void saveRequest(Request request, String login){
        final String insertQuery = "INSERT INTO public.requests (country, monthNumber, startDay, endDay, login) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertQuery,
                request.getCountry(),
                request.getMonth(),
                request.getStartDay(),
                request.getEndDay(),
                login);
    }

    public List<Request> getRequests(){
        String query = "SELECT * FROM public.requests";
        List<Request> result = jdbcTemplate.queryForList(query)
                .stream()
                .map(Request::fromMap)
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public void saveUser(User user) {

        final String insertQuery = "INSERT INTO public.users (userName, login, password, userType) " +
                "VALUES (?, ?, ?, ?)";

        if (getUserByLogin(user.getLogin()) == null) {
            jdbcTemplate.update(insertQuery,
                    user.getUserName(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getClass() == Leader.class ? "LEADER" : "USER");
        }
    }

    @Override
    public User getUserByLogin(String login) {
        String query = "SELECT * FROM public.users WHERE login = ?";
        try {
            User result = jdbcTemplate.queryForObject(query, new Object[]{login}, (resultSet, i) -> {

                  User user = new User(
                        resultSet.getString("userName"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));

                return user;
            });

            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void removeAllRequests() {
        String query = "DELETE * FROM public.requests";
        jdbcTemplate.update(query);
    }
}
