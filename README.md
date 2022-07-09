# SMART ROBOT
Se tiene un robot cuyo objetivo consiste en encontrar 2 ítems que están ocultos en un espacio de 10x10 casillas. En el ambiente se tienen 2 naves que el robot puede usar y que facilitan la exploración del lugar pero también existe un elemento hostil para el agente, son casillas con aceite que afectan el estado del robot.

El robot puede moverse arriba, abajo, izquierda y derecha. 
* El costo de cada movimiento realizado por el robot es de 1.
* Al obtener un ítem el costo de desplazarse no cambia porque el peso de los ítems es despreciable. 
* Llegar a una casilla con aceite tiene un costo de 4. 
* Al utilizar las naves que se encuentran en el ambiente, se tiene el costo de 1. Sin embargo, al pasar por casillas donde hay aceite estando en la nave éste no afecta al robot y por lo tanto el costo sigue siendo 1. 
* La nave 1 está cargada con combustible para 10 casillas mientras que la nave 2 para 20 casillas.

## Implementación
Se aplicaron algoritmos de búsqueda informada y no informada:
* **Búsqueda no informada:**
    * Amplitud (Breadth First Search).
    * Costo uniforme (Uniform Cost Search).
    * Profundidad de evitando ciclos (Depth First Search - avoiding cycles).
* **Búsqueda informada:**
    * Avara (Greedy Search).
    * A* (A star).

## Autores
* **Franklyn Narvaez** - *Desarrollador* - [Franklynnarvaez](https://github.com/Franklynnarvaez)
* **Christian Sanchez** - *Desarrollador* - [chris-Sanchez098](https://github.com/chris-Sanchez098)
* **Víctor Sapuyes** - *Desarrollador* - [victor262108](https://github.com/victor262108)

## Ejecuctables jar
En este enlace se encuentran alojados los ejecuctables de la aplicacion: [Ejecutables](https://correounivalleeduco-my.sharepoint.com/:f:/g/personal/victor_sapuyes_correounivalle_edu_co/En4zroCTq2hBkhDTLPKKSd8BQbOXKmFI2Pvpc4CMAeFOQw?e=3OycwI)

## Licencia
Este proyecto está bajo la Licencia *Apache License*, version 2.0. ver [LICENSE.md](LICENSE.md) para más detalles. 
