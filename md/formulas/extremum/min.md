# Formule minimum (MIN)
## Description
Cette formule représente le minimum d'un ensemble de valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] correspondant à la valeur minimum de toutes les valeurs fournies en paramètre

## Usages
Il existe 1 "usage" pour ce type de formule :

|Type Paramètre 1|Type Paramètre 2|Type Retour|
|----------------|----------------|-----------|
|&nbsp;<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|[`optionel`][`répétable`]<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|&nbsp;<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|

### Paramètres
- Le 1er paramètre est 
    - `obligatoire` (non-optionel), ce qui signifie que sa présence est requise
- Le 2ème paramètre est 
    - `optionel`, ce qui signifie qu'il peut être omis
    - `répétable`, ce qui signifie que ce paramètre peut être répété une infinité de fois

Du fait du caractère `obligatoire` du 1er paramètre et du caractère `optionel` et `répétable` du 2ème, cette formule accepte au minimum 1 paramètre et au minimum une infinité

## Type
Le type de cette formule dépend du type des valeurs des règles "enfants" de la règle fournie en paramètre :
- si les valeurs de toutes les règles "enfants" sont de type [`entier`][valeur-de-retour], la formule retourne une valeur de type [`entier`][valeur-de-retour]
- si la valeur d'au moins une règle "enfant" est de type [`numérique`][valeur-de-retour], la formule retourne une valeur de type [`numérique`][valeur-de-retour]

## Syntaxe
Le minimum s'écrit avec le mot clé `min` suivi ou non d'un espace et, entre parenthèse `( )` d'une liste d'expressions représentant des valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] séparées par un point-virgule `;`

    min ( <expression_de_type_entier_ou_numerique_1> { ; <expression_de_type_entier_ou_numerique_n>} )

## Exemples
    min (0)                         [= 0]
    min (1 ; 2 ; 3 ; 4)             [= 1]
    min (-1 ; -2)                   [= -2]
    min (1 ; 2,2 ; 3 ; 4)           [= 1,0]
    min (0,0)                       [= 0,0]
    min (1,1 ; 2,2 ; 3,3 ; 4,4)     [= 1,1]
    min (-1,1 ; -2,2)               [= -2,2]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour