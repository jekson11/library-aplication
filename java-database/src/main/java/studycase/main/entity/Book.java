package studycase.main.entity;

import java.util.Objects;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private boolean isBorrow;

    public Book(){

    }
    public Book(String title, String author, boolean isBorrow){
        this.title = title;
        this.author = author;
        this.isBorrow = isBorrow;
    }

    public Book(Integer id, String title, String author, boolean isBorrow) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrow = isBorrow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return isBorrow() == book.isBorrow() && Objects.equals(getId(), book.getId()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthor(), isBorrow());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isBorrow=" + isBorrow +
                '}';
    }
}
