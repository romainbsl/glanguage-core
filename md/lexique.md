# Lexique

Cette page contient des définitions de termes que l'on peut retrouver au travers de la documentation du `GLanguage`.

## Evaluation <a name="evaluation"></a>

L'_évaluation_ est l'action d'interpréter un objet et de lui assigner une valeur.

### Objets évaluables

Il existe 2 types d'objets évaluables :

- les règles

Par défintion, l'évaluation d'une [`règle`][rule] revient à évaluer sa [`formule`][formula] et à assigner le résultat de celle-ci à la [`règle`][rule].

> Une **règle** n'est évaluée qu'une seule fois

- les formules

L'évaluation d'une [`formule`][formula] revient à interpréter les éléments qui la compose (c.f. documentation).

### Valeur de retour

Il existe 6 types de retour possible :

- entier
- numérique
- chaîne de caractères
- booléen
- date
- durée

[rule]: rule.md
[formula]: formula.md