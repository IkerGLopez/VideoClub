package Fase1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class CatalogoIMDB {

	final String menor = String.valueOf(1900);
	final String mayor = String.valueOf(2030);

	private ListaPeliculas IListaPeliculas;
	private InterfaceInterpretes inters;


	//PATRON SINGLETON 
	private static CatalogoIMDB cat;

	public static CatalogoIMDB getInstance(InterfaceInterpretes patata) {
		if (cat == null) {
			cat = new CatalogoIMDB(patata);
		}
		return cat;
	}
	
	//CONSTRUCTORA
	private CatalogoIMDB(InterfaceInterpretes patata) {
		IListaPeliculas = new ListaPeliculas();
		this.setInterpretes(patata);
		
		//IListaPeliculas = (InterfaceInterpretes) new Object();
		//IListaPeliculas = (InterfaceInterpretes) new Object();
		//inters = new ListaInterpretes();
	}
	
	//GETTER
	public ListaPeliculas getListaPeliculas() {
		return IListaPeliculas;
	}

	/**
	 * Carga las peliculas del fichero cuyo nombre se pasa como parametro
	 * @param nomF
	 */
	public void cargarPeliculas(String nomF) {
		try {
			System.out.println("Cargando peliculas...");
			Scanner entrada = new Scanner(new FileReader(nomF));
			String[] linea;
			while (entrada.hasNext()) {
				linea = entrada.nextLine().split("\t");
				Pelicula auxi = new Pelicula(linea[0]); 
				auxi.setAno(Integer.parseInt(linea[1]));
				auxi.setRating(Float.parseFloat(linea[2]));
				auxi.setNumvotos(Integer.parseInt(linea[3]));
				IListaPeliculas.anadirPelicula(auxi);
			}
			System.out.println("En el catalogo hay " + IListaPeliculas.size() + " peliculas");
			entrada.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido leer el fichero.");
			e.printStackTrace();
		}
	}

	
	/**
	 * Carga los interpretes del fichero cuyo nombre se pasa como parametro
	 * @param nomF
	 */
	public void cargarInterpretes(String nomF) {
		try {
			System.out.println("Cargando interptretes...");
			Scanner entrada = new Scanner(new FileReader(nomF));
			while (entrada.hasNext()) {
				String linea = entrada.nextLine();
				String split1[] = linea.split("->", 0);
				String split2[] = split1[1].split("\\|\\|", 0);
				Interprete auxi = new Interprete(split1[0]);
				for (String pel : split2) {
					Pelicula peliBuscar = IListaPeliculas.buscarPelicula(pel);
					auxi.anadirPelicula(peliBuscar);
					peliBuscar.anadirInterprete(auxi);
				}
				inters.anadirInterprete(auxi);
			}
			System.out.println("En el catalogo hay " + inters.size() + " interpretes\n");
			entrada.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido leer el fichero.");
			e.printStackTrace();
		}
	}

	/**
	 *Muestra en pantalla la informacion de la pelicula cuyo nombre se pasa como parametros
	 * @param titulo T�tulo de la pel�cula
	 */
	public void imprimirInfoPelicula(String titulo) {
		Pelicula auxi = IListaPeliculas.buscarPelicula(titulo);
		System.out.println("Titulo: " + titulo);
		System.out.println("A�o: " + auxi.getAno());
		System.out.println("Rating: " + auxi.getRating());
		System.out.println("Cantidad de votos: " + auxi.getNumvotos());
		System.out.println("\nNumero de interpretes: " + auxi.inters.size() + "\nY los interpretes son:");
		for (Interprete i : auxi.inters.getLista()) {
			System.out.println(i.getNombre());
		}
	}

	/**
	 * Muestra en pantalla la informacion del interprete cuyo nombre se pasa como parametros
	 * @param nombre
	 */
	public void imprimirInfoInterprete(String nombre) {
		Interprete auxi = inters.buscarInterprete(nombre);
		System.out.println("Nombre: " + auxi.getNombre());
		auxi.calcularRating();
		System.out.println("Rating: " + auxi.getRating());
		System.out.println("Total de pel�culas del int�rprete: " + auxi.pelis.size());
		for (Pelicula i : auxi.pelis.getLista()) {
			System.out.println(i.getTitulo());
		}
	}

	/**
	 *A�ade un nuevo voto a una pel�cula 
	 *PRE: el valor del voto est� entre 0.0 y 10.0.
	 * @param titulo T�tulo de la pel�cula
	 * @param voto   Valor del voto
	 */
	public void anadirVoto(String titulo, float voto) {
		Pelicula auxi = IListaPeliculas.buscarPelicula(titulo);
		auxi.anadirVoto(voto);
	}
	
	/**
	* Elimina del cat�logo la pel�cula cuyo t�tulo se pasa como par�metro.
	* Adem�s, elimina la pel�cula de la lista de pel�culas de cada uno de los
	* int�rpretes de dicha pel�cula.
	* Aquellos int�rpretes que se quedan sin pel�culas son eliminados del
	* cat�logo, y al resto se les actualiza el rating.
	*/
	public void eliminarPelicula(String titulo) {
		Pelicula auxi = IListaPeliculas.buscarPelicula(titulo);
		for (Interprete i : auxi.getInters().getLista()) {
			i.getPelis().getLista().remove(auxi);
			if (i.getPelis().size() == 0) {
				inters.eliminarInterprete(i.getNombre());
			}
			i.calcularRating();
		}
		
		IListaPeliculas.eliminarPelicula(auxi);
		System.out.println("Se ha eliminado la pelicula " + titulo + "\nEn el catalogo quedan " + IListaPeliculas.size() + " y " + inters.size() + " interpretes");
	}
	
	
	
	/**
	* Inicializa el conjunto de int�rpretes del cat�logo con el conjunto de
	* int�rpretes que se le pasa como par�metro
	* @param int�rpretes: conjunto de int�rpretes
	*/
	public void setInterpretes(InterfaceInterpretes interpretes) {
		this.inters = interpretes;
	}
	
	//FASE 3
	/**
	* Devuelve la distancia m�nima entre dos int�rpretes dados.
	* @param inter1: nombre del primer int�rprete
	* @param inter2: nombre del segundo int�rprete
	* @return: distancia m�nima entre ambos int�rpretes. En caso de que no
	* est�n conectados, devuelve -1.
	* O(N+A)
	*/
	public int distancia(String inter1, String inter2){

		Interprete origen = inters.buscarInterprete(inter1);
		HashMap<Interprete, Integer> visitados = new HashMap<Interprete, Integer>();		
		Queue<Interprete> cola = new LinkedList<Interprete>();
		cola.add(origen);
		visitados.put(origen, 0);
		boolean enc = false;
		Interprete inter = null;
		
		while(!cola.isEmpty() && !enc) {
			inter = cola.remove();
			if(inter.getNombre().equals(inter2)) {
				enc = true;
			}
			else {
				for(Interprete aux: inter.obtenerAdyacentes()) {
					if(!visitados.containsKey(aux)) {
						cola.add(aux);
						visitados.put(aux, visitados.get(inter)+1);
					}
				}
			}
		}
		if(enc) {
			return visitados.get(inter);
		}
		else return -1;
		}
	
	
	/**
	* Imprime el camino m�s corto entre dos int�rpretes. Si no existe camino,
	* imprime un mensaje indicando este hecho.
	* @param inter1: nombre del primer int�rprete
	* @param inter2: nombre del segundo int�rprete
	* O(N+A)
	*/
	public void imprimirCamino(String inter1, String inter2) {
		
		Interprete origen = inters.buscarInterprete(inter1);
		LinkedList<String> resultado = new LinkedList<String>();
		HashMap<Interprete, Interprete> visitados = new HashMap<Interprete, Interprete>();
		Queue<Interprete> cola = new LinkedList<Interprete>();
		cola.add(origen);
		visitados.put(origen, null);
		boolean encontrado = false;
		while(!cola.isEmpty() && !encontrado) {
			Interprete inter = cola.remove();
			if(inter.getNombre().equals(inter2)) {
				encontrado = true;
			}
			else {
				for(Interprete aux:inter.obtenerAdyacentes()) {
					if(!visitados.containsKey(aux)) {
						cola.add(aux);
						visitados.put(aux, inter);//a aux hemos llegado desde calle
					}
				}
			}
		}
		Interprete destino = inters.buscarInterprete(inter2);
		if(encontrado) {
			Interprete actual = destino;
			while(actual!=null) {
				resultado.addFirst(actual.getNombre());
				actual = visitados.get(actual);
			}
			for(String resul: resultado) {
				System.out.println(resul);
			}
		}
		else {
			System.out.println("No hay camino que conecte esos dos interpretes");
		}
	}
}

	
