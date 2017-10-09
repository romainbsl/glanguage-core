# Formule somme groupe avec correction d'erreur d'arrondi (SUMV)
## Description
Cette formule représente la somme "ventilée" des valeurs des règles "enfants" d'une règle

Cette formule retourne une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] correspondant à la somme des valeurs de toutes les règles "enfants" de la règle dont la référence (voir [formule de référence à une règle][formule-reference-regle]) est forunie en paramètre, en propageant les écarts dus aux arrondis

## Note
Le report de l’écart dû à l’arrondi n’est possible que sur une règle qui est elle-même arrondie
Pour les règles non arrondies, la valeur restera inchangée et le ‘delta’ est gardé pour être reporté dès que possible, c’est-à-dire sur la première règle arrondie
L’idéal est que toutes les règles "enfants" de la règle fournie en paramètre soient arrondies

__ATTENTION__ :
Toutes les règles "enfants" de la règle dont la référence est fournie en paramètre doivent avoir une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

## Usages
Il n'existe pas d' "usage" pour ce type de formule

## Type
Le type de cette formule dépend du type des valeurs des règles "enfants" de la règle fournie en paramètre :
- si les valeurs de toutes les règles "enfants" sont de type [`entier`][valeur-de-retour], la formule retourne une valeur de type [`entier`][valeur-de-retour]
- si la valeur d'au moins une règle "enfant" est de type [`numérique`][valeur-de-retour], la formule retourne une valeur de type [`numérique`][valeur-de-retour]

## Syntaxe
Le test d'existence s'écrit avec le mot clé `sumv` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression représentant une [formule de référence à une règle][formule-reference-regle] 

    sumv (<reference_a_une_regle>)

## Exemples
    sumv (regle_parent)    [= somme des valeurs de toutes les règles "enfants" de la règle "regle_parent"]

`regle_parent` (voir [formule de référence à une règle][formule-reference-regle])

## Explication
Notons :

- &epsilon;`R` : la valeur exacte d'une règle
- `R` : la valeur arrondie de la règle (l’arrondi est demandé par l’auteur de la règle)
- &delta;`R` : (delta) l'erreur d'arrondi, soit : &delta;`R` = &epsilon;`R` - `R`

Soit `G` une règle comprenant quatre règles "enfants" arrondies au mieux : `R1`, `R2`, `R3`, `R4` et `R5`, et soit :

| |Valeur exacte|Valeur arrondie|Delta|
|-|-------------:|---------------:|-----:|
| |&epsilon;`R1` = 100,4|`R1` = 100|&delta;`R1` = 0,4|
| |&epsilon;`R2` = 100,4|`R2` = 100|&delta;`R2` = 0,4|
| |&epsilon;`R3` = 100,4|`R3` = 100|&delta;`R3` = 0,4|
| |&epsilon;`R4` = 100,4|`R4` = 100|&delta;`R4` = 0,4|
| |&epsilon;`R5` = 100,3|`R5` = 100|&delta;`R5` = 0,4|
|Total :|501,9|500|1,9|

Ainsi, on aura : 
> sum (G) = __500__

alors que : 
> rounded (&epsilon;R1 + &epsilon;R2 + &epsilon;R3 + &epsilon;R4 + &epsilon;R5) = __502__

En appliquant `sumv` sur `G`, on va effectuer une répartition des &delta; ci-dessus, de manière à ce que les écarts dus aux arrondis ne soient pas perdus

Ces écarts vont être propagés de règle en règle, et modifier par conséquent les valeurs de ces dernières pour tenir compte de la rectification

Pour l’exemple ci-dessus, on aura, après évaluation de  `sumv (G)` :

<table>
    <tr>
        <th></th>
        <th>Valeur exacte</th>
        <th>Valeur rectifiée</th>
        <th>Valeur arrondie</th>
        <th>delta</th>
    </tr>
    <tr>
        <th></th>
        <th>Initiale</th>
        <th>Après report</th>
        <th>Après report</th>
        <th>Après report</th>
    </tr>
    <tr>
        <td></td>
        <td align="right">&epsilon;R1 = 100,4</td>
        <td align="right">100,4</td>
        <td align="right">R1 = 100</td>
        <td align="right">&delta;R1 =  0,4</td>
    </tr>
    <tr>
        <td></td>
        <td align="right">&epsilon;R2 = 100,4</td>
        <td align="right">100,8</td>
        <td align="right">R2 = 101</td>
        <td align="right">&delta;R2 =  -0,2</td>
    </tr>
    <tr>
        <td></td>
        <td align="right">&epsilon;R3 = 100,4</td>
        <td align="right">100,2</td>
        <td align="right">R3 = 100</td>
        <td align="right">&delta;R3 =  0,2</td>
    </tr>
    <tr>
        <td></td>
        <td align="right">&epsilon;R4 = 100,4</td>
        <td align="right">100,6</td>
        <td align="right">R4 = 101</td>
        <td align="right">&delta;R4 =  -0,4</td>
    </tr>
    <tr>
        <td></td>
        <td align="right">&epsilon;R5 = 100,3</td>
        <td align="right">99,9</td>
        <td align="right">R5 = 100</td>
        <td align="right">&delta;R5 =  -0,1</td>
    </tr>
    <tr>
        <td>Total :</td>
        <td align="right">501.9</td>
        <td align="right"</td>
        <td align="right">502</td>
        <td align="right">-0,1</td>
    </tr>
</table>

Ainsi, on aura : 
> sumv (G) = __502__

En résumé, la fonction `sumv` possède les caractéristiques suivantes :
- Retourne la somme d’un ensemble de règles arrondies de manière à ce que cette somme soit égale à l’arrondi de la somme de leurs valeurs exactes, dans la mesure du possible
- Rectifie les règles de manière à ce que la somme obtenue (somme des arrondis) soit effectivement égale à l’arrondi de la somme de leurs valeurs exactes
- Les calculs se font comme montré ci-dessus, avec report uniquement sur les règles arrondies au mieux
- Le report se fait au mieux en fonction de l'évaluation des règles
- L'ordre d'évaluation étant important, si une règle est déjà calculée, on ne reporte pas d'arrondi dessus
- Si aucun report n'est possible, l'erreur est signalée
- Le cas idéal est : toutes les règles sont arrondies et aucune n'est évaluée
- L’écart entre le résultat de `sumv` et l’arrondi de la somme des valeurs exactes est majoré en valeur absolue par 0.5 (dû au dernier arrondi effectué) si tout s’est passé dans les conditions idéales, c’est-à-dire : les reports n’ont pas été rendus impossibles par le fait qu’une des règles n’ait pas été marqué arrondie et que ces mêmes règles n’aient pas été évaluées avant l’appel à `sumv`. Cet écart est en fait le dernier ‘delta après report’ calculé (&delta;`R5` ci-dessus).



[valeur-de-retour]: ../../lexique.md#valeur-de-retour
[formule-reference-regle]: ../call/rule_reference.md 