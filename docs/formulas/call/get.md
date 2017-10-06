# Formule de référence à une donnée fournie par un tiers(GET)

## Description

Cette formule représente l'appel à une fonction externe au plan, définie en Java, appelée `Façade` et qui retourne une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`booléen`][valeur-de-retour], [`date`][valeur-de-retour] ou [`durée`][valeur-de-retour]

Cette formule retourne une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`booléen`][valeur-de-retour], [`date`][valeur-de-retour] ou [`durée`][valeur-de-retour] en fonction du type de la `Façade` appelée.

## Usages

Il n'existe pas d'"usage" pour ce type de formule.

## Syntaxe

La formule de référence à une façade s'écrit avec le mot clé `get` suivi du type de donnée obtenu au travers de l'appel de cette façade, puis de l'appel à la façade elle même. Cet appel peut être une succession d'appel de fonction avec ou sans paramètre,en utilisant la notation pointée tout comme en Java.

    get <type_de_retour_facade> <facade> [ ( <parametre> { ; <parametre> } ) ] {.<facade> [ (<parametre> { ; <parametre> } ) ] }

## Exemples (façades et résultats fictifs)

    get STRING person.firstname                     [= "James"]
    get INTEGER person.age                          [= 40.0]
    get NUMERIC person.salary                       [= 2000.0]
    get NUMERIC prestation.element("T10").montant   [= "01/01/2000"]
    get DATE person.birthDate                       [= "01/01/1967"]
    get DATE prestation.periode.dateDebut           [= "01/01/2000"]
    get DUREE person.workDayDuration                [= "PT8H" (durée de 8 heures)]
    get BOOLEAN person.hasChildren                  [= true]
    get BOOLEAN existInCodeList("CD_PAYS" ; "XX")   [= false]


[valeur-de-retour]: ../lexique.md#valeur-de-retour