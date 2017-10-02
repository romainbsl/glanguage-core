# Documentation des ensembles de règles

Un **ensemble de règles** (ou _rule set_) est le type d'entité de plus haut niveau dans le `GLanguage`. En effet, de manière simplifiée, un ensemble de règles contient des [règles][rule], qui sont elles même constituées de [formules][formula].

## Description

### Structure

Un **ensemble de règles** est constitué d'un `alias` et d'une `description`, tous deux traduits dans différentes langues, sachant qu'un `alias` doit être unique.

Le concept d'**ensemble de règles** permet de créer des regroupements, cohérents, de règles selon des notions (métiers ou non). Un **ensemble de règles** peut référencer un nombre indéfini de règles, avec un minimum d'une règle 'racine', dite `ROOT`. Cette règle 'racine' défini le point d'entrée de la hiérarchie et l'ordre des règles qui composent l'ensemble.

// Schéma RULE\_SET + RULES ?0

> Mmodularité et réutilisabilité

Afin de promouvoir la modularité et la réutilisabilité, il est convenu qu'un **ensemble de règles** peut référencer un nombre indéfini d'**ensemble de règles**, dits `inclus` ou `sous-ensemble de règles`.

// Schéma RULE\_SET + INCLUDED\_RULE\_SET ?

### Historisation

Un **ensemble de règles** peut être versionné. Au travers de la notion de `date de mise en production`, nous avons la possibilité d'archiver, mais surtout de ré-évaluer d'anciennes versions, d'un **ensemble de règles** ainsi que de ses `sous-ensemble de règles`. Cela signifie qu'à une date donnée, il ne peut y avoir qu'une et une seule version pour un **ensemble de règles** en `production`. Un `sous-ensemble de règles` peut lui, avoir plusieurs versions en `production`, chacune rattachée à un **ensemble de règles** parent. Un `sous-ensemble de règles` ne peut avoir en `production` qu'une seule version en tant qu'**ensemble de règles**.

// Schéma timeline + plans ?

### Plan <a name="plan"></a>

Un `plan` est une vue d'un **ensemble de règles** à une `date de production`, une `date d'effet` et une [définition][definition] données.
Les contraintes d'historisation des **ensembles de règles** et de **règles** assurent que pour une `date de production`, il n'existe qu'une et une seule version d'un **ensemble de règles** applicable en `production`, et qu'à la `date d'effet` une et une seule version de règle sera effective pour chacune des `règles` de l'ensemble.

### Statuts et droits

Afin de garantir une intégrité des **ensembles de règles** il est nécessaire de gérer des droits d'accès par utilisateurs. Cette partie n'est pas implémentée pour le moment.

En plus des droits d'accès, nous avons besoin de gérer de multiple versions des **ensembles de règles** et des **règles**. Puor cela, nous avons défini un statut par **ensemble de règles**, qui permettra de nommer l'état dans lequel ce trouve cet ensemble (e.g. `production`, `patch`, `développement`, `test`).

[formula]: formula.md
[rule]: rule.md
[definition]: rule.md#definition