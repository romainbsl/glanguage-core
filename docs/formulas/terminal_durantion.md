# Formule constante durée (TERMINAL_DURATION)
## Description
Cette formule représente une valeur constante de type __durée__
## Type
__durée__
## Syntaxe
Ces formules :
- sont toujours définies en encadrant la valeur souhaitée par des apostrophes ' '
- sont toujours écrites sous la forme P[nY][nM][nD][T[nH][nM][n][.nS]], ou n est une nombre entier qui représente, pour chaque partie de la durée, le nombre de ces parties 

        'PT.1S'             [= 1 nanoseconde]
        'PT1'               [= 1 seconde]
        'PT2M'              [= 2 minutes]
        'PT2M1.1S'          [= 2 minutes 1 seconde et 1 nanoseconde]
        'PT3H2M1.1S'        [= 3 heures 2 minutes 1 seconde et 1 nanoseconde]
        'P4D'               [= 4 jours]
        'P5M4D'             [= 5 mois et 4 jours]
        'P6Y5M4D'           [= 6 années 5 mois et 4 jours]
        'P6Y5M4DT3H2M1.1S'  [= 6 années 5 mois 4 jours 3 heures 2 minutes 1 seconde et 1 nanoseconde]
