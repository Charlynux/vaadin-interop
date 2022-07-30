# Vaadin Interop

Quelques essais autour des possibilités d'intéropérabilité entre Vaadin et les langages de la JVM.

Le projet a été initié via le "Vaadin Quick Start" V23 : [ici](https://vaadin.com/docs/latest/guide/quick-start).

## Pour lancer l'application

```shell
./mvnw
```

L'application est disponible à l'adresse : http://localhost:8080

## Langages

### Kotlin

#### Intégration

A l'ajout d'une classe Kotlin, IntelliJ m'a proposé de configurer Maven pour ça. Au lancement du build pourtant, 
Maven a lancé le build du Java avant d'avoir compilé le Kotlin. Le build a donc planté logiquement sur un ClassNotFound.

Cet [article](https://www.baeldung.com/kotlin/maven-java-project) explique le problème est la solution.

A ce niveau, ce que je ne saisis pas, c'est que je peux utiliser une classe Kotlin dans mon Java,
mais aussi une classe Java (de mon projet) dans mon Kotlin.

#### Utilisation

En dehors de vouloir migrer son projet entièrement en Kotlin, peut-être est-il intéressant d'intégrer partiellement Kotlin
pour certains besoins.

- Ecriture de composants

La concision pourrait aider à écrire plus vite certains composants simples.

- Ecriture de controller/logique métier

Le typage, l'aspect fonctionnel, ... pourrait permettre d'écrire du code plus simple de ces parties.

- Data classes

Sans Java 19 et les records, la verbosité de Java peut dissuader d'écrire une petite classe uniquement 
pour passer de la donnée à un composant. Avec les data classes, encore une fois la concision vient aider à ce niveau.

- Fonctions d'extension

Dans l'écriture d'un composant, isoler du code dans une extension pourrait être intéressant.

#### Limitations

A priori, je vois deux limitations qui a étudié avant de mélanger Kotlin dans du code Java de production :

- Augmentation significative du temps de build

- Intégration dans les différents IDE de l'équipe 

### Clojure

J'avais déjà joué avec Clojure dans un projet Java. Je me suis donc inspiré de ma précédente [expérience](https://charlynux.github.io/posts-output/2020-11-29-repl-experiments-2/) pour démarrer un Clojure REPL en même temps que l'application Spring Boot.

Pour avoir le REPL Clojure, il faut ajouter le profil au lancement de l'application. 

```
./mvnw -Pclojure-repl
```

#### Ce que je n'ai pas fait

Si je m'étais intéressé à l'aspect Spring de cette application, j'aurais été voir du côté de ce qu'a fait [Jakub Holy](https://blog.jakubholy.net/java-troubleshooting-on-steroids-with-clojure-repl/) ou j'aurais pu tester le projet [Spring Boost](https://github.com/jaju/spring-boost).

Mais ici, l'objectif était de faire du Vaadin.

#### Solution "à la Stuart Halloway"

Stuart Halloway a déjà réalisé un projet [Clojure From Java](https://github.com/stuarthalloway/clojure-from-java).

Le README de son projet explique bien les nombreux choix qui se présentent au développeur souhaitant mettre du Clojure dans son application Java.
Son approche à l'avantage d'être simple à mettre en place. Plutôt que d'implémenter des classes en Clojure, il expose des fonctions de type "Factory" qui instancie des objets.

##### Composant "ClojureTitle"

Mon premier essai a consisté à instancier un composant Vaadin. L'essai est concluant, j'ai pu utiliser mon composant depuis la MainView de mon application.

Là où l'utilisation de Clojure prend tout son sens, c'est qu'avec le REPL, j'ai pu modifier mon composant à la volée sans redémarrage du serveur.


##### LabelService

J'ai pu aussi faire une implémentation de la même interface qu'en Kotlin.

Pour instancier et injecter ce service, je me suis appuyé sur Spring. Le défaut, c'est que comme c'est un Singleton, je ne peux plus modifier le code du service dans le REPL.
Mais après avoir extrait le "métier" dans une fonction à part, j'ai pu passer de "upper-case" à "lower-case" directement dans le REPL.

#### Un premier avis

##### Intérêt

Modifier une application en live grace au REPL est assez satisfaisant.

Toutefois, je ne suis pas sûr que l'écriture de composants soit très intéressante en Clojure. Il faudrait batailler avec l'API Java plutôt que d'écrire du Clojure "idiomatique".

Pour l'écriture de services, sur certains besoins spécifiques cela pourrait s'entendre.

##### Expérience de développement

Le problème, c'est que sans REPL, le développement Clojure perd de son intérêt. Or, à chaque redémarrage du serveur le REPL est coupé, si on jongle pas mal entre Clojure et Java, cela risque d'être handicapant.

Pour des plus gros besoins en Clojure, l'idée serait de disposer d'un projet dédié avec son REPL de développement hors serveur et d'utiliser le REPL de l'application pour raffiner ou debugger certains aspects.

Pour l'instant, c'est un "Socket REPL" qui est initié. Il serait sûrement intéressant de passer à du "NRepl" qui est compatible avec Calva (l'extension VS Code la plus répandue pour Clojure).