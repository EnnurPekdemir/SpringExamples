package com.haydikodlayalim.pagination.repository;

import com.haydikodlayalim.pagination.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Slice<Book> findByPageCountGreaterThan(int pageCount, Pageable pageable);
}
