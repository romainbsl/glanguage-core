# Formule parenthèses (BRACKETS)

## Description

Cette formule représente l'encapsulation d'une formule, pouvant elle même encapsuler plusieurs formules.

Cette formule retourne une valeur ayant le même type que la résultante de la formule qu'elle encapsule.

## Usages

Il existe six 'usages' pour ce type de formule formule.

1. Encapsulation d'expression de type [`entier`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|

2. Encapsulation d'expression de type [`numérique`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`numérique`][valeur-de-retour]|[`numérique`][valeur-de-retour]|

2. Encapsulation d'expression de type [`chaîne de caractères`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`chaîne de caractères`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|

3. Encapsulation d'expression de type [`date`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`date`][valeur-de-retour]|[`date`][valeur-de-retour]|

4. Encapsulation d'expression de type [`durée`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`durée`][valeur-de-retour]|[`durée`][valeur-de-retour]|

5. Encapsulation d'expression de type [`booléen`][valeur-de-retour]

|Type paramètre|Type de retour|
|--|--|
|[`booléen`][valeur-de-retour]|[`booléen`][valeur-de-retour]|

## Syntaxe

La formule de parenthèses s'écrit avec les symboles `(`et `)`. Entre ses parenthèses on retrouve la formule à appliquer.

    (<expression_de_type_formule>)

## Exemples

    1 + ( 2 ) * 2 [= 1 + 2 * 2 = 1 + 4 = 5]
    ( 1 + 2 ) * 2 [= 3 * 2 = 6]
    1 + ( 2 * 2 ) [= 1 + 4 = 5]