package models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import com.fasterxml.jackson.annotation.JsonIgnore;

import models.classes.enums.UF;

@Entity
@Table
public class Favela extends Model {
	
	@Id
    @Column
    private Long id;
	
	@Column
	private int pontuacao;
	
	@Column
	private String nome;
	
	@Enumerated
	private UF uf;
	
	@Column
	private String cidade;
	
	@JsonIgnore
	@WhenCreated
	@Column
	private DateTime dataCriacao;
	
	@JsonIgnore
	@WhenModified
	@Column
	private DateTime dataModificacao;
	
	public Favela() {}

	public Favela(String nome, UF uf, String cidade) {
		this.pontuacao = 0;
		this.nome = nome;
		this.uf = uf;
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public DateTime getDataModificacao() {
		return dataModificacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	
}
