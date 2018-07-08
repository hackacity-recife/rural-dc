package controllers;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import exceptions.AppException;
import models.classes.Municipio;
import models.classes.enums.UF;
import models.utils.AppUtil;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import services.MunicipioService;

public class MunicipioController extends BaseController {

    @Inject
    public MunicipioController(FormFactory formFactory) {
        super(formFactory);
    }

    public Result listarUf(String uf) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		UF euf = UF.parse(uf);
    		if(euf != null) {
    			List<Municipio> municipios = MunicipioService.findByUf(euf);
    			resultado = true;
    			resposta.put("municipios", municipios);
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
    	}catch(AppException e) {
    		e.printStackTrace();
    		resposta.put("mensagem", e.getMessage());
    	}catch(Exception e) {
    		e.printStackTrace();
    		resposta.put("mensagem", e.getMessage());
    	}
    	resposta.put("resultado", resultado);
    	if(resultado){
    		return ok(Json.toJson(resposta));
    	}else{
    		return badRequest(Json.toJson(resposta));
    	}
    }
}
            
