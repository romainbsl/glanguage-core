# Documentation de GLanguage

## Introduction

Le projet `GLanguage` est un projet technique regroupant différents concepts relatifs aux `Plans`  ; notion propre au _**Group S**_.

Ce projet est un ensemble de concepts qui permettent de définir des instructions pouvant être exécutées, individuellement ou successivement, afin d'en extraire un résultat particulier (donnée brute, calcul, validation de données, etc.). Ces notions s'imbriquent les unes dans les autres afin de définir un tout, les `Plans`.

- [Ensemble de règles](ruleset.md)
- [Règle](rule.md)
- [Formules](formula.md)
- GLanguage

Le nom de `GLanguage` vient du fait que ce projet s'appuie sur un pseudo langage de programmation, spécifique au _**Group S**_, pouvant être catégorisé de [DSL (Domain Specific Language)](https://fr.wikipedia.org/wiki/Langage_d%C3%A9di%C3%A9).

## Objectifs

Le premier objectif de ce projet est d'externaliser des concepts métiers, au delà des seuls `plans`de calculs d'aujourd'hui, pouvant être invoqués depuis n'importe quel applicatif au sein du _**Group S**_ (et éventuellement au delà). Cette externalisation à un but de centralisation, afin d'éviter la répétition de ces notions métiers (calculs, contrôles, etc.) au travers de toutes nos applications, mais également d'offrir plus de flexibilité quant à la modification ou correction d'une instruction, sans devoir modifier et relivrer de nouvelles versions de programmes.

Le second objectif, est de permettre à des personnes n'ayant pas de compétences techniques, des _Business Analyst_ principalement, d'implémenter ses concepts métiers sous la forme d'instructions 'non-informatiques' pouvant être interprétées par nos programmes. En effet, la législation étant au coeur de notre métier, cela parait plus efficient que les personnes suivant cette législation implémentent directement ses concepts.

## Nouvelle Implémentation

### Un peu d'historique

Ce projet s'appuie sur un projet existant au _**Group S**_, le `SLangage` (c.f. K:\43 DEVELOPPEMENT\43 0780 BNCL\070 TRANSITION\MANUELS\bncl_livr_19971113a_manuel_S_langage.doc).

le `SLangage` est utilisé depuis de nombreuses années, pour le calcul des paies. Cependant, nous avons le désir d'évoluer, et d'élargir le scope de nos plans. C'est pourquoi nous avons entrepris la refonte du projet `SLangage` au travers du projet `GLanguage`.

Le `SLangage`utilise des fichiers plats, stockés sous [SNV][]/[Git][], pour encoder les `Plans`. Ceux-ci sont interprétés par des programmes [Eiffel][], et plus récemment [Java][].

### Ce qui change

Tout en gardant les concepts déjà existant, le projet à été pensé pour répondre a des besoins aussi divers que variés:

- Calcul de paies
- Validation de données
- Aide à la siasie
- Documentation
- Etc.

C'est en ayant ce besoin de modularité que nous avons créé un nouveau projet, sous une nouvelle architecture, pour aboutir au `GLanguage`.

#### Le choix de la base de données

Le besoin de modularité; le fait de pouvoir utiliser des notions métiers (e.g. Signalétique Personne) au travers de tous nos applicatifs; nous a conduis à choisir la base de données comme moyen de stockage des `Plans. 

Cela permet de dissocier au maximum les notions métiers, et de les faire évoluer les unes indépedemment des autres. Cela nous offre également la possibilité d'enrichir le scope de nos `Plans`, via des schéma de données d'extension (e.g. schéma de [Control](http://gitlabprod.groups.local/groups-commons/groups-control)).

L'utilisation de la base de données nous offre, également, la possiblité de tracer et maitriser les actions concernant les `Plans`:

- Qu'est ce qui a été modifié ?
    - Par qui ?
    - Quand ?
- Qui à l'autorisation de modifier ?
    - Quel `Plan` ?
- Qui à l'autorisation de mettre en production une nouvelle version ?

#### La technologie [Java][]

Technologie déjà utilisée dans le cadre du projet `SLangage`, en remplacement du langage [Eiffel][], le [Java][] est un langage très utilisé au `Group S`, et certaines notions existantes pouvant être réutilisées. De plus, le `Java` est une technologie qui offre l'opportunité de s'exécuter dans de multiples contextes (web, batch, desktop, etc.). Il est donc apparu évident que nous devions continuer avec cette technologie.

[SVN]: https://fr.wikipedia.org/wiki/Apache_Subversion
[Git]: https://fr.wikipedia.org/wiki/Git
[Java]: https://fr.wikipedia.org/wiki/Java_(technique)
[Eiffel]: https://fr.wikipedia.org/wiki/Eiffel_(langage)