# Formule sous-chaîne de caractères (SUBSTRING)
## Description
Cette formule représente une partie d'une [`chaîne de caractères`][valeur-de-retour] 

Cette formule retourne une valeur de type [`chaîne de caractères`][valeur-de-retour] correspondant à la partie de la chaîne de caractères, fournie paramètre, comprise entre un index dé début et un idex de fin, forunis en paramètre

__ATTENTION__

Une erreur rendant l'évaluation de la formule impossible surviendra dans les cas suivants :
- la chaîne de caractères est vide ("")
- l'index de début est inférieur à 0
- l'index de debut est supérieur à l'index de fin
- l'index de fin est supérieur à la longueure (nombre de caractère) de la chaîne de caractères

## Usages
Il existe 1 "usage" pour ce type de formule :

|Type Paramètre 1|Type Paramètre 2|Type Paramètre 3|Type Retour|
|----------------|----------------|----------------|-----------|
|[`chaîne de caractères`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`optionel`] [`entier`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|

### Paramètres
- Paramètre 1 :
    - [`chaîne de caractères`][valeur-de-retour] représentant la chaîne de caractères à séparer en fonction du séparateur (paramètre 2) et partir de laquelle un élément doit être extrait
- Paramètre 2 :
    - [`entier`][valeur-de-retour] représentant l'index de début (commencant à 1) de la partie à extraire de la chaîne de caractères (paramètre 1)
- Paramètre 3 :
    - [`entier`][valeur-de-retour] représentant l'index de fin (commencant à 1) de la partie à extraire de la chaîne de caractères (paramètre 1)
    - `optionel`, ce qui signifie qu'il peut être omis
        - `valeur par défaut` : la longueure (nombre de caractère) de la chaîne de caractères

## Type
Le type de cette formule est toujours [`chaîne de caractères`][valeur-de-retour]

## Syntaxe
Cette formule s'écrit avec le mot clé `subString` suivi des paramètres entre parenthèses `( )` et séparés par un point-vrigule `;`

    subString ( <chaine_de_caractère> ; <index_debut> [; <index_fin>] )

## Exemples
    subString ("bar:foo:bar", 1)           [= "bar:foo:bar"]
    subString ("bar:foo:bar", 4)           [= ":foo:bar"]
    subString ("bar:foo:bar", 4, 8)        [= ":foo:"]
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour