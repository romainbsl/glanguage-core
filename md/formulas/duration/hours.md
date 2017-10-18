# Formule heures (HOURS)
## Description
Cette formule représente soit :
- une [`durée`][valeur-de-retour] correspondant à un nombre d'heures
- un nombre [`entier`][valeur-de-retour] d'heures coorrespondant à une durée

Cette formule retourne soit :
- une valeur de type [`durée`][valeur-de-retour] correspondant à la durée d'un nombre [`entier`][valeur-de-retour] d'heures fourni en paramètre
- une valeur type [`entier`][valeur-de-retour] correspondant au nombre d'heures comprise dans la [`durée`][valeur-de-retour] fournie en paramètre

## Usages
Il existe 2 "usages" pour ce type de formule :

### 1. Avec 1 [`entier`][valeur-de-retour] représentant un nombre d'heures :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`durée`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le nombre d'heures à convertir en durée

### 2. Avec 1 [`durée`][valeur-de-retour] :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`durée`][valeur-de-retour]|[`entier`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`durée`][valeur-de-retour] représentant la durée à convertir en nombre d'heures

## Type
Le type de cette formule peut être soit [`entier`][valeur-de-retour] soit [`durée`][valeur-de-retour] en fonction du type de son paramètre (voir [Usages](#usages))

## Syntaxe
Cette formule s'écrit avec le mot clé `hours` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`durée`][valeur-de-retour] ou d'1 expression de type [`entier`][valeur-de-retour]

    hours ( <expression_de_type_duree> )
    hours ( <expression_de_type_entier> )
    
## Exemples
    hours ('P1DT1H1M1')         [= 25 (durée de 1 jour 1 heure 1 minute et 1 seconde = 25 heures)]
    hours (25)                  [= 'PT25H' (= durée de 25 heures)]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour
