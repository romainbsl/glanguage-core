# Formule maximum signé (MAX)
## Description
Cette formule représente la valeur absolue maximale d'un ensemble de valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour], signée par le signe de la première valeur

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] correspondant à la valeur absolue maximale de toutes les valeurs fournies en paramètre, signée par le signe de la première valeur

Cette formule a au minimimum 2 paramètres

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

Du fait du caractère `obligatoire` du 1er paramètre et du caractère `optionel` et `répétable` du 2ème, cette formule accepte au minimum 1 paramètre et au maximum une infinité

## Type
Le type de cette formule dépend du type des valeurs des règles "enfants" de la règle fournie en paramètre :
- si les valeurs de toutes les règles "enfants" sont de type [`entier`][valeur-de-retour], la formule retourne une valeur de type [`entier`][valeur-de-retour]
- si la valeur d'au moins une règle "enfant" est de type [`numérique`][valeur-de-retour], la formule retourne une valeur de type [`numérique`][valeur-de-retour]

## Syntaxe
Le maximum signé s'écrit avec le mot clé `smax` suivi ou non d'un espace et, entre parenthèse `( )` d'une liste d'expressions représentant des valeurs de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] séparées par un point-virgule `;`

    smax ( <expression_de_type_entier_ou_numerique_1> { ; <expression_de_type_entier_ou_numerique_n>} )

## Exemples
    smax (0)                                [= 0]
    smax (1 ; 2 ; 3 ; 4)                    [= 4]
    smax (-1 ; 2 ; 3 ; 4)                   [= -4]
    smax (1 ; 2 ; 3 ; -4)                   [= 4]
    smax (-1 ; -2)                          [= -2]
    smax (1 ; 2,2 ; 3 ; 4)                  [= 4,0]
    smax (0,0)                              [= 0,0]
    smax (1,1 ; 2,2 ; 3,3 ; 4,4)            [= 4,4]
    smax (-1,1 ; 2,2 ; 3,3 ; 4,4)           [= -4,4]
    smax (1,1 ; 2,2 ; 3,3 ; -4,4)           [= 4,4]
    smax (-1,1 ; -2,2)                      [= -2,2]
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour