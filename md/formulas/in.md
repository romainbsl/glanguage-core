# Formule de contrôle  d'appartenance à une liste (IN)

## Description

Cette formule représente l'action de vérifier si une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`date`][valeur-de-retour], [`durée`][valeur-de-retour] ou [`booléen`][valeur-de-retour] se retrouve dans une liste de données, du même type.

Cette formule retourne une valeur de type [`booléen`][valeur-de-retour] correspondant au contrôle d'appartenance du paramètre fourni, au sein d'une liste définie:

- Si le premier paramètre est `1`, et que la liste est `(1;2;3;4;5)`, la formule retourne `true`.
- Si le premier paramètre est `1`, et que la liste est `(2;3;4;5:6)`, la formule retourne `false`.
- Si le premier paramètre est `1.0`, et que la liste est `(1.0;2.0;3.0;4.0;5.0)`, la formule retourne `true`.
- Si le premier paramètre est `1.0`, et que la liste est `(2.0;3.0;4.0;5.0;6.0)`, la formule retourne `false`.
- Si le premier paramètre est `1.0`, et que la liste est `(1;2;3;4;5;6)`, la formule retourne `true`.
- Si le premier paramètre est `1`, et que la liste est `(1.0;2.0;3.0;4.0;5.0;6.0)`, la formule retourne `true`.
- Si le premier paramètre est `'A'`, et que la liste est `('A';'B';'C';'D')`, la formule retourne `true`.
- Si le premier paramètre est `'A'`, et que la liste est `('B';'C';'D';'E')`, la formule retourne `false`.
- Si le premier paramètre est `31/03/2017`, et que la liste est `(28/03/2017; 29/03/2017; 30/03/2017; 31/03/2017)`, la formule retourne `true`.
- Si le premier paramètre est `31/03/2017`, et que la liste est `(01/01/2017; 31/12/2017)`, la formule retourne `true`.
- Si le premier paramètre est `P1Y`, et que la liste est `(P1Y; P1D; P1H)`, la formule retourne `true`.
- Si le premier paramètre est `P1H`, et que la liste est `(P1Y; P1D; P1M)`, la formule retourne `false`.
- Si le premier paramètre est `true`, et que la liste est `(; true; false)`, la formule retourne `true`.
- Si le premier paramètre est `true`, et que la liste est `(false; false; false)`, la formule retourne `false`.

## Usages

Il existe cinq 'usages' pour ce type de formule:

1. contrôle  d'appartenance à une liste pour une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour]

|#|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|obligatoire|
|2|Liste de valeurs de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

2. contrôle  d'appartenance à une liste pour une valeur de type [`chaine de caractères`][valeur-de-retour]

|#|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`chaine de caractères`][valeur-de-retour]|obligatoire|
|2|Liste de valeurs de type [`chaine de caractères`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

3. contrôle  d'appartenance à une liste pour une valeur de type [`date`][valeur-de-retour]

|#|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`date`][valeur-de-retour]|obligatoire|
|2|Liste de valeurs de type [`date`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

4. contrôle  d'appartenance à une liste pour une valeur de type [`durée`][valeur-de-retour]

|#|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`durée`][valeur-de-retour]|obligatoire|
|2|Liste de valeurs de type [`durée`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

5. contrôle  d'appartenance à une liste pour une valeur de type [`booléen`][valeur-de-retour]

|#|Type Paramètre|Présence|
|--------------|--------------|--------------|
|1|[`booléen`][valeur-de-retour]|obligatoire|
|2|Liste de valeurs de type [`booléen`][valeur-de-retour]|obligatoire|

> Type Retour: [`booléen`][valeur-de-retour]

## Syntaxe

La formule de contrôle d'appartenance d'une valeur dans une liste s'exprime avec le mot clé `in`, précédé de la valeur cible et suivi de la liste des valeurs de contrôle.

    <expression_de_type_entier_numerique> in ([list <expression_de_type_entier_numerique>])
    <expression_de_type_chaine_caractere> in ([list <expression_de_type_chaine_caractere>])
    <expression_de_type_date> in ([list <expression_de_type_date>])
    <expression_de_type_duree> in ([list <expression_de_type_duree>])
    <expression_de_type_booleen> in ([list <expression_de_type_booleen>])

## Exemples

    1 in (1;2;3;4;5)                                                          [= true ]
    1 in (2;3;4;5:6)                                                          [= false]
    1.0 in (1.0;2.0;3.0;4.0;5.0)                                              [= true ]
    1.0 in (2.0;3.0;4.0;5.0;6.0)                                              [= false]
    1.0 in (1;2;3;4;5;6)                                                      [= true ]
    1 in (1.0;2.0;3.0;4.0;5.0;6.0)                                            [= true ]
    'A' in ('A';'B';'C';'D')                                                  [= true ]
    'A' in ('B';'C';'D';'E')                                                  [= false]
    '31/03/2017' in ('28/03/2017'; '29/03/2017'; '30/03/2017'; '31/03/2017')  [= true ]
    '31/03/2017' in ('01/01/2017'; '31/12/2017')                              [= false]
    'P1Y' in ('P1Y'; 'P1D'; 'P1H')                                            [= true ]
    'P1H' in ('P1Y'; 'P1D'; 'P1M')                                            [= false]
    true in (; true; false)                                                   [= true ]
    true in (false; false; false)                                             [= false]

[valeur-de-retour]: ../lexique.md#valeur-de-retour