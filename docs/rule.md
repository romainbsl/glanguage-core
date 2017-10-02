# Documentation des règles

Une **règle** (ou _rule_) est l'entité atomique du [`Plan`][plan] qui représente une valeur ou un résultat obtenu après un enchainement d'instructions: 

- Manipulation de données en _mémoire_
- Manipulation de données venant de la _base de données_
- Manipulation de données venant d'une application tierce
- etc.

## Description

Une règle est définie par un `code` et un `alias` unique (pouvant être traduit dans plusieurs langues). Chaacune des **règles** peut être détaillée au travers d'une `description`, dans autant de langues que souhaité. **Une règle** est valable sur une certaine période, au travers d'une `date de début d'effectivité` et d'une `date de fin d'effectivité`, qui peut être illimitée si non determinée.

Afin d'aboutir à un résultat, une **règle** doit être [évaluée][evaluation], via une [`formule`][formula] qui lui est rattachée. Il est possible qu'une `formule` d'une **règle** ne soit pas évaluée, puisqu'une **règle** a également une `condition d'applicabilité`, qui se trouve être une `formule` retournant une valeur booléenne, définissant, selon une logique technique ou métier, si les conditions sont réunies pour que la `formule` de la **règle** soit évaluée.

### Définition par contexte

Une **règle** peut être redéfinie selon de multiples niveaux (e.g. employeur, commission paritaireetc.). Chacune des **règles** peut avoir une nombre indéfini de définitions. Une **règle** dispose d'au minimum une définition. A la création d'une **règleù**, une définition par _défaut_ lui est attribuée.

// Schéma def ?

### Historisation

A l'instar des [**esembles de règles**][ruleset], une _(définition de)_ **règle** peut être versionnée. Cela peut s'avérer très utile si l'on souhaite apporter des  modifications, corrections,  ou encore ajouter une `période d'effet`à une **règle**. En effet, la moindre modifcation doit être tracée et enregistrée pour permettre de rejouer d'anciennes versions de **règles**. Chaque modification d'une **règle** implique la création d'une nouvelle version. Ainsi, l'ancienne version d'une **règle** est alors conservée avant que la nouvelle version ne la remplace en `production`.

Une _(définition de)_ règle peut avoir un nombre indéfini de version, et chaque _(définition de)_ règle a au minimum une version.

// Schéma hist rules ? +/- hist ruleset

#### Effectivité

Une _(définition de)_ **règle** peut être versionnée au travers d'une ou plusieurs `période(s) d'effet`. Afin d'assurer une intégrité des versinos, pour une même _(définition de)_ **règle**, celles-ci ne peuvent avoir des `périodes d'effet` qui se chevauchent, ce qui signifie qu'à une date donnée, une et une seule version de règle est applicable (_effective_).

// Schéma timeline règles ?

### Hiérarchie

Afin de permettre d'organiser les **règles** et la logique d'un [**ensemble de règles**][ruleset] chacune des **règles** peut référencer un nombre indéfini de `sous-règles`, ou `règles enfants`.

// Schéma hiérarchie ?

### Type d'une règle

Par définition, le type de retour d'une **règle** est le même que celui de sa [`formule`][formula].

### Evaluation d'une règle

L'[évaluation][evaluation] d'une règle revient à évaluer sa [`formule`][formula] et à assigner le résultat de celle-ci à la **règle**.

> Une **règle** n'est évaluée qu'une seule fois

[plan]: ruleset.md#plan
[formula]: formula.md
[ruleset]: ruleset.md
[evaluation]: formula.md#evaluation