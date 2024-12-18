package Fase1;

public interface InterfaceInterpretes {
	
	public void anadirInterprete(Interprete inter);
	public Interprete buscarInterprete(String nombre);
	public Interprete eliminarInterprete(String nombre);
	public int size();
}