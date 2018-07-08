package models.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.WhenCreated;
import com.avaje.ebean.annotation.WhenModified;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import models.classes.enums.AtividadeCategoria;
import models.classes.enums.AtividadeTipo;
import models.utils.DateUtil;

@Entity
@Table
public class Atividade extends Model {
	
	@Id
    @Column
    private Long id;
	
	@Enumerated
	private AtividadeTipo atividadeTipo;
	
	@Enumerated
	private AtividadeCategoria atividadeCategoria;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@JsonIgnore
	@Column
	private DateTime dataAtividade;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private Favela favela;
	
	@JsonIdentityInfo(property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
	@OneToMany
	private List<Contribuicao> contribuicoes;
	
	@JsonIgnore
	@WhenCreated
	@Column
	private DateTime dataCriacao;
	
	@JsonIgnore
	@WhenModified
	@Column
	private DateTime dataModificacao;
	
	@JsonProperty
	@Transient
	private boolean contribuindo;

	public Atividade() {}
	
	public Atividade(String titulo, String descricao, AtividadeTipo atividadeTipo, AtividadeCategoria atividadeCategoria, DateTime dataAtividade, Pessoa pessoa, Favela favela) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataAtividade = dataAtividade;
		this.pessoa = pessoa;
		this.favela = favela;
		this.atividadeTipo = atividadeTipo;
		this.atividadeCategoria = atividadeCategoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public DateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(DateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Long getId() {
		return id;
	}
	
	public Favela getFavela() {
		return favela;
	}

	public List<Contribuicao> getContribuicao() {
		return contribuicoes;
	}

	public void setContribuicao(List<Contribuicao> contribuicoes) {
		this.contribuicoes = contribuicoes;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	@JsonIgnore
	public DateTime getDataAtividade() {
		return dataAtividade;
	}

	public String getDataAtividadeFormatada() {
		return DateUtil.format(dataAtividade, DateUtil.DATE_PATTERN_BR);
	}
	
	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public AtividadeTipo getAtividadeTipo() {
		return atividadeTipo;
	}

	public void setAtividadeTipo(AtividadeTipo atividadeTipo) {
		this.atividadeTipo = atividadeTipo;
	}

	public AtividadeCategoria getAtividadeCategoria() {
		return atividadeCategoria;
	}

	public void setAtividadeCategoria(AtividadeCategoria atividadeCategoria) {
		this.atividadeCategoria = atividadeCategoria;
	}

	public boolean isContribuindo() {
		return contribuindo;
	}

	public void setContribuindo(boolean contribuindo) {
		this.contribuindo = contribuindo;
	}
}
