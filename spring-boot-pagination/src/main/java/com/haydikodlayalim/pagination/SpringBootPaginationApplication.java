package com.haydikodlayalim.pagination;

import com.haydikodlayalim.pagination.model.Book;
import com.haydikodlayalim.pagination.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPaginationApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(BookRepository bookRepository) {
        return args -> {
            
            List<Book> sampleBooks = Arrays.asList(
                    Book.builder().title("1984").author("George Orwell").isbn("978-0451524935").publishDate(LocalDate.of(1949, 6, 8)).pageCount(328).build(),
                    Book.builder().title("Animal Farm").author("George Orwell").isbn("978-0451526342").publishDate(LocalDate.of(1945, 8, 17)).pageCount(144).build(),
                    Book.builder().title("To Kill a Mockingbird").author("Harper Lee").isbn("978-0060935467").publishDate(LocalDate.of(1960, 7, 11)).pageCount(336).build(),
                    Book.builder().title("The Great Gatsby").author("F. Scott Fitzgerald").isbn("978-0743273565").publishDate(LocalDate.of(1925, 4, 10)).pageCount(180).build(),
                    Book.builder().title("Brave New World").author("Aldous Huxley").isbn("978-0060850524").publishDate(LocalDate.of(1932, 1, 1)).pageCount(288).build(),
                    Book.builder().title("The Catcher in the Rye").author("J.D. Salinger").isbn("978-0316769488").publishDate(LocalDate.of(1951, 7, 16)).pageCount(277).build(),
                    Book.builder().title("The Hobbit").author("J.R.R. Tolkien").isbn("978-0547928227").publishDate(LocalDate.of(1937, 9, 21)).pageCount(310).build(),
                    Book.builder().title("The Fellowship of the Ring").author("J.R.R. Tolkien").isbn("978-0547928210").publishDate(LocalDate.of(1954, 7, 29)).pageCount(423).build(),
                    Book.builder().title("The Two Towers").author("J.R.R. Tolkien").isbn("978-0547928203").publishDate(LocalDate.of(1954, 11, 11)).pageCount(352).build(),
                    Book.builder().title("The Return of the King").author("J.R.R. Tolkien").isbn("978-0547928197").publishDate(LocalDate.of(1955, 10, 20)).pageCount(416).build(),
                    Book.builder().title("Crime and Punishment").author("Fyodor Dostoevsky").isbn("978-0486415871").publishDate(LocalDate.of(1866, 1, 1)).pageCount(430).build(),
                    Book.builder().title("The Brothers Karamazov").author("Fyodor Dostoevsky").isbn("978-0374528379").publishDate(LocalDate.of(1880, 11, 1)).pageCount(796).build(),
                    Book.builder().title("War and Peace").author("Leo Tolstoy").isbn("978-1400079988").publishDate(LocalDate.of(1869, 1, 1)).pageCount(1225).build(),
                    Book.builder().title("Anna Karenina").author("Leo Tolstoy").isbn("978-0143035008").publishDate(LocalDate.of(1877, 4, 1)).pageCount(864).build(),
                    Book.builder().title("The Metamorphosis").author("Franz Kafka").isbn("978-0486290386").publishDate(LocalDate.of(1915, 10, 1)).pageCount(60).build(),
                    Book.builder().title("The Trial").author("Franz Kafka").isbn("978-0805209990").publishDate(LocalDate.of(1925, 4, 26)).pageCount(255).build(),
                    Book.builder().title("Clean Code").author("Robert C. Martin").isbn("978-0132350884").publishDate(LocalDate.of(2008, 8, 1)).pageCount(464).build(),
                    Book.builder().title("Clean Architecture").author("Robert C. Martin").isbn("978-0134494166").publishDate(LocalDate.of(2017, 9, 20)).pageCount(432).build(),
                    Book.builder().title("Refactoring").author("Martin Fowler").isbn("978-0134757599").publishDate(LocalDate.of(2018, 11, 30)).pageCount(448).build(),
                    Book.builder().title("Design Patterns").author("Erich Gamma").isbn("978-0201633610").publishDate(LocalDate.of(1994, 10, 21)).pageCount(395).build()
            );

            bookRepository.saveAll(sampleBooks);
            System.out.println("20 sample books have been loaded into database for pagination testing.");
        };
    }
}
