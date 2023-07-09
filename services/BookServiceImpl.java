package javadasar.studycase.perpusoop.services;

import javadasar.studycase.perpusoop.entities.Book;
import javadasar.studycase.perpusoop.repositories.BookRepositoryImpl;

import java.util.List;

public class BookServiceImpl implements BookService{

    BookRepositoryImpl bookRepository;
    public BookServiceImpl(BookRepositoryImpl bookRepository){
        this.bookRepository = bookRepository;
    }

    public boolean bookSize(){
        List<Book> books = bookRepository.getAll();
        return books.isEmpty();
    }
    @Override
    public void showBook() {
        if (bookSize()){
            System.out.println("The Books In The Library Are Still Empty\n");
        }else {
            List<Book> books = bookRepository.getAll();
            for (var book : books){
                System.out.println("Book id: "+book.getId() + "\nBook title: " +book.getTitle() + "\nBook author: "+book.getAuthor());
                if (book.getIsItBorrowed()){
                    System.out.println("Book status: is borrowed");
                    System.out.println("====================");
                }else {
                    System.out.println("Book status: not borrowed");
                    System.out.println("====================");
                }
            }
        }
    }

    @Override
    public boolean addBook(Book book) {
        String id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();
        boolean isItBorrowed = book.getIsItBorrowed();

        Book books = new Book(id,title,author,isItBorrowed);
        return bookRepository.add(books);
    }

    @Override
    public boolean removeBook(String id) {
        if (bookSize()){
            return false;
        }
        boolean success = bookRepository.remove(id);
        return success;
    }

    @Override
    public Book[] searchBook(String title) {
        if (bookSize()){
            return null;
        }else {
            return bookRepository.search(title);
        }
    }

    @Override
    public Book borrowBook(String title, String id) {
       if (bookSize()){
           return new Book();
       }
       return bookRepository.borrow(title, id);
    }

    @Override
    public Book returnBook(String title, String id) {
        if (bookSize()){
            return null;
        }
        return bookRepository.returnBook(title,id);
    }

    @Override
    public boolean borrowedBooks() {
        if (bookSize()){
            return false;
        }
        var book = bookRepository.borrowed();
        if (book == null){
            return false;
        }
        for (var value : book){
            System.out.println("Book id: "+value.getId());
            System.out.println("Book title: "+value.getTitle());
            System.out.println("Book author: "+value.getAuthor());
            System.out.println("====================");
        }
        return true;
    }

    @Override
    public boolean notBorrowedBook() {
        if (bookSize()){
            return false;
        }
        var book = bookRepository.notBorrowed();
        if (book == null){
            return false;
        }
        for (var value : book){
            System.out.println("Book id: "+value.getId());
            System.out.println("Book title: "+value.getTitle());
            System.out.println("Book author: "+value.getAuthor());
            System.out.println("====================");
        }
        return true;
    }

    @Override
    public boolean totalBooks() {
        if (bookSize()){
            return false;
        }
        System.out.println("Total numbers of books in the library: " + bookRepository.getTotal());
        return true;
    }
}
