# Formule "pplicabilité d'une règle" (APPLICABILITY)

## Description

Cette formule représente l'appel à la condition d'applicabilité d'une règle du plan

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] représentant la valeur de la condition d'applicabilité d'une règle du plan

## Usages

Il n'existe pas d'"usage" pour ce type de formule.

## Syntaxe

La formule de référence à une règle s'écrit simplementavec l'identifiant de la règle souhaitée suivi d'un point `.` et du mot clé `applicable`

    <identitifiant_regle>.applicable

`<identitifiant_regle>` peut être soit :
- le code
- le libellé (dans une des langues pour lesquelles il est définit)

## Exemples (façades et résultats fictifs)

    r_1.applicable                          [= valeur de la condition d'applicabilité de la règle identifiée par "r_1"]
    personGeneralFirstname.applicable       [= true (par défaut, si pas de condition d'applicabilité)]


[valeur-de-retour]: ../../lexique.md#valeur-de-retour