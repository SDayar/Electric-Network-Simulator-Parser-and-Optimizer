# Projet-PAA
# Projet : Réseau de distribution d’électricité
## 📚 Contexte
Ce projet s'inscrit dans le cadre du cours **Programmation avancée et application** de la Licence 3 Informatique à l'Université Paris Cité. Il vise à modéliser et optimiser un réseau de distribution électrique simplifié, composé de **générateurs** et de **maisons** (consommateurs).

## 🎯 Objectifs
Développer un logiciel permettant :
1. De représenter un réseau électrique avec générateurs et maisons.
2. De simuler les connexions possibles entre eux.
3. De calculer et minimiser le **coût** d'une solution.

## 🧠 Modélisation
Une instance du problème est définie par un triplet **(M, G, C)** :
- **M** : ensemble des maisons avec une consommation (10, 20 ou 40 kW).
- **G** : ensemble des générateurs avec une capacité maximale.
- **C** : ensemble des connexions entre maisons et générateurs (chaque maison est connectée à un seul générateur).

## 📊 Calcul du coût
Le coût d'une instance est défini par :
- **Dispersion (Disp)** : mesure l'équilibre des charges entre générateurs.
- **Surcharge** : pénalise les générateurs dépassant leur capacité.
- **Cout(S) = Disp(S) + N × Surcharge(S)**, avec N = 10 (sévérité de la pénalisation).

## 🛠️ Fonctionnalités du programme
Le programme propose deux phases :
### Phase 1 : Configuration du réseau
- Ajouter un générateur
- Ajouter une maison
- Ajouter une connexion
- Terminer la configuration

### Phase 2 : Analyse et modification
- Calculer le coût du réseau
- Modifier une connexion
- Afficher le réseau
- Quitter

## ⚠️ Contraintes
- Chaque maison doit être connectée à un unique générateur.
- La somme des consommations ne doit pas dépasser la capacité totale des générateurs.
- Certaines configurations peuvent ne pas permettre un coût nul.

## 📅 Remise
- À rendre sur Moodle avant le **14 novembre 2024**.
- Démonstration obligatoire en TP la semaine du **17 novembre 2024**.
