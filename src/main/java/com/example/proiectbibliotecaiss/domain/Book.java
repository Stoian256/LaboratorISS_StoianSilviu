package com.example.proiectbibliotecaiss.domain;

public class Book {
    private int bookID;
    private String ISBN;
    private String title;
    private String author;
    private int year;
    private BookStatus status;

    public Book(int bookID, String ISBN, String title, String author, int year, BookStatus status) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
}
