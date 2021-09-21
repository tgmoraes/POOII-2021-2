package aula2.sincrona;


class No <T> {
	T info;
	No<T> prox;
	No(T informacao){
		this.info = informacao;
		this.prox = null;
	}
}
