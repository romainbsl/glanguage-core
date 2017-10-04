# Formule de formatage de valeur entière (FORMAT_INTEGER)

## Description

Cette formule représente le formatage d'un paramètre de type  [`entier`][valeur-de-retour], en fonction de paramètres.

Cette formule retourne une valeur de type [`chaine de caractères`][valeur-de-retour] correspondant au premier paramètre fourni, formaté en fonction de quatres autres paramètres (optionels) fournis.

## Paramètres
Les paramètres de cette formule peuvent être regroupés en 3 groupes :
- `Groupe 1` :
    - `valeur à formater` : paramètre de type [`entier`][valeur-de-retour]
- `Groupe 2` (optionel) :
    - `longueure (nombre de caractères) minimum` après formattage : paramètre de type [`entier`][valeur-de-retour]
    - `aligement` : paramètre de type [`chaine de caractères`][valeur-de-retour]
        - Valeurs possibles :
            <table>
                <tr>
                    <th>Valeur</th>
                    <th>Signification&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
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
        - __ATTENTION__ : le caractère de remplissage, bien que de type [`chaine de caractères`][valeur-de-retour], doit obligatoirement avoir une longueur (un nombre de caractères) égale à 1

- `Groupe 3` (optionel) : 
    - `affichage du signe` : paramètre de type [`chaine de caractères`][valeur-de-retour]
        - Valeurs possibles :
            <table>
                <tr>
                    <th>Valeur</th>
                    <th>Signification</th>
                </tr>
                <tr>
                    <td>`"none"`</td>
                    <td>pas de signe</td>
                </tr>
                <tr>
                    <td>`"both"`</td>
                    <td>signe négatif et positif</td>
                </tr>
                <tr>
                    <td>`"negativeonly"`</td>
                    <td>signe négatif uniquement</td>
                </tr>
                <tr>
                    <td>`"positiveonly"`</td>
                    <td>signe positif uniquement</td>
                </tr>
            </table>
        - Valeur par défaut : `"negativeonly"`


Le `groupe 1` est toujours présent

Les `groupe 2` et `groupe 3` peuvent être présents ou non

Si un paramètre d'un `groupe` est présent, tous les autres paramètres du `groupe` doivent obligatoirement être présents également

Si le `groupe 2` est absent, aucun formatage ne sera effectué (longueure, alignement, caractère de remplissage)

Si le `groupe 3` est absent, la valeur par défaut (`"negativeonly"`) sera appliquée ; seul le signe négatif sera affiché si la valeur à formater est négative

## Usages

Il existe 4 "usages" pour ce type de formule en fonction des `groupes` de paramètres présents :

### 1. Avec seulement le `groupe 1`
|Type Paramètre|Type retour|
|--------------|-----------|
|[`entier`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|

### 2. Avec les `groupe 1` et `groupe 2`
|Type Paramètre 1|Type Paramètre 2|Type Paramètre 3|Type Paramètre 4|Type retour|
|----------------|----------------|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|

### 3. Avec (tous) les `groupe 1`, `groupe 2` et `groupe 3`
|Type Paramètre 1|Type Paramètre 2|Type Paramètre 3|Type Paramètre 4|Type Paramètre 5|Type retour|
|----------------|----------------|----------------|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|

### 4. Avec les `groupe 1` et `groupe 3`
|Type Paramètre 1|Type Paramètre 2|Type retour|
|----------------|----------------|-----------|
|[`entier`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|[`chaine de caractères`][valeur-de-retour]|

## Type 
Le type de cette formule est toujours [`chaine de caractères`][valeur-de-retour]

## Syntaxe

Le formatage de valeur entière s'écrit avec le mot clé `formatInteger` suivi ou non d'un espace et, entre parenthèse `( )` d'une expression de type [`entier`][valeur-de-retour] (, et de manière optionelle d'une ou plusieurs expressions de type [`entier`][valeur-de-retour] ou [`chaine de caractères`][valeur-de-retour] représentant les options de formatage séparées par un point-virgule `;`).

    formatInteger (<entier_a_formater> [ ; <longueure> ; <alignement> ; <caractère_de_remplissage>] [ ; <signe> ] )

## Exemples

    formatInteger(1)                                            [= "1"]
    formatInteger(-1)                                           [= "-1"]

    formatInteger(1 ; 5 ; "left" ; "#")                         [= "1####"]
    formatInteger(-1 ; 5 ; "left" ; "#")                        [= "-1###"]
    formatInteger(1 ; 5 ; "right" ; "#")                        [= "####1"]
    formatInteger(-1 ; 5 ; "right" ; "#")                       [= "###-1"]
    formatInteger(1 ; 5 ; "center" ; "#")                       [= "##1##"]
    formatInteger(-1 ; 5 ; "center" ; "#")                      [= "##-1#"]

    formatInteger(1 ; "none")                                   [= "1"]
    formatInteger(-1 ; "none")                                  [= "1"]
    formatInteger(1 ; "both")                                   [= "+1"]
    formatInteger(-1 ; "both")                                  [= "-1"]
    formatInteger(1 ; "positiveonly")                           [= "+1"]
    formatInteger(-1 ; "positiveonly")                          [= "1"]
    formatInteger(1 ; "negativeonly")                           [= "1"]
    formatInteger(-1 ; "negativeonly")                          [= "-1"]

    formatInteger(-1 ; 5 ; "left" ; "#" ; "none")               [= "1####"]
    formatInteger(1 ; 5 ; "left" ; "#", "both")                 [= "+1###"]
    formatInteger(-1 ; 5 ; "right" ; "#" ; "positiveonly")      [= "####1"]
    formatInteger(1 ; 5 ; "center" ; "#"; "negativeonly")       [= "##1##"]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour