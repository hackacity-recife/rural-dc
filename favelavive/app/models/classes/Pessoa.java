package models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import com.fasterxml.jackson.annotation.JsonProperty;

import models.classes.enums.PessoaTipo;
import models.utils.DateUtil;

@Entity
@Table
public class Pessoa extends Model {
		
	@Id
    @Column
    private Long id;
	
	@Enumerated
	private PessoaTipo pessoaTipo;
	
	@Column
	private int pontuacao;
	
	@Column
	private String nome;
	
	@Column
	private String email;
	
	@JsonIgnore
	@Column
	private String senha;
	
	@Column
	private String genero;
	
	@Column
	private String urlFoto;
	
	@Column
	private String token;
	
	@ManyToOne
	@Column(nullable=true)
	private Favela favela;
	
	@Column
	@JsonIgnore
	private DateTime dataNascimento;

	@JsonIgnore
	@WhenCreated
	@Column
	private DateTime dataCriacao;
	
	@JsonIgnore
	@WhenModified
	@Column
	private DateTime dataModificacao;

	public Pessoa() {}
	
	public Pessoa(String nome, String email, String senha, String genero, String urlFoto, String token,
			DateTime dataNascimento, PessoaTipo pessoaTipo, Favela favela) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.genero = genero;
		this.urlFoto = urlFoto;
		this.token = token;
		this.pessoaTipo = pessoaTipo;
		this.favela = favela;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonIgnore
	public DateTime getDataNascimento() {
		return dataNascimento;
	}
	
	@JsonProperty
	public String getDataNascimentoFormatada() {
		return DateUtil.format(dataNascimento, DateUtil.DEFAULT_VIEW_DATE_PATTERN);
	}

	public void setDataNascimento(DateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public PessoaTipo getPessoaTipo() {
		return pessoaTipo;
	}

	public void setPessoaTipo(PessoaTipo pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}

	public Favela getFavela() {
		return favela;
	}

	public void setFavela(Favela favela) {
		this.favela = favela;
	}
}
