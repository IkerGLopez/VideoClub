package Fase1;

import java.util.ArrayList;

public class ListaPeliculas {
	private ArrayList<Pelicula> lista;
	
	
	//CONSTRUCTORA
	public ListaPeliculas() {
		lista = new ArrayList<Pelicula>();
	}
	
	
	//GETTER
	public ArrayList<Pelicula> getLista() {
		return lista;
	}
	
	/**
	* Añade una película a la lista
	* @param pel Película a añadir
	*/
	public void anadirPelicula(Pelicula pel) {
		lista.add(pel);
	}

	/**
	 * Muestra los titulos de todas la peliculas de la lista
	 */
	public void mostrarPeliculas() {
		for(Pelicula pelis : this.lista)System.out.println(pelis.getTitulo());
	}
	
	/**
	 * Devuelve el numero de peliculas en la lista
	 * @return tamaño de la lista
	 */
	public int size() {
		return lista.size();
	}
	
	/**
	 * Añade la pelicula pasada como parametro a la lista
	 * @param p
	 */
	public void add(Pelicula p) {
		lista.add(p);
	}
	
	/**
	 * Devuelve la pelicula en la posicion i
	 * @param i posicion de la pelicula
	 * @return pelicula en la posicion i
	 */
	public Pelicula get(int i) {
		return lista.get(i);
	}
	
	/**
	* Busca una película en la lista y la devuelve
	* @param titulo Título de la película a buscar
	* @return la Película (si está en la lista), null en caso contrario
	*/
	public Pelicula buscarPelicula(String titulo) {
	        int lo = 0;
	        int hi = lista.size() - 1;
	        int mid=0;
	        while (lo <= hi) {
	        	mid = lo + (hi - lo)/2;
	            if (lista.get(mid).equals(titulo)){
	            	return lista.get(mid);
	            }
	            else if (lista.get(mid).compareTo(titulo) < 0) {
	                lo = mid + 1;
	            }
	            else {
	                hi = mid;
	            }
	            if(mid==lo) {
	            	return null;
	            }
	        }
	        if(lista.get(mid).getTitulo().equals(titulo)) {
	        	return lista.get(mid);
	        }
	        else {
	        	return null;
	        }
	}
	
	/**
	 * Elimina la pelicula pasada como parametro de la lista de peliculas, si la pelicula no se encuentra devuelve false
	 * @param pel
	 * @return
	 */
	public boolean eliminarPelicula(Pelicula pel) {
		if (this.getLista().contains(pel)) {
			this.getLista().remove(pel);
			return true;
		}
		return false;
	}


	
}