package Fase1;

import java.util.LinkedList;

public class NodoABBInterpretes  {
	Interprete info;
	NodoABBInterpretes left;
	NodoABBInterpretes right;

	
	//CONSTRUCTORA
	public NodoABBInterpretes(Interprete info) {
		this.info = info;
	}

	
	//METODOS AUXILIARES
	public boolean isLeaf() {
		return left == null && right == null;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	/**
	 * Muestra en pantalla el ABB
	 */
	public void imprimirArbol() {
		if (this.isLeaf())
			System.out.print("[ " + this.info + " ] ");
		else {
			System.out.print("[ " + info + " ");
			if (this.hasLeft())
				this.left.imprimirArbol();
			else
				System.out.print("* ");
			if (this.hasRight())
				this.right.imprimirArbol();
			else
				System.out.print("* ");
			System.out.print("] ");
		}
	}

	/**
	 * A�ade un int�rprete a la lista
	 * @param inter Int�rprete a a�adir
	 */
	public void anadirInterprete(Interprete inter) {
		if (inter.compareTo(this.info) < 0) {
			if (this.hasLeft()) {
				this.left.anadirInterprete(inter);
			} else {
				this.left = new NodoABBInterpretes(inter);
			}
		} else {
			if (this.hasRight()) {
				this.right.anadirInterprete(inter);
			} else {
				this.right = new NodoABBInterpretes(inter);
			}
		}
	}

	/**
	 * Busca un int�rprete en la lista y lo devuelve
	 * @param nombre Nombre del int�rprete a buscar
	 * @return el Interprete (si est� en la lista), null en caso contrario
	 */
	public Interprete buscarInterprete(String nombre) {
		Interprete inf = this.info;
		Interprete in = new Interprete(nombre);
		if (in.compareTo(inf) == 0) {
			return inf;
		} else if (in.compareTo(inf) < 0) {
			if (this.hasLeft()) {
				return this.left.buscarInterprete(nombre);
			} else
				return null;
		} else { // elem es mayor que el actual
			if (this.hasRight()) {
				return this.right.buscarInterprete(nombre);
			} else
				return null;
		}
	}

	/**
	 * Elimina un int�rprete del �rbol (puede seguir estando en las listas de int�rpretes de las pel�culas)
	 * @param nombre Nombre del int�rprete a eliminar
	 * @return el Interprete (si se ha eliminado), null en caso contrario
	 */
	public ResultadoRemoveMin eliminarInterprete(String nombre) {
		ResultadoRemoveMin auxi = new ResultadoRemoveMin();
		if(info.getNombre().equals(nombre)) {
			auxi.setInfo(this.info);
			if(!this.hasLeft()) { 
				auxi.setNodo(this.right);
				return auxi;
			}
			else if(!this.hasRight()) {
				auxi.setNodo(left);
				return auxi;
			}
			else {
				ResultadoRemoveMin min = this.right.removeMin();
				this.right =  min.getNodo();
				this.info = min.getInfo();
				auxi.setNodo(this);
				return auxi;
			}
		}
		else if(info.getNombre().compareTo(nombre)<0){
			if(this.hasLeft()) {
				auxi = left.eliminarInterprete(nombre);
				this.left = auxi.getNodo();
			}
			auxi.setNodo(this);
			return auxi;
			}
		else {
			if (this.hasRight()) {
				auxi = right.eliminarInterprete(nombre);
				this.right = auxi.getNodo();
			}
			auxi.setNodo(this);
			return auxi;
			}
	}

	/**
	 * Devuelve el n� de elementos del �rbol.
	 * @return n� de elementos del �rbol
	 */
	public int size(LinkedList<Interprete> lista) {
		lista.add(this.info);
		int resul=0;
		if (this.hasLeft()) {
			resul=this.left.size(lista);
		}
		if (this.hasRight()) {
			resul=this.right.size(lista);
		}
		resul=lista.size();
		return resul;
	}
	
	/**
	 * Elimina el minimo de el ABB
	 * @return
	 */
	public ResultadoRemoveMin removeMin() {
		 ResultadoRemoveMin resul = new ResultadoRemoveMin(null,null);
		 if(!this.hasLeft()) {//El m�nimo es el actual
		 resul.setInfo(this.info);
		 resul.setNodo(this.right);
		 }else { //El m�nimo est� en el sub�rbol izquierdo
		 ResultadoRemoveMin resulLeft = this.left.removeMin();
		 this.left = resulLeft.getNodo();
		 resul.setInfo(resulLeft.getInfo());
		 resul.setNodo(this);
		 }
		 return resul;
		}

	/**
	 * DEvuelve el numero de iterpretes en la lista
	 * @return numero de interpretes en la lista
	 */
	public int size() {
		LinkedList<Interprete> lista2 = new LinkedList<Interprete>();
		return (this.size(lista2));
	}

	/**
	 * Compara dos nodos basandose en el nombre del interprete de estos
	 * @param actual
	 * @return
	 */
	public int compareTo(NodoABBInterpretes actual) {
		return (this.info.getNombre().compareTo(actual.info.getNombre()));
	}
}