# Formule de formatage de valeur de type chaîne de caractère (FORMAT_INTEGER)

## Description

Cette formule représente le formatage d'un paramètre de type [`chaine de caractères`][valeur-de-retour], en fonction de paramètres.

Cette formule retourne une valeur de type [`chaine de caractères`][valeur-de-retour] correspondant au premier paramètre fourni, formaté en fonction de quatres autres paramètres (optionels) fournis.

## Paramètres
Les paramètres de cette formule peuvent être regroupés en 3 groupes :
- `valeur à formater` : paramètre de type [`entier`][valeur-de-retour]
- `longueure (nombre de caractères)` après formattage : paramètre de type [`entier`][valeur-de-retour]
    - si le paramètre à une valeur inférieure ou égale à 0, une [`chaine de caractères`][valeur-de-retour] vide (`""`) sera retournée
    - si le paramètre à une valeur inférieure à la longueure de la `valeur à formater`, la `valeur à formater` sera tronquée à cette longueure avant d'être retournée
    - si le paramètre à une valeur égale à la longueure de la `valeur à formater`, la `valeur à formater` sera retournée telle quelle
    - si le paramètre à une valeur supérieure à la longueure de la `valeur à formater`, la `valeur à formater` sera formatée avant d'être retournée
- `aligement` : paramètre de type [`chaine de caractères`][valeur-de-retour]
    - Valeurs possibles :
        <table>
            <tr>
                <th>Valeur</th>
                <th>Signification</th>
            </tr>
            <tr>
                <td>`"left"`</td>
                <td align="left">alignement à gauche</td>
            </tr>
            <tr>
                <td>`"right"`</td>
                <td align="right">alignement à droite</td>
            </tr>
            <tr>
                <td>`"center"`</td>
                <td align="center">alignement au centre</td>
            </tr>
        </table>

- `caractère de remplissage` : paramètre de type [`chaine de caractères`][valeur-de-retour]
    - __ATTENTION__ : le `caractère de remplissage`, bien que de type [`chaine de caractères`][valeur-de-retour], doit obligatoirement avoir une longueur (un nombre de caractères) égale à 1

## Usages

Il existe 1 seul "usage" pour ce type de formule :

|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`chaine de caractères`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|

## Type 
Le type de cette formule est toujours [`chaine de caractères`][valeur-de-retour]

## Syntaxe

Le formatage de valeur de type chaîne de caractère s'écrit avec le mot clé `formatString` suivi ou non d'un espace et, entre parenthèse `( )` d'une expression de type [`chaine de caractères`][valeur-de-retour] représentant la `valeur à formater`, d'une expression de type [`entier`][valeur-de-retour] représentant la `longueure (nombre de caractères)` après formatage et de deux expressions de type [`chaine de caractères`][valeur-de-retour] représentant respectivement l'`alignement` et le `caractère de remplissage`, séparées par un point-virgule `;`.

    formatString (<entier_a_formater> ; <longueure> ; <alignement> ; <caractère_de_remplissage> )

## Exemples

    formatString("abc" ; 0 ; "left" ; "#")          [= ""]
    formatString("abc" ; 1 ; "left" ; "#")          [= "a"]
    formatString("abc" ; 5 ; "left" ; "#")          [= "abc##"]
    formatString("abc" ; 5 ; "center" ; "#")        [= "#abc#"]
    formatString("abc" ; 5 ; "right" ; "#")         [= "##abc"]    


[valeur-de-retour]: ../../lexique.md#valeur-de-retour