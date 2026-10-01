# Spring Boot Dockerization Örneği

Bu modül, bir Spring Boot 3.x uygulamasının Dockerize edilmesi adımlarını ve pratik kullanımını göstermektedir.

---

##  Proje Yapısı

- **Java Sürümü:** 17
- **Spring Boot Sürümü:** 3.3.4
- **Temel Bağımlılıklar:** `spring-boot-starter-web`, `spring-boot-starter-test`
- **Docker Base Image:** `eclipse-temurin:17-jdk-alpine`

---

## 🚀 Adım Adım Docker ile Çalıştırma

### 1. Uygulama Paketini (JAR) Oluşturma

Projeyi derleyip çalıştırılabilir JAR dosyasını `target/` klasörüne çıkarmak için ana dizinde şu komutu çalıştırın:

```powershell
.\mvnw.cmd clean package -pl spring-boot-dockerization -DskipTests
```

### 2. Docker Image'ını Derleme (Build)

Modül dizinine geçin ve Dockerfile üzerinden imajı oluşturun:

```powershell
cd spring-boot-dockerization
docker build -t spring-boot-dockerization:1.0 .
```

### 3. Docker Container'ı Başlatma (Run)

Oluşturulan imajı 8080 portunu dışarı açarak arka planda (`-d`) çalıştırın:

```powershell
docker run -d -p 8080:8080 --name spring-docker-app spring-boot-dockerization:1.0
```

---

## 🧪 Test Etme

Konteyner çalıştıktan sonra endpoint'i test edebilirsiniz:

- **URL:** [http://localhost:8080/mesaj](http://localhost:8080/mesaj)
- **HTTP Metodu:** `GET`
- **Beklenen Yanıt:**
  ```text
  Docker container içerisinden merhaba!
  ```

### cURL / PowerShell ile Test:
```powershell
curl http://localhost:8080/mesaj
# veya
Invoke-RestMethod -Uri "http://localhost:8080/mesaj"
```

---

## 🛠️ Yararlı Docker Komutları

| Komut | Açıklama |
|---|---|
| `docker ps` | Çalışan konteynerleri listeler |
| `docker logs -f spring-docker-app` | Uygulama loglarını canlı izler |
| `docker stop spring-docker-app` | Konteyneri durdurur |
| `docker rm -f spring-docker-app` | Konteyneri durdurup siler |
| `docker rmi spring-boot-dockerization:1.0` | Oluşturulan Docker imajını siler |
