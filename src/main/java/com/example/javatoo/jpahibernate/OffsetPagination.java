package com.example.javatoo.jpahibernate;
/*
import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/authors/{page}/{size}")
    public Page<Author> fetchAuthors(@PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNextPage(page, size);
    }

    @GetMapping("/authorsByGenre/{page}/{size}")
    public Page<Author> fetchAuthorsByGenre(@PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNextPageByGenre(page, size);
    }

    @GetMapping("/authorsByGenreExplicitCount/{page}/{size}")
    public Page<Author> fetchAuthorsByGenreExplicitCount(@PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNextPageByGenreExplicitCount(page, size);
    }

    @GetMapping("/authorsByGenreNative/{page}/{size}")
    public Page<Author> fetchAuthorsByGenreNative(@PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNextPageByGenreNative(page, size);
    }

    @GetMapping("/authorsByGenreNativeExplicitCount/{page}/{size}")
    public Page<Author> fetchAuthorsByGenreNativeExplicitCount(@PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNextPageByGenreNativeExplicitCount(page, size);
    }

    @GetMapping("/authors")
    // Request example: http://localhost:8080/authors?page=1&size=3&sort=name,desc
    public Page<Author> fetchAuthors(Pageable pageable) {

        return bookstoreService.fetchNextPagePageable(pageable);
    }
}

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", age=" + age
                + ", name=" + name + ", genre=" + genre + '}';
    }
}

import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenre(String genre, Pageable pageable);

    @Query(value = "SELECT a FROM Author a WHERE a.genre = ?1",
            countQuery = "SELECT COUNT(*) FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenreExplicitCount(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE genre = ?1",
            nativeQuery = true)
    public Page<Author> fetchByGenreNative(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE genre = ?1",
            countQuery = "SELECT COUNT(*) FROM author WHERE genre = ?1",
            nativeQuery = true)
    public Page<Author> fetchByGenreNativeExplicitCount(String genre, Pageable pageable);
}

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> fetchNextPage(int page, int size) {

        return authorRepository.findAll(PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenre(int page, int size) {

        return authorRepository.fetchByGenre("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreExplicitCount("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreNative(int page, int size) {

        return authorRepository.fetchByGenreNative("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenreNativeExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreNativeExplicitCount("History",
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPagePageable(Pageable pageable) {

        return authorRepository.findAll(pageable);
    }
}

 */
public class OffsetPagination {
}
