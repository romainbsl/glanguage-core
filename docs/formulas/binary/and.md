# Formule `ET` logique (AND)

## Description

Cette formule représente la [conjonction][] de deux propositions de type [`booléen`][valeur-de-retour].

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant à la [conjonction][] de deux paramètres (e.g. *A* et *B*):

- Si la proposition *A* est `true` et que la proposition *B* est `true`, la formule retourne `true`
- Si la proposition *A* est `true` et que la proposition *B* est `false`, la formule retourne `false`
- Si la proposition *A* est `false` et que la proposition *B* est `true`, la formule retourne `false`
- Si la proposition *A* est `false` et que la proposition *B* est `false`, la formule retourne `false`

## Usages

Il existe un seul "usage" pour ce type de formule :

|Type Paramètre|Présence|
|--------------|--------------|
|[`booléen`][valeur-de-retour]|obligatoire|
|[`booléen`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

## Syntaxe

Le `ET` logique s'écrit avec le mot clé `and` suivi et précédé de deux expressions de type
[`booléen`][valeur-de-retour] auxquelles il faut appliquer la [conjonction][].

    <expression_de_type_booleen> and <expression_de_type_booleen>

## Exemples

    true and true   [= true ]
    true and false  [= false]
    false and true  [= false]
    false and false [= false]

[valeur-de-retour]: ../lexique.md#valeur-de-retour
[conjonction]: https://fr.wikipedia.org/wiki/Conjonction_logique