package models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.Model;

import models.classes.enums.UF;

@Entity
@Table
public class Municipio extends Model {
		
	@Id
    @Column
    private int id;
	
	@Column
	private int codigo;
	
	@Column
	private String uf;
	
	@Column
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public UF getUf() {
		return UF.valueOf(uf.toUpperCase());
	}
}
