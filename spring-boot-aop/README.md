# Spring Boot AOP (Aspect Oriented Programming) Örneği

Bu modül, Spring Boot üzerinde **Aspect Oriented Programming (AOP)** kavramlarını, temel bileşenlerini ve Advice türlerinin pratik kullanımını göstermektedir.

---

## 🧠 AOP Nedir?

AOP (Aspect Oriented Programming), uygulamanızın ana iş mantığını (Business Logic) kesen ve birden çok yerde tekrar eden çapraz kesişen işlevleri (Cross-Cutting Concerns) modüler hale getirmeye yarayan programlama yaklaşımıdır.

### Yaygın Kullanım Alanları:
- **Logging (Kayıt tutma)**
- **Security & Authorization (Güvenlik ve Yetkilendirme)**
- **Performance Measurement (Performans & Süre Ölçümü)**
- **Transaction Management (İşlem Yönetimi)**
- **Caching (Önbellekleme)**
- **Exception Handling (Hata Yönetimi)**

---

## 🔑 Temel AOP Terimleri

| Terim | Açıklama |
|---|---|
| **Aspect** | Çapraz kesen fonksiyonelliğin (Cross-Cutting Concern) tanımlandığı sınıftır (`@Aspect`). |
| **JoinPoint** | Programın akışında araya girilebilecek spesifik bir nokta (genellikle metot çağrısı). |
| **Pointcut** | Hangi JoinPoint'lerde Advice'ın çalışacağını belirten ifade (Ör: `execution(* com.HaydiKodlayalim.aop.service.*.*(..))`). |
| **Advice** | Belirlenen Pointcut noktalarında çalıştırılacak olan asıl eylem/kod parçası. |
| **Weaving** | Aspect kodlarının hedef nesnelerle çalışma anında (veya derleme anında) birleştirilme işlemi. |

---

## 🎯 Advice Türleri

Bu projede [`ServiceAspect.java`](src/main/java/com/HaydiKodlayalim/aop/aspect/ServiceAspect.java) sınıfında aşağıdaki Advice türleri örneklendirilmiştir:

1. **`@Before`**: Hedef metot çalıştırılmadan **hemen önce** devreye girer.
2. **`@After`**: Hedef metot çalıştıktan sonra (başarılı veya hatalı fark etmeksizin - `finally` bloğu gibi) devreye girer.
3. **`@AfterReturning`**: Hedef metot **başarıyla** tamamlanıp bir değer döndürdüğünde devreye girer ve dönen değeri yakalayabilir.
4. **`@AfterThrowing`**: Hedef metot bir **hata (Exception)** fırlattığında devreye girer ve hatayı yakalayabilir.
5. **`@Around`**: Metodun hem öncesini hem sonrasını sarmalar; metodun çalıştırılma kararını, çalışma süresini veya dönen değerini kontrol edebilir (`ProceedingJoinPoint`).

---

## 🚀 Çalıştırma ve Test

### 1. Modülü Derleyin:
```powershell
.\mvnw.cmd clean package -pl spring-boot-aop -DskipTests
```

### 2. Uygulamayı Başlatın:
```powershell
.\mvnw.cmd spring-boot:run -pl spring-boot-aop
```

### 3. Normal Çağrı Testi (Başarılı Akış):
```powershell
curl "http://localhost:8080/mesaj?param=merhaba"
```
**Konsol Çıktısı:**
```text
[AOP @Around] Metot çağrısı öncesi - mesaj
-> IkinciMesajService calisti: merhaba
[AOP @Around] Metot çağrısı bitti. Geçen süre: 1 ms
[AOP @Before] mesajVer() metodundan önce yakalandı.
  Parametreler: [merhaba]
  Hedef: MesajService
-> Metot calisti. Parametre: merhaba
[AOP @AfterReturning] Metot başarıyla döndü. Dönen Değer: Mesaj: merhaba
[AOP @After] mesajVer() metodu tamamlandıktan sonra çalıştı.
```

### 4. Hata Senaryosu Testi:
```powershell
curl "http://localhost:8080/mesaj?param=hata"
```
**Konsol Çıktısı:**
```text
[AOP @Before] mesajVer() metodundan önce yakalandı.
  Parametreler: [hata]
  Hedef: MesajService
-> Metot calisti. Parametre: hata
[AOP @AfterThrowing] Metot hata fırlattı! Hata mesajı: Hata parametresi gönderildi!
[AOP @After] mesajVer() metodu tamamlandıktan sonra çalıştı.
```
