package javadasar.studycase.perpusoop.view;

import javadasar.studycase.perpusoop.entities.Book;
import javadasar.studycase.perpusoop.repositories.BookRepositoryImpl;
import javadasar.studycase.perpusoop.services.BookService;
import javadasar.studycase.perpusoop.services.BookServiceImpl;

public class TestBookView {

    static BookRepositoryImpl bookRepository = new BookRepositoryImpl();
    static BookService bookService = new BookServiceImpl(bookRepository);
    static BookView bookView = new BookView(bookService);

    static Book book0 = new Book("01","aku","jekson",true);
    static Book book1 = new Book("02","kau","ujang",false);
    static Book book01 = new Book("002","kau","ujang",false);
    static Book book2 = new Book("03","kita","udin",true);
    static Book book3 = new Book("04","kita","udin",true);
    static Book book4 = new Book("05","kita","udin",true);
    static Book book5 = new Book("06","kita","udin",true);
    static Book book6 = new Book("07","kita","udin",true);
    static Book book7 = new Book("08","kita","udin",true);


    public static void main(String[] args) {
        testViewApp();
    }

    public static void testViewApp(){
        bookView.viewApp();
    }
    public static void testShowBook(){
//        Book book0 = new Book("01","aku","jekson",true);
//        Book book1 = new Book("02","kau","ujang",false);
//        Book book2 = new Book("03","kita","udin",true);

        bookRepository.add(book0);
        bookRepository.add(book1);
        bookRepository.add(book2);
        bookView.showBook();
    }

    public static void testAddBook(){
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        BookService bookService = new BookServiceImpl(bookRepository);
//        BookView bookView = new BookView(bookService);

        bookView.addBook();
    }

    public static void testRemoveBook(){

        bookService.addBook(book0);
        bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookView.showBook();
        bookView.removeBook();

    }

    public static void testSearchBook(){

        bookService.addBook(book0);
        bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book01);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        bookService.addBook(book6);
        bookService.addBook(book7);
        bookView.showBook();

        bookView.searchBook();
    }

    public static void testBorrowBook(){
        bookService.addBook(book0);
        bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book01);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        bookService.addBook(book6);
        bookService.addBook(book7);
        bookView.showBook();

        bookView.borrowBook();
    }

    public static void testReturnBook(){
        bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);

        bookService.showBook();
        bookService.borrowBook("kau","02");
        bookService.showBook();
        bookView.returnBook();

    }

    public static void testBorrowed(){
      //  bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);

        bookView.borrowedBooks();
    }

    public static void testNoBorrowedBooks(){
        bookService.addBook(book0);
       // bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);

        bookView.notBorrowedBook();
    }

    public static void testTotal(){
        bookView.totalBooks();

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);

        bookView.totalBooks();
    }

}
