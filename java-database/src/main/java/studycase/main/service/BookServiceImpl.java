package studycase.main.service;

import studycase.main.entity.Book;
import studycase.main.repository.BookRepositoryImpl;
import studycase.main.utils.DataUtil;
import java.util.List;

public class BookServiceImpl implements BookService{
    private final BookRepositoryImpl bookRepository;
    private static final String NO_BOOKS_MESSAGE = "There are no books in the library";
    private static final String UPDATE_SUCCESS_MESSAGE = "Successful updating of the book";
    private static final String BOOK_NOT_FOUND_MESSAGE = "Book with id: %d is not found";
    private static final String COMMENT_NOT_RECOGNIZED_MESSAGE = "Comment not recognized: %s";

    public BookServiceImpl(BookRepositoryImpl bookRepository){
        this.bookRepository = bookRepository;
    }
    @Override
    public String insertBook(String title, String author){
        if (DataUtil.isNull(title, author)){
            return "Invalid data";
        }else {
            Book book = new Book(title, author, false);
            bookRepository.insert(book);
            return "Data valid";
        }
    }

    @Override
    public String removeBook(int id) {
        if (bookTotals() != 0){
            boolean success = bookRepository.remove(id);
            if (success){
                return "Successful remove book";
            }else {
                return "Book not found";
            }
        }else {
            return "There are no book in library";
        }
    }

    @Override
    public String findBookById(int id) {
        Book byId = bookRepository.findById(id);
        if (byId == null){
            return "Book not found";
        }else if (byId.getTitle() == null){
            return "There ara no book in library";
        }else {
            System.out.println(byId);
            return "Book found";
        }
    }

    @Override
    public void findBookByTitle(String title) {
        if (bookTotals() == 0){
            System.out.println("There are no book in library");
        }else {
            List<Book> books = bookRepository.findByTitle(title);
            if (books.isEmpty()){
                System.out.println("Book not found");
            }else {
                books.forEach(System.out::println);
                System.out.println("Book found");
            }
        }
    }

    @Override
    public void findBookAll(){
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()){
            System.out.println("There are no book in library");
        }else {
            books.forEach(System.out::println);
        }
    }

    @Override
    public String updateBook(int id, String update, String data) {
        if (DataUtil.isValid(update)){
            if (bookTotals() == 0){
                return NO_BOOKS_MESSAGE;
            }else {
                boolean success = bookRepository.update(id, update, data);
                if (success){
                    return UPDATE_SUCCESS_MESSAGE;
                }else {
                    return String.format(BOOK_NOT_FOUND_MESSAGE,id);
                }
            }
        }
        return String.format(COMMENT_NOT_RECOGNIZED_MESSAGE,update);
    }

    @Override
    public boolean borrowBook(Integer id, String title) {
        boolean exist = DataUtil.isExist(id, title);
        if (exist){
            boolean success = bookRepository.borrow(id, title);
            return success;
        }else {
            return false;
        }
    }

    @Override
    public boolean returnBook(int id, String title) {
        boolean exist = DataUtil.isExist(id, title);
        if (exist){
            boolean success = bookRepository.returnBook(id, title);
            return success;
        }else {
            return false;
        }
    }

    @Override
    public void borrowedBook() {
        List<Book> books = bookRepository.borrowedBook();
        if (books.isEmpty()){
            System.out.println("No books to borrow");
        }
        books.forEach(System.out::println);
    }

    @Override
    public void notBorrowedBook() {
        List<Book> books = bookRepository.notBorrowedBook();
        if (books.isEmpty()){
            System.out.println("No books available");
        }else {
            books.forEach(System.out::println);
        }
    }
    @Override
    public int bookTotals() {
        return bookRepository.bookTotals();
    }
}
