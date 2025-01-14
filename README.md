# TP 3 & TP 4 : Application Web avec Spring Boot
## Introduction
lien github : https://github.com/AhmedBourazza/appSpringBootTP3

## Introduction

Ce projet consiste à créer une application web utilisant Java et plusieurs technologies Spring. L'application est réalisée avec Spring Boot, JPA, Hibernate, H2, Thymeleaf et d'autres outils pour interagir avec des bases de données et des APIs. Ce TP est divisé en deux parties : la création d'une application de gestion d'adresses avec Spring Boot (TP3), et l'intégration d'APIs externes pour récupérer des données météo basées sur une adresse (TP4).

## Lancer l'application
entrez ce lien : http://127.0.0.1:9090/home
Le code pour configurer un proxy est déjà inclus, mais il est commenté. Vous pouvez l'utiliser pour effectuer la configuration.

## Technologies et Dépendances

### 1. **Spring** ([Site officiel](https://spring.io/))
Spring est un framework pour le développement d'applications Java, offrant des fonctionnalités telles que l'inversion de contrôle (IoC), la gestion des transactions et l'injection de dépendances. Il simplifie le développement en structurant le code de manière modulaire.

### 2. **Spring Boot** ([Site officiel](https://spring.io/projects/spring-boot))
Spring Boot est un module de Spring qui permet de créer rapidement des applications Java autonomes et prêtes pour la production. Il offre une configuration automatique et des dépendances prédéfinies pour accélérer le développement.

### 3. **JPA** ([Documentation](https://www.tutorialspoint.com/jpa/index.htm))
JPA (Java Persistence API) est une spécification Java pour gérer les interactions entre les objets Java et les bases de données relationnelles. Elle permet de mapper des classes Java à des tables de base de données.

### 4. **Hibernate** ([Site officiel](http://hibernate.org/))
Hibernate est une implémentation populaire de JPA. Il facilite la persistance des données en générant automatiquement le SQL nécessaire pour interagir avec la base de données. Il prend également en charge des fonctionnalités avancées telles que le cache et les transactions.

### 5. **H2 Database** ([Site officiel](http://www.h2database.com/html/main.html))
H2 est une base de données relationnelle légère et embarquée. Elle est souvent utilisée pour le développement et les tests en raison de sa simplicité et de son exécution en mémoire.

### 6. **Spring Data JPA** ([Site officiel](https://spring.io/projects/spring-data-jpa))
Spring Data JPA est une extension de Spring qui simplifie l'accès aux bases de données en fournissant des interfaces prêtes à l'emploi pour effectuer des opérations CRUD. Elle réduit le code boilerplate en permettant de définir des requêtes directement dans des interfaces.

### 7. **Thymeleaf** ([Site officiel](https://www.thymeleaf.org/))
Thymeleaf est un moteur de templates Java utilisé pour créer des interfaces utilisateur dynamiques. Il est souvent utilisé avec Spring Boot pour générer des pages HTML en utilisant les données fournies par les contrôleurs.

## Questions et Réponses

### TP 3 : Application Web avec Spring Boot

1. **Avec quelle partie du code avez-vous paramétré l'URL d'appel /greeting ?**
   - L'URL d'appel `/greeting` est paramétrée dans la méthode `@GetMapping("/greeting")` du contrôleur `HelloWorldController.java`. Cette annotation indique à Spring que cette méthode répondra aux requêtes HTTP GET adressées à `/greeting`.

2. **Avec quelle partie du code avez-vous choisi le fichier HTML à afficher ?**
   - Le fichier HTML à afficher est choisi dans la ligne suivante du contrôleur :
     ```
     return "greeting";
     ```
     Cela indique à Spring que le fichier Thymeleaf correspondant est `greeting.html` dans le répertoire `templates`.

3. **Comment envoyez-vous le nom à qui vous dites bonjour avec le second lien ?**
   - Le nom est passé via un paramètre d'URL (`nameGET`) grâce à l'annotation `@RequestParam(name="nameGET")`. Ce paramètre est récupéré dans le contrôleur et ajouté au modèle avec la méthode `model.addAttribute("nomTemplate", nameGET)`. Le nom est ensuite affiché dans le fichier `greeting.html`.

