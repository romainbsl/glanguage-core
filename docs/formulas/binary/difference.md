# Formule de test de `DIFFERENCE` (EQUAL)

## Description

Cette formule représente le test de différnece entre deux paramètres de type  [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour] ou [`chaîne de caractères`][valeur-de-retour].

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant au contrôle de la différence entre deux paramètres fournis:

- Si le prmier paramètre est 123 et que le second est 123, la formule retourne `false`
- Si le prmier paramètre est 123 et que le second est 456, la formule retourne `true`
- Si le prmier paramètre est 12.3 et que le second est 12.3, la formule retourne `false`
- Si le prmier paramètre est 12.3 et que le second est 45.6, la formule retourne `true`
- Si le prmier paramètre est "ABC" et que le second est "ABC", la formule retourne `false`
- Si le prmier paramètre est "ABC" et que le second est "DEF", la formule retourne `true`

## Usages

Il existe deux "usages" pour ce type de formule :

1. Différence entre paramètres de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|
|2|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

2. Différence entre paramètres de type [`chaîne de caractères`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`chaîne de caractères`][valeur-de-retour]|obligatoire|
|2|[`chaîne de caractères`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

## Syntaxe

Le test de `DIFFERENCE` s'écrit avec le symbole `!=` suivi et précédé de deux expressions de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour] ou [`chaîne de caractères`][valeur-de-retour] auxquelles il faut appliquer la formule.

    <expression_de_type_entier_numerique> != <expression_de_type_entier_numerique>
    <expression_de_type_chaine_caractere> != <expression_de_type_chaine_caractere>

## Exemples

    123 != 123       [= false ]
    123 != 456       [= true  ]
    12.3 != 12.3     [= false ]
    12.3 != 45.6     [= true  ]
    "ABC" != "ABC"   [= false ]
    "ABC" != "DEF"   [= true  ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour