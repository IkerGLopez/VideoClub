package Fase1;

import java.util.ArrayList;

public class ListaInterpretes implements InterfaceInterpretes {

	private ArrayList<Interprete> lista;

	
	//CONSTRUCTORA
	public ListaInterpretes() {
		lista = new ArrayList<Interprete>();
	}
	
	
	//GETTERS
	public ArrayList<Interprete> getLista() {
		return lista;
	}

	/**
	 * A�ade un int�rprete a la lista
	 * @param inter Int�rprete a a�adir
	 * @Override
	 */
	public void anadirInterprete(Interprete inter) {
		lista.add(inter);
	}

	/**
	 * Busca un int�rprete en la lista y lo devuelve
	 * @param nombre Nombre del int�rprete a buscar
	 * @return el Interprete (si est� en la lista), null en caso contrario
	 * @Override
	 */
	public Interprete buscarInterprete(String nombre) {
		for (Interprete i : lista) {
			if (i.getNombre().equals(nombre)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Devuelve el tama�o de la lista
	 * @Override
	 */
	public int size() {
		return lista.size();
	}

	/**
	 * A�ade un interprete a la  lista
	 * @param in
	 */
	public void add(Interprete in) {
		lista.add(in);
	}

	/**
	 * Devuelve el interprete en la posicion pasada como parametro
	 * @param i posicion del interprete a devolver
	 * @return interprete en la poscion i
	 */
	public Interprete get(int i) {
		return lista.get(i);
	}

	/**
	 * Busca un int�rprete dado su nombre como par�mero, lo elimina y lo devuelve.
	 * @param nombre Nombre del int�rprete a buscar
	 * @return el Interprete
	 */
	@Override
	public Interprete eliminarInterprete(String nombre) {
		Interprete interborrar = new Interprete(nombre);
		this.getLista().remove(interborrar);
		return interborrar;
	}
}