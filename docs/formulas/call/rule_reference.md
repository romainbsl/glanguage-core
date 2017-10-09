# Formule de référence à une règle (RULE_REFERENCE)

## Description

Cette formule représente l'appel à une règle du plan

Cette formule retourne une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`booléen`][valeur-de-retour], [`date`][valeur-de-retour] ou [`durée`][valeur-de-retour] en fonction du type de la règle appelée, représentant la valeur d'une règle du plan

Cette formule a pour but de pouvoir réutiliser une règle (ou la valeur de celle-ci) dans les formules des autres règles

## Usages

Il n'existe pas d'"usage" pour ce type de formule.

## Syntaxe

La formule de référence à une règle s'écrit simplementavec l'identifiant de la règle souhaitée sans aucun autre caractère

    <identitifiant_regle>

`<identitifiant_regle>` peut être soit :
- le code
- le libellé (dans une des langues pour lesquelles il est définit)

## Exemples (façades et résultats fictifs)

    r_1                         [= valeur de la règle identifiée par "r_1"]
    personGeneralFirstname      [= valeur de la règle identifiée par "personGeneralFirstname"]


[valeur-de-retour]: ../../lexique.md#valeur-de-retour