### TP 3 : Base de données H2 et Hibernate

4. **Avez-vous remarqué une différence dans la console H2 après avoir ajouté l'entité Address ?**
   - Après avoir ajouté l'entité `Address`, une nouvelle table `Address` apparaît dans la base de données H2. Hibernate génère automatiquement cette table en fonction de l'entité JPA, ce qui permet de persister les données.

5. **Expliquez l'apparition de la nouvelle table en vous aidant de vos cours sur Hibernate, et de la dépendance Hibernate de Spring.**
   - L'annotation `@Entity` sur la classe `Address` indique à Hibernate qu'elle doit être traitée comme une entité persistante. En associant Hibernate à Spring Boot via la dépendance `spring-boot-starter-data-jpa`, la table correspondante est générée automatiquement dans la base de données H2. Hibernate se charge de créer la structure de la base de données selon les entités définies dans le code.

6. **Pourquoi la table `Address` apparaît-elle dans la base H2 après l'ajout de l'entité ?**
   - L'entité `Address` est gérée par Hibernate, qui utilise la configuration JPA pour générer la table automatiquement. Cette action est effectuée au démarrage de l'application, en fonction des annotations JPA et de la configuration de la source de données.

7. **Pourquoi le fichier data.sql est-il correctement inséré dans la base de données ?**

  -Le fichier data.sql est exécuté correctement grâce à la ligne de configuration suivante, ajoutée avant l'exécution 
    spring.jpa.defer-datasource-initialization=true
    Cela garantit que l'initialisation des données se fait après la configuration de la source de données.


.
### Ajouter des données et utiliser le Repository

7. **Que fait l'annotation `@Autowired` dans le contrôleur `AddressController` ?**
   - L'annotation `@Autowired` permet d'injecter automatiquement une instance du `AddressRepository` dans le contrôleur. Cela permet d'accéder aux données de la table `Address` dans la base de données sans avoir à créer manuellement une instance du repository.

###  Utilisation d'APIs Web avec Spring Boot

8. **Faut-il une clé API pour appeler MeteoConcept ?**
   - Une clé API est nécessaire pour accéder à l'API MeteoConcept. Il faut s'inscrire sur leur site pour obtenir une clé API.

9. **Quelle URL appeler pour obtenir les informations de météo ?**
   - L'URL à appeler est :
     ```
     https://api.meteoconcept.com/api/forecast/complete
     ```
     Cette URL permet de récupérer les prévisions météorologiques pour des coordonnées GPS spécifiques.

10. **Quelle méthode HTTP utilisez-vous pour appeler MeteoConcept ?**
    - La méthode HTTP à utiliser est **GET**.

11. **Comment passez-vous les paramètres d'appels pour l'API MeteoConcept ?**
    - Les paramètres à passer dans l'URL incluent les coordonnées GPS du lieu pour lequel on veut obtenir la météo. Les paramètres sont :
      ```
      ?lat={latitude}&lon={longitude}&token={votre_clé_API}
      ```

12. **Où est l'information dont vous avez besoin dans la réponse de l'API MeteoConcept pour afficher la température et la prévision de météo ?**
    - Dans la réponse JSON de l'API, la température se trouve sous le champ `forecast`, et la prévision est dans des sous-champs comme `temperature` ou `weather`. Ces valeurs doivent être extraites en utilisant un objet Java pour la désérialisation de la réponse JSON.

14. **Ajout de Bootstrap et création d'une navbar**
    - Bootstrap a été ajouté via CDN. La navbar a été créée en utilisant les composants de Bootstrap pour une navigation facile entre les pages.
     L'intégration de Bootstrap via un CDN est une solution pratique et performante. Elle permet d'utiliser directement les fichiers CSS et JavaScript hébergés sur des serveurs optimisés, ce qui améliore la vitesse de chargement des pages. De plus, une mise à jour est simplifiée, car il suffit de modifier l'URL du CDN sans avoir à gérer des fichiers locaux.



