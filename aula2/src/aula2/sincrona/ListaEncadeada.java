package aula2.sincrona;

public class ListaEncadeada<T> {
	private int tamanho;
	private No inicio;
	private No fim;
	
	public ListaEncadeada(){
		this.tamanho =0;
		this.inicio=null;
		this.fim=null;
	}
	//adiciona no final
	public void add(T valor) {
		No nodo = new No(valor);
		if(this.inicio==null) {
			this.inicio = nodo;
			this.fim = nodo;
		}
		else {
			this.fim.prox = nodo;
			this.fim = nodo;
		}
		this.tamanho++;
	}
	//retorna elemento na Pos
	public T get(int pos) {
		int aux=0;
		No nodoAux= this.inicio;
		while(aux!=pos && nodoAux!=null) {
			aux++;
			nodoAux = nodoAux.prox;
		}
		if(aux!=pos) throw new IllegalArgumentException("posição inválida");
		return nodoAux.info;
	}
	public int getTamanho() {
		return this.tamanho;
	}
	//classe interna
	private class No  {
		T info;
		No prox;
		No(T informacao){
			this.info = informacao;
			this.prox = null;
		}
	}
}
