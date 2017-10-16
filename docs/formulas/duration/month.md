# Formule heures (months)
## Description
Cette formule représente soit :
- une [`durée`][valeur-de-retour] correspondant à un nombre de mois
- un nombre [`entier`][valeur-de-retour] de mois coorrespondant à une durée
- un nombre [`entier`][valeur-de-retour] correspondant à la partie mois d'une date

Cette formule retourne soit :
- une valeur de type [`durée`][valeur-de-retour] correspondant à la durée d'un nombre [`entier`][valeur-de-retour] de mois fourni en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant au nombre de mois comprise dans la [`durée`][valeur-de-retour] fournie en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant à la partie mois d'une [`date`][valeur-de-retour] fournie en paramètre

## Usages
Il existe 3 "usages" pour ce type de formule :

### 1. Avec 1 [`entier`][valeur-de-retour] représentant un nombre de mois :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`durée`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le nombre de mois à convertir en durée

### 2. Avec 1 [`durée`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`durée`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`durée`][valeur-de-retour] représentant la durée à convertir en nombre de mois

### 3. Avec 1 [`date`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`date`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`date`][valeur-de-retour] à partir de laquelle la partie mois doit être extraite

## Type
Le type de cette formule peut être soit [`entier`][valeur-de-retour] soit [`durée`][valeur-de-retour] en fonction du type de son paramètre (voir [Usages](#usages))

## Syntaxe
Cette formule s'écrit avec le mot clé `months` suivi du paramètre entre parenthèses `( )`

    months ( <expression_de_type_duree> )
    months ( <expression_de_type_entier> )
    months ( <expression_de_type_date> )
    
## Exemples
    months ('P1Y1M1DT1H1M1')        [= 13 (durée de 1 année 1 mois 1 jour 1 heure 1 minute et 1 seconde = 13 mois (1 année = 12 mois))]
    months (13)                     [= 'P13M' (= durée de 13 mois)]
    days ('31/12/2000')             [= 12]
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour
