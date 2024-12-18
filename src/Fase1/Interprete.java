package Fase1;

import java.util.HashSet;

public class Interprete {

	private String nombre;
	private float rating;

	ListaPeliculas pelis;

	
	// CONSTRUCTORAS
	public Interprete(String n) {
		this.nombre = n;
		pelis = new ListaPeliculas();
	}

	public Interprete() {
	}

	public ListaPeliculas getPelis() {
		return pelis;
	}

	// GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	 * Calcula y asigna el rating del intérprete en base al rating de sus películas
	 */
	public void calcularRating() {
		float sum = 0;
		float cantVotos = 0;
		for (int i = 0; i < pelis.size(); i++) {
			if (this.getRating() != -1) {
				sum = sum + (pelis.get(i).getRating() * pelis.get(i).getNumvotos());
				cantVotos = cantVotos + (pelis.get(i).getNumvotos());
			}
		}
		if (sum != 0) {
			rating = sum / cantVotos;
		}
	}

	/**
	 * Añade una película al intérprete
	 * @param pel Película a añadir POST: El rating del intérprete NO se modifica en este momento
	 */
	public void anadirPelicula(Pelicula pel) {
		pelis.add(pel);
	}
	public int hashCode() {
		return nombre.hashCode();
		
		
	}
	
	/**
	* Devuelve un HashSet con todos los adyacentes del intérprete, es decir,
	* aquellos intérpretes con los que ha participado en alguna película.
	* @return: el HashSet con los intérpretes que son adyacentes.
	*/
	public HashSet<Interprete> obtenerAdyacentes(){
		HashSet<Interprete> auxi = new HashSet<Interprete>();
		for(int i = 0; i<pelis.size();i++) {
			ListaInterpretes listaInterpretesDeCadaPelicula = pelis.get(i).getInters();
			for(int j = 0; j < listaInterpretesDeCadaPelicula.size(); j++) {
				auxi.add(listaInterpretesDeCadaPelicula.get(j));
			}
		}
		return auxi;
	}
	/**
	 * Compara dos interpretes baasandose en sus nombres
	 * @param aComparar
	 * @return
	 */
	public int compareTo(Interprete aComparar) {
		int l1 = aComparar.nombre.length();
		int l2 = this.nombre.length();
		int lmin = Math.min(l1, l2);

		for (int i = 0; i < lmin; i++) {
			int str1 = (int) aComparar.nombre.toLowerCase().charAt(i);
			int str2 = (int) this.nombre.toLowerCase().charAt(i);

			if (str1 != str2){
				return str1 - str2;
			}
		}

		if (l1 > l2)
			return 1;
		else if (l1 < l2)
			return -1;
		else
			return 0;
	}
}