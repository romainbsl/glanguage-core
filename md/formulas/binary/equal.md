# Formule de test d'égalité (EQUAL)

## Description

Cette formule représente le test d'égalité entre deux paramètres de type  [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`date`][valeur-de-retour], [`durée`][valeur-de-retour] ou [`booléen`][valeur-de-retour].

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant au contrôle de l'égalité entre deux paramètres fournis:

- Si le prmier paramètre est 123 et que le second est 123, la formule retourne `true`
- Si le prmier paramètre est 123 et que le second est 456, la formule retourne `false`
- Si le prmier paramètre est 12.3 et que le second est 12.3, la formule retourne `true`
- Si le prmier paramètre est 12.3 et que le second est 45.6, la formule retourne `false`
- Si le prmier paramètre est "ABC" et que le second est "ABC", la formule retourne `true`
- Si le prmier paramètre est "ABC" et que le second est "DEF", la formule retourne `false`
- Si le prmier paramètre est `01/01/2017` et que le second est `01/01/2017`, la formule retourne `true`
- Si le prmier paramètre est `01/01/2017` et que le second est `31/12/2017`, la formule retourne `false`
- Si le prmier paramètre est `P1Y` et que le second est `P1Y`, la formule retourne `true`
- Si le prmier paramètre est `P1Y` et que le second est `P1D`, la formule retourne `false`
- Si le prmier paramètre est `true` et que le second est `true`, la formule retourne `true`
- Si le prmier paramètre est `true` et que le second est `false`, la formule retourne `false`

__ATTENTION__ :
Le test d'égalité ne peux se faire qu'entre certains type de données. Ce référer aux "usages" et à la syntaxe pour savoir quels types sont compatibles à la comparaison.

## Usages

Il existe cinq "usages" pour ce type de formule :

1. Egalité entre paramètres de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|
|2|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

2. Egalité entre paramètres de type [`chaîne de caractères`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`chaîne de caractères`][valeur-de-retour]|obligatoire|
|2|[`chaîne de caractères`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

3. Egalité entre paramètres de type [`date`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`date`][valeur-de-retour]|obligatoire|
|2|[`date`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

4. Egalité entre paramètres de type [`duree`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`duree`][valeur-de-retour]|obligatoire|
|2|[`duree`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

5. Egalité entre paramètres de type [`booléen`][valeur-de-retour]

|Numéro Paramètre|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`booléen`][valeur-de-retour]|obligatoire|
|2|[`booléen`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

## Syntaxe

Le test d'`EGALITE` s'écrit avec le symbole `=` suivi et précédé de deux expressions de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`date`][valeur-de-retour], [`durée`][valeur-de-retour] ou [`booléen`][valeur-de-retour] auxquelles il faut appliquer la formule.

    <expression_de_type_entier_numerique> = <expression_de_type_entier_numerique>
    <expression_de_type_chaine_caractere> = <expression_de_type_chaine_caractere>
    <expression_de_type_date> = <expression_de_type_date>
    <expression_de_type_duree> = <expression_de_type_duree>
    <expression_de_type_booleen> = <expression_de_type_booleen>

## Exemples

    123 = 123               [= true  ]
    123 = 456               [= false ]
    12.3 = 12.3             [= true  ]
    12.3 = 45.6             [= false ]
    "ABC" = "ABC"           [= true  ]
    "ABC" = "DEF"           [= false ]
    01/01/2017 = 01/01/2017 [= true  ]
    01/01/2017 = 31/12/2017 [= false ]
    'P1Y' = 'P1Y'           [= true  ]
    'P1Y' = 'P1D'           [= false ]
    true = true             [= true  ]
    true = false            [= false ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour