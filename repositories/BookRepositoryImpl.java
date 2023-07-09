package javadasar.studycase.perpusoop.repositories;

import javadasar.studycase.perpusoop.entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookRepositoryImpl implements BookRepository{

    public List<Book> books = new ArrayList<>();
    public boolean uniqueId(Book book){;
        return  books.stream().anyMatch(item ->
                Objects.equals(item.getId(), book.getId()));
    }
    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public boolean add(Book book) {
        if (uniqueId(book)){
            return false;
        }
        books.add(book);
        return true;
    }

    @Override
    public boolean remove(String idBook) {
        boolean match = books.stream().anyMatch(id ->
                Objects.equals(id.getId(), idBook));
        if (match){
            books.removeIf(value -> Objects.equals(value.getId(), idBook));
            return true;
        }
        return false;
    }

    public int getArrayLength(String title){
        int temp=0;
        for (var book : books){
            if (book.getTitle().equalsIgnoreCase(title)){
                temp++;
            }
        }
        return temp;
    }
    @Override
    public Book[] search(String title) {
        int temp = getArrayLength(title);
        int i=0;
       if (temp == 0){
           return null;
       }

       Book[] value = new Book[temp];
        for (var book : books){
            if (book.getTitle().equalsIgnoreCase(title)){
                value[i] = book;
                i++;
            }
        }
        return value;
    }

    public boolean isMatchingBook(Book book, String title, String id){
        return book.getTitle().equalsIgnoreCase(title) && book.getId().equalsIgnoreCase(id);
    }

    @Override
    public Book borrow(String title, String id) {
        for (var book : books){
            if (isMatchingBook(book, title, id)){
                if (book.getIsItBorrowed()){
                    return null;
                }else {
                    book.setItBorrowed(true);
                    return book;
                }
            }
        }
        return new Book();
    }

    @Override
    public Book returnBook(String title, String id) {
        for (var book : books){
            if (isMatchingBook(book,title,id)){
                if (book.getIsItBorrowed()){
                    book.setItBorrowed(false);
                    return book;
                }else {
                    return null;
                }
            }
        }
        return new Book();
    }

    @Override
    public Book[] borrowed() {
        var temp = 0;
        var i = 0;
        for (var book : books){
            if (book.getIsItBorrowed()){
                temp++;
            }
        }
        Book[] value = new Book[temp];
        if (value.length == 0){
            return null;
        }
        for (var book : books){
            if (book.getIsItBorrowed()){
                value[i] = book;
                i++;
            }
        }
        return value;
    }

    @Override
    public Book[] notBorrowed() {
        var temp = 0;
        var i = 0;
        for (var book : books){
            if (!book.getIsItBorrowed()){
                temp++;
            }
        }
        Book[] value = new Book[temp];
        if (value.length == 0){
            return null;
        }
        for (var book : books){
            if (!book.getIsItBorrowed()){
                value[i] = book;
                i++;
            }
        }
        return value;
    }

    @Override
    public int getTotal() {
        return books.size();
    }
}
