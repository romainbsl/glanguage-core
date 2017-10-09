# Formule multiplication groupe (MULT)
## Description
Cette formule représente la multiplication des valeurs des règles "enfants" d'une règle

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] correspondant à la multiplication des valeurs de toutes les règles "enfants" de la règle dont la référence (voir [formule de référence à une règle][formule-reference-regle]) est forunie en paramètre

__ATTENTION__ :
Toutes les règles "enfants" de la règle dont la référence est fournie en paramètre doivent avoir une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

## Usages
Il n'existe pas d' "usage" pour ce type de formule

## Type
Le type de cette formule dépend du type des valeurs des règles "enfants" de la règle fournie en paramètre :
- si les valeurs de toutes les règles "enfants" sont de type [`entier`][valeur-de-retour], la formule retourne une valeur de type [`entier`][valeur-de-retour]
- si la valeur d'au moins une règle "enfant" est de type [`numérique`][valeur-de-retour], la formule retourne une valeur de type [`numérique`][valeur-de-retour]

## Syntaxe
Le test d'existence s'écrit avec le mot clé `mult` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression représentant une [formule de référence à une règle][formule-reference-regle] 

    mult (<reference_a_une_regle>)

## Exemples
    mult (regle_parent)    [= somme des valeurs de toutes les règles "enfants" de la règle "regle_parent"]

`regle_parent` (voir [formule de référence à une règle][formule-reference-regle])
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour
[formule-reference-regle]: ../call/rule_reference.md 