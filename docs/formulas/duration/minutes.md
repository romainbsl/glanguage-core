# Formule minutes (MINUTES)
## Description
Cette formule représente soit :
- une [`durée`][valeur-de-retour] correspondant à un nombre de minutes
- un nombre [`entier`][valeur-de-retour] de minutes coorrespondant à une durée

Cette formule retourne soit :
- une valeur de type [`durée`][valeur-de-retour] correspondant à la durée d'un nombre [`entier`][valeur-de-retour] de minutes fourni en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant au nombre de minutes comprise dans la [`durée`][valeur-de-retour] fournie en paramètre

## Usages
Il existe 2 "usages" pour ce type de formule :

### 1. Avec 1 [`entier`][valeur-de-retour] représentant un nombre de minutes :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`durée`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le nombre de minutes à convertir en durée

### 2. Avec 1 [`durée`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`durée`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`durée`][valeur-de-retour] représentant la durée à convertir en nombre de minutes

## Type
Le type de cette formule peut être soit [`entier`][valeur-de-retour] soit [`durée`][valeur-de-retour] en fonction du type de son paramètre (voir [Usages](#usages))

## Syntaxe
Cette formule s'écrit avec le mot clé `minutes` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`durée`][valeur-de-retour] ou d'1 expression de type [`entier`][valeur-de-retour]

    daminutesys ( <expression_de_type_duree> )
    minutes ( <expression_de_type_entier> )
    
## Exemples
    minutes ('PT1H1M1')         [= 61 (durée de 1 heure 1 minute et 1 seconde = 61 minutes)]
    minutes (61)                [= 'PT61M' (= durée de 61 minutes)]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour
