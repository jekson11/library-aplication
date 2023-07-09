package javadasar.studycase.perpusoop.view;

import javadasar.studycase.perpusoop.entities.Book;
import javadasar.studycase.perpusoop.services.BookService;
import javadasar.studycase.perpusoop.util.InputUtil;

public class BookView {
    private BookService bookService;
    public BookView(BookService bookService){
        this.bookService = bookService;
    }

    public void viewApp(){

        System.out.println("\n===========JACKSON LIBRARY===========");
        while (true){
            System.out.println("1. Show Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. ReturnBook");
            System.out.println("7. Borrowed Books");
            System.out.println("8. List Of Not Borrowed Books");
            System.out.println("9. Total Books");
            System.out.println("x. For Exit");
            System.out.println("=====================================\n");

            String userInput = InputUtil.input ("Do pick the number(x) for exit");
            if (userInput.equalsIgnoreCase("x")){
                System.out.println("Logout");
                return;
            }
            switch(userInput){
                case "1" -> showBook();
                case "2" -> addBook();
                case "3" -> removeBook();
                case "4" -> searchBook();
                case "5" -> borrowBook();
                case "6" -> returnBook();
                case "7" -> borrowedBooks();
                case "8" -> notBorrowedBook();
                case "9" -> totalBooks();
                default -> System.out.println("Cannot Understand The Commend!!\n");

            }

        }
    }

    public void showBook(){
        System.out.println("\n===========LIST OF BOOKS===========");
        bookService.showBook();
    }
    public void addBook(){
        String next = InputUtil.next();
        if (next.equalsIgnoreCase("y")){
            System.out.println("\n===========ADD BOOKS===========");
            while (true){
                String bookId = InputUtil.input("Enter The Book Id");
                String bookTitle = InputUtil.input("Enter The Book Title");
                String bookAuthor = InputUtil.input("Enter The Book Author");

                Book newBook = new Book(bookId, bookTitle, bookAuthor, false);
                boolean successes = bookService.addBook(newBook);
                if (successes){
                    System.out.println("\nSuccessfully added book");
                    showBook();
                    if (InputUtil.keepAddingM()){
                        System.out.println("\nContinue adding books\n");
                    }else {
                        return;
                    }
                }else {
                    System.out.println("\nUnable to add book with same id\n");
                }
            }
        }else if (next.equalsIgnoreCase("n")){
            System.out.println("\n===========JACKSON LIBRARY===========");
        }else {
            System.out.println(next);
        }
    }
    public void removeBook(){
        System.out.println("\n===========DELETE BOOKS===========");
        while (true){

            String id = InputUtil.input("Enter id you want to remove (x to cancel)");

            if (id.equalsIgnoreCase("x")){
                return;
            }
            boolean success = bookService.removeBook(id);
            if (success){
                System.out.println("\nSuccessful delete book");
                showBook();
            }else {
                System.out.println("Failed to delete, because id not found!!\n");
            }
        }
    }
    public void searchBook(){
        System.out.println("\n===========SEARCH BOOKS===========");
        while (true){
            var title = InputUtil.input("Enter Book Title (x to cancel)");
            if (title.equalsIgnoreCase("x")){
                System.out.println("exit");
                return;
            }else {
                var success = bookService.searchBook(title);
                if (success != null){
                    for (var book : success){
                        System.out.println("Book id: "+book.getId());
                        System.out.println("Book title: "+book.getTitle());
                        System.out.println("Book author: "+book.getAuthor());

                        if (book.getIsItBorrowed()){
                            System.out.println("Book status is: Borrowed");
                            System.out.println("======================\n");
                        }else {
                            System.out.println("Book status is: Not borrowed");
                            System.out.println("======================\n");
                        }
                    }
                }else {
                    System.out.println("Cant not to find the book, because the title is not found!!\n");
                }
            }
        }
    }
    public void borrowBook(){
        System.out.println("\n===========BORROW BOOKS===========");

        while (true){
            var continueBorrow = InputUtil.input("(x) to exit (continue) to continue");
            if (continueBorrow.equalsIgnoreCase("x")) {
                System.out.println("exit\n");
                return;
            }else if (continueBorrow.equalsIgnoreCase("continue")){
                String id = InputUtil.input("Enter book id ");
                String title = InputUtil.input("Enter book title ");
                var success = bookService.borrowBook(title, id);
                if (success == null){
                    System.out.println("Failed to borrow the book, because the book is being borrowed\n");
                }else if (success.getTitle() == null){
                    System.out.println("Could not find the book you are looking for\n");
                }else {
                    System.out.println("\nSuccessful borrow book");
                    System.out.println("Book id: "+success.getId());
                    System.out.println("Book title: "+success.getTitle());
                    System.out.println("Book author: "+success.getAuthor());
                    if (success.getIsItBorrowed()){
                        System.out.println("Book Status: Is borrowed");
                        System.out.println("====================");
                    }else {
                        System.out.println("Book Status: Is not borrowed");
                        System.out.println("====================");
                    }
                }
            }else {
                System.out.println("Can not understand the command\n");
            }
        }
    }
    public void returnBook(){
        System.out.println("\n===========RETURNING LOAN BOOKS===========");

        while (true){
            var returnBook = InputUtil.input("(x) to exit (continue) to continue");
            if (returnBook.equalsIgnoreCase("x")) {
                System.out.println("exit");
                return;
            } else if (returnBook.equalsIgnoreCase("continue")){
                String id = InputUtil.input("Enter book id ");
                String title = InputUtil.input("Enter book title ");
                var success = bookService.returnBook(title, id);

                if (success == null){
                    System.out.println("Failed return the book, because the book was not borrowed\n");
                }else if (success.getTitle() == null){
                    System.out.println("Unable to return book because, The book is not available\n");
                } else {
                    System.out.println("\nSuccessful return book");
                    System.out.println("Book id: "+success.getId());
                    System.out.println("Book title: "+success.getTitle());
                    System.out.println("Book author: "+success.getAuthor());
                    if (success.getIsItBorrowed()){
                        System.out.println("Book Status: Is borrowed");
                        System.out.println("====================");
                    }else {
                        System.out.println("Book Status: Is not borrowed");
                        System.out.println("====================");
                    }
                }

            }else {
                System.out.println("can not understand the command");
            }
        }
    }
    public void borrowedBooks(){
        System.out.println("\n===========LIST BORROWED BOOKS===========");
        var borrowedBooks = bookService.borrowedBooks();
        if (!borrowedBooks){
            System.out.println("No books to borrow\n");
        }
    }
    public void notBorrowedBook(){
        System.out.println("\n===========LIST NOT BORROWED BOOKS===========");
        var borrowedBooks = bookService.notBorrowedBook();
        if (!borrowedBooks){
            System.out.println("No books available\n");
        }
    }
    public void totalBooks(){
        var success = bookService.totalBooks();
        if (!success){
            System.out.println("There are no books in the library\n");
        }
    }
}
