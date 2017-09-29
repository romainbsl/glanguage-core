# Evaluation
L'évaluation est l'action d'interpréter un objet et de lui assigner une valeur
## Objet évaluable
Il existe 2 types d'objet évaluable :
- les formules
- les règles
## Valeur
Il existe 6 types de valeur :
- entier
- numérique
- chaîne de caractères
- booléen
- date
- durée

# Formule
## Description
Une formule représente une expression "évaluable" qui, lors de son évaluation, retourne une valeur.

Les formules peuvent être classifiées en 4 catégories :
- constante
- appel à une instruction ou fonction définie par le GLanguage
- référence à une règle
- référence à une donnée fournie par un tiers (façades)

Le GLanguage définit une instruction et un grand nombre de fonction (voir liste exhaustive au paragraphe "Liste des formules") qui représentent des opérations atomiques applicables à un ou plusieurs paramètres qui sont eux-mêmes des formules
## Usage et type
Chaque formule du GLanguage a un nombre défini d'"usage" et au minimum un

Un "usage" consiste en la description des paramètres qu'une formule peut accepeter et le type de retour associé à ces paramètres

Le type d'une formule dépend donc de l'"usage" qui en est fait
### Exemple
La formule "MULTIPLY", qui représente la multiplication mathématique, acceptent exactement 2 paramètres dont les types peuvent uniquement être __entier__ ou __numérique__ et le type de de valeur retrourné par la formule dépend des types de ces paramètres

Il existe 2 "usages" pour cette formule :
1. 2 paramètres de type __entier__ : la valeur de retour est de type __entier__
2. au moins 1 paramètre de type __numérique__ et l'autre de type __entier__ ou __numérique__ : la valeur de retour est de type __numérique__
## Priorité et précédence
Chaque formule a une priorité qui indique sa précédence dans l'évaluation de la formule

Plus la priorité est haute, plus la précédence est haute 

Les parenthèses permettent d'assurer l'évaluation dans un autre ordre que celui de la précédence car la formule à laquelle les parenthèses correspondent a la priorité la plus haute
### Exemple
- La formule "SUM" à une priorité égale à 5
- La formule "MULTIPLY" à une priorité égale à 6 

1. Considérons la formule suivante :

        1 + 2 * 5
    
    La priorité plus élevé de la formule "MULTIPLY" force l'évaluation de l'expression `2 * 5` en premier lieu

    Le résultat final de cette formule sera donc équaivalent à celui de la formule `1 + 10 (= 11)`

2. Considérons la formule suivante :

        (1 + 2) * 5

    Cette fois, l'expression entre parenthèse `(1 + 2)` est évaluée en premier lieu

    Le résultat final de cette formule sera donc équaivalent à celui de la formule `3 * 5 (= 15)`

## Liste des formules
Il existe 61 formules définies par le GLanguage qui sont classifiées en fonction du nombre de leurs paramètres ou de leur domaine d'application : 
### Constantes
Ces formules n'ont pas de paramètres, elles ont simplement la valeur de la constante qu'elles définissent
Ces formules 
#### Entier (TERMINAL_INTEGER)  
##### Sytaxe :
    1
    +2147483647 (limite maximum)
    -2147483648 (limite minimum)
##### 

