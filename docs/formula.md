# Documentation des formules

## Description
Une formule représente une expression [`évaluable`][evaluation] qui, lors de son évaluation, retourne une valeur

Les formules peuvent être classifiées en 6 catégories :
1. [constante](#1-constantes)
2. [fonction définie par le GLanguage](#2-fonctions-définies-par-le-glanguage)
3. [instruction définie par le GLanguage](#3-instructions-définies-par-le-glanguage)
4. [référence à une donnée fournie par un tiers](#4-référence-à-une-donnée-fournie-par-un-tiers)
5. [référence à une règle](#5-référence-à-une-règle)
6. [applicable à une règle](#6-applicable-à-une-règle)

Le GLanguage définit une instruction et un grand nombre de fonction (voir liste exhaustive au paragraphe [Liste des formules](#liste-des-formules)) qui représentent des opérations atomiques applicables à un ou plusieurs paramètres qui sont eux-mêmes des formules

## Usage et type
Chaque formule du GLanguage a un nombre défini d'"usage" et au minimum un

Un "usage" consiste en la description des paramètres qu'une formule peut accepeter et le type de retour associé à ces paramètres

Le type d'une formule dépend donc de l'"usage" qui en est fait

### Exemple
La formule "MULTIPLY", qui représente la multiplication mathématique, acceptent exactement 2 paramètres dont les types peuvent uniquement être [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] et le type de de valeur retrourné par la formule dépend des types de ces paramètres

Il existe 2 "usages" pour cette formule :
1. 2 paramètres de type [`entier`][valeur-de-retour] : la valeur de retour est de type [`entier`][valeur-de-retour]
2. au moins 1 paramètre de type [`numérique`][valeur-de-retour] et l'autre de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour] : la valeur de retour est de type [`numérique`][valeur-de-retour]

## Priorité et précédence
Chaque formule a une priorité qui indique sa précédence dans l'évaluation de la formule plus complexe dont elle fait partie

Plus la priorité est haute, plus la précédence est haute 

Les parenthèses "( )" permettent d'assurer l'évaluation dans un autre ordre que celui de la précédence car la formule à laquelle les parenthèses correspondent a la priorité la plus haute

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

## Liste des formules
Il existe 60 formules définies par le GLanguage
Dans la liste ci-dessous, elles sont classifiées en fonction des 6 catégories : 

### 1. Constantes
Ces formules n'ont pas de paramètre, elles retournes simplement la valeur de la constante qu'elles définissent

Ces formules n'ont pas d'"usage"

Ces formules sont dites "terminales" car leur valeur est connue directement contrairement aux autres formules qui doivent être évaluées et sont dites "non-terminales"

##### [Entier (TERMINAL_INTEGER)](./formulas/terminal/terminal_integer.md)
##### [Numérique (TERMINAL_NUMERIC)](./formulas/terminal/terminal_numeric.md)
##### [Chaîne de caractères (TERMINAL_STRING)](./formulas/terminal/terminal_string.md)
##### [Booléen (TERMINAL_BOOLEAN)](./formulas/terminal/terminal_boolean.md)
##### [Date (TERMINAL_DATE)](./formulas/terminal/terminal_date.md)
##### [Durée (TERMINAL_DURATION)](./formulas/terminal/terminal_durantion.md)

### 2. Fonctions définies par le GLanguage
Pour plus de clareté, les formules ci-dessous sous regroupées en fonction du nombre de paramètre ou du domaine d'application

#### 2.1. Formules unaires booléennes
Ces formules acceptent 1 et 1 seul paramètre et retournent une valeur de type [`booléen`][valeur-de-retour]

##### [Test d'existence (EXIST)](./formulas/unary/exist.md)
##### [Négation (NOT)](./formulas/not.md)

#### 2.2 Formules unaires mathématiques
Ces formules acceptent 1 et 1 seul paramètre et retournent une valeur de type [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

##### [Moins unaire (UNARY_MINUS)](./formulas/unary/unary_minus.md)
##### [Valeur absolue (ABS)](./formulas/unary/abs.md)
##### [Signe (SIGN)](./formulas/unary/sign.md)

#### 2.3. Formules binaires booléennes
Ces formules acceptent 2 paramètres et retournent une valeur de type [`booléen`][valeur-de-retour]

##### ["ET" logique (AND)](./formulas/binary/and.md)
##### ["OU" logique (OR)](./formulas/binary/or.md)
##### [Test d'égalité (EQUAL)](./formulas/binary/equal.md)
##### [Test d'inégalité (DIFFERENCE)](./formulas/binary/difference.md)
##### [Test plus grand (GREATER)](./formulas/binary/greater.md)
##### [Test plus grand ou égal (GREATER_OR_EQUAL)](./formulas/binary/greater_or_equal.md)
##### [Test plus petit (SMALLER)](./formulas/binary/smaller.md)
##### [Test plus petit ou égal (SMALLER_OR_EQUAL)](./formulas/binary/smaller_or_equal.md)

#### 2.4. Formules binaires non-booléenne
Ces formules acceptent 2 paramètres et retournent une valeur de type [`entier`][valeur-de-retour], [`numérique`][valeur-de-retour], [`chaîne de caractères`][valeur-de-retour], [`date`][valeur-de-retour] ou [`durée`][valeur-de-retour]

##### [Plus (PLUS)](./formulas/binary/plus.md)
##### [Moins (MINUS)](./formulas/binary/minus.md)
##### [Multiplication (MULTIPLY)](./formulas/binary/multiply.md)
##### [Division (DIVIDE)](./formulas/binary/divide.md)
##### [Division entière (INTEGER_DIVISION)](./formulas/binary/integer_division.md)
##### [Modulo (MODULO)](./formulas/binary/modulo.md)

#### 2.5. Formules "groupe"
Ces formules acceptent 1 et 1 seul paramètre qui doit être une référence à une règle

Ces formules appliquent une fonction mathématique (addition ou multiplication) à l'ensemble des règles "enfants" de la règle dont la référence est en paramètre

##### [Somme groupe (SUM)](./formulas/group/sum.md)
##### [Somme groupe avec correction d'erreur d'arrondi (SUMV)](./formulas/group/sumv.md)
##### [Multiplication groupe (MULT)](./formulas/group/mult.md)

#### 2.6. Formules "extremum"
Ces formules acceptent un nombre indéfini de paramètres et retrounent la valeur maximale ou minimale

##### [Maximum (MAX)](./formulas/extremum/max.md)
##### [Minimum (MIN)](./formulas/extremum/min.md)
##### [Maximum signé (SIGNED_MAX)](./formulas/extremum/signed_max.md)
##### [Minimum signé (SIGNED_MIN)](./formulas/extremum/signed_min.md)

#### 2.7. Formules d'arrondis
Ces formules acceptent 2 paramètres

Le premier paramètre est toujours une valeur de type [`entier`](./lexique.md#valeur-de-retour) ou [`numérique`][valeur-de-retour] représentant la valeur à arrondir 

Le deuxième paramètre est la précision (ou le nombre de décimales) à appliquer pour arrondir la valeur du premier paramètre (ce paramètre est optionnel; une valeur par défaut est définie pour chaque type d'arrondi dans le cas ou le paramètre n'est pas fourni)

##### [Arrondi arithmétique (ROUNDED)](./formulas/rounding/rounded.md)
##### [Arrondi par défaut (CEIL)](./formulas/rounding/ceil.md)
##### [Arrondi par excès (FLOOR)](./formulas/rounding/floor.md)
##### [Arrondi bancaire (BANKERS_ROUNDED)](./formulas/rounding/bankers_rounded.md)
##### [Troncature (TRUNC)](./formulas/rounding/trunc.md)

#### 2.8. Formules de manipulation de chaîne de caractères
Ces formules acceptent un nombre variables de paramètres

Le premier paramètre est toujours une valeur de type [`chaîne de caractères`][valeur-de-retour] représentant la chaîne de caractère à manipuler

##### [Elément de chaîne de caractères (STRING_ITEM)](./formulas/string/string_item.md)
##### [Longueur de chaîne de caractères (STRING_LENGTH)](./formulas/string/string_length.md)
##### [Sous-chaîne de caractères (SUBSTRING)](./formulas/string/substring.md)

#### 2.9. Formules de formattage
Ces formules acceptent un certain nombre de paramètre dont certains sont optionnels

Le premier paramètre est toujours la valeur à formatter

Ces formules formatte la valeur du premier paramètre en fonction des autres paramètres (ou de valeurs par défaut, si absent) et retourne le résultat sous forme d'une valeur de type [`chaîne de caractères`][valeur-de-retour]

##### [Format date (FORMAT_DATE)](./formulas/format/format_date.md)
##### [Format entier (FORMAT_INTEGER)](./formulas/format/format_integer.md)
##### [Format numérique (FORMAT_NUMERIC)](./formulas/format/format_numeric.md)
##### [Format chaîne de caractères (FORMAT_STRING)](./formulas/format/format_string.md)

#### 2.10. Formules date
Ces formules retorunent une valeur de type [`date`][valeur-de-retour]

##### [Date (DATE)](./formulas/date/date.md)

#### 2.11. Formules durée
Ces formules retorunent une valeur de type [`durée`][valeur-de-retour]

##### [Minutes (MINUTES)](./formulas/minutes.md)
##### [Heures (HOURS)](./formulas/hours.md)
##### [Jours (DAYS)](./formulas/days.md)
##### [Mois (MONTHS)](./formulas/month.md)
##### [Années (YEARS)](./formulas/years.md)

#### 2.12. Formule parenthèses

##### [Formule parenthèses (BRACKETS)](./formulas/brackets.md)
Ces formules permettent d'isoler une ou plusieurs formules faisant partie d'une formule complexe afin de forcer l'évaluation de celles-ci comme un tout, avant de  combiner le résultat obtenu avec les autres parties de la formule complexe

#### 2.13. Formule de test d'appertenance à une liste
Ces formules permettent de tester l'appartenance d'une valeur à une liste de valeur

##### [Formule de test d'appertenance à une liste (IN)](./formulas/in.md)

#### 2.14. Formule "anomalie"
Ces formules permettent de déclencher une "anomalie"

##### [Formule "anomalie" (ANOMALY)](./formulas/anomaly.md)

#### 2.15. Formule indéfinie
Ces formules représentent une absence de formule

##### [Formule indéfinie (UNDEFINED)](./formulas/undefined)

### 3. Instructions définies par le GLanguage
Ces formules représentent des instructions 

##### ["if ... then ... [{else if ...}] [else ...] end (IF)"](./formulas/if.md)

### 4. Référence à une donnée fournie par un tiers
Ces formules sont traduite par des références à une donnée fournie par un tiers (façades)

##### [Référence vers une donnée fournie par un tiers (GET)](./formulas/get.md)

### 5. Référence à une règle
Ces formules sont traduite par des références à une règle

##### [Référence vers une règle (RULE_REFERENCE)](./formulas/rule_reference.md)

### 6. Applicable à une règle
Ces formules sont applicables à une règle

##### [Formule d'une règle (FORMULA)](./formulas/formula.md)
##### [Applicabilité d'une règle (APPLICABILITY)](./formulas/applicability.md)


[evaluation]: lexique.md#evaluation
[valeur-de-retour]: lexique.md#valeur-de-retour