# Formule "formule d'une règle" (FORMULA)

## Description

Cette formule représente l'appel à la formule d'une règle du plan

Cette formule retourne une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`booléen`][valeur-de-retour], [`date`][valeur-de-retour] ou [`durée`][valeur-de-retour] en fonction du type de la formule de la règle appelée, représentant la valeur de la formule d'une règle du plan

Cette formule permet de forcer l'évaluation de la formule d'une règle, même si celle-ci a déjà été évaluée (une règle n'étant évaluée qu'une et une seule fois)

## Usages

Il n'existe pas d'"usage" pour ce type de formule.

## Syntaxe

La formule de référence à une règle s'écrit simplementavec l'identifiant de la règle souhaitée suivi d'un point `.` et du mot clé `formula`

    <identitifiant_regle>.formula

`<identitifiant_regle>` peut être soit :
- le code
- le libellé (dans une des langues pour lesquelles il est définit)

## Exemples (façades et résultats fictifs)

    r_1.formula                         [= valeur de la formule (réévaluée) de la règle identifiée par "r_1"]
    personGeneralFirstname.formula      [= valeur de la formule (réévaluée) de la règle identifiée par "personGeneralFirstname"]


[valeur-de-retour]: ../../lexique.md#valeur-de-retour