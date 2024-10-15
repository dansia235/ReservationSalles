# ReservationSalles

![Licence MIT](https://img.shields.io/badge/licence-MIT-blue.svg)
![Java](https://img.shields.io/badge/java-23-red.svg)
![Maven](https://img.shields.io/badge/maven-3.6.3-brightgreen.svg)

**Version :** 1.0  
**Licence :** MIT

## Table des Matières

- [Présentation](#présentation)
- [Fonctionnalités](#fonctionnalités)
- [Technologies Utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Déploiement](#déploiement)
- [Utilisation](#utilisation)
- [Documentation de l'API](#documentation-de-lapi)
- [Contribution](#contribution)
- [Licence](#licence)
- [Contact](#contact)

## Présentation

**ReservationSalles** est une application web conçue pour faciliter la réservation des salles de réunion au sein d'un environnement professionnel. Développée avec **Java** et **Jakarta EE 10**, l'application propose une API RESTful permettant aux utilisateurs de :

- Vérifier la disponibilité des salles de réunion.
- Réserver des créneaux horaires spécifiques pour des réunions.
- Gérer leurs réservations (modifier ou annuler).
- Les administrateurs peuvent ajouter, mettre à jour ou supprimer des salles de réunion et gérer leur équipement associé.

L'application utilise **WildFly 34** comme serveur d'applications et **MySQL** comme système de gestion de base de données relationnelle.

## Fonctionnalités

- **Vérification de Disponibilité des Salles :** Vérifiez si une salle de réunion est disponible pour les dates et créneaux horaires souhaités.
- **Gestion des Réservations :** Créez, modifiez et annulez des réservations.
- **Authentification des Utilisateurs :** Accès sécurisé aux fonctionnalités de réservation et de gestion via une authentification basée sur JWT.
- **Contrôles Administratifs :** Ajoutez, mettez à jour et supprimez des salles de réunion ainsi que gérez leur équipement.
- **Historique des Réservations :** Consultez les réservations passées et à venir.
- **API RESTful :** Expose des endpoints bien définis pour toutes les opérations.

## Technologies Utilisées

- **Backend :**
  - Java 23
  - Jakarta EE 10
  - JAX-RS (pour les services RESTful)
  - JPA (Hibernate comme implémentation)
  - JWT (JSON Web Tokens) pour l'authentification

- **Base de Données :**
  - MySQL 8.0

- **Serveur :**
  - WildFly 34

- **Gestion de Build et Dépendances :**
  - Maven

- **Contrôle de Version :**
  - Git & GitHub

## Prérequis

Avant de configurer le projet, assurez-vous d'avoir installé les éléments suivants :

- **Java Development Kit (JDK) 23** ou supérieur
- **Apache Maven** 3.6.0 ou supérieur
- **MySQL Server** 8.0 ou supérieur
- **Serveur d'Applications WildFly** 34
- **NetBeans IDE** 23 (avec support Maven)
- **Git** (pour le contrôle de version)

## Installation

1. **Cloner le Dépôt :**

   ```bash
   git clone https://github.com/dansia235/ReservationSalles.git
