# Spring Boot Pagination & Sorting (Sayfalama ve Sıralama)

Bu modül, Spring Data JPA ile veri tabanındaki büyük veri kümelerini verimli bir şekilde sayfalamak (`Page`, `Slice`) ve sıralamak (`Sort`) için örnek senaryolar içerir.

---

## 🚀 Özellikler

1. **Spring Data JPA `Pageable` & `Page<T>`**:
   - Toplam sayfa sayısı (`totalPages`), toplam kayıt sayısı (`totalElements`), sayfa numarası ve boyutu ile tam sayfalama desteği.
   - Sayfa elemanlarının DTO'ya dönüştürülmesi (`Page.map(...)`).

2. **Spring Data JPA `Slice<T>`**:
   - `COUNT(*)` sorgusu **çalıştırmadan** sonraki sayfanın olup olmadığını (`hasNext()`) kontrol eder.
   - Sonsuz kaydırma (Infinite Scroll) ve mobil liste görünümleri için yüksek performans sunar.

3. **Çoklu Sıralama (`Sort`)**:
   - Birden fazla alana göre artan (`ASC`) veya azalan (`DESC`) sıralama.

4. **Otomatik Örnek Veri (CommandLineRunner)**:
   - Uygulama ayağa kalktığında otomatik olarak 20 adet örnek kitap verisi yüklenir.

5. **H2 In-Memory Veri Tabanı ve Konsolu**:
   - Harici veri tabanı kurulumuna gerek kalmadan doğrudan çalıştırılabilir.

---

## 🛠️ API Uç Noktaları ve Örnek İstekler

### 1. Temel Sayfalama ve Sıralama (`Page<Book>`)
- **Tüm kitapları varsayılan sayfalama ile getir (Sayfa 0, Boyut 10):**
  ```http
  GET http://localhost:8080/books
  ```
- **1. sayfadan 5 adet getir, başlığa göre artan sırala:**
  ```http
  GET http://localhost:8080/books?page=0&size=5&sort=title,asc
  ```
- **Yayınlanma tarihine göre azalan, sonra ID'ye göre artan sırala:**
  ```http
  GET http://localhost:8080/books?page=0&size=5&sort=publishDate,desc&sort=id,asc
  ```

### 2. DTO Formatında Sayfalama (`Page<BookDto>`)
- **Kitapları DTO nesneleri olarak sayfala:**
  ```http
  GET http://localhost:8080/books/dto?page=0&size=5
  ```

### 3. Yüksek Performanslı Sayfalama (`Slice<Book>`)
- **Sayfa sayısı 200'den büyük kitapları `Slice` olarak getir:**
  ```http
  GET http://localhost:8080/books/slice?minPages=200&page=0&size=5
  ```

### 4. Özel Parametrelerle Sayfalama (`PageRequest`)
- **Manuel parametreler ile sayfalama:**
  ```http
  GET http://localhost:8080/books/custom?page=0&size=5&sortBy=author&direction=ASC
  ```

### 5. Yazar Filtresi ile Sayfalama
- **Yazar adında 'Tolkien' geçenleri sayfala:**
  ```http
  GET http://localhost:8080/books/by-author?author=Tolkien&page=0&size=5
  ```

---

## 📊 H2 Konsolu

- **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:paginationdb`
- **Username:** `sa`
- **Password:** *(boş bırakın)*

---

## 🧪 Derleme ve Çalıştırma

```bash
# Projeyi derlemek için:
./mvnw clean package -pl spring-boot-pagination

# Uygulamayı başlatmak için:
./mvnw spring-boot:run -pl spring-boot-pagination
```
