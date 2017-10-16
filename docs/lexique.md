# Lexique

Cette page contient des définitions de termes que l'on peut retrouver au travers de la documentation du `GLanguage`.

## Evaluation <a name="evaluation"></a>

L'_évaluation_ est l'action d'interpréter un objet et de lui assigner une valeur.

### Objets évaluables

Il existe 2 types d'objets évaluables :

- les règles

Par défintion, l'évaluation d'une [`règle`][rule] revient à évaluer sa [`formule`][formula] et à assigner le résultat de celle-ci à la [`règle`][rule].

> Une **règle** n'est évaluée qu'une seule fois

- les formules

L'évaluation d'une [`formule`][formula] revient à interpréter les éléments qui la compose (c.f. documentation).

## Façade

Une _façade_ agit comme une interface entre les [`règles`][rule] et l'application à proprement parlé afin de fournir des
données, venant de différentes sources, exploitables par les [`règles`][rule].

Chaque application ayant besoin d'évaluer une [`règle`][rule] faisant appel à une ou plusieurs fonctions d'une ou
plusieurs _façade_, au travers de [formules dédiées][formula_get] du GLanguage, à la responsabilité de fournir ces
_façade_ déclarant les fonctions nécessaires, ayant les paramètres et le type de retour attendu.

__ATTENTION__
Si la façade, ou une des fonctions appelées n'est pas présente, cela mène à une erreur rendant
l'évaluation impossible

## Valeur de retour

Il existe 6 types de retour possible :
1. Entier
2. Numérique
3. Chaîne de caractères
4. Booléen
5. Date
6. Durée

__Compatibilité entier numérique__

Les types entier et numérique sont compatible, l'un peut être utlisé en lieu et place de l'autre sans distinction.
Toute fois lorsqu'un numérique est utilisé en lieu et place d'un entier, __la partie décimale est perdue__.

### entier (INTEGER)

Du point de vue mathématique, une valeur constante faisant partie de l'ensemble des entiers relatifs, l'ensemble Z,
qui reprend l'esemble des nombres entiers positifs et négatifs

Un limitation, induite par le langage de programmation, doit cependant être respecter :

> limite maximum : 2147483647

> limite minimum : -2147483648

Pour obtenir une valeur de type entier, plusieurs possibilités existent :
1. Définition d'une constante

        123                         [= 123]

2. Appel à une fonction du GLanguage

        abs (-123)                  [= 123]

3. Appel à une fonction d'une façade

        get INTEGER <nom_de_la_fonction>

### Numérique (NUMERIC)

Du point de vue mathématique, une valeur constante faisant partie de l'ensemble des réels, l'ensemble R,
qui reprend l'esemble des nombres décimaux positifs et négatifs

Un limitation, induite par le langage de programmation, doit cependant être respecter :

> limite maximum : (2-2-52)^21023

> limite minimum : 2^-1074

Pour obtenir une valeur de type numérique, plusieurs possibilités existent :
1. Définition d'une constante

        123,456                     [= 123,456]

2. Appel à une fonction du GLanguage

        3 / 2                       [= 1,5]

3. Appel à une fonction d'une façade

        get NUMERIC <nom_de_la_fonction>

### Chaîne de caractères (STRING)

Une valeur constante réprésentant une suite de caractères alphanuémriques

Pour obtenir une valeur de type chaîne de caractère, plusieurs possibilités existent :
1. Définition d'une constante

        "abc"                                       [= "abc"]

2. Appel à une fonction du GLanguage

        formatDate(`31/12/2017` ,"dd MMMM yyy")     [= "31 décembre 2017"]

3. Appel à une fonction d'une façade

        get STRING <nom_de_la_fonction>

### Booléen (BOOLEAN)

Une valeur constante booléenne (vrai (true) ou faux (false))

Pour obtenir une valeur de type booléen, plusieurs possibilités existent :
1. Définition d'une constante

        true                        [= true]

2. Appel à une fonction du GLanguage

        x <= 0                      [= true si x est plus petit ou égal à 0, false sinon]

3. Appel à une fonction d'une façade

        get BOOLEAN <nom_de_la_fonction>

### Date (DATE)

Une valeur constante représentant un date

Pour obtenir une valeur de type date, plusieurs possibilités existent :
1. Définition d'une constante

        '31/12/2017'                [= 31/12/2017]

2. Appel à une fonction du GLanguage

        date (31 ; 12 ; 2017)       [= 31/12/2017]

3. Appel à une fonction d'une façade

        get DATE <nom_de_la_fonction>

### Durée (DURATION)

Une valeur constante représentant une durée

Pour obtenir une valeur de type durée, plusieurs possibilités existent :
1. Définition d'une constante

        'P1D'                       [= 1 jour]

2. Appel à une fonction du GLanguage

        days (1)                    [= 1 jour]

3. Appel à une fonction d'une façade

        get DURATION <nom_de_la_fonction>

[rule]: rule.md
[formula]: formula.md
[formula_get]: ./formulas/call/get.md