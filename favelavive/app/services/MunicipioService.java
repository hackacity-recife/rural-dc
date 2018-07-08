package services;

import java.util.List;

import com.avaje.ebean.Model.Find;

import models.classes.Municipio;
import models.classes.enums.UF;

public class MunicipioService {
	
	private static Find<Long, Municipio> FIND = new Find<Long, Municipio>() {};
	
	public static List<Municipio> findByUf(UF uf) {
		return FIND.where().eq("uf", uf.name()).findList();
	}
}
