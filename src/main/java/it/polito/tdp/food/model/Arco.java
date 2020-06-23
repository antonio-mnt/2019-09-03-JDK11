package it.polito.tdp.food.model;

public class Arco {
	
	private String prodotto1;
	private String prodotto2;
	private int peso;
	
	public Arco(String prodotto1, String prodotto2, int peso) {
		super();
		this.prodotto1 = prodotto1;
		this.prodotto2 = prodotto2;
		this.peso = peso;
	}

	public String getProdotto1() {
		return prodotto1;
	}

	public void setProdotto1(String prodotto1) {
		this.prodotto1 = prodotto1;
	}

	public String getProdotto2() {
		return prodotto2;
	}

	public void setProdotto2(String prodotto2) {
		this.prodotto2 = prodotto2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodotto1 == null) ? 0 : prodotto1.hashCode());
		result = prime * result + ((prodotto2 == null) ? 0 : prodotto2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (prodotto1 == null) {
			if (other.prodotto1 != null)
				return false;
		} else if (!prodotto1.equals(other.prodotto1))
			return false;
		if (prodotto2 == null) {
			if (other.prodotto2 != null)
				return false;
		} else if (!prodotto2.equals(other.prodotto2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arco [prodotto1=" + prodotto1 + ", prodotto2=" + prodotto2 + ", peso=" + peso + "]";
	}
	
	

}
