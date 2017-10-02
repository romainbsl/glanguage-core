# Documentation des formules

## Description
Une formule représente une expression [`évaluable`](#evaluation) qui, lors de son évaluation, retourne une valeur

Les formules peuvent être classifiées en 5 catégories :
- constante
- appel à une instruction ou fonction définie par le GLanguage
- référence à une règle
- référence à une donnée fournie par un tiers (façades)
- applicable à une règle

Le GLanguage définit une instruction et un grand nombre de fonction (voir liste exhaustive au paragraphe [Liste des formules](#liste-des-formules)) qui représentent des opérations atomiques applicables à un ou plusieurs paramètres qui sont eux-mêmes des formules

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
Chaque formule a une priorité qui indique sa précédence dans l'évaluation de la formule plus complexe dont elle fait partie

Plus la priorité est haute, plus la précédence est haute 

Les parenthèses "()" permettent d'assurer l'évaluation dans un autre ordre que celui de la précédence car la formule à laquelle les parenthèses correspondent a la priorité la plus haute

### Exemple
- La formule "SUM" à une priorité égale à 5
- La formule "MULTIPLY" à une priorité égale à 6 

1. Considérons la formule suivante :

        1 + 2 * 5
    
    La priorité plus élevé de la formule "MULTIPLY" force l'évaluation de l'expression `2 * 5` en premier lieu

    Le résultat final de cette formule sera donc équivalent à celui de la formule `1 + 10 [= 11]`

2. Considérons la formule suivante :

        (1 + 2) * 5

    Cette fois, l'expression entre parenthèse `(1 + 2)` est évaluée en premier lieu

    Le résultat final de cette formule sera donc équivalent à celui de la formule `3 * 5 [= 15]`

## Liste des formules ## 
<a name = "liste_des_formules"></a>
Il existe 61 formules définies par le GLanguage qui sont classifiées en fonction du nombre de leurs paramètres ou de leur domaine d'application : 

### Constantes
Ces formules n'ont pas de paramètre, elles retournes simplement la valeur de la constante qu'elles définissent

Ces formules n'ont pas d'"usage"

Ces formules sont dites "terminales" car leur valeur est connue directement contrairement aux autres formules qui doivent être évaluées et sont dites "non-terminales"

#### [Entier (TERMINAL_INTEGER)](./formulas/terminal_integer.md)
#### [Numérique (TERMINAL_NUMERIC)](./formulas/terminal_numeric.md)
#### [Chaîne de caractères (TERMINAL_STRING)](./formulas/terminal_string.md)
#### [Booléen (TERMINAL_BOOLEAN)](./formulas/terminal_boolean.md)
#### [Date (TERMINAL_DATE)](./formulas/terminal_date.md)
#### [Durée (TERMINAL_DURATION)](./formulas/terminal_durantion.md)

### Formules unaires booléennes
Ces formules acceptent 1 et 1 seul paramètre et retournent une valeur booléenne

#### [Test d'existance (EXIST)](./formulas/exist.md)
#### [Négation (NOT)](./formulas/not.md)

### Formules unaires mathématiques
Ces formules acceptent 1 et 1 seul paramètre et retournent une valeur entière ou numérique

#### [Moins unaire (UNARY_MINUS)](./formulas/unary_minus.md)
#### [Valeur absolue (ABS)](./formulas/abs.md)
#### [Signe (SIGN)](./formulas/sign.md)

### Formules binaires booléennes
Ces formules acceptent 2 paramètres et retournent une valeur de type __booléen__

#### ["ET" logique (AND)](./formulas/and.md)
#### ["OU" logique (OR)](./formulas/or.md)
#### [Test d'égalité (EQUAL)](./formulas/equal.md)
#### [Test d'inégalité (DIFFERENCE)](./formulas/difference.md)
#### [Test plus grand (GREATER)](./formulas/greater.md)
#### [Test plus grand ou égal (GREATER_OR_EQUAL)](./formulas/greater_or_equal.md)
#### [Test plus petit (SMALLER)](./formulas/smaller.md)
#### [Test plus petit ou égal (SMALLER_OR_EQUAL)](./formulas/smaller_or_equal.md)

### Formules binaires non-booléenne
Ces formules acceptent 2 paramètres et retournent une valeur de type __entier__, __numérique__, __chaîne de caractères__, __date__ ou __durée__

#### [Plus (PLUS)](./formulas/plus.md)
#### [Moins (MINUS)](./formulas/minus.md)
#### [Multiplication (MULTIPLY)](./formulas/multiply.md)
#### [Division (DIVIDE)](./formulas/divide.md)
#### [Division entière (INTEGER_DIVISION)](./formulas/integer_division.md)
#### [Modulo (MODULO)](./formulas/modulo.md)

### Formules "groupe"
Ces formules acceptent 1 et 1 seul paramètre qui doit être une référence à une règle

Ces formules appliquent une fonction mathématique (addition ou multiplication) à l'ensemble des règles "enfants" de la règle dont la référence est en paramètre

#### [Somme groupe (SUM)](./formulas/sum.md)
#### [Somme groupe avec correction d'erreur d'arrondi (SUMV)](./formulas/sumv.md)
#### [Multiplication groupe (MULT)](./formulas/mult.md)

### Formules "extremum"
Ces formules acceptent un nombre indéfini de paramètres et retrounent la valeur maximale ou minimale

#### [Maximum (MAX)](./formulas/max.md)
#### [Minimum (MIN)](./formulas/min.md)
#### [Maximum signé (SIGNED_MAX)](./formulas/signed_max.md)
#### [Minimum signé (SIGNED_MIN)](./formulas/signed_min.md)

### Formules d'arrondis
Ces formules acceptent 2 paramètres

Le premier paramètre est toujours une valeur de type __entier__ ou __numérique__ représentant la valeur à arrondir 

Le deuxième paramètre est la précision (ou le nombre de décimales) à appliquer pour arrondir la valeur du premier paramètre (ce paramètre est optionnel; une valeur par défaut est définie pour chaque type d'arrondi dans le cas ou le paramètre n'est pas fourni)

#### [Arrondi arithmétique (ROUNDED)](./formulas/rounded.md)
#### [Arrondi par défaut (CEIL)](./formulas/ceil.md)
#### [Arrondi par excès (FLOOR)](./formulas/floor.md)
#### [Arrondi bancaire (BANKERS_ROUNDED)](./formulas/bankers_rounded.md)
#### [Troncature (TRUNC)](./formulas/trunc.md)

### Formules de manipulation de chaîne de caractères
Ces formules acceptent un nombre variables de paramètres

Le premier paramètre est toujours une valeur de type __chaîne de caractères__ représentant la chaîne de caractère à manipuler

#### [Elément de chaîne de caractères (STRING_ITEM)](./formulas/string_item.md)
#### [Longueur de chaîne de caractères (STRING_LENGTH)](./formulas/string_length.md)
#### [Sous-chaîne de caractères (SUBSTRING)](./formulas/substring.md)

### Formules de formattage
Ces formules acceptent un certain nombre de paramètre dont certains sont optionnels

Le premier paramètre est toujours la valeur à formatter

Ces formules formatte la valeur du premier paramètre en fonction des autres paramètres (ou de valeurs par défaut, si absent) et retourne le résultat sous forme d'une valeur de type __chaîne de caractères__

#### [Format date (FORMAT_DATE)](./formulas/format_date.md)
#### [Format entier (FORMAT_INTEGER)](./formulas/format_integer.md)
#### [Format numérique (FORMAT_NUMERIC)](./formulas/format_numeric.md)
#### [Format chaîne de caractères (FORMAT_STRING)](./formulas/format_string.md)

### Formules __date__
Ces formules retorunent une valeur de type __date__

#### [Date (DATE)](./formulas/date.md)

### Formules __durée__
Ces formules retorunent une valeur de type __durée__

#### [Minutes (MINUTES)](./formulas/minutes.md)
#### [Heures (HOURS)](./formulas/hours.md)
#### [Jours (DAYS)](./formulas/days.md)
#### [Mois (MONTHS)](./formulas/month.md)
#### [Années (YEARS)](./formulas/years.md)

### Formules de référence
Ces formules sont traduite par des références à une règle ou à une donnée fournie par un tiers (façades)

#### [Référence vers une donnée (GET)](./formulas/get.md)
#### [Référence vers une règle (RULE_REFERENCE)](./formulas/rule_reference.md)

### Formules applicables à une règle
Ces formules sont applicables à une règle

#### [Formule d'une règle (FORMULA)](./formulas/formula.md)
#### [Applicabilité d'une règle (APPLICABILITY)](./formulas/applicability.md)

### Formule parenthèses

#### [Formule parenthèses (BRACKETS)](./formulas/brackets.md)
Cette formule permet d'isoler une formule faisant partie d'une formule complexe afin de forcer son évaluation telle quelle avant de la combiner avec les autres parties de la formule complexe

### Formule de test d'appertenance à une liste
Cette formule permet de tester l'appartenance d'une valeur à une liste de valeur

#### [Formule de test d'appertenance à une liste (IN)](./formulas/in.md)

### Formule "anomalie"
Cette formule permet de déclencher une "anomalie"

#### [Formule "anomalie" (ANOMALY)](./formulas/anomaly.md)

### Formule indéfinie
Cette formule représente une absence de formule

#### [Formule indéfinie (UNDEFINED)](./formulas/undefined)

### Instructions
Ces formules représentent de instructions 

#### ["if ... then ... [{else if ...}] [else ...] end (IF)"](./formulas/if.md)