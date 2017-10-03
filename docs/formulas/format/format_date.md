# Formule de formatage de date (FORMAT_DATE)

## Description

Cette formule représente le formatage d'un paramètre de type  [`date`][valeur-de-retour], en fonction d'un pattern de formatage de type [`chaine de caractères`][valeur-de-retour].

Cette formule retourne une valeur de type [`chaine de caractères`][valeur-de-retour] correspondant au premier paramètre fourni, formaté en fonction du pattern (optionel) fourni en second paramètre.

- Si le prmier paramètre est `31/12/2017` et que le second est non renseigné, la formule retourne `31/12/2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd/MM/yyyy`, la formule retourne `31/12/2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd-MM-yyyy`, la formule retourne `31-12-2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd MM yyyy`, la formule retourne `31 12 2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd MM yy`, la formule retourne `31 12 17`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd MMM yyyy`, la formule retourne `31 déc. 2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `dd MMMM yyyy`, la formule retourne `31 décembre 2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `MM dd yyyy`, la formule retourne `12 31 2017`
- Si le prmier paramètre est `31/12/2017` et que le second est `yyyy dd MM`, la formule retourne `2017 31 12`
- Si le prmier paramètre est `31/12/2017` et que le second est `yyyy MM dd`, la formule retourne `2017 31 12`

|Symbole|Signification|
|--------------|--------------|
|d|Jour du mois|
|M|Mois de l'année|
|y|Année|

## Usages

Il existe un seul "usage" pour ce type de formule :

|Numéro Paramètre|Type Paramètre|Présence|Valeur par défaut|
|--------------|--------------|--------------|--------------|
|1|[`date`][valeur-de-retour]|obligatoire|-|
|1|[`chaine de caractères`][valeur-de-retour]|optionelle|'dd/MM/yyyy'|

> Type Retour: [`chaine de caractères`][valeur-de-retour]

## Syntaxe

Le formatage de date s'écrit avec le mot clé `formatDate` suivi ou non d'un espace et, entre parenthèse "( )" d'une expression de type [`date`][valeur-de-retour] ou [`numérique`][valeur-de-retour] (, et de manière optionelle d'un séparateur ";" et d'une expression de type [`chaine de caractères`][valeur-de-retour]) à laquelle il faut appliquer la formule.

    formatDate (<expression_de_type_date>
    [;<expression_de_type_chaine_caractere>])

## Exemples

    formatDate(`31/12/2017`)                [= "31/12/2017"      ]
    formatDate(`31/12/2017` ,"dd/MM/yyyy")  [= "31/12/2017"      ]
    formatDate(`31/12/2017` ,"dd-MM-yyyy")  [= "31-12-2017"      ]
    formatDate(`31/12/2017` ,"dd MM yyyy")  [= "31 12 2017"      ]
    formatDate(`31/12/2017` ,"dd MM yy")    [= "31 12 17"        ]
    formatDate(`31/12/2017` ,"dd MMM yyyy") [= "31 déc. 2017"    ]
    formatDate(`31/12/2017` ,"dd MMMM yyy") [= "31 décembre 2017"]
    formatDate(`31/12/2017` ,"MM dd yyyy")  [= "12 31 2017"      ]
    formatDate(`31/12/2017` ,"yyyy dd MM")  [= "2017 31 12"      ]
    formatDate(`31/12/2017` ,"yyyy MM dd")  [= "2017 31 12"      ]

[valeur-de-retour]: ../lexique.md#valeur-de-retour