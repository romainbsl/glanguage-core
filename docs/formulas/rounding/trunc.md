# Formule d'arrondi tronqué (TRUNC)

## Description

Cette formule représente l'arrondi tronqué d'un paramètre de type  [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour], en fonction d'une précision de type [`entier`][valeur-de-retour].

Cette formule retourne une valeur de type [`numérique`][valeur-de-retour] correspondant au premier paramètre fourni, arrondi en fonction de la précision (optionelle) fournie en second paramètre.

> NB: La précision correspond au nombre de décimales à prendre en compte pour l'arrondi

- Si le prmier paramètre est `1` et que le second est non renseigné, la formule retourne `1.0`

- Si le prmier paramètre est `1` et que le second est `2`, la formule retourne `1.0`

- Si le prmier paramètre est `1.1245` et que le second est `2`, la formule retourne `1.12`

- Si le prmier paramètre est `1.124` et que le second est `2`, la formule retourne `1.12`

- Si le prmier paramètre est `1.125` et que le second est `2`, la formule retourne `1.12`

- Si le prmier paramètre est `1.1255` et que le second est `3`, la formule retourne `1.125`

- Si le prmier paramètre est `1.1255` et que le second est `2`, la formule retourne `1.12`

- Si le prmier paramètre est `1.126` et que le second est `2`, la formule retourne `1.12`

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Type Paramètre|Présence|Valeur par défaut|
|--------------|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|-|
|2|<ul><li>[`entier`][valeur-de-retour]</li>|optionelle|2|

> Type Retour: [`numérique`][valeur-de-retour]

## Syntaxe

L'arrondi tronqué d'un paramètre s'écrit avec le mot clé `trunc` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`entier`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    trunc (<expression_de_type_entier_ou_numerique>
    [;<expression_de_type_entier>] )

## Exemples

    trunc (1)             [= 1.0  ]
    trunc (1;2)           [= 1.0  ]
    trunc(1.1245;2)       [= 1.12 ]
    trunc(1.124;2)        [= 1.12 ]
    trunc(1.125;2)        [= 1.12 ]
    trunc(1.1255;3)       [= 1.125]
    trunc(1.1255;2)       [= 1.12 ]
    trunc(1.126;2)        [= 1.12 ]

[valeur-de-retour]: ../../lexique.md#valeur-de-retour