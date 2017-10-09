# Formule d'arrondi supérieur (CEIL)

## Description

Cette formule représente l'arrondi supérieur d'un paramètre de type  [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour], en fonction d'une précision de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour].

Cette formule retourne une valeur de type [`numérique`][valeur-de-retour] correspondant au premier paramètre (__A__) fourni, arrondi a la valeur supérieure, en fonction de la précision (optionelle) fournie en second paramètre (__B__).

> Formule mathématique appliquée: __résultat = arrondi_sup(A / B) * B__
>
> NB: Le résultat est un multiple de la précision (__B__)

- Si le prmier paramètre est `1` et que le second est non renseigné, la formule retourne `1.0`

        résultat = arrondi_sup(1 / 1) * 1
        résultat = arrondi_sup(1) * 1
        résultat = 1 * 1 = 1

- Si le prmier paramètre est `1` et que le second est `2`, la formule retourne `2.0`

        résultat = arrondi_sup(1 / 2) * 2
        résultat = arrondi_sup(0.5) * 2
        résultat = 1 * 2 = 0

- Si le prmier paramètre est `1.124` et que le second est `2`, la formule retourne `2.0`

        résultat = arrondi_sup(1.124 / 2) * 2
        résultat = arrondi_sup(0,562) * 2
        résultat = 1 * 2 = 2

- Si le prmier paramètre est `1.124` et que le second est `0.01`, la formule retourne `1.13`

        résultat = arrondi_sup(1.124 / 0.01) * 0.01
        résultat = arrondi_sup(112.4) * 0.01
        résultat = 113 * 0.01 = 1.13

- Si le prmier paramètre est `1.125` et que le second est `0.01`, la formule retourne `1.13`

        résultat = arrondi_sup(1.125 / 0.01) * 0.01
        résultat = arrondi_sup(112.5) * 0.01
        résultat = 113 * 0.01 = 1.13

- Si le prmier paramètre est `1.126` et que le second est `0.01`, la formule retourne `1.13`

        résultat = arrondi_sup(1.126 / 0.01) * 0.01
        résultat = arrondi_sup(112.6) * 0.01
        résultat = 113 * 0.01 = 1.13

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Type Paramètre|Présence|Valeur par défaut|
|--------------|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|-|
|2|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|optionelle|1|

> Type Retour: [`numérique`][valeur-de-retour]

## Syntaxe

L'arrondi supérieur d'un paramètre s'écrit avec le mot clé `ceil` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    ceil (<expression_de_type_entier_ou_numerique>
    [;<expression_de_type_entier_ou_numerique>] )

## Exemples

    ceil (1)             [= 1.0 ]
    ceil (1;2)           [= 2.0 ]
    ceil(1.124;2)        [= 2.0 ]
    ceil(1.124;0.01)     [= 1.13]
    ceil(1.125;0.01)     [= 1.13]
    ceil(1.126;0.01)     [= 1.13]

[valeur-de-retour]: ../../lexique.md#valeur-de-retour