# Formule d'arrondi arithmétique (ROUNDED)

## Description

Cette formule représente l'arrondi arithmétique d'un paramètre de type  [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour], en fonction d'une précision de type [`entier`][valeur-de-retour].

Cette formule retourne une valeur de type [`numérique`][valeur-de-retour] correspondant au premier paramètre fourni, arrondi en fonction de la précision fournie en second paramètre:

- Si le prmier paramètre est `123` et que le second est non renseigné, la formule retourne `123.0`
- Si le prmier paramètre est `123` et que le second est `2`, la formule retourne `123.00`
- Si le prmier paramètre est `123.123` et que le second est `2`, la formule retourne `123.12`
- Si le prmier paramètre est `123.125` et que le second est `2`, la formule retourne `123.13`
- Si le prmier paramètre est `123.126` et que le second est `2`, la formule retourne `123.13`

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Type Paramètre|Présence|Valeur par défaut|
|--------------|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|-|
|2|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|optionelle|1|

> Type Retour: [`numérique`][valeur-de-retour]

## Syntaxe

L'arrondi d'un paramètre s'écrit avec le mot clé `rounded` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`entier`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    rounded (<expression_de_type_entier_ou_numerique> [;<expression_de_type_entier>] )

## Exemples

    rounded (1)         [= 1.0 ]
    rounded (1;2)       [= 1.00]
    rounded(1.123;2)    [= 1.12]
    rounded(1.125;2)    [= 1.13]
    rounded(1.126;2)    [= 1.13]

[valeur-de-retour]: ../lexique.md#valeur-de-retour