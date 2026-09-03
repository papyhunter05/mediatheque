# Mediatheque - Projet de refactoring (avant refactoring)

Ce projet Maven est un point de depart volontairement mal concu, a utiliser
pour le TP de refactoring / integration continue.

## Comment l'importer dans Eclipse

1. Eclipse > File > Import > Maven > Existing Maven Projects
2. Selectionner le dossier `mediatheque`
3. Eclipse telecharge les dependances (JUnit 5) automatiquement

## Code smells volontaires a identifier et corriger

- **Duplication** entre `Etudiant` et `Enseignant` (methodes `getDureeMaxPretJours()`,
  `getNombreMaxDocuments()`, `peutEmprunter()` quasi identiques)
- **Duplication** entre `Livre`, `DVD`, `JeuDeSociete` (methode `calculerPenalite()`
  copiee-collee avec seulement le plafond qui change)
- **God Method** : `Emprunt.traiterRetourEmprunt()` fait trop de choses a la fois
  (determiner la duree max, calculer le retard, calculer la penalite, formater
  un message)
- **instanceof en cascade** dans `Emprunt.traiterRetourEmprunt()` a la place
  du polymorphisme
- **Magic numbers** : durees de pret (14, 21, 7), quotas (3, 5), tarifs (0.50),
  plafonds de penalite (10, 15, 20) codes en dur

## Etapes suggerees

1. Ecrire des tests JUnit qui capturent le comportement ACTUEL (avant refactoring)
2. Refactoriser progressivement, un commit par etape
3. Relancer les tests apres chaque etape
4. Configurer Jenkins pour builder et tester automatiquement a chaque commit

## Comment lancer le projet

le projet se lance avec Maven avec la commande : mvn javafx:run
