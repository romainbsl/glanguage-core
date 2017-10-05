# Instruction "if ... then ... [{else if ...}] [else ...] end (IF)"

## Description

Cette formule représente l'instruction "if-then-else" qui permet de définir des branches conditionnelles et d'évaluer celle pour laquelle la condition est vérifiée

Cette formule retourne une valeur de n'importe quel type correspondant à l'évaluation de l'expression de la branche pour laquelle la condition est vérifiée

Toutes les branches dovent avoir une expression de type équivalent

### Valeurs par défaut
Si il n'y a aucune branche pour laquelle la condition est vérifiée, une valeur par défaut sera retourné en fonction du type des expressions, sauf pour le type [`date`][valeur-de-retour]

__ATTENTION__ : 
Si les expressions sont de type [`date`][valeur-de-retour], cela mène à une erreur rendant impossible l'évaluation impossible car il n'existe pas de valeur par défaut pour les valeurs de type [`date`][valeur-de-retour]

|Type|Valeur par défaut|
|----|-----------------|
|[`entier`][valeur-de-retour]|`0`|
|[`numérique`][valeur-de-retour]|`0.0`|
|[`chaîne de caractères`][valeur-de-retour]|`""` (chaîne de caractères vide)|
|[`booléen`][valeur-de-retour]|`false`|
|[`date`][valeur-de-retour]|_erreur_|
|[`durée`][valeur-de-retour]|`'PT0S'` (durée nulle)|

## Paramètres

1. `condition`
    - condition a évalué
    - type : [`booléen`][valeur-de-retour]
 
Si la condition est vérifiée, évaluer l'expression de cette branche (`expression "si"`)

Sinon, évaluer l'autre branche (`expression "sinon"`)

2. `expression "si"`
    - expression à évaluer si `condition` est vérifiée
    - type : 
        - [`entier`][valeur-de-retour]
        - [`numérique`][valeur-de-retour]
        - [`chaîne de caractères`][valeur-de-retour]
        - [`booléen`][valeur-de-retour]
        - [`date`][valeur-de-retour]
        - [`durée`][valeur-de-retour]

3. `expression "sinon"`
    - expression à évaluer si `condition` n'est pas vérifiée
    - type : doit être equivalent à celui de `expression "si"`
    - __ASTUCE__ : cette expression peut elle-même être une `instruction if`, ce qui permet de définir un arbre décisionel

## Usages

Il existe 5 "usages" pour ce type de formule, 1 par type d'expression compatible

### 1. [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]
|#|Signification|Type|
|-------------------|-------------|----|
|1|`condition`|[`booléen`][valeur-de-retour]|
|2|`expression "si"`|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|
|3|`expression "sinon"`|<ul><li>[`entier`][valeur-de-retour]</li><li>[`numérique`][valeur-de-retour]</li></ul>|

> Type de retour : [`entier`][valeur-de-retour] ou [`numérique`][valeur-de-retour]

### 2. [`chaîne de caractères`][valeur-de-retour]
|#|Signification|Type|
|-------------------|-------------|----|
|1|`condition`|[`booléen`][valeur-de-retour]|
|2|`expression "si"`|[`chaîne de caractères`][valeur-de-retour]|
|3|`expression "sinon"`|[`chaîne de caractères`][valeur-de-retour]|

> Type de retour : [`chaîne de caractères`][valeur-de-retour]

### 3. [`booléen`][valeur-de-retour]
|#|Signification|Type|
|-------------------|-------------|----|
|1|`condition`|[`booléen`][valeur-de-retour]|
|2|`expression "si"`|[`booléen`][valeur-de-retour]|
|3|`expression "sinon"`|[`booléen`][valeur-de-retour]|

> Type de retour : [`booléen`][valeur-de-retour]

### 4. [`date`][valeur-de-retour]
|#|Signification|Type|
|-------------------|-------------|----|
|1|`condition`|[`booléen`][valeur-de-retour]|
|2|`expression "si"`|[`date`][valeur-de-retour]|
|3|`expression "sinon"`|[`date`][valeur-de-retour]|

> Type de retour : [`date`][valeur-de-retour]

### 5. [`durée`][valeur-de-retour]
|#|Signification|Type|
|-------------------|-------------|----|
|1|`condition`|[`booléen`][valeur-de-retour]|
|2|`expression "si"`|[`durée`][valeur-de-retour]|
|3|`expression "sinon"`|[`durée`][valeur-de-retour]|

> Type de retour : [`durée`][valeur-de-retour]

## Syntaxe

Le formule indéfinie s'écrit avec le mot clé `if` suivi d'un esapce et d'une expression de type [`booléen`][valeur-de-retour] suivi d'un espace et du mot clé `then` suivi d'un espace suivi d'une expression de n'importe quel type (, et de manière optionelle du mot clé `else` suivi d'un espace suivi d'une expression d'un type compatible à celui de l'expression précédente ) et se termine par le mot clé `end`

__ASTUCE__ : Si l'expression de la branche `else` est également une `instruction if`, les mots clés `else` et `if` peuvent être concaténé pour donner `elseif` en un mot
    
    if <condition> then <expression> [{ elseif <conditon> then <expression>}] [ else <expression> ] end 

## Exemples

    if true then
        1
    end

    [= 1]
&nbsp;

    if false then
        1
    end

    [= 0]
&nbsp;

    if x >= 0 then
        "positif"
    else
        "négatif"
    end

    [= "positif" si x >= 0]
    [= "négatif" sinon]
&nbsp;

    if x == 0 then
        0
    elseif x > 0 then
        1,0
    else
        -1,0
    end

    [= 0 si x == 0]
    [= 1,0 si x > 0]
    [= -1,0 sinon]
&nbsp;

    if x == 0 then
        0
    elseif x > 0 then
        if x > 10 then
            10,0
        else
            1,0
        end
    else
        -1,0
    end

    [= 0 si x == 0]
    [= 10,0 si x > 0 et > 10]
    [= 1,0 si 0 < x < 10]
    [= -1,0 sinon]

    
[valeur-de-retour]: ../lexique.md#valeur-de-retour