package com.haydikodlayalim.pagination.service;

import com.haydikodlayalim.pagination.dto.BookDto;
import com.haydikodlayalim.pagination.model.Book;
import com.haydikodlayalim.pagination.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Standart Spring Pageable kullanımı.
     * Page objesi; mevcut sayfa verilerini, toplam sayfa sayısını (totalPages),
     * toplam kayıt sayısını (totalElements) ve sayfa numarasını içerir.
     */
    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * Page objesi üzerinden Entity -> DTO dönüşümü (Page.map kullanımı).
     */
    public Page<BookDto> getBooksAsDto(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(this::convertToDto);
    }

    /**
     * Slice kullanımı:
     * Toplam kayıt sayısını (COUNT) hesaplamaz; sadece bir sonraki sayfa var mı (hasNext) bakar.
     * Sonsuz kaydırma (infinite scroll) ve mobil uygulamalar için yüksek performans sağlar.
     */
    public Slice<Book> getBooksAsSlice(int minPageCount, Pageable pageable) {
        return bookRepository.findByPageCountGreaterThan(minPageCount, pageable);
    }

    /**
     * Manuel PageRequest oluşturma örneği.
     */
    public Page<Book> getBooksCustomPagination(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("DESC") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    /**
     * Yazara göre filtreleme ve sayfalama.
     */
    public Page<Book> getBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    private BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publishDate(book.getPublishDate())
                .pageCount(book.getPageCount())
                .build();
    }
}
