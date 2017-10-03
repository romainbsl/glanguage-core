# Formule d'`EGALITE` (EQUAL)

## Description

Cette formule représente le test d'égalité entre deux paramètres de type  [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour] ou [`chaîne de caractères`][valeur-de-retour].

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant au contrôle de l'égalité entre deux paramètres fournis:

- Si le prmier paramètre est 123 et que le second est 123, la formule retourne `true`
- Si le prmier paramètre est 123 et que le second est 456, la formule retourne `false`
- Si le prmier paramètre est 12.3 et que le second est 12.3, la formule retourne `true`
- Si le prmier paramètre est 12.3 et que le second est 45.6, la formule retourne `false`
- Si le prmier paramètre est "ABC" et que le second est "ABC", la formule retourne `true`
- Si le prmier paramètre est "ABC" et que le second est "DEF", la formule retourne `false`

## Usages

Il existe deux "usages" pour ce type de formule :

1. Paramètres de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|
|2|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

2. Paramètres de type [`chaîne de caractères`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`chaîne de caractères`][valeur-de-retour]|obligatoire|
|2|[`chaîne de caractères`][valeur-de-retour]|obligatoire|

## Syntaxe

Le test d'`EGALITE` s'écrit avec le symbole `=` suivi et précédé de deux expressions de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour] ou [`chaîne de caractères`][valeur-de-retour] auxquelles il faut appliquer la formule.

    <expression_de_type_entier_numerique> = <expression_de_type_entier_numerique>
    <expression_de_type_chaine_caractere> = <expression_de_type_chaine_caractere>

## Exemples

    123 = 123       [= true  ]
    123 = 456       [= false ]
    12.3 = 12.3     [= true  ]
    12.3 = 45.6     [= false ]
    "ABC" = "ABC"   [= true  ]
    "ABC" = "DEF"   [= false ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour