# Formule division entière (INTEGER_DIVISION)
# Description
Cette formule représente le reste de la [division mathématique entière][divison-entiere] de valeurs de type [`entier`][valeur-de-retour]

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] correspondant au reste de la [division entière][divison-entiere] d'une valeur de type [`entier`][valeur-de-retour] par une autre valeur de type [`entier`][valeur-de-retour] 

## Usages
Il existe 1 seul "usage" pour ce type de formule :

|Type Paramètre 1|Type Paramètre 2|Type Retour|
|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|

## Syntaxe
Le modulo ou reste [division entière][divison-entiere] s'écrit avec des expressions séparées par deux barres obliques inversées `\\`

    <expression_de_type_entier_1> \\ <expression_de_type_entier_2>

## Exemples
    3 \\ 2               [= 1 (3 = (1 * 2) + 1)]
    2 \\ 3               [= 2 (2 = (0 * 3) + 2)]
 

[valeur-de-retour]: ../lexique.md#valeur-de-retour
[divison-entiere]: https://fr.wikipedia.org/wiki/Division_euclidienne