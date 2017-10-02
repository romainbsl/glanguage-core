# Formule signe (SIGN)
## Description
Cette formule représente le signe, au sens mathématique, d'une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

Cette formule retourne une valeur de type [`numérique`][valeur-de-retour] correspondant au signe de la valeur absolue de la valeur fournie en paramètre :
- si la valeur est négative, la formule retourne une valeur négative
- si la valeur est positive, la formule retourne une valeur positive
- si la valeur est 0, la formule retourne la valeur 0

## Usages
Il existe 1 "usage" pour ce type de formule :

|Type Paramètre|Type Retour|
|--------------|-----------|
|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|[`numérique`][valeur-de-retour]|

## Syntaxe
Le signe s'écrit avec le mot clé `sign` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] à laquelle il faut appliquer la formule

    abs (<expression_de_type_entier_ou_numerique>)

## Exemples
    sign (1)        [= valeur numérique positive]
    sign(-0,001)    [= valeur numérique négative]
    sign (0)        [= 0,0]

    
[valeur-de-retour]: ../lexique.md#valeur-de-retour