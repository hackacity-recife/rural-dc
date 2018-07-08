package services;

import java.util.List;

import com.avaje.ebean.Model.Find;

import models.classes.Favela;
import models.classes.enums.UF;

public class FavelaService {
	
	private static Find<Long, Favela> FIND = new Find<Long, Favela>() {};
	
	public static Favela findById(Long id) {
		return FIND.where().idEq(id).findUnique();
	}
	
	public static List<Favela> findByUf(UF uf) {
		return FIND.where().eq("uf", uf).findList();
	}
	
	public static List<Favela> findByCidade(UF uf, String cidade) {
		return FIND.where().and().eq("uf", uf).ieq("cidade", cidade).endJunction().findList();
	}
	
	public static List<Favela> findByName(String nome) {
		return FIND.where().ieq("nome", nome).findList();
	}
}
