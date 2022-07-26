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

TBD