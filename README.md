# Sistema de Ficheros FAT

![Imagen Representativa FAT32](https://user-images.githubusercontent.com/118160820/233442146-e6103875-f7a8-49fe-bd86-7612f3c2f620.png)
![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)

## Índice

- [Descripción](#descripción)
- [Estado del Proyecto](#estado-del-proyecto)
- [Características de la Aplicación y Demostración](#características-de-la-aplicación-y-demostración)
- [Personas-Desarrolladores del Proyecto](#personas-desarrolladores-del-proyecto)
- [Conclusión](#conclusión)

## Descripción del Proyecto

Dicho proyecto es una simulacion de como funcionaria un sistema de archivos FAT en su interior. Tenemos multiples funciones, como las que tendriamos en un caso real: Crear, Mover, Borrar, Copiar y Mostrar el estado de la FAT.

## Estado del Proyecto

Actualmente el proyecto es totalmente funcional por consola. Aunque se esta desarollando una interfaz grafica mediante la libreria Swing de java. Esta funcion estara disponible en versiones posteriores del proyecto.

## Características de la Aplicación y Demostración

La aplicacion permite, en el momento de lanzamiento, elegir un tamaño de clusters que querremos que contenga nuestro sistema de ficheros FAT.
En primer momento de lanzamiento, se crearan clusters vacios, como se muestra en la siguiente imagen:
(Ejemplo para una FAT creada con 15 clusters)
<img width="368" alt="Estado FAT inicialmente" src="https://github.com/Yorrryiii/PracticaSSOO/assets/118160820/4f3408dd-2522-472b-bd8e-b616a4e52fb1">

Para poder lanzar la aplicacion desde un ordenador, lo podremos hacer de multiples formas:
1. Bajando el codigo fuente e incluirlo dentro de un proyecto de vuestro id de preferencia. (Nosotros hemos usado Visual Studio Code)
2. Bajando el .jar ejecutable del proyecto que se encuentra dentro de la carpeta JarFiles del proyecto. (Ejecutar el siguiente comando en consola: java -jar (RutaAlArchivo)/ExecutableJAR.jar)

Cuando la aplicacion se lanza de primeras, se lanzara con un unico directorio creado. Dicho directorio es el directorio raiz, el cual se referenciara como root o root/.

Para empezar a crear archivos, se llamara al metodo crear mostrado por la consola. Ejemplo de crearcion de un archivo en la carpeta root/:
<img width="302" alt="Captura de pantalla 2023-06-11 a las 18 41 23" src="https://github.com/Yorrryiii/PracticaSSOO/assets/118160820/a7158910-3e68-462f-9184-54e1411b3ab7">
El tamaño SIEMPRE sera en CLUSTERS.

Ejemplo para la creacion de un directorio en la carpeta root/:
<img width="302" alt="Captura de pantalla 2023-06-11 a las 18 41 23" src="https://github.com/Yorrryiii/PracticaSSOO/assets/118160820/2cf78873-ee7d-4a5f-bd53-5179ea5a4441">

Nota: los archivos creados, copiados, movidos o borrados son solo una simulacion. El programa no esta comunicado con los archivos que contiene el ordenador.

Para referenciar a una ruta completa cuando el programa solicite el nombre de algun archivo/carpeta, se hara mediante la representacion de la ruta completa.
Ejemplo direcotio: root/NotasInformatica/2_Curso/
Ejemplo archivo: root/InfoComunidadesESP/zaragoza.dat
(Dichos ejemplos son rutas ficticias que no existen dentro en el momento de creación)

Nota: al especificar un direcotio, puedes o no incluir la barra del final de este.

## Personas-Desarrolladores del Proyecto

Proyecto desarrollado por:

- Jorge Serrano Galindo (alu.123202@usj.es)
- Ramiro Buisan Matarredona (alu.130042@usj.es)
- Iván Royo Gutiérrez (alu.135046@usj.es)
- Fernando Otal Toro (alu.134960@usj.es)
- Chris Andrei Ardeleanu (alu.136065@usj.es)
- Santiago Sarsa Gracia (alu.136052@usj.es)


## Conclusión

Este proyecto ha sido creado por un grupo de alumnos de la Universidad San Jorge (USJ) y desarrollado para la asignatura de Sistemas Operativos de 2º Curso, cuyo profesor es Carlos Cetina Englada (ccetina@usj.es)
