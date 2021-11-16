# 2
* a) Zeichnen Sie einen vollständigen Entscheidungsbaum für n = 3 Datenelemente auf.
  [D1, D2, D3] wäre dann die sortierte Folge

  ![Entscheidungsbaum](Entscheidungsbaum.png)


* b) Für einen Binärbaum mit B Blättern gilt bekanntlich h ≥ log2(B)+1. Stimmt dies für Ihren aufgezeichneten
  Entscheidungsbaum?

  h >= (log2(6)+1 = 3.58496) -> h = 4


* c) Wie viele Vergleiche sind also mindestens notwendig, um jeden der n! möglichen Ausgangssituationen differenzieren
  zu können?

  C >= (h - 1 = 4 - 1 = 3 || log2(6) = 2.58496) -> C = 3


# 3
* a) Angenommen, Ihnen steht ein super Sortieralgorithmus mit einer Laufzeitkomplexität von
  O(g(n)) zur Verfügung. Der Algorithmus ist aber leider instabil! Wie liesse sich erreichen,
  dass schlussendlich trotzdem ein stabiles Sortierergebnis vorliegt? Dokumentieren Sie die
  Modifikationen gut nachvollziehbar (vgl. Pseudocode, Beispiel, Grafik, Prosa).

  - Elemente vorhägnig druch numerieren. Die numerierung als kleinste prio beim sortieren verwenden. 

  