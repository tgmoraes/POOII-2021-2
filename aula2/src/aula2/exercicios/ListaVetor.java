package aula2.exercicios;

public class ListaVetor<E> implements Lista<E> {
	private E[] valores;
	private int tamanho;
	private int passo;

	public ListaVetor() {
		this.passo = 10;
		// @SuppressWarnings("unchecked")
		E[] es = (E[]) new Object[this.passo];
		this.valores = es;
		this.tamanho = 0;
	}

	public void add(E valor) {
		if (this.cheio()) {
			this.alteraVet();
		}
		this.valores[this.tamanho] = valor;
		this.tamanho++;
	}

	@Override
	public void add(E elemento, int pos) {
		if(pos<0 || pos>this.tamanho)
			throw new IllegalArgumentException(msgPosInv());
		if (this.cheio())
			this.alteraVet();
		for(int i = this.tamanho;i>pos;i--)
			this.valores[i]=this.valores[i-1];
	}

	public E remove(int index) {
		if (index < 0 || index >= this.tamanho)
			throw new IllegalArgumentException(msgPosInv());
		E elemento = this.valores[index];

		for (int i = index; i < this.tamanho; i++) {
			this.valores[i] = this.valores[i + 1];
		}
		this.tamanho--;
		return elemento;
	}
	private String msgPosInv() {
		return "Posição da lista inválida. Deve ser de de 0 até "
				+ (this.tamanho - 1);
	}
	@Override
	public boolean remove(E elemento) {
		if (elemento == null)
			return false;
		for (E item : this.valores)
			if (item.equals(elemento))
				return true;
		return false;
	}

	public int size() {
		return this.tamanho;
	}

	public E get(int index) {
		if (index >= 0 && index < this.tamanho) {
			return this.valores[index];
		}
		if (index < 0)
			return this.valores[0];
		else
			return this.valores[this.tamanho];
	}

	private boolean cheio() {
		return this.tamanho == this.valores.length;
	}

	private void alteraVet() {
		int novoTamanho = this.valores.length + this.passo;
		@SuppressWarnings("unchecked")
		E[] vetNovo = (E[]) new Object[novoTamanho];
		for (int i = 0; i < this.tamanho; i++) {
			vetNovo[i] = this.valores[i];
		}
		this.valores = vetNovo;
	}

	@Override
	public String toString() {
		String ret = "tam:" + this.tamanho + " - tam físico:" + this.valores.length + " -->[";
		for (int i = 0; i < this.tamanho; i++) {
			ret += this.valores[i] + " ";
		}
		ret += "]";
		return ret;
	}
}
