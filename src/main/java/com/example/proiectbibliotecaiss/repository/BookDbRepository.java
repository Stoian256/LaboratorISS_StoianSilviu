package com.example.proiectbibliotecaiss.repository;

import com.example.proiectbibliotecaiss.domain.Book;
import com.example.proiectbibliotecaiss.domain.BookStatus;
import com.example.proiectbibliotecaiss.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class BookDbRepository implements IBookRepository {
    private JdbcUtils dbUtils;

    public BookDbRepository(Properties properties) {
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public int add(Book entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Books(ISBN,title,author,year,status) values (?,?,?,?,?)")) {
            preStmt.setString(1, entity.getISBN());
            preStmt.setString(2, entity.getTitle());
            preStmt.setString(3, entity.getAuthor());
            preStmt.setInt(4, entity.getYear());
            preStmt.setString(5, entity.getStatus().toString());
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
        int ID = -1;
        try (PreparedStatement preStmt = con.prepareStatement("SELECT bookid FROM Books ORDER BY bookId DESC LIMIT 1")) {
            ResultSet resultSet = preStmt.executeQuery();
            resultSet.next();
            ID = resultSet.getInt("bookId");
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
        return ID;
    }

    @Override
    public Book findByUsername(String username) {
        return null;
    }

    @Override
    public Book findOne(int ID) {
        Connection con = dbUtils.getConnection();
        Book book = null;
        try (PreparedStatement preStmt = con.prepareStatement(" select * from Books where bookId=?")) {
            preStmt.setInt(1, ID);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("bookId");
                    String ISBN = result.getString("isbn");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    int year = result.getInt("year");
                    BookStatus bookStatus = BookStatus.valueOf(result.getString("status"));

                    book = new Book(id, ISBN, title, author, year, bookStatus);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
        return book;
    }

    @Override
    public Collection<Book> findAll() {
        Connection con = dbUtils.getConnection();
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement(" select * from Books")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("bookId");
                    String ISBN = result.getString("isbn");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    int year = result.getInt("year");
                    BookStatus bookStatus = BookStatus.valueOf(result.getString("status"));

                    Book book = new Book(id, ISBN, title, author, year, bookStatus);
                    bookList.add(book);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
        return bookList;
    }

    @Override
    public void update(Book entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement(" update Books set status=? where bookId=?")) {
            preStmt.setString(1, entity.getStatus().toString());
            preStmt.setInt(2, entity.getBookID());
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public Collection<Book> findBorrowedBooksBySubscriberIdAll(int subscriberId) {
        Connection con = dbUtils.getConnection();
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement(" select * from Books INNER JOIN borrowed ON Books.bookid=borrowed.book_id\n" +
                "INNER JOIN subscribers ON subscribers.subscriberid=borrowed.subscriber_id where borrowed.subscriber_id=?")) {
            preStmt.setInt(1, subscriberId);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("bookId");
                    String ISBN = result.getString("isbn");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    int year = result.getInt("year");
                    BookStatus bookStatus = BookStatus.valueOf(result.getString("status"));

                    Book book = new Book(id, ISBN, title, author, year, bookStatus);
                    bookList.add(book);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
        return bookList;
    }

    @Override
    public void updateBorrowedBook(int subscriberID,int bookId) {
        Connection con = dbUtils.getConnection();
            try (PreparedStatement preStmt = con.prepareStatement("insert into borrowed(subscriber_id,book_id) values (?,?)")) {
                preStmt.setInt(1, subscriberID);
                preStmt.setInt(2, bookId);
                preStmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("error DB " + ex);
            }

    }

    @Override
    public void returnBook(int subscriberID, int bookId) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from borrowed where subscriber_id = ? and book_id = ?")) {
            preStmt.setInt(1, subscriberID);
            preStmt.setInt(2, bookId);
            preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error DB " + ex);
        }
    }
}
