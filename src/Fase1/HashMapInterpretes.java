package Fase1;
import java.util.HashMap;
import java.util.Map;
//IMPORTANTE, el hashCode de la clase interprete da el mismo valor que hacer nombredelInterprete.hashcode()
public class HashMapInterpretes implements InterfaceInterpretes{
	
	HashMap<Integer, Interprete> mapaInterpretes;
	

	public HashMapInterpretes() {
		super();
		this.mapaInterpretes = new HashMap<Integer, Interprete>();
	}

	@Override
	public void anadirInterprete(Interprete inter) {
		//System.out.print("se ha cargado 1 interprete");
		//AQUI NO VA NOMBRE?
		mapaInterpretes.put(inter.hashCode(),inter);
		
	}

	@Override
	public Interprete buscarInterprete(String nombre) {
		return mapaInterpretes.get(nombre.hashCode());
	}

	@Override
	public Interprete eliminarInterprete(String nombre) {
		return mapaInterpretes.remove(nombre.hashCode());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mapaInterpretes.size();
	}

}
