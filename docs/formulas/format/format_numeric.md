# Formule de formatage de valeur numérique (FORMAT_NUMERIQUE)

## Description

Cette formule représente le formatage d'un paramètre de type  [`numérique`][valeur-de-retour], en fonction de paramètres.

Cette formule retourne une valeur de type [`chaine de caractères`][valeur-de-retour] correspondant au premier paramètre fourni, formaté en fonction de 6 autres paramètres (optionels) fournis.

## Paramètres
Les paramètres de cette formule peuvent être regroupés en 5 groupes :
- `Groupe 1` :
    - `valeur à formater` : paramètre de type [`nuùérique`][valeur-de-retour]
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
        - __ATTENTION__ : le `caractère de remplissage`, bien que de type [`chaine de caractères`][valeur-de-retour], doit obligatoirement avoir une longueur (un nombre de caractères) égale à 1

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
- `Groupe 4` (optionel) :
    - `nombre de décimales` : paramètre de type [`entier`][valeur-de-retour]
        - Valeur par défaut : `2`
        - __ATTENTION__ : si `nombre de décimales` est inférieur aux nombre de décimales significatives de la `valeur à formater`, un arrondi arithmétique est appliqué afin d'obtenir le nombre de décimales souhaité

- `Groupe 5`
    - `caractère décimal` : paramètre de type  [`chaine de caractères`][valeur-de-retour]
        - Valeurs possibles :
            - `"."`
            - `","`
        - Valeur par défaut : `","`

Le `groupe 1` est toujours présent

Les `groupe 2`, `groupe 3`, `groupe 4`, `groupe 4` peuvent être présents ou non

Les `groupes` et les paramètres des groupes doivent apparaître en suivant l'ordre de définition ci-dessus

Si un paramètre d'un `groupe` est présent, tous les autres paramètres du `groupe` doivent obligatoirement être présents également

Si le `groupe 2` est absent, aucun formatage ne sera effectué (longueure, alignement, caractère de remplissage)

Si le `groupe 3` est absent, la valeur par défaut (`"negativeonly"`) sera appliquée ; seul le signe négatif sera affiché si la valeur à formater est négative

Si le `groupe 4` est absent, la valeur par défaut `2`sera appliquée ; le résultat aura 2 décimales

Si le `groupe 5` est absent, la valeur par défaut `","`sera appliquée ; le résultat aura une virgule `,` comme caractère décimal

## Usages

Il existe 15 "usages" pour ce type de formule en fonction des `groupes` de paramètres présents :

### 1. Avec seulement le `groupe 1`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|

### 2. Avec les `groupe 1` et `groupe 2`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|

### 3. Avec les `groupe 1`, `groupe 2` et `groupe 3`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|

### 4. Avec les `groupe 1`, `groupe 2` et `groupe 4`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`nombre de décimales`|[`entier`][valeur-de-retour]|

### 5. Avec les `groupe 1`, `groupe 2` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 6. Avec les `groupe 1`, `groupe 2`, `groupe 3` et `groupe 4`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|6|`nombre de décimales`|[`entier`][valeur-de-retour]|

### 7. Avec les `groupe 1`, `groupe 2`, `groupe 3` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|6|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 8. Avec les `groupe 1`, `groupe 2`, `groupe 3`, `groupe 4` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`longueure minimum`|[`entier`][valeur-de-retour]|
|3|`alignement`|[`chaine de caractères`][valeur-de-retour]|
|4|`caractère de remplissage`|[`chaine de caractères`][valeur-de-retour]|
|5|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|6|`nombre de décimales`|[`entier`][valeur-de-retour]|
|7|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 9. Avec les `groupe 1` et `groupe 3`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|

### 10. Avec les `groupe 1`, `groupe 3` et `groupe 4`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|3|`nombre de décimales`|[`entier`][valeur-de-retour]|

### 11. Avec les `groupe 1`, `groupe 3` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|3|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 12. Avec les `groupe 1`, `groupe 3`, `groupe 4` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`affichage du signe`|[`chaine de caractères`][valeur-de-retour]|
|3|`nombre de décimales`|[`entier`][valeur-de-retour]|
|4|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 13. Avec les `groupe 1`, et `groupe 4`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`nombre de décimales`|[`entier`][valeur-de-retour]|

### 14 Avec les `groupe 1`, `groupe 4` et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`nombre de décimales`|[`entier`][valeur-de-retour]|
|3|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

### 15. Avec les `groupe 1`, et `groupe 5`
|#|Signification|Type|
|-------------------|-------------|----|
|1|`valeur à formater`|[`numérique`][valeur-de-retour]|
|2|`caractère décimal`|[`chaine de caractères`][valeur-de-retour]|

## Type 
Le type de cette formule est toujours [`chaine de caractères`][valeur-de-retour]

## Syntaxe

Le formatage de valeur entière s'écrit avec le mot clé `formatNumeric` suivi ou non d'un espace et, entre parenthèse `( )` d'une expression de type [`entier`][valeur-de-retour] (, et de manière optionelle d'une ou plusieurs expressions de type [`entier`][valeur-de-retour] ou [`chaine de caractères`][valeur-de-retour] représentant les options de formatage séparées par un point-virgule `;`).

    formatNumeric (<numerique_a_formater> [ ; <longueure> ; <alignement> ; <caractere_de_remplissage>] [ ; <signe> ] [ ; <nombre_de_decimales>] [ ; <caractere_decimal>] )

## Exemples

    formatNumeric(1,0)                                                  [= "1,00"]
    formatNumeric(-1,0)                                                 [= "-1,00"]

    formatNumeric(1,0 ; 8 ; "left" ; "#")                               [= "1,00####"]
    formatNumeric(-1,0 ; 8 ; "left" ; "#")                              [= "-1,00###"]
    formatNumeric(1,0 ; 8 ; "right" ; "#")                              [= "####1,00"]
    formatNumeric(-1,0 ; 8 ; "right" ; "#")                             [= "###-1,00"]
    formatNumeric(1,0 ; 8 ; "center" ; "#")                             [= "##1,00##"]
    formatNumeric(-1,0 ; 8 ; "center" ; "#")                            [= "##-1,00#"]

    formatNumeric(1 ; "none")                                           [= "1,00"]
    formatNumeric(-1 ; "none")                                          [= "1,00"]
    formatNumeric(1 ; "both")                                           [= "+1,00"]
    formatNumeric(-1 ; "both")                                          [= "-1,00"]
    formatNumeric(1 ; "positiveonly")                                   [= "+1,00"]
    formatNumeric(-1 ; "positiveonly")                                  [= "1,00"]
    formatNumeric(1 ; "negativeonly")                                   [= "1,00"]
    formatNumeric(-1 ; "negativeonly")                                  [= "-1,00"]

    formatNumeric(1 ; 4)                                                [= "1,0000"]
    formatNumeric(1 ; 0)                                                [= "1"]

    formatNumeric(1 ; ",")                                              [= "1,00"]
    formatNumeric(1 ; ".")                                              [= "1.00"]

    formatNumeric(-1 ; 8 ; "left" ; "#" ; "none" ; 4 ; ".")             [= "1.0000##"]
    formatNumeric(1 ; 8 ; "left" ; "#", "both" ; 4 ; ".")               [= "+1.0000#"]
    formatNumeric(-1 ; 8 ; "right" ; "#" ; "positiveonly" ; 0)          [= "#######1"]
    formatNumeric(1 ; 8 ; "center" ; "#"; "negativeonly"; 4 ; ",")      [= "#1,0000#"]
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour