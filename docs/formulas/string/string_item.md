# Formule élément de chaîne de caractères (STRING_ITEM)
## Description
Cette formule représente un élément d'une [`chaîne de caractères`][valeur-de-retour] 

Cette formule retourne une valeur de type [`chaîne de caractères`][valeur-de-retour] correspondant à l'élément à un certain index, foruni en paramètre, dans la liste des éléments résultant de la séparation de la chaîne de caractères, fournie paramètre, en fonction d'un séparateur foruni en paramètre

__ATTENTION__

Une chaîne de caractère vide ("") est retournée dans les cas suivants :
- la chaîne de caractère est vide ("")
- le séparateur est vide ("")
- l'index est inférieur ou égal à 0
- après la séparation de la chaîne de caractères en fonction du séparateur, il n'y a pas un nombre d'élément supérieur ou égal à l'index

## Usages
Il existe 1 "usage" pour ce type de formule :

|Type Paramètre 1|Type Paramètre 2|Type Paramètre 3|Type Retour|
|----------------|----------------|----------------|-----------|
|[`chaîne de caractères`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|[`entier`][valeur-de-retour]|[`chaîne de caractères`][valeur-de-retour]|

### Paramètres
- Paramètre 1 :
    - [`chaîne de caractères`][valeur-de-retour] représentant la chaîne de caractères à séparer en fonction du séparateur (paramètre 2) et partir de laquelle un élément doit être extrait
- Paramètre 2 :
    - [`chaîne de caractères`][valeur-de-retour] représentant le séparateur à utiliser pour séparer la chaîne de caractères (paramètre 1)
- Paramètre 3 :
    - [`entier`][valeur-de-retour] représentant l'index (commencant à 1) de l'élément à extraire de la chaîne de caractères (paramètre 1) séparée en fonction du séparateur (paramètre 2)

## Type
Le type de cette formule est toujours [`chaîne de caractères`][valeur-de-retour]

## Syntaxe
Cette formule s'écrit avec le mot clé `stringItem` suivi ou non d'un espace et, entre parenthèse `( )` de 2 expressions de type [`chaîne de caractères`][valeur-de-retour] et d'1 expression de type [`entier`][valeur-de-retour] séparées par un point-vrigule `;`

    stringItem ( <chaine_de_caractère> ; <separateur> ; <index> )

## Exemples
    stringItem ("bar:foo:bar", ":", 2)          [= "foo"]
    stringItem ("bar:foo:bar", "a", 2)          [= "r:foo:b"]
    stringItem ("", ":", 1)                     [= ""]
    stringItem ("bar:foo:bar", "", 2)           [= ""]
    stringItem ("bar:foo:bar", "z", 2)          [= ""]
    stringItem ("bar:foo:bar", ":", 0)          [= ""]
    stringItem ("bar:foo:bar", ":", 4)          [= ""]
    

[valeur-de-retour]: ../../lexique.md#valeur-de-retour
