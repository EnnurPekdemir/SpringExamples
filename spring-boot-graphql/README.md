# Spring Boot GraphQL Örneği

Bu modül, Spring Boot üzerinde **Spring for GraphQL** kullanımını, Schema (`schema.graphqls`), Query ve Mutation operasyonlarını ve GraphiQL arayüzü ile test edilmesini göstermektedir.

---

## 🧠 GraphQL Nedir?

GraphQL, API'ler için bir sorgu dili (Query Language) ve mevcut verilerinizle bu sorguları yerine getirmek için bir çalışma zamanıdır (Runtime). REST API'lerdeki over-fetching (ihtiyaçtan fazla veri alma) ve under-fetching (yetersiz veri sebebiyle çoklu istek yapma) sorunlarını ortadan kaldırır.

### Temel Özellikler:
- **İstemci Odaklı Sorgulama:** İstemci (Client) tam olarak hangi alanlara ihtiyacı varsa sadece onları talep eder.
- **Tek Endpoint:** Tüm sorgu ve mutasyonlar genellikle tek bir endpoint (`/graphql`) üzerinden yürütülür.
- **Güçlü Tip Sistemi (Schema):** Veri modelleri ve operasyonlar `.graphqls` şema dosyalarında tanımlanır.
- **GraphiQL Arayüzü:** Tarayıcı üzerinden şemayı inceleme, sorguları otomatik tamamlama ve test etme imkanı sunar.

---

## 🔑 Temel GraphQL Kavramları

| Terim | Açıklama |
|---|---|
| **Schema (`.graphqls`)** | API'nin desteklediği tipleri, sorguları (Query) ve mutasyonları (Mutation) tanımlayan sözleşmedir. |
| **Query** | Veri okuma ve listeleme operasyonları (REST'teki `GET` karşılığı). |
| **Mutation** | Veri ekleme, güncelleme veya silme operasyonları (REST'teki `POST`, `PUT`, `DELETE` karşılığı). |
| **Scalar / Type** | `ID`, `String`, `Int`, `Boolean` gibi ilkel tipler veya özel nesne tipleri (`Vehicle`). |
| **Input** | Mutasyonlara parametre olarak gönderilen karmaşık veri yapıları (`VehicleDto`). |
| **Resolver / Controller** | Şemadaki alanları ve operasyonları Java metotlarına bağlayan bileşendir (`@QueryMapping`, `@MutationMapping`). |

---

## 🛠️ Proje Yapısı

```
spring-boot-graphql/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/HaydiKodlayalim/graphql/
    │   │   ├── SpringBootGraphqlApplication.java
    │   │   ├── controller/
    │   │   │   └── VehicleController.java
    │   │   ├── dto/
    │   │   │   └── VehicleDto.java
    │   │   ├── entity/
    │   │   │   └── Vehicle.java
    │   │   └── repo/
    │   │       └── VehicleRepository.java
    │   └── resources/
    │       ├── application.properties
    │       └── graphql/
    │           └── schema.graphqls
    └── test/
        └── java/com/HaydiKodlayalim/graphql/
            └── SpringBootGraphqlApplicationTests.java
```

---

## 📜 GraphQL Şeması (`schema.graphqls`)

```graphql
type Vehicle {
    id: ID!
    type: String!
    modelCode: String
    brandName: String
    launchDate: String
}

input VehicleDto {
    type: String!
    modelCode: String
    brandName: String
    launchDate: String
}

type Query {
    getVehicles(type: String): [Vehicle]
    getById(id: ID!): Vehicle
}

type Mutation {
    createVehicle(vehicle: VehicleDto): Vehicle
    deleteVehicle(id: ID!): Boolean
}
```

---

## 🚀 Çalıştırma ve Test

### 1. Modülü Derleyin:
```powershell
.\mvnw.cmd clean package -pl spring-boot-graphql -DskipTests
```

### 2. Uygulamayı Başlatın:
```powershell
.\mvnw.cmd spring-boot:run -pl spring-boot-graphql
```

### 3. GraphiQL Arayüzüne Erişin:
Tarayıcınızdan aşağıdaki adrese gidin:
👉 [http://localhost:8080/graphiql](http://localhost:8080/graphiql)

---

## 📝 Örnek GraphQL İstekleri

### 1. Yeni Araç Ekleme (Mutation):
```graphql
mutation {
  createVehicle(vehicle: {
    type: "Sedan",
    brandName: "Toyota",
    modelCode: "Corolla 2024",
    launchDate: "2024-01-15"
  }) {
    id
    brandName
    modelCode
    type
  }
}
```

### 2. Tüm Araçları Listeleme (Query):
```graphql
query {
  getVehicles {
    id
    brandName
    modelCode
    type
    launchDate
  }
}
```

### 3. Tipe Göre Filtreleyerek Listeleme (Query):
```graphql
query {
  getVehicles(type: "Sedan") {
    id
    brandName
    modelCode
  }
}
```

### 4. ID ile Araç Getirme (Query):
```graphql
query {
  getById(id: 1) {
    id
    brandName
    modelCode
  }
}
```

### 5. Araç Silme (Mutation):
```graphql
mutation {
  deleteVehicle(id: 1)
}
```

---

## 💡 cURL ile İstek Örneği

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "query { getVehicles { id brandName modelCode } }"}'
```
