# Formule plus (PLUS)
# Description
Cette formule représente différentes opérations en fonction du type des paramètres :
- l'addition mathématique pour les valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]
- la concaténation pour les valeurs de type [`chaîne de caractères`][valeur-de-retour]
- le l'addition pour les valeurs de type [`date`][valeur-de-retour] et [`durée`][valeur-de-retour]

## Usages
Il existe plusieurs "usage" pour ce type de formule, un pour chaque combinaison de types de paramètre acceptée :

|Type Paramètre 1|Type Paramètre 2|Type Retour|
|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|
|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|[`numérique`][valeur-de-retour]|
|[`chaîne de caractères`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|
|[`date`][valeur-de-retour]|[`durée`][valeur-de-retour]|[`date`][valeur-de-retour]|
|[`durée`][valeur-de-retour]|[`date`][valeur-de-retour]|[`date`][valeur-de-retour]|
|[`durée`][valeur-de-retour]|[`durée`][valeur-de-retour]|[`durée`][valeur-de-retour]|

## Syntaxe
L'addition / la concaténation s'écrit avec des expressions séparées par un signe plus `+`

    <expression_1> + <expression_2>

## Exemples
    1 + 1                   [= 2]
    1 + 0,1                 [= 1,1]
    0,99 + 0,01             [= 1,0]
    '1/1/2000' + 'P1D'      [= '2/1/2000' (1/1/2000 + 1 jour)]
    'P1Y' + '1/1/2000'      [= '1/1/2001' (1 année + 1/1/2000)]
    'P1Y' + 'P1D'           [= 'P1Y1D' (1 année + 1 jour = 1 année et 1 jour)]
 

[valeur-de-retour]: ../../lexique.md#valeur-de-retour