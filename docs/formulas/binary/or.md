# Formule `OU` logique (OR)

## Description

Cette formule représente la [disjonction][] de deux propositions de type [`booléen`][valeur-de-retour].

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant à la [disjonction][] de deux paramètres (e.g. *A* et *B*):

- Si la proposition *A* est `true` et que la proposition *B* est `true`, la formule retourne `true`
- Si la proposition *A* est `true` et que la proposition *B* est `false`, la formule retourne `true`
- Si la proposition *A* est `false` et que la proposition *B* est `true`, la formule retourne `true`
- Si la proposition *A* est `false` et que la proposition *B* est `false`, la formule retourne `false`

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`booléen`][valeur-de-retour]|obligatoire|
|2|[`booléen`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

## Syntaxe

Le `OU` logique s'écrit avec le mot clé `or` suivi et précédé de deux expressions de type
[`booléen`][valeur-de-retour] auxquelles il faut appliquer la [disjonction][].

    <expression_de_type_booleen> or <expression_de_type_booleen>

## Exemples

    true and true   [= true ]
    true and false  [= true ]
    false and true  [= true ]
    false and false [= false]

[valeur-de-retour]: ../../lexique.md#valeur-de-retour
[disjonction]: https://fr.wikipedia.org/wiki/Disjonction_logique