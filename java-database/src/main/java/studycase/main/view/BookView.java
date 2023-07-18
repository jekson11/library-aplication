package studycase.main.view;

import studycase.main.service.BookService;
import studycase.main.utils.DataUtil;
import java.util.Scanner;

public class BookView {
    private final BookService bookService;
    private static final Scanner scan = new Scanner(System.in);
    public BookView(BookService bookService) {
        this.bookService = bookService;
    }

    public void viewApp(){
        while(true){
            System.out.println("===============================");
            System.out.println("1. Insert Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book By Id");
            System.out.println("4. Search Book By Title");
            System.out.println("5. Get All Book");
            System.out.println("6. Update Book");
            System.out.println("7. borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Borrowed Book");
            System.out.println("10. Not Borrowed Book");
            System.out.println("11. Book Totals");
            System.out.println("===============================");

            String userInput = DataUtil.input("Choose the number (x) for exit");
            if (userInput.equalsIgnoreCase("x")){
                System.out.println("Logout..");
                return;
            }

            switch (userInput){
                case "1" -> insertBook();
                case "2" -> removeBook();
                case "3" -> searchBookById();
                case "4" -> searchBookByTitle();
                case "5" -> getAllBook();
                case "6" -> updateBook();
                case "7" -> borrowBook();
                case "8" -> returnBook();
                case "9" -> borrowedBook();
                case "10" -> notBorrowedBook();
                case "11" -> bookTotals();
            }
        }
    }

    public void insertBook(){
        System.out.println("===============================");
        System.out.println("Insert Book");
        System.out.println("===============================");

        while (true){
            String condition = DataUtil.input("Continue to insert data? (x) for exit");
            if (condition.equalsIgnoreCase("x")){
                return;
            }
            String title = DataUtil.input("Title").trim();
            String author = DataUtil.input("Author").trim();

            String value = bookService.insertBook(title, author);
            if (value.equalsIgnoreCase("Data Valid")){
                System.out.println(value + ", Successful insert book");
            }else {
                System.out.println(value + ", Can not insert null data");
            }
        }
    }
    public void removeBook(){
        System.out.println("===============================");
        System.out.println("Remove Book");
        System.out.println("===============================");

        while (true){
            String input = DataUtil.input("Continue to remove data (x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }
            System.out.print("Id book: ");
            int id = scan.nextInt();
            String massage = bookService.removeBook(id);
            System.out.println(massage);
        }
    }
    public void searchBookById(){
        System.out.println("===============================");
        System.out.println("Search Book By Id");
        System.out.println("===============================");

        while (true){
            String input = DataUtil.input("(x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }
            System.out.print("Enter Id book :");
            int id = scan.nextInt();
            String book = bookService.findBookById(id);
            System.out.println(book);
        }
    }
    public void searchBookByTitle(){
        System.out.println("===============================");
        System.out.println("Search Book By Title");
        System.out.println("===============================");

        while (true){
            String input = DataUtil.input("Enter Title book (x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }
            bookService.findBookByTitle(input);
        }
    }
    public void getAllBook(){
        System.out.println("===============================");
        System.out.println("Get All Book");
        System.out.println("===============================");

        bookService.findBookAll();
    }
    public void borrowBook(){
        System.out.println("===============================");
        System.out.println("Borrow Book");
        System.out.println("===============================");

        while (true){
            String input = DataUtil.input("Continue to borrow book (x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }

            System.out.print("Enter book id: ");
            int id = scan.nextInt();
            String title = DataUtil.input("Enter book title");

            boolean success = bookService.borrowBook(id, title);
            if (success){
                System.out.println("Changes are saved now, The book is borrowed");
            }else {
                System.out.println("Book not found, or is being borrowed");
            }
        }
    }
    public void returnBook(){
        System.out.println("===============================");
        System.out.println("Return Book");
        System.out.println("===============================");

        while (true){
            String input = DataUtil.input("Continue to return book (x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }

            System.out.print("Enter book id: ");
            int id = scan.nextInt();
            String title = DataUtil.input("Enter book title");

            boolean success = bookService.returnBook(id, title);
            if (success){
                System.out.println("The book is returned, The data is saved");
            }else {
                System.out.println("Book not found, or the book is not being borrowed");
            }
        }
    }
    public void updateBook(){
        System.out.println("===============================");
        System.out.println("Update Book");
        System.out.println("===============================");

        while(true){
            String input = DataUtil.input("Continue to update data (x)for exit");
            if (input.equalsIgnoreCase("x")){
                return;
            }

            System.out.print("Enter book id: ");
            int id = scan.nextInt();
            String update = DataUtil.input("What will update");
            String data = DataUtil.input("Update Data");

            String message = bookService.updateBook(id, update, data);
            System.out.println(message);
        }
    }
    public void borrowedBook(){
        System.out.println("===============================");
        System.out.println("List of borrowed book");
        System.out.println("===============================");

        bookService.borrowedBook();
    }
    public void notBorrowedBook(){
        System.out.println("===============================");
        System.out.println("List of not borrowed book");
        System.out.println("===============================");

        bookService.notBorrowedBook();
    }
    public void bookTotals(){
        System.out.println("===============================");
        int totals = bookService.bookTotals();
        System.out.println("Book Totals: " + totals);
        System.out.println("===============================");
    }

}
