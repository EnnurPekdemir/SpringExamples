# Spring Boot RestTemplate Örneği

Bu modül, Spring Boot üzerinde **RestTemplate** kullanarak harici REST API'leri tüketme (consume) işlemlerini, HTTP GET, POST ve `exchange` metotlarının pratik kullanımını göstermektedir.

---

## 🧠 RestTemplate Nedir?

`RestTemplate`, Spring Framework tarafından sunulan ve harici RESTful web servislerine HTTP istekleri (GET, POST, PUT, DELETE vb.) göndermek ve yanıtları Java nesnelerine dönüştürmek için kullanılan senkron (synchronous) bir HTTP istemcisidir.

### Temel Metotlar:
- **`getForEntity()` / `getForObject()`:** Belirtilen URL'den veri çekmek (GET) için kullanılır. `getForEntity` HTTP durum kodu ve başlıkları içeren `ResponseEntity` dönerken, `getForObject` doğrudan gövdeyi (body) döner.
- **`postForEntity()` / `postForObject()`:** Belirtilen URL'e veri göndermek (POST) için kullanılır.
- **`exchange()`:** HTTP metodunu (`GET`, `POST`, `PUT`, `DELETE`), özel header'ları ve gövdeyi (`HttpEntity`) esnek bir şekilde belirlemenizi sağlar. `ParameterizedTypeReference` ile Generic List/Map dönüşümlerinde sıkça tercih edilir.

---

## 🛠️ Proje Yapısı

```
spring-boot-rest-template/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/HaydiKodlayalim/resttemplate/
    │   │   ├── SpringBootRestTemplateApplication.java
    │   │   ├── api/
    │   │   │   └── RestClientController.java
    │   │   ├── config/
    │   │   │   └── RestTemplateConfig.java
    │   │   └── model/
    │   │       └── UserDto.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/HaydiKodlayalim/resttemplate/
            └── SpringBootRestTemplateApplicationTests.java
```

---

## 🚀 Çalıştırma ve Test

### 1. Modülü Derleyin:
```powershell
.\mvnw.cmd clean package -pl spring-boot-rest-template -DskipTests
```

### 2. Uygulamayı Başlatın:
```powershell
.\mvnw.cmd spring-boot:run -pl spring-boot-rest-template
```

---

## 📝 Örnek Endpoint'ler ve İstekler

Bu örnek, varsayılan olarak harici JSONPlaceholder API'sini (`https://jsonplaceholder.typicode.com/users`) tüketmektedir.

### 1. Tüm Kullanıcıları Listele (GET - `exchange` Metodu ile):
```bash
curl http://localhost:8080/users
```

### 2. ID ile Kullanıcı Getir (GET - `getForEntity` Metodu ile):
```bash
curl http://localhost:8080/users/1
```

### 3. Yeni Kullanıcı Ekle (POST - `postForEntity` Metodu ile):
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ennur Pekdemir",
    "username": "ennur",
    "email": "ennur@example.com",
    "website": "example.com"
  }'
```
