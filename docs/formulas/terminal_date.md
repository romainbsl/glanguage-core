# Formule constante date (TERMINAL_DATE)
## Description
Cette formule représente une valeur constante de type __date__
## Type
__date__
## Syntaxe
Ces formules :
- sont toujours définies en encadrant la valeur souhaitée par des apostrophes ' '
- sont toujours écrites sous la forme "jour, mois, année"
- le séparateur peut être le slash "/" ou le tiret "-" indifféremment
- le mois peut être exprimé : 
    - par son numéro dans l'année (1 = janvier, 12 = décembre)
    - par son nom complet (en anglais)
    - par son son abréviation sur 3 lettres (en anglais)

Les noms des mois reconnus par la grammaire sont :

|Numéro |Nom complet |Abréviation |
|-------|------------|------------|
|1|january|jan
|2|february|feb
|3|march|mar
|4|april|apr
|5|may|may
|6|june|jun
|7|july|jul
|8|august|aug
|9|september|sep
|10|october|oct
|11|november|nov
|12|december|dec

    '1/1/2000' ou '1-1-2000'
    '01/01/2000' ou '01-01-2000'
    '1/jan/2000' ou '1-jan-2000'
    '01/january/2000' ou '01-january-2000'
