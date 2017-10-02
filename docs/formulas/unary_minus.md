# Formule moins uniare (UNARY_MINUS)
## Description
Cette formule représente l'opposé au sen mathématique d'une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] correspondant à l'opposé de la valeur fournie en paramètre

## Usages :
Il existe 2 "usages" pour ce type de formule, un par type de paramètre accepté :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|
|[`numérique`][valeur-de-retour]|[`numérique`][valeur-de-retour]|

## Syntaxe
Le moins unaire s'écrit avec un tiret "-" suivi ou non d'un espace et d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] à laquelle il faut appliquer la fromule

    - <expression_de_type_entier_ou_numerique>

## Exemples
    -1                                      [= -1]
    - 0,001                                 [= -0,001]

    
[valeur-de-retour]: ../lexique.md#valeur-de-retour