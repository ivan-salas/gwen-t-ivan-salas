# Gwen't

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of the
[_Gwent_](https://www.playgwent.com/en)card game developed by [_CD PROJEKT RED_](https://cdprojektred.com/en/)

---

**Estructura del proyecto**

El proyecto está estructurado en 5 paquetes distintos:

- PackageBarajas: Contiene las clases necesarias para crear las barajas de cartas, en especifico contiene: AbstractBaraja, Baraja, Mano y Mazo. Los jugadores utilizaron una mano y un mazo cada uno a lo largo del juego.
- PackageCartas: Contiene las clases necesarias para modelar las distintas cartas del juego. El juego tiene cartas del tipo: Clima, CuerpoCuerpo, Asedio y Rango. Las cartas se juegan en el tablero a través de un double dispatch.
- PackageEstados: Se utiliza el patron de diseño State para modelar los distintos estados en los cuales se puede encontrar el juego, los detalles de los estados y sus transiciones se encuentran en el diagrama adjunto.
- PackageJugador: Contiene las clases necesarias para modelar a los jugadores del juego, en este caso son la clas Usuario y Cpu, tambien hay un trait Jugador y una clase abstracta AbstractJugador. Aparte de esas clases tambien se tiene la clase Gem Observer que se encarga de avisar al controlador el estado de las gemas de los jugadores, esto se hizo modelando un observer pattern.
- PackageTablero: El tablero del juego se divide en tres zonas, la zona de la carta clima (unica tanto para cpu como jugador), la zona CPU y la zona Usuario, a su vez las zonas de los jugadores se subdividen en tres filas las cuales almacenan las cartas del tipo correspondiente.
- PackageView: Contiene el trait con la UserInterface y una clas con una implementación simple, pero no se ha completado la UI del juego.

Tambien contamos con la clase GameController fuera de estos paquetes.

Sobre la implementación de los efectos de las cartas en el proyecto. Se tomaron los siguientes supuestos:
- Solo existen 4 cartas clima, una por cada efecto contemplado (esto para simplificar la implementación)
- Solo existe una carta con refuerzo moral, la carta cuerpo a cuerpo de nombre "Bob Charlie" (esto para simplificar la implementación)
- Se usa pattern matching para implementar los efectos de las cartas clima, "matcheando" los nombres correspondientes.

Tambien para efectos de demostrar el funcionamiento correcto del juego, el controlador a través de los estados siempre va a generar una partida con las mismas cartas iniciales, esto se puede interpretar como que en esta version de gwent, solo hay un set fijo inicial de cartas para cada partida, ese set es identico para el usuario y para CPU.

A continuación se presenta el diagrama de estados del proyecto:
![diagra-estados.drawio (2).png](..%2F..%2F..%2F..%2F..%2FDownloads%2Fdiagra-estados.drawio%20%282%29.png)