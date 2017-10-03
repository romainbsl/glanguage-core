# Formule test d'existence (EXIST)
## Description
Cette formule représente un test d'existence d'une valeur

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] en fonction de l'existence ou non de la valeur à tester forunie en paramètre :
- si la valeur "existe", la formule retourne la valeur `true`
- si la valeur n' "existe" pas, la formule retourne la valeur `false`

Une valeur "existe" si celle-ci n'est pas nulle

__ATTENTION__ :
Une [`chaîne de caractères`][valeur-de-retour] vide ("") n'est pas nulle

## Usages
Il existe un seul "usage" pour ce type de formule :

|Type Paramètre|Type Retour|
|--------------|-----------|
|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li><li>[`chaîne de caractères`][valeur-de-retour]</li><li>[`booléen`][valeur-de-retour]</li><li>[`date`][valeur-de-retour]</li><li>[`durée`][valeur-de-retour]</li></ul> |[`booléen`][valeur-de-retour]|

## Syntaxe
Le test d'existence s'écrit avec un point d'intérogation  `?` suivi d'un espace et d'une expression à laquelle il faut appliquer le test

Dans la plupart des cas, l'expression est en fait une [formule de référence à une règle][formule-reference-regle], ce qui permet de tester, par exemple, la possibilité d'utiliser la valeur de cette règle dans une autre formule (utiliser une valeur nulle dans une formule peut mener à une erreur rendant l'évaluation impossible)

    ? <expression>

## Exemples
    ? regle_a_tester    [= dépend de de l'existence de la valeur de la règle "regle_a_tester"]
    ? ""                [= false]

`regle_a_tester` (voir [formule de référence à une règle][formule-reference-regle])


[valeur-de-retour]: ../lexique.md#valeur-de-retour
[formule-reference-regle]: ../call/rule_reference.md 