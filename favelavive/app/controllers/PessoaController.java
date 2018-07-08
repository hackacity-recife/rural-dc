package controllers;

import java.util.HashMap;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;

import exceptions.AppException;
import interceptors.PessoaInterceptor;
import models.classes.Pessoa;
import models.classes.enums.PessoaTipo;
import models.forms.CadastroForm;
import models.utils.AppUtil;
import models.utils.DebugUtil;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.With;
import services.PessoaService;

public class PessoaController extends BaseController {

    @Inject
    public PessoaController(FormFactory formFactory) {
        super(formFactory);
    }

    public Result acessar() {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		JsonNode rjson = request().body().asJson();
    		Form<CadastroForm> form = this.formFactory.form(CadastroForm.class).bind(rjson);
    		
    		if(form != null) {
    			CadastroForm cf = form.get();
    			if(cf.isValido()) {
    				Pessoa pessoa = PessoaService.findByEmail(cf.email);
    				if(pessoa == null) {
    					pessoa = new Pessoa(cf.nome, cf.email, cf.senha, cf.genero, cf.urlFoto, cf.token, cf.dataNascimento, PessoaTipo.VOLUNTARIO, null);
    					pessoa.save();
    				}
    				resultado = true;
    				resposta.put("pessoa", pessoa);
    			}
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
    
    @With({PessoaInterceptor.class})
    public Result detalhes(Long pessoaId) {
    	DebugUtil.i("DETALHES");
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		Pessoa pessoa = PessoaService.findById(pessoaId);
    		resultado = true;
			resposta.put("pessoa", pessoa);
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
            
