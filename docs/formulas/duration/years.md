# Formule heures (years)
## Description
Cette formule représente soit :
- une [`durée`][valeur-de-retour] correspondant à un nombre d'années
- un nombre [`entier`][valeur-de-retour] d'années coorrespondant à une durée
- un nombre [`entier`][valeur-de-retour] correspondant à la partie année d'une date

Cette formule retourne soit :
- une valeur de type [`durée`][valeur-de-retour] correspondant à la durée d'un nombre [`entier`][valeur-de-retour] d'années fourni en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant au nombre d'années comprise dans la [`durée`][valeur-de-retour] fournie en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant à la partie année d'une [`date`][valeur-de-retour] fournie en paramètre

## Usages
Il existe 3 "usages" pour ce type de formule :

### 1. Avec 1 [`entier`][valeur-de-retour] représentant un nombre d'années :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`durée`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le nombre d'années à convertir en durée

### 2. Avec 1 [`durée`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`durée`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`durée`][valeur-de-retour] représentant la durée à convertir en nombre d'années

### 3. Avec 1 [`date`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`date`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`date`][valeur-de-retour] à partir de laquelle la partie année doit être extraite

## Type
Le type de cette formule peut être soit [`entier`][valeur-de-retour] soit [`durée`][valeur-de-retour] en fonction du type de son paramètre (voir [Usages](#usages))

## Syntaxe
Cette formule s'écrit avec le mot clé `years` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`durée`][valeur-de-retour] ou d'1 expression de type [`entier`][valeur-de-retour] ou d'1 expression de type [`date`][valeur-de-retour]

    years ( <expression_de_type_duree> )
    years ( <expression_de_type_entier> )
    years ( <expression_de_type_date> )
    
## Exemples
    years ('P1Y13M1DT1H1M1')        [= 2 (durée de 1 année 13 année 1 jour 1 heure 1 minute et 1 seconde = 2 années (12 mois = 1 année))]
    years (2)                       [= 'P2Y' (= durée de 2 années)]
    days ('31/12/2000')             [= 2000]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour
