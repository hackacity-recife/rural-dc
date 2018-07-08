package services;

import java.util.List;

import com.avaje.ebean.Model.Find;

import models.classes.Favela;
import models.classes.Pessoa;

public class PessoaService {
	
	private static Find<Long, Pessoa> FIND = new Find<Long, Pessoa>() {};
	
	public static Pessoa findById(Long id) {
		return FIND.where().idEq(id).findUnique();
	}
	
	public static Pessoa findByEmail(String email) {
		return FIND.where().ieq("email", email).findUnique();
	}
}
