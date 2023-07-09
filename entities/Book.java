package javadasar.studycase.perpusoop.entities;

public class Book {
    private String id, title, author;
    private boolean isItBorrowed;
    Book book;

    public Book(){

    }

    public Book(String id, String title, String author, boolean isItBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isItBorrowed = isItBorrowed;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean getIsItBorrowed() {
        return isItBorrowed;
    }

    public void setItBorrowed(boolean itBorrowed) {
        isItBorrowed = itBorrowed;
    }
}
