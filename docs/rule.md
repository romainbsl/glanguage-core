# Documentation des règles

Une _règle_ (ou _rule_) est l'entité atomique de l' [esemble de règles][ruleset] qui représente une valeur ou un résultat obtenu après un enchainement d'instructions:

- Manipulation de données en _mémoire_
- Manipulation de données venant de la _base de données_
- Manipulation de données venant d'une application tierce
- etc.

## Description

Une _règle_ sert de mécanisme d'encapsulation d'une [formule][formula].

Une _règle_ permet d'une part, de stocker la valeur résultant de l'évaluation de sa formule et, d'autre part, d'ajouter différentes données :

#### Identifiants 

Afin de pouvoir réutiliser la valeur d'une _règle_ dans les [formules][formula] d'autres _règles_, il faut pouvoir l'identifier de manière unique.

Pour cela une _règle_ a 2 identifiants :
- un `code` unique
- un `alias` multilingue unique

Une _règle_ peut avoir plusieurs `alias` identitiques dans des langues différentes mais 2 règles différentes ne peuvent avoir un `alias` identique

#### _Description_

Une _règle_ peut avoir une `description` textuelle multilingue qui peut servir de documentation

#### Période d'effet

La periode d'effet d'une _règle_ définit à partir de quelle date, et évenutellement jusqu'à quelle date, une _règle_ peut être évaluée.

Pour cela une _règle_ a :
- une `date de début d'effet` obligatoire
- une `date de fin d'effet` optionnelle

Si la `date de fin d'effet` est omise, elle sera interprétée comme illimitée dans le temps.

#### Condition d'applicabilité

La `condition d'applicabilité` d'une _règle_ permet de définir, selon une logique technique ou métier, si les conditions sont réunies pour qu'une _règle_ soit [évaluée][evaluation].

Elle consiste en une [formule][formula] retournant une valeur de type booléen.

## Définition par contexte

Une _règle_ peut être redéfinie selon de multiples niveaux (e.g. employeur, commission paritaire, etc.). Chacune des _règles_ peut avoir une nombre indéfini de définitions. Une _règle_ dispose d'au minimum une définition. A la création d'une _règle_, une définition par _défaut_ lui est attribuée.

// Schéma def ?

## Historisation

A l'instar des [_esembles de règles_][ruleset], une _(définition de)_ _règle_ peut être versionnée. Cela peut s'avérer très utile si l'on souhaite apporter des  modifications, corrections, ou encore ajouter une `période d'effet` à une _règle_. En effet, la moindre modifcation doit être tracée et enregistrée pour permettre de rejouer d'anciennes versions de _règles_. Chaque modification d'une _règle_ implique la création d'une nouvelle version. Ainsi, l'ancienne version d'une _règle_ est alors conservée avant que la nouvelle version ne la remplace en `production`.

Une _(définition de)_ _règle_ peut avoir un nombre indéfini de version, et chaque _(définition de)_ _règle_ a au minimum une version.

// Schéma hist rules ? +/- hist ruleset

## Effectivité

Une _(définition de)_ _règle_ peut être versionnée au travers d'une ou plusieurs `période(s) d'effet`. Afin d'assurer une intégrité des versinos, pour une même _(définition de)_ _règle_, celles-ci ne peuvent avoir des `périodes d'effet` qui se chevauchent, ce qui signifie qu'à une date donnée, une et une seule version de _règle_ est applicable (_effective_).

// Schéma timeline règles ?

## Hiérarchie

Afin de permettre d'organiser les _règles_ et la logique d'un [_ensemble de règles_][ruleset] chacune des _règles_ peut référencer un nombre indéfini de `sous-règles`, ou `règles enfants`.

// Schéma hiérarchie ?

## Type d'une règle

Par définition, le type de retour d'une _règle_ est le même que celui de sa [`formule`][formula].

## [Evaluation][evaluation] d'une règle

[plan]: ruleset.md#plan
[formula]: formula.md
[ruleset]: ruleset.md
[evaluation]: lexique.md#evaluation