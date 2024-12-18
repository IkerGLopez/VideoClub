package Fase1;

import java.util.LinkedList;

public class ABBInterpretes implements InterfaceInterpretes {
	NodoABBInterpretes root;

	
	//CONSTRUCTORA
	public ABBInterpretes() {
		root = null; 
	}

	
	// MÉTODOS AUXILIARES
	public boolean isEmpty() {
		return this.root == null;
	}

	public void imprimirArbol() {
		if (this.isEmpty())
			System.out.println("*");
		else {
			this.root.imprimirArbol();
			System.out.println();
		}
	}
	
	/**
	* Añade un intérprete a la lista
	* @param inter Intérprete a añadir
	*/
	@Override
	public void anadirInterprete(Interprete inter) {
		if (!this.isEmpty()) {
			this.root.anadirInterprete(inter);
		}
		else {
			this.root = new NodoABBInterpretes(inter);
		}
	}
	
		/**
		* Busca un intérprete en la lista y lo devuelve
		* @param nombre Nombre del intérprete a buscar
		* @return el Interprete (si está en la lista), null en caso contrario
		*/
		@Override
		public Interprete buscarInterprete(String nombre) {
			if (!this.isEmpty()) {
				return this.root.buscarInterprete(nombre);
			}
			return null;
		}
	
	/**
	* Elimina un intérprete del árbol (puede seguir estando en las listas de
	* intérpretes de las películas)
	* @param nombre Nombre del intérprete a eliminar
	* @return el Interprete (si se ha eliminado), null en caso contrario
	*/
	@Override
	public Interprete eliminarInterprete(String nombre) {
		if (!this.isEmpty()) {
		//	System.out.println("Se va a eliminar" + nombre);
			return this.root.eliminarInterprete(nombre).getInfo();
		}
		return null;
	}
	
	/**
	* Devuelve el nº de elementos del árbol.
	* @return nº de elementos del árbol
	*/
	@Override
	public int size() {
		if (!this.isEmpty()) {
			LinkedList<Interprete> lista = new LinkedList<Interprete>();
			return this.root.size(lista);
		}
		return 0;
	}
}