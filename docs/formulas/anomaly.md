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

Cette formule s'écrit avec le mot clé `anomaly` suivi des paramètres entre parenthèses `( )` et séparés par un point-vrigule `;`

    anomaly(<expression_de_type_chaine_caractère>[;<expression_de_type_chaine_caractère>])

## Exemples

    anomaly('5152')
    anomaly('5152', 'Message')
    
    
[valeur-de-retour]: ../lexique.md#valeur-de-retour