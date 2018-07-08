package services;

import java.util.List;

import com.avaje.ebean.Model.Find;

import models.classes.Atividade;
import models.classes.enums.UF;

public class AtividadeService {
	
	private static Find<Long, Atividade> FIND = new Find<Long, Atividade>() {};
	
	public static Atividade findById(Long id) {
		return FIND.where().idEq(id).findUnique();
	}
	
	public static List<Atividade> findByFavela(Long favelaId) {
		return FIND.where().eq("favela.id", favelaId).findList();
	}
	
	public static List<Atividade> findByCidade(UF uf, String cidade) {
		return FIND.where().and().eq("favela.uf", uf).eq("favela.cidade", cidade).endJunction().findList();
	}
	
	public static List<Atividade> findByPessoa(Long pessoaId) {
		return FIND.where().eq("pessoa", pessoaId).findList();
	}
}
