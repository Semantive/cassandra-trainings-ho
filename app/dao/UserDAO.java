package dao;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.DriverException;
import dao.cassandra.CassandraSupport;
import models.User;
import play.Logger;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserDAO {

    private final CassandraSupport cassandraSupport;

    @Inject
    public UserDAO(CassandraSupport cSupport) {
        this.cassandraSupport = cSupport;
    }


    private String md5(String text) {
        if(text == null || text.isEmpty())
            return text;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(text.getBytes("UTF-8"));
            return new String(thedigest);
        } catch (NoSuchAlgorithmException e) {
            Logger.error("no MD5");
        } catch (UnsupportedEncodingException e) {
            Logger.error("usupprted encoding");
        }
        return text;
    }

    public boolean updateUser(User user) {
        Session session = cassandraSupport.getSession();

        PreparedStatement statement = session.prepare(
            "update user set firstname = ?, lastname = ?, email = ?, password = ?, reputation = ? where login = ?;"
        );

        BoundStatement boundStatement = new BoundStatement(statement);

        try {
            session.execute(boundStatement.bind(user.getFirstName(), user.getLastName(), user.getEmail(), md5(user.getPassword()), user.getReputation(), user.getLogin()));
            return true;
        } catch (DriverException e) {
            Logger.error("unable update user", e);
        }

        return false;
    }

    public boolean createUser(User user) {
        Session session = cassandraSupport.getSession();

        PreparedStatement statement = session.prepare(
            "insert into user (login, firstname, lastname, email, password, reputation) " +
                    "values(?, ?, ?, ?, ?, ?) IF NOT EXISTS;"
        );

        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(), md5(user.getPassword()), 0));
        return rs.one().getBool(0);
    }

    public boolean authenticate(String login, String password) {
        Session session = cassandraSupport.getSession();
        PreparedStatement statement = session.prepare(
                "select password from user where login = ?;"
        );

        ResultSet rs = session.execute(new BoundStatement(statement).bind(login));
        Row row = rs.one();
        return row != null && md5(password).equals(row.getString(0));
    }


    public User getUser(String login) {
        Session session = cassandraSupport.getSession();

        PreparedStatement statement = session.prepare(
                "select login, firstname, lastname, email, password, reputation from user where login = ?; "
        );

        BoundStatement boundStatement = new BoundStatement(statement);
        ResultSet rs = session.execute(boundStatement.bind(login));
        Row row = rs.one();
        if(row != null) {
            return new User(
                    row.getString("login"),
                    row.getString("firstname"),
                    row.getString("lastname"),
                    row.getString("email"),
                    row.getString("password"),
                    row.getInt("reputation")
            );
        } else {
            return new User(login);
        }
    }
}
