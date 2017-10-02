# Documentation des règles

Une _règle_ (ou _rule_) est l'entité atomique du [`Plan`][plan] qui représente une valeur ou un résultat obtenu après un enchainement d'instructions:

- Manipulation de données en _mémoire_
- Manipulation de données venant de la _base de données_
- Manipulation de données venant d'une application tierce
- etc.

## Description

Une règle est définie par un `code` et un `alias` unique (pouvant être traduit dans plusieurs langues). Chaacune des _règles_ peut être détaillée au travers d'une `description`, dans autant de langues que souhaité. Une _règle_ est valable sur une certaine période, au travers d'une `date de début d'effectivité` et d'une `date de fin d'effectivité`, qui peut être illimitée si non determinée.

Afin d'aboutir à un résultat, une _règle_ doit être [évaluée][evaluation], via une [`formule`][formula] qui lui est rattachée. Il est possible qu'une `formule` d'une _règle_ ne soit pas [évaluée][evaluation], puisqu'une _règle_ a également une `condition d'applicabilité`, qui se trouve être une `formule` retournant une valeur booléenne, définissant, selon une logique technique ou métier, si les conditions sont réunies pour que la `formule` de la _règle_ soit [évaluée][evaluation].

### Définition par contexte

Une _règle_ peut être redéfinie selon de multiples niveaux (e.g. employeur, commission paritaireetc.). Chacune des _règles_ peut avoir une nombre indéfini de définitions. Une _règle_ dispose d'au minimum une définition. A la création d'une _règle_, une définition par _défaut_ lui est attribuée.

// Schéma def ?

### Historisation

A l'instar des [_esembles de règles_][ruleset], une _(définition de)_ _règle_ peut être versionnée. Cela peut s'avérer très utile si l'on souhaite apporter des  modifications, corrections,  ou encore ajouter une `période d'effet`à une _règle_. En effet, la moindre modifcation doit être tracée et enregistrée pour permettre de rejouer d'anciennes versions de _règles_. Chaque modification d'une _règle_ implique la création d'une nouvelle version. Ainsi, l'ancienne version d'une _règle_ est alors conservée avant que la nouvelle version ne la remplace en `production`.

Une _(définition de)_ règle peut avoir un nombre indéfini de version, et chaque _(définition de)_ règle a au minimum une version.

// Schéma hist rules ? +/- hist ruleset

#### Effectivité

Une _(définition de)_ _règle_ peut être versionnée au travers d'une ou plusieurs `période(s) d'effet`. Afin d'assurer une intégrité des versinos, pour une même _(définition de)_ _règle_, celles-ci ne peuvent avoir des `périodes d'effet` qui se chevauchent, ce qui signifie qu'à une date donnée, une et une seule version de règle est applicable (_effective_).

// Schéma timeline règles ?

### Hiérarchie

Afin de permettre d'organiser les _règles_ et la logique d'un [_ensemble de règles_][ruleset] chacune des _règles_ peut référencer un nombre indéfini de `sous-règles`, ou `règles enfants`.

// Schéma hiérarchie ?

### Type d'une règle

Par définition, le type de retour d'une _règle_ est le même que celui de sa [`formule`][formula].

### [Evaluation][evaluation] d'une règle

[plan]: ruleset.md#plan
[formula]: formula.md
[ruleset]: ruleset.md
[evaluation]: formula.md#evaluation