# Battleships - Wzorce Projektowe

## Główne Założenia

Celem projektu jest przeniesienie klasycznej gry planszowej "Statki" do formy interaktywnej aplikacji okienkowej, z wykorzystaniem biblioteki Java Swing do stworzenia graficznego interfejsu użytkownika. Gracze mogą taktycznie rozmieszczać swoje statki i strzelać w przeciwnika, korzystając z prostego i intuicyjnego interfejsu graficznego.

## Funkcjonalności

### Gra przeciwko Komputerowi
- Gracze mogą rozegrać mecz przeciwko komputerowi z trzema poziomami trudności (łatwy, normalny, trudny).
- Możliwość interaktywnego rozmieszczania statków przed rozpoczęciem gry.
- Po każdym strzale, gracz otrzymuje informację o wyniku (trafienie lub pudło).
- Pierwszy gracz, który zatopi wszystkie statki przeciwnika, wygrywa.
- Po zakończeniu gry, gracze otrzymują punkty do rankingu, które mogą zostać użyte w sklepie.

### Symulacja Gry Między Komputerami
- Umożliwia symulację rozgrywki między dwoma komputerami, a gracz może obserwować przebieg bitwy.

### Zasady Gry
- Gracze mogą w dowolnym momencie uzyskać dostęp do zasad gry, aby lepiej zrozumieć reguły.

### Zakupy w Sklepie
- Możliwość zdobycia specjalnych statków lub barier, które mogą być zakupione w wirtualnym sklepie za punkty rankingowe.

### Ranking
- System rankingowy pozwala na śledzenie postępów graczy i porównywanie wyników z innymi.

## Wzorce Projektowe

1. **Singleton**
    - **Cel**: Zapewnienie, że istnieje tylko jedna instancja kontrolera gry (`Game`).
    - **Lokalizacja**: `Game.java`.

2. **State**
    - **Cel**: Zarządzanie różnymi stanami gracza, takimi jak atakowanie (`ShotState`) czy obrona (`DefendState`).
    - **Lokalizacja**: `model.clases.State`.

3. **Metoda Fabrykująca**
    - **Cel**: Tworzenie różnych typów statków (np. łodzi podwodnych, niszczycieli).
    - **Lokalizacja**: `model.clases.FactoryMethod`.

4. **Dekorator**
    - **Cel**: Modyfikowanie punktów rankingowych w zależności od poziomu trudności.
    - **Lokalizacja**: `model.clases.Decorator`.

5. **Obserwator**
    - **Cel**: Obserwowanie zmian na planszy i dostosowanie prawdopodobieństw dla strategii AI.
    - **Lokalizacja**: `model.clases.strategy`, `model.clases.iterator`.

6. **Strategia**
    - **Cel**: Kapsułkacja różnych algorytmów poziomu trudności AI.
    - **Lokalizacja**: `model.clases.strategy`.

7. **Iterator**
    - **Cel**: Przechodzenie po tablicy prawdopodobieństw w celu odnalezienia najlepszego pola do strzału.
    - **Lokalizacja**: `model.clases.iterator`.

8. **Model-View-Controller (MVC)**
    - **Cel**: Oddzielenie logiki gry od interfejsu graficznego, co ułatwia rozwój i utrzymanie aplikacji.
    - **Lokalizacja**: `controller`, `model`, `view`.

## Interesujące Zagadnienia Projektowe

- **Wykorzystanie Swinga**: Prosta integracja Swing do zarządzania interfejsem graficznym i zapewnienia estetycznego wyglądu.
- **Architektura MVC**: Ułatwia dodawanie nowych widoków i zmianę strategii prezentacji bez modyfikacji logiki aplikacji..

