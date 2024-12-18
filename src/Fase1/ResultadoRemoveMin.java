package Fase1;

//CLASE AUXILIAR PARA EL METODO REMOVEMIN DE NODOABB
	public class ResultadoRemoveMin {
		private Interprete info;
		private NodoABBInterpretes Nodo;
		public Interprete getInfo() {
			return info;
		}
		
		public void setInfo(Interprete info) {
			this.info = info;
		}
		
		public NodoABBInterpretes getNodo() {
			return Nodo;
		}
		
		public void setNodo(NodoABBInterpretes nodo) {
			Nodo = nodo;
		}
		
		public ResultadoRemoveMin(Interprete info, NodoABBInterpretes nodo) {
			super();
			this.info = info;
			Nodo = nodo;
		}
		
		public ResultadoRemoveMin() { 	
		}
}