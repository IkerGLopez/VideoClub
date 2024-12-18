package Fase1;


public class Pelicula {

	private String titulo;
	private int ano;
	private float rating;
	private int numvotos;

	ListaInterpretes inters;

	//CONSTRUCTORAS
	public Pelicula() {
	}

	public Pelicula(String titulo) {
		this.titulo = titulo;
		inters = new ListaInterpretes();
	}

	
	// GETTERS Y SETTERS
	public ListaInterpretes getInters() {
		return inters;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int año) {
		this.ano = año;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getNumvotos() {
		return numvotos;
	}

	public void setNumvotos(int numvotos) {
		this.numvotos = numvotos;
	}

	/**
	 * Añade un intérprete a la película
	 * @param inter Intérprete a añadir
	 */
	public void anadirInterprete(Interprete inter) {
		inters.add(inter);
		inter.calcularRating();
	}

	/**
	 * Añade un nuevo voto a la película. POST: se han recalculado los ratings de
	 * sus intérpretes
	 * @param voto
	 */
	public void anadirVoto(float voto) {
		float rat = ((this.getRating() * this.getNumvotos()) + voto) / (this.getNumvotos() + 1);
		this.setRating(rat);
		this.setNumvotos(numvotos + 1);
		for (Interprete i : this.getInters().getLista()) {
			i.calcularRating();
		}
	}

	/**
	 * Compara dos pelicuals basandose en su titulo
	 * @param aComparar
	 * @return
	 */
	public int compareTo(String aComparar) {
		return this.titulo.compareTo(aComparar);
	}

	/**
	 * Comprueba si dos peliculas son iguales basandose en su titulo
	 * @param nom
	 * @return
	 */
	public boolean equals(String nom) {
		return this.getTitulo().equals(nom);
	}
}