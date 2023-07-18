package studycase.main.app;

import com.zaxxer.hikari.HikariDataSource;
import studycase.main.repository.BookRepositoryImpl;
import studycase.main.service.BookService;
import studycase.main.service.BookServiceImpl;
import studycase.main.utils.ConnectionUtil;
import studycase.main.view.BookView;

public class BookApp {
    private static final HikariDataSource dataSource = ConnectionUtil.getDataSource();
    private static final BookRepositoryImpl bookRepository = new BookRepositoryImpl(dataSource);
    private static final BookService bookService = new BookServiceImpl(bookRepository);
    private static final BookView bookView = new BookView(bookService);

    public static void main(String[] args) {

        bookView.viewApp();

    }
}
