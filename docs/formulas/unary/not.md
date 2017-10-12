# Formule négation (NOT)
## Description
Cette formule représente l'opposé d'une valeur de type [`booléen`][valeur-de-retour]

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant à l'opposé de la valeur fournie en paramètre :
- si la valeur est `false`, la formule retourne la valeur `true`
- i la valeur est `true`, la formule retourne la valeur `false`

## Usages
Il existe un seul "usage" pour ce type de formule :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`booléen`][valeur-de-retour]|[`booléen`][valeur-de-retour]|

## Syntaxe
Cette formule s'écrit avec le mot clé `not` suivi d'une expression de type [`booléen`][valeur-de-retour]

    ! <expression_de_type_booléen>

## Exemples
    ! regle_de_type_booléen     [= dépend de de l'existence de la valeur de la règle "regle_de_type_booléen"]
    ! (x >= 0)                  [= dépend de la valeur de x]
    ! true                      [= false]

`regle_de_type_booléen` (voir [formule de référence à une règle][formule-reference-regle])
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour
[formule-reference-regle]: ../call/rule_reference.md 