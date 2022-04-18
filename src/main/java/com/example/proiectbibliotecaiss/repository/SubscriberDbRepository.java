package com.example.proiectbibliotecaiss.repository;

import com.example.proiectbibliotecaiss.domain.Librarian;
import com.example.proiectbibliotecaiss.domain.Subscriber;
import com.example.proiectbibliotecaiss.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class SubscriberDbRepository implements IRepository<Subscriber> {
    private JdbcUtils dbUtils;

    public SubscriberDbRepository(Properties properties) {
        dbUtils=new JdbcUtils(properties);
    }

    @Override
    public Subscriber findByUsername(String username) {
        Connection con= dbUtils.getConnection();
        Subscriber subscriber=null;
        try(PreparedStatement preStmt=con.prepareStatement(" select * from Subscribers where cnp=?")){
            preStmt.setString(1,username);
            try(ResultSet result =preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("subscriberId");
                    String CNP = result.getString("cnp");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String address =result.getString("address");
                    String phone =result.getString("phone");
                    String password =result.getString("password");
                    subscriber = new Subscriber(id,CNP,firstName,lastName,address,phone,password);
                }
            }
        }catch (SQLException ex){
            System.out.println("error DB "+ex);
        }
        return subscriber;
    }

    @Override
    public int add(Subscriber entity) {
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Subscribers(cnp,firstName,lastName,address,phone,password) values (?,?,?,?,?,?)")){
            preStmt.setString(1,entity.getCNP());
            preStmt.setString(2,entity.getFirstName());
            preStmt.setString(3,entity.getLastName());
            preStmt.setString(4,entity.getAddress());
            preStmt.setString(5,entity.getPhone());
            preStmt.setString(6,entity.getPassword());
            preStmt.executeUpdate();
        }catch (SQLException ex){
            System.out.println("error DB "+ex);
        }
        int ID=-1;
        try(PreparedStatement preStmt=con.prepareStatement("SELECT subscriberId FROM Subscribers ORDER BY subscriberId DESC LIMIT 1")){
            ResultSet resultSet = preStmt.executeQuery();
            resultSet.next();
            System.out.println(ID);
        }catch (SQLException ex){
            System.out.println("error DB1 "+ex);
        }
        return ID;
    }

    @Override
    public Subscriber findOne(int ID) {
        return null;
    }

    @Override
    public Collection<Subscriber> findAll() {
        Connection con= dbUtils.getConnection();
        List<Subscriber> subscribers = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement(" select * from Subscribers")){
            try(ResultSet result =preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("subscriberId");
                    String CNP = result.getString("cnp");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String address =result.getString("address");
                    String phone =result.getString("phone");
                    String password =result.getString("password");
                    Subscriber subscriber = new Subscriber(id,CNP,firstName,lastName,address,phone,password);
                    subscribers.add(subscriber);
                }
            }
        }catch (SQLException ex){
            System.out.println("error DB "+ex);
        }
        return subscribers;
    }

    @Override
    public void update(Subscriber entity) {

    }

    @Override
    public void delete(int ID) {

    }
}
