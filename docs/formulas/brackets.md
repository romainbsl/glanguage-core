# Formule parenthèses (BACKETS)

## Description

Cette formule représente l'encapsulation d'une ou plusieurs formules, faisant partie(s) d'une formule supérieure plus complexe.

Cette formule retourne une valeur ayant le même type que la résultante des formules qu'elle encapsule.

## Usages

Il n'existe pas d'usage particuliers pour la formule parenthèses, puisque celle-ci ne sert qu'a orchestrer les formules qui l'entoure, par isolation.

## Syntaxe

La formule de parenthèses s'utilise avec les symboles `(`et `)`. Entre ses parenthèses on retrouve la ou les formule(s) à appliquer.

    `(`<expression_de_type_formule> [;<expression_de_type_formule>...] `)`

## Exemples

    1 + `(` 2 `)` * 2 [= 1 + 2 * 2 = 4]
    `(` 1 + 2 `)` * 2 [= 3 * 2 = 6]
    1 + `(` 2 * 2 `)` [= 1 + 4 = 5]