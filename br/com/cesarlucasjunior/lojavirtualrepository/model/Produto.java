package br.com.cesarlucasjunior.lojavirtualrepository.model;

public class Produto {

	private int id;
	private String nome;
	private String descricao;
	private int categoriaId;
	
	public Produto(int id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Produto(String nome, String descricao, int categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCategoriaId() {
		return this.categoriaId;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + " NOME: " + this.getNome() + " DESCRIÇÃO: " + this.getDescricao();
	}

}
