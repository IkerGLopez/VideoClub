package Fase1;

import java.util.LinkedList;

public class ABBInterpretes implements InterfaceInterpretes {
	NodoABBInterpretes root;

	
	//CONSTRUCTORA
	public ABBInterpretes() {
		root = null; 
	}

	
	// M�TODOS AUXILIARES
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
	* A�ade un int�rprete a la lista
	* @param inter Int�rprete a a�adir
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
		* Busca un int�rprete en la lista y lo devuelve
		* @param nombre Nombre del int�rprete a buscar
		* @return el Interprete (si est� en la lista), null en caso contrario
		*/
		@Override
		public Interprete buscarInterprete(String nombre) {
			if (!this.isEmpty()) {
				return this.root.buscarInterprete(nombre);
			}
			return null;
		}
	
	/**
	* Elimina un int�rprete del �rbol (puede seguir estando en las listas de
	* int�rpretes de las pel�culas)
	* @param nombre Nombre del int�rprete a eliminar
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
	* Devuelve el n� de elementos del �rbol.
	* @return n� de elementos del �rbol
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