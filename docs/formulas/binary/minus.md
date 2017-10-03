# Formule moins (MINUS)
# Description
Cette formule représente différentes opérations en fonction du type des paramètres :
- la soustraction mathématique pour les valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]
- la soustraction pour les valeurs de type [`date`][valeur-de-retour] et [`durée`][valeur-de-retour]

## Usages
Il existe plusieurs "usage" pour ce type de formule, un pour chaque combinaison de types de paramètre acceptée :

|Type Paramètre 1|Type Paramètre 2|Type Retour|
|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|
|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|[`numérique`][valeur-de-retour]|
|[`date`][valeur-de-retour]|[`durée`][valeur-de-retour]|[`date`][valeur-de-retour]|
|[`durée`][valeur-de-retour]|[`durée`][valeur-de-retour]|[`durée`][valeur-de-retour]|

## Syntaxe
La soustraction s'écrit avec des expressions séparées par un tiret `-`

    <expression_1> - <expression_2>

## Exemples
    1 - 1                   [= 0]
    1 - 0,1                 [= 0,9]
    0,99 - 0,01             [= 0,98]
    '1/1/2000' - 'P1D'      [= '31/12/1999' (1/1/2000 - 1 jour)]
    'P1Y' - 'P1D'           [= 'P364D' (1 année - 1 jour = 364 jours (1 année = 365 jours)]
 

[valeur-de-retour]: ../lexique.md#valeur-de-retour