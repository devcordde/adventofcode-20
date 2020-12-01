# adventofcode-20

Ein Sammel-Repository für [Advent of Code](https://adventofcode.com) 2020-Lösungen der deutschen [DevCord](http://join.devcord.xyz)-Community.

A repository collecting [Advent of Code](https://adventofcode.com) 2020 solutions made by the German [DevCord](http://join.devcord.xyz) community.

## Überblick/Overview

Hier ist ein Überblick über die Vollständigkeit sowie die existierenden Lösungen und verwendeten Sprachen in diesem Repository:

Here's an overview of the completeness as well as the existing solutions and languages used in this repository:

| Day/Lang | Python | TypeScript | C   | Kotlin |
| -------- | ------ | ---------- | --- | ------ |
| 01       | ✅      | ✅          | ✅   |        |

❌   = Keine Lösung/No solution,
✅   = Voll gelöst/Fully solved,
(✅) = Erster Teil gelöst/First part solved

## Ordnerstruktur/Directory Structure

Die Struktur dieses Repositorys ist wie folgt: 

The structure of this repository is as follows:

- shared        (1)
  - `user`
    - \*
- Day-`XX`        (2) 
  - `lang`        (3)
    - `user`    (4)

(1) Der *shared*-Order ist für zusätzliche Dateien, die neben der Lösungsdatei benötigt werden, etwa Hilfscode oder Bibliotheken. Jede\*r Beitragende darf dort einen eigenen, entsprechend benannten Ordner erstellen, über den frei verfügt werden darf.

(2) Jeder neue Tag bekommt einen eigenen Ordner, der nach dem Schema *Day-`XX`* benannt ist, wobei `XX` die zwei Ziffern des Tages darstellen. Also z.B. steht *Day-01* für Tag 1 und *Day-15* für Tag 15.

(3) Jeder Tag hat je einen Unterordner für die Programmiersprachen, in denen er gelöst wurde. Dabei ist für den Namen des Ordners der kleingeschriebene Name der Sprache zu verwenden, also z.B. *java*, *go* oder *javascript*.

(4) Es können mehrere Lösungen von unterschiedlichen Beitragenden in der gleichen Programmiersprache abgegeben werden. Deshalb sollte sich pro Lösung ein Ordner in dem entsprechenden Sprachen-Ordner befinden, der den Namen des\*der Beitragenden trägt und den Quellcode der Lösung (für beide Teilaufgaben, falls vorhanden) enthält. Der Name dieses Ordners sollte identisch mit dem Namen des dazu passenden Ordner in *shared* sein, falls einer existiert.

----------------

(1) The *shared* directory can be used for additional files and resources contributors might need besides their solution file, such as helper code or libraries. Every contributor may create an own directory in *shared* that corresponds to their name.

(2) Every day of the challenge gets an own directory named with the scheme of *Day-`XX`*, where `XX` stands for the two digits of the day. E.g. *Day-01* is for day 1 and *Day-15* is for day 15.

(3) Every day gets one sub directory for each language it has been solved in. The name of those directories must be the name of the programming language in lower case, e.g. *java*, *go* or *javascript*.

(4) There can be more than one solution in each language for each day by different contributors. For this reason, for each solution, there should be one directory in the corresponding language directory for the day named after the contributor who created it containing the source code of that solution. This should be the same name that is also used for the personal directory in *shared*, should one exist.

## Lösungen ausführen/Run the solutions

TODO

## Anleitung zum Beitragen (only for DevCord members)

Wenn du zu den Lösungen beitragen möchtest, hast du zwei Optionen: **Selbst mit git arbeiten und Pull Requests erstellen** oder **deine Lösungen der Verwaltung dieses Repositories auf Discord schicken**. Wir empfehlen ersteres, wenn du schon mal git benutzt hast und zweiteres, wenn du von diesem System nur Bahnhof verstehst.

### Pull Requests erstellen

1. Forke dieses Repository in deinen GitHub-Account (oben rechts auf "Fork" klicken)
2. Klone das Repository bei dir lokal
3. Füge deine Lösungen hinzu und bearbeite sie - **beachte dabei die [Ordnerstruktur](#ordnerstrukturdirectory-structure)**
4. Bearbeite **nicht** die README! Die Tabelle wird von uns ergänzt.
5. Mache eine Pull Request wann immer du
   - neue funktionierende Lösung(en) hast
   - Lust drauf hast (du musst nicht jeden Tag eine Pull Request machen, kannst dir auch Zeit damit lassen)

### Lösungen an die Verwaltung geben

1. Löse die Aufgabe(n)
2. Schicke sie an einen der folgenden DevCord-User mit Informationen zu Sprache, Tag, evtl. benötigten zusätzlichen Dateien und Namen:
   - `das_#9677`
   - `Johnny#3826`
