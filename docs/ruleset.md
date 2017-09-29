# Documentation des ensembles de règles

Un **ensemble de règles** (ou _rule set_) est le type d'entité de plus haut niveau dans le `GLanguage`. En effet, de manière simplifiée, un ensemble de règles contient des [règles][rule], qui sont elles même constituées de [formules][formula].

## Description

### Structure

Un **ensemble de règles** est constitué d'un``alias` et d'une `description`, tous deux traduits dans différentes langues, sachant qu'un `alias` doit être unique.

Le concept d'**ensemble de règles** permet de créer des regroupements, cohérents, de règles selon des notions (métiers ou non). Un **ensemble de règles** peut référencer un nombre indéfini de règles, avec un minimum d'une règle 'racine', dite `ROOT`. Cette règle 'racine' défini le point d'entrée de la hiérarchie et l'ordre des règles qui composent l'ensemble.

// Schéma RULE\_SET + RULES ?

> Mmodularité et la réutilisabilité

Afin de promouvoir la modularité et la réutilisabilité, il est convenu qu'un **ensemble de règles** peut référencer un nombre indéfini d'**ensemble de règles**, dits `inclus` ou `sous-ensemble de règles`.

// Schéma RULE\_SET + INCLUDED\_RULE\_SET ?

### Historisation

Un **ensemble de règles** peut être versionné. Au travers de la notion de `date de mise en production`, nous avons la possibilité d'archiver, mais surtout de ré-évaluer d'anciennes versions, d'un **ensemble de règles** ainsi que de ses `sous-ensemble de règles`. Cela signifie qu'à une date donnée, il ne peut y avoir qu'une et une seule version pour un **ensemble de règles** en `production`.

// Schéma timeline + plans ?

### Plan

Un plan est une vue d'un ensemble de règle à une date de production donnée et une date d'effet donnée (et une définition)
Les contraintes d'historisation des ensembles de règles et de règles assurent qu'à la date de production une et une seule version d'un ensemble de règle et en production, et qu'à la date d'effet une et une seule version de règle sera effective pour chaque règle de l'ensemble de règle

### Statuts et droits

Gestion des utilisateurs, droits
Gestion des versions en production, patch, développement, test)

[formula]: formula.md
[rule]: rule.md
