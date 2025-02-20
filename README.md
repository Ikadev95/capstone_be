

# 📡 Capstone Backend

Questo repository contiene il backend dell’applicazione Capstone, un sistema per la gestione del concorso di fotografia e poesia organizzato dal Circolo Airali di San Secondo di Pinerolo. Il backend è sviluppato con Spring Boot, Hibernate e PostgreSQL e include funzionalità per autenticazione, gestione utenti, upload file, pagamenti con Stripe e generazione di report.

##  🛠 Tecnologie utilizzate 
	Java 21 
	Spring Boot 3.4.2 
	Spring Security con JWT 
	Spring Data JPA (Hibernate) con PostgreSQL 
	Stripe API per la gestione dei pagamenti 
	Swagger (documentazione API con OpenAPI) 
	Lombok (riduzione del boilerplate di codice Java) 
	ModelMapper (conversione tra DTO ed Entity) 
	OpenCSV (gestione CSV per esportazione dati) 

## 🚀 Setup del progetto


Assicurati di avere installati: <br>
	•	JDK 21 <br>
	•	Maven <br>
	•	PostgreSQL <br>


Nel file application.properties, sostituisci il valore di spring.jpa.hibernate.ddl-auto con create per il primo avvio:

```bash
spring.jpa.hibernate.ddl-auto=create
```


Dopo aver eseguito l’app per la prima volta, ripristina il valore a update per evitare la perdita dei dati.
<br>

## Impostazione delle variabili d’ambiente
<br>
Configura le seguenti variabili d’ambiente per il database, JWT e Stripe:
<br>

DB_NAME=capstone_db <br>
DB_USER=tuo_utente <br>
DB_PASSWORD=tua_password <br>
JWT_SECRET=chiave_di_44_caratteri <br>
STRIPE_SECRET_KEY=la_tua_stripe_key <br>



## 🔑 Autenticazione e Sicurezza

Il sistema utilizza Spring Security con JWT per autenticare gli utenti. I token hanno una durata di 1 ora e devono essere inviati nell’Authorization Header delle richieste.

Esempio di richiesta autenticata:

GET /api/protected-resource
Authorization: Bearer <JWT_TOKEN>

## 💳 Integrazione con Stripe

L’API include endpoint per gestire i pagamenti tramite Stripe Checkout.
