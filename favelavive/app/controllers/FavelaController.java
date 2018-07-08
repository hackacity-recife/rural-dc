package controllers;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import exceptions.AppException;
import interceptors.PessoaInterceptor;
import models.classes.Favela;
import models.classes.enums.UF;
import models.utils.AppUtil;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.With;
import services.FavelaService;

public class FavelaController extends BaseController {

    @Inject
    public FavelaController(FormFactory formFactory) {
        super(formFactory);
    }

    @With({PessoaInterceptor.class})
    public Result listarUf(String uf) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		UF euf = UF.parse(uf);
    		if(euf != null) {
    			List<Favela> favelas = FavelaService.findByUf(euf);
    			resposta.put("favelas", favelas);
    			resultado = true;
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
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
    
    @With({PessoaInterceptor.class})
    public Result listarCidade(String uf, String cidade) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		UF euf = UF.parse(uf);
    		if(euf != null) {
	    		List<Favela> favelas = FavelaService.findByCidade(euf, cidade);
	    		resposta.put("favelas", favelas);
	    		resultado = true;
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
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
            
