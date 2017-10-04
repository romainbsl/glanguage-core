# Formule de formatage de valeur entière (FORMAT_INTEGER)

## Description

Cette formule représente le formatage d'un paramètre de type  [`entier`][valeur-de-retour], en fonction d'un pattern de formatage de type [`chaine de caractères`][valeur-de-retour].

Cette formule retourne une valeur de type [`chaine de caractères`][valeur-de-retour] correspondant au premier paramètre fourni, formaté en fonction de quatres autres paramètres (optionels) fournis.

- Si le premier paramètre est `1` et qu'il n'y a pas d'autre paramètre, la formule retourne `1`

- Si le premier paramètre est `1` et que les paramètres suivants sont `5`, `right`, `0`, `none`, la formule retourne `00001`

- Si le premier paramètre est `1` et que les paramètres suivants sont `5`, `left`, `#`, `none`, la formule retourne `1####`
- Si le premier paramètre est `1` et que les paramètres suivants sont `5`, `center`, `*`, `none`, la formule retourne `**1**`
- Si le premier paramètre est `-1` et que les paramètres suivants sont `5`, `center`, `*`, `none`, la formule retourne `**-1*`
- Si le premier paramètre est `-1` et que les paramètres suivants sont `5`, `center`, `*`, `POSITIVEONLY`, la formule retourne `**1**`
- Si le premier paramètre est `15` et que les paramètres suivants sont `5`, `center`, `#`, `none`, la formule retourne `##15#`
- Si le premier paramètre est `15` et que les paramètres suivants sont `5`, `none`, la formule retourne `##15#`

> Alignement possible

|Valeur|Signification|
|--------------|--------------|
|none|pas d'alignement|
|left|alignement à gauche|
|right|alignement à droite|
|center|alignement au centre|

> Signe possible

|Valeur|Signification|
|--------------|--------------|
|none|pas de signe|
|both|signe négatif et positif|
|negativeonly|signe négatif uniquement|
|positiveonly|signe positif uniquement|

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Signification Paramètre|Type Paramètre|Présence|Valeur par défaut|
|--------------|--------------|--------------|--------------|--------------|
|1|Valeur à formater|[`entier`][valeur-de-retour]|obligatoire|-|
|2|nombre minimum de caractères du résultat si un alignement est requis|[`entier`][valeur-de-retour]|optionelle|`10`|
|3|alignment type|[`chaine de caractères`][valeur-de-retour]|optionelle|`none`|
|4|caractère à utiliser pour compléter le résultat, selon l'alignement et le nombre de caractères|[`chaine de caractères`][valeur-de-retour]|optionelle|-|
|5|format du signe|[`chaine de caractères`][valeur-de-retour]|optionelle|`negativeonly`|


> Type Retour: [`chaine de caractères`][valeur-de-retour]

## Syntaxe

Le formatage de date s'écrit avec le mot clé `formatDate` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`date`][valeur-de-retour] ou [`numérique`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`chaine de caractères`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    formatDate (<expression_de_type_date>
    [;<expression_de_type_chaine_caractere>])

## Exemples

    formatDate(`31/12/2017`)                      [= "31/12/2017"      ]
    formatDate(`31/12/2017` ,"dd/MM/yyyy")        [= "31/12/2017"      ]
    formatDate(`31/12/2017` ,"dd-MM-yyyy")        [= "31-12-2017"      ]
    formatDate(`31/12/2017` ,"dd MM yyyy")        [= "31 12 2017"      ]
    formatDate(`31/12/2017` ,"dd MM yy")          [= "31 12 17"        ]
    formatDate(`31/12/2017` ,"dd MMM yyyy")       [= "31 déc. 2017"    ]
    formatDate(`31/12/2017` ,"dd MMMM yyy")       [= "31 décembre 2017"]
    formatDate(`31/12/2017` ,"MM dd yyyy")        [= "12 31 2017"      ]
    formatDate(`31/12/2017` ,"yyyy dd MM")        [= "2017 31 12"      ]
    formatDate(`31/12/2017` ,"yyyy MM dd")        [= "2017 31 12"      ]
    formatDate( "31/dec/2017", "dd/MM/yyyy")      [= "31/12/2017"      ]
    formatDate( "31/december/2017", "dd/MM/yyyy") [= "31/12/2017"      ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour