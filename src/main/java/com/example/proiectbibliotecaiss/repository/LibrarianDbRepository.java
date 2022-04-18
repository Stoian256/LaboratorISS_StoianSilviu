package com.example.proiectbibliotecaiss.repository;
import com.example.proiectbibliotecaiss.domain.Librarian;
import com.example.proiectbibliotecaiss.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class LibrarianDbRepository implements IRepository<Librarian>{
    private JdbcUtils dbUtils;

    public LibrarianDbRepository(Properties properties) {
        dbUtils=new JdbcUtils(properties);
    }

    @Override
    public Librarian findByUsername(String username) {
        Connection con= dbUtils.getConnection();
        Librarian librarian=null;
        try(PreparedStatement preStmt=con.prepareStatement(" select * from Librarians where username=?")){
            preStmt.setString(1,username);
            try(ResultSet result =preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("librarianId");
                    String CNP = result.getString("cnp");
                    String libUsername = result.getString("username");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String password =result.getString("password");
                    librarian = new Librarian(id,CNP,libUsername,firstName,lastName,password);
                }
            }
        }catch (SQLException ex){
            System.out.println("error DB "+ex);
        }
        return librarian;
    }

    @Override
    public int add(Librarian entity) {
        return -1;
    }

    @Override
    public Librarian findOne(int ID) {
        return null;
    }

    @Override
    public Collection<Librarian> findAll() {
        Connection con= dbUtils.getConnection();
        List<Librarian> librarians = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement(" select * from Librarians")){
            try(ResultSet result =preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("librarianId");
                    String CNP = result.getString("cnp");
                    String libUsername = result.getString("username");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    String password =result.getString("password");
                    Librarian librarian = new Librarian(id,CNP,libUsername,firstName,lastName,password);
                    librarians.add(librarian);
                }
            }
        }catch (SQLException ex){
            System.out.println("error DB "+ex);
        }
        return librarians;
    }

    @Override
    public void update(Librarian entity) {

    }

    @Override
    public void delete(int ID) {

    }
}
