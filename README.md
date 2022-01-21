# telekom_jpa_spring

Materialien zum Training:
- HR-Schema.pdf ----   Datenbank-Sample-Schema
- handouts_jpa ---- Folien-Handouts
- h2.zip ----     Skripte zum Anlegen des Datenbank-Schemas


Code zu DB-Zugriff und JPA:
- sk.train.x10_SimpleJDBC_Ex  ----  Aufgabe: Nutzen der Datenbank via JDBC ohne Spring-Support
- sk.train.x10_SimpleJDBC_Solution  ----  Nutzen der Datenbank via JDBC ohne Spring-Support
- sk.train.x10_SimpleJDBC_SpringBoot_Solution  ---- Nutzen der Datenbank via JDBC mit Unterstützung von Spring Boot
- sk.train.x10_SimpleJDBC_Spring_Ex   ----  Aufgabe: Nutzen der Datenbank via JDBC mit Spring-Unterstützung (DriverManagerDatasource, JDBC-Template), aber ohne Spring Boot
- sk.train.x10_SimpleJDBC_Spring_Solution  ---- Nutzen der Datenbank via JDBC mit Spring-Unterstützung (DriverManagerDatasource, JDBC-Template)
- sk.train.x11_01_JPA_Ex  ---- Aufgabe: Nutzen von JPA zur Persistierung von Employee-Objekten
- sk.train.x11_01_JPA_Solution   ----  Nutzen von JPA zur Persistierung von Employee-Objekten
- sk.train.x11_02_JPA_Ex ---- Aufgabe: Nutzen von JPA zur Persistierung von Employee- und Department-Objekten (Schlüsselbeziehungen abbilden)
- sk.train.x11_02_JPA_Solution ----  Nutzen von JPA zur Persistierung von Employee- und Department-Objekten (Schlüsselbeziehungen abbilden)
- sk.train.x11_03_JPA_Ex  ----  Aufgabe: Nutzen von JPA-Queries
- sk.train.x11_03_JPA_Solution  ----  Nutzen von JPA-Queries
- sk.train.x11_04_JPA_Spring_Solution  ----   Umstellung auf Spring-Unterstützung (nur Konfig durch Spring bereit stellen)
- sk.train.x11_05_JPA_Spring_Solution  ----   Nutzen von JPA- Unterstützung und deklarativer Transaktionssteuerung durch Spring
- sk.train.x11_05_JPA_Spring_Solution_SpringBoot  ----  Variante auf Basis von Spring Boot
- sk.train.x11_06_JPA_Spring_Solution   ----  Erste Nutzung von JPA-Repository
- sk.train.x11_07_JPA_Spring_Solution   ----  Erweiterung von JPA-Repository mit eigenen Zugriffs-Methoden
- sk.train.x11_08_JPA_Spring_Solution   ----  Mapping zwischen Employee und Department mit 2 JPA-Repositories
- Karrer_DBZugriff_InitializeDB   ----    Beispiel: zeigt wie man mit Spring Embedded Database aufsetzt und nutzt
- logback.xml   ----    Konfig-Datei für Logging via LogBack
- sk.train.x1102-JPA-TransactionTemplate   ----  Nutzen des TransactionTemplate von Spring

Materialien zu Block 7 Restful Web-Services:

    3-Spring MVC.pdf ---- Folien zu Spring MVC und Rest mit Spring
    Swagger.pdf ---- Folien zu Swagger
    java_servlets.pdf ---- Folien zur Servlets
    
    Kap4_java_Threads.pdf ----- Folien zu Threads
    9-SpringTest.pdf    ----    Folien zu Test
    

    sk.train.x12_01_JPA_Rest_Boot_Solution ---- CRUD via Rest mit Spring Boot
    sk.train.x12_02_JPA_Rest_Boot_Solution ---- Rest mit JPA, Spring Boot und ensprechender Fehlerbehandlung bzw. Statuscodes
    sk.train.x12_03_JPA_Rest_Boot_Solution ---- Rest mit JPA, Spring Boot und XML-MessageFormat mit Jackson (Dazu sind weitere Bibliotheken im ClassPath nötig)
    sk.train.x12_03_JPA_Rest_Boot_Solution2 ---- Hier jetzt mit getrennten Controller-Klassen für XML und JSON
    sk.train.x12_04_JPA_Rest_Boot_Solution ---- jetzt kommt noch HATEOAS-Support hinzu
    
    JPABootDemo_Rest            ---- erstes DemoProjekt zu Rest auf Basis DemoJPA (Umbenannt damit kein Konflikt mit "JPABootDemo"
    karrer_demo_rest ---- Demo zu JPA mit Rest-Frontend (Basis Kontoklasse)
    karrer_demo_rest_fehlerhandling ---- Demo zu JPA mit Rest-Frontend (Basis Kontoklasse) und Fehlerbehandlung (mit nettem Fehler!!)
    karrer_demo_rest_fehlerhandling_xml_ohneJAXB2 ---- Demo zu JPA mit Rest-Frontend (Basis Kontoklasse), Fehlerbehandlung und XML-Unterstützung
    karrer_demo_rest_hateoas ---- Demo zu JPA mit Rest-Frontend (Basis Kontoklasse) mit Hateoas
   




