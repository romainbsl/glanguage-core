# Formule heures (days)
## Description
Cette formule représente soit :
- une [`durée`][valeur-de-retour] correspondant à un nombre de jours
- un nombre [`entier`][valeur-de-retour] de jours coorrespondant à une durée
- un nombre [`entier`][valeur-de-retour] correspondant à la partie jour d'une date

Cette formule retourne soit :
- une valeur de type [`durée`][valeur-de-retour] correspondant à la durée d'un nombre [`entier`][valeur-de-retour] de jours fourni en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant au nombre de jours comprise dans la [`durée`][valeur-de-retour] fournie en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant à la partie jour d'une [`date`][valeur-de-retour] fournie en paramètre

## Usages
Il existe 3 "usages" pour ce type de formule :

### 1. Avec 1 [`entier`][valeur-de-retour] représentant un nombre de jours :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`durée`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le nombre de jours à convertir en durée

### 2. Avec 1 [`durée`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`durée`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`durée`][valeur-de-retour] représentant la durée à convertir en nombre de jours

### 3. Avec 1 [`date`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`date`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`date`][valeur-de-retour] à partir de laquelle la partie jour doit être extraite

## Type
Le type de cette formule peut être soit [`entier`][valeur-de-retour] soit [`durée`][valeur-de-retour] en fonction du type de son paramètre (voir [Usages](#usages))

## Syntaxe
Cette formule s'écrit avec le mot clé `days` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`durée`][valeur-de-retour] ou d'1 expression de type [`entier`][valeur-de-retour] ou d'1 expression de type [`date`][valeur-de-retour]

    days ( <expression_de_type_duree> )
    days ( <expression_de_type_entier> )
    days ( <expression_de_type_date> )
    
## Exemples
    days ('P1M1DT1H1M1')        [= 32 (durée de 1 mois 1 jour 1 heure 1 minute et 1 seconde = 32 jours (1 mois = 31 jours))]
    days (32)                   [= 'P32D' (durée de 32 jours)]
    days ('31/12/2000')         [= 31]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour
