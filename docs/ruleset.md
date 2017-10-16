# Documentation des ensembles de règles

Un _ensemble de règles_ (ou _rule set_) est le type d'entité de plus haut niveau dans le `GLanguage`. En effet, de manière simplifiée, un ensemble de [règles][rule] contient des [règles][rule], qui sont elles même constituées de [formules][formula].

## Description

### Structure

Un _ensemble de règles_ est constitué d'un `alias` et d'une `description`, tous deux traduits dans différentes langues, sachant qu'un `alias` doit être unique.

Le concept d'_ensemble de règles_ permet de créer des regroupements, cohérents, de [règles][rule] selon des notions (métiers ou non). Un _ensemble de règles_ peut référencer un nombre indéfini de règles, avec un minimum d'une [règle][rule] 'racine', dite `ROOT`. Cette [règle][rule] 'racine' défini le point d'entrée de la hiérarchie et l'ordre des [règles][rule] qui composent l'ensemble.

// Schéma RULE\_SET + RULES ?0

> Mmdularité et réutilisabilité

Afin de promouvoir la modularité et la réutilisabilité, il est convenu qu'un _ensemble de règles_ peut référencer un nombre indéfini d'_ensemble de règles_, dits `inclus` ou `sous-ensemble de règles`.

// Schéma RULE\_SET + INCLUDED\_RULE\_SET ?

### Historisation

Un _ensemble de règles_ peut être versionné. Au travers de la notion de `date de mise en production`, nous avons la possibilité d'archiver, mais surtout de ré-évaluer d'anciennes versions, d'un _ensemble de règles_ ainsi que de ses `sous-ensemble de règles`.

Cela signifie qu'à une date donnée, il ne peut y avoir qu'une et une seule version pour un _ensemble de règles_ en `production`.

Un `sous-ensemble de règles` peut lui, avoir plusieurs versions en `production`, chacune rattachée à un _ensemble de règles_ parent. Un `sous-ensemble de règles` ne peut avoir en `production` qu'une seule version en tant qu'_ensemble de règles_.

// Schéma timeline + plans ?

### Plan <a name="plan"></a>

Un `plan` est une vue d'un _ensemble de règles_ à une `date de production`, une `date d'effet` et une [définition][definition] données.
Les contraintes d'historisation des _ensembles de règles_ et de _règles_ assurent que pour une `date de production`, il n'existe qu'une et une seule version d'un _ensemble de règles_ applicable en `production`, et qu'à la `date d'effet` une et une seule version de [règle][rule] sera effective pour chacune des `règles` de l'ensemble.

### Statuts et droits

Afin de garantir une intégrité des _ensembles de règles_ il est nécessaire de gérer des droits d'accès par utilisateurs. Cette partie n'est pas implémentée pour le moment.

En plus des droits d'accès, nous avons besoin de gérer de multiple versions des _ensembles de règles_ et des _règles_. Pour cela, nous avons défini un statut par _ensemble de règles_, qui permettra de nommer l'état dans lequel ce trouve cet ensemble (e.g. `production`, `patch`, `développement`, `test`).

[formula]: formula.md
[rule]: rule.md
[definition]: rule.md#definition