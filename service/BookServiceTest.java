package javadasar.studycase.service;

import javadasar.studycase.perpusoop.entities.Book;
import javadasar.studycase.perpusoop.repositories.BookRepositoryImpl;
import javadasar.studycase.perpusoop.services.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
//SEMUA ERROR YANG ADA DI UNIT TEST INI KARENA AKU MERUBAH DATA
//BOOK DI CLASS BookRepository MENJADI PRIVATE JANGAN HHIRAUKAN
   BookRepositoryImpl bookRepository;
   Book book0,book01, book1,book11, book2,book21, book3,book31, book4,book41,book42,book43,book44,book45,book46,book47,book48,book49,book40;
   @BeforeEach
   void setUp() {
      bookRepository = new BookRepositoryImpl();

      book0 = new Book("01","aku","jekson",true);
      book01 = new Book("011","aku","jekson",true);
      book1 = new Book("02","kau","ujang",false);
      book11 = new Book("021","kau","ujang",false);
      book2 = new Book("03","kita","udin",true);
      book21 = new Book("031","kita","udin",true);
      book3 = new Book("04","dia","ucok",false);
      book31 = new Book("041","dia","ucok",false);
      book4 = new Book("05","kami","ucup",true);
      book41 = new Book("051","kami","ucup",true);
      book42 = new Book("052","kami","ucup",true);
      book43 = new Book("053","kami","ucup",true);
      book44 = new Book("054","kami","ucup",true);
      book45 = new Book("055","kami","ucup",true);
      book46 = new Book("056","kami","ucup",true);
      book47 = new Book("057","kami","ucup",true);
      book48 = new Book("058","kami","ucup",true);
      book49 = new Book("059","kami","ucup",true);
      book40 = new Book("050","kami","ucup",true);

   }

   @Test
   void testGetAllRepository(){
       var value = bookRepository.getAll();

       bookRepository.add(book0);
       bookRepository.add(book1);
       bookRepository.add(book1);
       bookRepository.add(book2);
       bookRepository.add(book3);

       var value1 = bookRepository.getAll();
       assertNotNull(value1);


   }

   @Test
   void testShowBook(){

        assertTrue(bookRepository.books.isEmpty());
        assertEquals(0,bookRepository.books.size());

        bookRepository.books.add(book0);
        bookRepository.books.add(book1);
        bookRepository.books.add(book2);
        bookRepository.books.add(book3);
        bookRepository.books.add(book4);

        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        bookService.showBook();

        assertNotNull(bookRepository.books);
        assertEquals(5,bookRepository.books.size());
        assertFalse(bookRepository.books.isEmpty());

   }

   @Test
   void testAddAndUniqueIdBookRepository(){
      bookRepository.add(book0);
      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);

      BookServiceImpl bookService = new BookServiceImpl(bookRepository);
      bookService.showBook();

      assertEquals(4, bookRepository.books.size());
      assertTrue(bookRepository.uniqueId(book1));
      assertFalse(bookRepository.uniqueId(book4));

    }

   @Test
   void testAddBookService(){

      BookServiceImpl bookService = new BookServiceImpl(bookRepository);
      bookService.addBook(book0);
      bookService.addBook(book0);
      bookService.addBook(book1);
      bookService.addBook(book2);

      assertEquals(3, bookRepository.books.size());
      assertTrue(bookService.addBook(book3));
      assertFalse(bookService.addBook(book2));
      assertEquals(4, bookRepository.books.size());
      bookService.showBook();

   }

   @Test
    void testRemoveRepository(){

       BookServiceImpl bookService = new BookServiceImpl(bookRepository);

        bookService.addBook(book0);
        bookService.addBook(book1);
        bookService.addBook(book2);

        bookService.showBook();
        assertEquals(3, bookRepository.books.size());
        bookRepository.remove("01");
        bookService.showBook();
        assertEquals(2, bookRepository.books.size());
        assertTrue(bookRepository.remove("02"));
        assertFalse(bookRepository.remove("05"));
        assertEquals(1, bookRepository.books.size());

   }

   @Test
    void testRemoveBookService(){
       BookServiceImpl bookService = new BookServiceImpl(bookRepository);

       boolean notFount = bookService.removeBook("02");
       assertFalse(notFount);

       bookRepository.add(book0);
       bookRepository.add(book1);
       bookRepository.add(book2);

       bookService.showBook();

       boolean expect = bookService.removeBook("01");
       assertTrue(expect);
       boolean expect1 = bookService.removeBook("01");
       assertFalse(expect1);
       boolean failExpect = bookService.removeBook("04");
       assertFalse(failExpect);

       bookService.showBook();
   }

   @Test
    void testSearchRepository(){

       bookRepository.add(book0);
       bookRepository.add(book01);
       bookRepository.add(book1);
       bookRepository.add(book11);
       bookRepository.add(book2);
       bookRepository.add(book21);
       bookRepository.add(book3);
       bookRepository.add(book31);
       bookRepository.add(book4);
       bookRepository.add(book41);
       bookRepository.add(book42);
       bookRepository.add(book43);
       bookRepository.add(book44);
       bookRepository.add(book45);
       bookRepository.add(book46);
       bookRepository.add(book47);
       bookRepository.add(book48);
       bookRepository.add(book49);
       bookRepository.add(book40);

       var expect1 = bookRepository.search("kami");
       System.out.println(expect1);
       for (Book book : expect1) {
           if (book != null) {
               System.out.println(book.getTitle() + " " + book.getId());
           }
       }
       assertNotNull(expect1);
       var expect2 = bookRepository.search("kapan");
       assertNull(expect2);
   }

   @Test
    void testSearchBookService(){
       BookServiceImpl bookService = new BookServiceImpl(bookRepository);

       var expect1 = bookService.searchBook("kau");
       assertNull(expect1);

       bookRepository.add(book0);
       bookRepository.add(book01);
       bookRepository.add(book1);
       bookRepository.add(book11);
       bookRepository.add(book2);
       bookRepository.add(book21);
       bookRepository.add(book3);
       bookRepository.add(book31);
       bookRepository.add(book4);
       bookRepository.add(book41);
       bookRepository.add(book42);
       bookRepository.add(book43);
       bookRepository.add(book44);
       bookRepository.add(book45);
       bookRepository.add(book46);
       bookRepository.add(book47);
       bookRepository.add(book48);
       bookRepository.add(book49);
       bookRepository.add(book40);

       var expect = bookService.searchBook("kau");
       for (var boook : expect){
           System.out.println(boook.getTitle());
       }
       assertNotNull(expect);
       var expect2 = bookService.searchBook("kapan");
       assertNull(expect2);
   }

   @Test
    void testBorrowRepository(){

       bookRepository.add(book0);
       bookRepository.add(book01);
       bookRepository.add(book1);
       bookRepository.add(book11);
       bookRepository.add(book2);
       bookRepository.add(book21);
       bookRepository.add(book3);
       bookRepository.add(book31);
       bookRepository.add(book4);
       bookRepository.add(book41);
       bookRepository.add(book42);
       bookRepository.add(book43);
       bookRepository.add(book44);
       bookRepository.add(book45);
       bookRepository.add(book46);
       bookRepository.add(book47);
       bookRepository.add(book48);
       bookRepository.add(book49);
       bookRepository.add(book40);

       BookServiceImpl bookService = new BookServiceImpl(bookRepository);

       var expect = bookRepository.borrow("aku","01");
       assertNull(expect);

       var expect1 = bookRepository.borrow("kau","02");
       assertNotNull(expect1);
       bookService.showBook();
   }

   @Test
    void testBorrowBook(){
       bookRepository.add(book0);
       bookRepository.add(book01);
       bookRepository.add(book1);
       bookRepository.add(book11);
       bookRepository.add(book2);
       bookRepository.add(book21);
       bookRepository.add(book3);
       bookRepository.add(book31);
       bookRepository.add(book4);
       bookRepository.add(book41);
       bookRepository.add(book42);
       bookRepository.add(book43);
       bookRepository.add(book44);
       bookRepository.add(book45);
       bookRepository.add(book46);
       bookRepository.add(book47);
       bookRepository.add(book48);
       bookRepository.add(book49);
       bookRepository.add(book40);

       BookServiceImpl bookService = new BookServiceImpl(bookRepository);

       var expect = bookService.borrowBook("aku","01");
       assertNull(expect);

       var expect1 = bookService.borrowBook("kau","021");
       assertNotNull(expect1);

       bookService.showBook();
       var expect2 = bookService.borrowBook("kapan","091");
       System.out.println(expect2.getTitle());
       assertNull(expect2.getTitle());
   }

   @Test
    void testReturnBookRepository(){

       bookRepository.add(book0);
       bookRepository.add(book1);
       bookRepository.add(book1);
       bookRepository.add(book2);
       bookRepository.add(book3);
       bookRepository.add(book4);

       assertFalse(book1.getIsItBorrowed());
       BookServiceImpl bookService = new BookServiceImpl(bookRepository);
       var isBorrowed = bookRepository.borrow("kau", "02");
       assertTrue(isBorrowed.getIsItBorrowed());

       var isNotBorrowed = bookService.returnBook("kau","02");
       assertFalse(isNotBorrowed.getIsItBorrowed());
       assertNotNull(isNotBorrowed);
       assertSame(isBorrowed, isNotBorrowed);

       var returnNull = bookService.returnBook("kapan","02");
       assertNull(returnNull);
   }

   @Test
   void testBorrowedRepository(){

      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);
      bookRepository.add(book4);
      bookRepository.add(book40);
      bookRepository.add(book48);
      bookRepository.add(book49);

      var book = bookRepository.borrowed();
      assertNotNull(book);
      assertEquals(6, book.length);

      for (var value : book){
         System.out.println(value.getTitle());
      }
   }

   @Test
   void testBorrowedBookService(){
      BookServiceImpl bookService = new BookServiceImpl(bookRepository);
      var bo = bookService.borrowedBooks();
      assertFalse(bo);
      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);
      bookRepository.add(book4);
      bookRepository.add(book40);
      bookRepository.add(book48);
      bookRepository.add(book49);

      var book = bookService.borrowedBooks();
      assertTrue(book);

   }

   @Test
   void testNotBorrowed(){
      var bo = bookRepository.notBorrowed();
      assertNull(bo);
      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);
      bookRepository.add(book4);
      bookRepository.add(book40);
      bookRepository.add(book48);
      bookRepository.add(book49);

      var book = bookRepository.notBorrowed();
      assertNotNull(book);
      assertEquals(2, book.length);

      for (var value : book){
         System.out.println(value.getTitle());
      }
   }

   @Test
   void testNotBorrowedBook(){
      BookServiceImpl bookService = new BookServiceImpl(bookRepository);
      var bo = bookService.notBorrowedBook();
      assertFalse(bo);
      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);
      bookRepository.add(book4);
      bookRepository.add(book40);
      bookRepository.add(book48);
      bookRepository.add(book49);

      var book = bookService.notBorrowedBook();
      assertTrue(book);
   }

   @Test
   void testGetTotal(){
      var to = bookRepository.getTotal();
      assertEquals(0,to);

      bookRepository.add(book0);
      bookRepository.add(book1);
      bookRepository.add(book1);
      bookRepository.add(book2);
      bookRepository.add(book3);
      bookRepository.add(book4);
      bookRepository.add(book40);
      bookRepository.add(book48);
      bookRepository.add(book49);

      var total = bookRepository.getTotal();
      assertEquals(8,total);
   }

}
