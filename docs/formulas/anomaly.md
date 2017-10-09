# Formule anomalie (ANOMALY)

## Description

Cette formule a pour but d'enregistrer des messages d'erreur a destination d'autre application, via la base de données, dans la table ANOMALIE.

On passe 2 paramètres à la formule, un code d'erreur obligatoire et un message complémentaire, optionel.

## Usages

Il existe un seul 'usage' pour ce type de formule:

|#|Type Paramètre|
|---|---|
|1|[`chaîne de caractères`][valeur-de-retour]|obligatoire|
|2|[`chaîne de caractères`][valeur-de-retour]|optionel|

> Aucun retour

## Syntaxe

La formule anomalie s'écrit avec le mot clé `anomaly` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`chaine de caractères`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`chaine de caractères`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    anomaly(<expression_de_type_chaine_caractère>[;<expression_de_type_chaine_caractère>])

## Exemples

    anomaly('5152')
    anomaly('5152', 'Message')
    
    
[valeur-de-retour]: ../lexique.md#valeur-de-retour