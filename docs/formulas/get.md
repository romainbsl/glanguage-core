# Formule de référence à une donnée fournie par un tiers(GET)

## Description

Cette formule représente l'appel à une fonction externe au plan, définie en Java, appelée `Façade`.

Cette formule peut retourner une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`date`][valeur-de-retour], [`durée`][valeur-de-retour] ou [`booléen`][valeur-de-retour], définie en paramètre et en accord avec le tiers appelé.

## Usages

Il n'existe pas d'"usage" pour ce type de formule.

## Syntaxe

La formule de référence à une façade s'écrit avec le mot clé `get` suivi du type de donnée obtenu au travers de l'appel de cette façade, puis de l'appel à la façade elle même. cet appel peut être une succession d'appel de fonctions et de paramètres, tout comme en Java.

    get <type_de_retour_facade> {<facade> [(<parametre>';')] '.'}

## Exemples

    get STRING person.firstname         [= "James"      ]
    get INTEGER person.age              [= 40.0         ]
    get NUMERIC person.salary           [= 2000.0       ]
    get DATE person.birthDate           [= "01/01/1967" ]
    get DUREE person.workDayDuration    [= "P8H"        ]
    get BOOLEAN person.hasChildren      [= true         ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour