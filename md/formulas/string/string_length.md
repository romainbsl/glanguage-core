# Formule longueur de chaîne de caractères (STRING_LENGTH)
## Description
Cette formule représente la longueure (le nombre de caractère) d'une [`chaîne de caractères`][valeur-de-retour] 

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] correspondant à la longueure (le nombre de caractère) d'une chaîne de caractères fournie paramètre

## Usages
Il existe 1 "usage" pour ce type de formule :

|Type Paramètre|Type Retour|
|--------------|-----------|
|[`chaîne de caractères`][valeur-de-retour]|[`entier`][valeur-de-retour]|

### Paramètres
- Paramètre 1 :
    - [`chaîne de caractères`][valeur-de-retour] représentant la chaîne de caractères dont il faut déterminer la longueure (le nombre de caractère)

## Type
Le type de cette formule est toujours [`entier`][valeur-de-retour]

## Syntaxe
Cette formule s'écrit avec le mot clé `stringLength` suivi ou non d'un espace et, entre parenthèse `( )` d'1 expression de type [`chaîne de caractères`][valeur-de-retour]

    stringLength ( <chaine_de_caractère> )

## Exemples
    stringLength ("")                   [= 0]
    stringLength ("bar:foo:bar")        [= 11]
    

[valeur-de-retour]: ../lexique.md#valeur-de-retour