package Fase1;

import java.util.Scanner;

public class AplicacionIMDB {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String auxi;
		String auxi1;
		String auxi2;
		float auxi3;

		// TO DO: ...
		CatalogoIMDB catalogo;
		HashMapInterpretes mapadeInterpretes = new HashMapInterpretes();

		System.out.println("Bienvenid@ a la aplicaci�n IMDB");
		catalogo = CatalogoIMDB.getInstance(mapadeInterpretes);
		// TO DO: Cargar pel�culas
		catalogo.cargarPeliculas("src\\Files\\smallerfiles\\films_tiny.txt");
		// catalogo.mostrarPeliculas();
		// TO DO Cargar int�rpretes
		catalogo.cargarInterpretes("src\\Files\\smallerfiles\\cast_tiny.txt");
		// ABBInterpretes mapadeInterpretes = new ABBInterpretes() =
		// catalogo.cargarInterpretes("src\\Files\\smallerfiles\\cast_tiny.txt");
		// Men�
		try {
			int opcion = -1;
			while (opcion != 0) {
				System.out.println("\n\nEscoja una opci�n:");
				System.out.println("1. Mostrar informaci�n de pel�cula");
				System.out.println("2. Mostrar informaci�n de int�rprete");
				System.out.println("3. A�adir voto a pel�cula");
				System.out.println("4. Eliminar pel�cula del cat�logo");
				System.out.println("5. Calcular distancia entre interpretes");
				System.out.println("6. Mostrar camino entre interpretes");
				System.out.println("0. Salir");

				opcion = Integer.parseInt(sc.nextLine());

				switch (opcion) {
				case 1:
					try {
						System.out.println("\nIntroduzca el nombre de la pel�cula");
						auxi = sc.nextLine();
						System.out.println("\n");
						catalogo.imprimirInfoPelicula(auxi);
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado esa pel�cula, int�ntelo de nuevo\n");
					}
					break;

				case 2:
					try {
						System.out.println("Introduzca el nombre del int�rprete");
						auxi = sc.nextLine();
						System.out.println("\n");
						catalogo.imprimirInfoInterprete(auxi);
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado ese int�rprete, int�ntelo de nuevo\\n");
					}
					break;

				case 3:
					try {
						System.out.println("Introduzca el nombre de la pelicula que quiere valorar");
						auxi = sc.nextLine();
						System.out.println("Introduzca una puntuaci�n entre 0.0 y 10.0");
						auxi3 = Float.valueOf(sc.nextLine());
						if (auxi3 < 0.0 || auxi3 > 10.0) {
							System.out.println("Ese valor no esta admitido, por favor intrduzca un valor valido\n");
							break;
						}
						catalogo.anadirVoto(auxi, auxi3);
						Pelicula pel = catalogo.getListaPeliculas().buscarPelicula(auxi);
						System.out.println("El nuevo rating de la pel�cula es: " + pel.getRating());
						System.out.println("\n");
						break;
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado esa pelicula, intentelo de nuevo\n");
						break;
					}

				case 4:
					try {
						System.out.println("Introduzca el nombre de la pel�cula que quiere eliminar:");
						auxi = sc.nextLine();
						catalogo.eliminarPelicula(auxi);
						break;
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado esa pelicula, intentelo de nuevo\n");
						break;
					}
				case 5:
					try {
						System.out.println("Introduzca los nombres de los interpretes para calcular su distancia:");
						auxi1 = sc.nextLine();
						auxi2 = sc.nextLine();
						System.out.println(catalogo.distancia(auxi1, auxi2));
						break;
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado esa pelicula, intentelo de nuevo\n");
						break;
					}
				case 6:
					try {
						System.out.println("Introduzca los nombres de los interpretes para mostrar los interpretes mediante los que se relacionan:");
						auxi1 = sc.nextLine();
						auxi2 = sc.nextLine();
						catalogo.imprimirCamino(auxi1, auxi2);
						break;
					} catch (java.lang.NullPointerException e) {
						System.out.println("No se ha encontrado esa pelicula, intentelo de nuevo\n");
						break;
					}
				}
			}
		} catch (java.lang.NumberFormatException e) {
			System.out.println("T�rmino incorrecto, cerrando programa \n...");
		}
		sc.close();
	}
}