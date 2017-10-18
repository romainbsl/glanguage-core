# Formule date (DATE)
## Description
Cette formule représente une [`date`][valeur-de-retour] 

Cette formule retourne une valeur de type [`date`][valeur-de-retour] correspondant à la date décrite un paramètre de type [`chaîne de caractères`][valeur-de-retour] ou par 3 paramètres de type [`entier`][valeur-de-retour] représentant le jour, le mois et l'année, fournis en paramètre

## Usages
Il existe 2 "usage" pour ce type de formule :

### 1. Avec 1 [`chaîne de caractères`][valeur-de-retour] représentant une date :

|Type Paramètre 1|Type Retour|
|----------------|-----------|
|[`chaîne de caractères`][valeur-de-retour]|[`date`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`chaîne de caractères`][valeur-de-retour] représentant la date voulue au format `dd/MM/yyyy`

### 2. Avec 3 [`entier`][valeur-de-retour] représentant le jour, le mois et l'année

|Type Paramètre 1|Type Paramètre 2|Type Paramètre 3|Type Retour|
|----------------|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`date`][valeur-de-retour]|

#### Paramètres
- Paramètre 1 :
    - [`entier`][valeur-de-retour] représentant le jour de la date voulue
- Paramètre 2 :
    - [`entier`][valeur-de-retour] représentant le mois de la date voulue
- Paramètre 3 :
    - [`entier`][valeur-de-retour] représentant l'année de la date voulue

## Type
Le type de cette formule est toujours [`date`][valeur-de-retour]

## Syntaxe
Cette formule s'écrit avec le mot clé `date` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`chaîne de caractères`][valeur-de-retour] ou de 3 expression de type [`entier`][valeur-de-retour] séparées par un point-vrigule `;`

    date ( <chaine_de_caractère> )
    date ( <expression_de_type_entier_jour> ; <expression_de_type_entier_mois> ; <expression_de_type_entier_annee> )

## Exemples
    date ("01/01/2000")         [= 1/1/2000]
    date ("1/1/2000")           [= 1/1/2000]
    date (1 ; 1 ; 2000)         [= 1/1/2000]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour
