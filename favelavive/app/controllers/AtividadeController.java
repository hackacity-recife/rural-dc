package controllers;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import exceptions.AppException;
import interceptors.PessoaInterceptor;
import models.classes.Atividade;
import models.classes.Contribuicao;
import models.classes.Pessoa;
import models.classes.enums.UF;
import models.forms.AtividadeForm;
import models.utils.AppUtil;
import models.utils.DebugUtil;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.With;
import services.AtividadeService;
import services.ContribuicaoService;
import services.PessoaService;

public class AtividadeController extends BaseController {

    @Inject
    public AtividadeController(FormFactory formFactory) {
        super(formFactory);
    }

    @With({PessoaInterceptor.class})
    public Result listarCidade(String uf, String cidade, Long pessoaId) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		UF euf = UF.parse(uf);
    		if(euf != null) {
    			List<Atividade> atividades = AtividadeService.findByCidade(euf, cidade);    			
    			resposta.put("atividades", atividades);
    			resultado = true;
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
    public Result listarFavela(Long favelaId) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		List<Atividade> atividade = AtividadeService.findByFavela(favelaId);
			resposta.put("atividades", atividade);
			resultado = true;
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
    public Result criar(Long pessoaId) {
    	DebugUtil.i("CRIAR");
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		String req = request().body().asText();
    		ObjectMapper mapper = new ObjectMapper();
    		JsonNode actualObj = mapper.readTree(req);
    		DebugUtil.i(actualObj.toString());
    		Form<AtividadeForm> form = this.formFactory.form(AtividadeForm.class).bind(actualObj);
    		
    		if(form != null) {
    			AtividadeForm af = form.get();
    			if(af.isValido()) {
    				Pessoa pessoa = PessoaService.findById(pessoaId);
    				Atividade atividade = new Atividade(af.titulo, af.descricao, af.atividadeTipo, af.atividadeCategoria, new DateTime(af.dataAtividade), pessoa, pessoa.getFavela());
    				atividade.save();
    				resultado = true;
    				resposta.put("atividade", atividade);
    			}
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
    		resultado = true;
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
    public Result contribuicoes(Long pessoaId) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		List<Contribuicao> contribuicoes = ContribuicaoService.findByPessoa(pessoaId);
    		resposta.put("contribuicoes", contribuicoes);
    		resultado = true;
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
    public Result contribuir(Long pessoaId, Long atividadeId) {
    	DebugUtil.i("CONTRIBUIR");
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		Pessoa pessoa = PessoaService.findById(pessoaId);
    		Atividade atividade = AtividadeService.findById(atividadeId);
    		if(pessoa != null && atividade != null && atividade.getPessoa().getId() != pessoa.getId()) {
    			Contribuicao contribuicao = ContribuicaoService.findByPessoaEAtividade(pessoaId, atividadeId);
    			if(contribuicao == null){
        			contribuicao = new Contribuicao(atividade, pessoa);
        			contribuicao.save();
    			}
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
    		resultado = true;
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
    public Result contribuindo(Long pessoaId, Long atividadeId) {
    	DebugUtil.i("CONTRIBUINDO");
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		Contribuicao contribuicao = null;
    		Pessoa pessoa = PessoaService.findById(pessoaId);
    		Atividade atividade = AtividadeService.findById(atividadeId);
    		if(pessoa != null && atividade != null && atividade.getPessoa().getId() != pessoa.getId()) {
    			contribuicao = ContribuicaoService.findByPessoaEAtividade(pessoaId, atividadeId);
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
    		resposta.put("contribuindo", contribuicao != null);
    		resultado = true;
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
    public Result cancelarContribuicao(Long pessoaId, Long atividadeId) {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		Pessoa pessoa = PessoaService.findById(pessoaId);
    		Atividade atividade = AtividadeService.findById(atividadeId);
    		if(pessoa != null && atividade != null) {
    			Contribuicao contribuicao = ContribuicaoService.findByPessoaEAtividade(pessoaId, atividadeId);
    			if(contribuicao != null){
        			contribuicao.delete();
    			}
    		}else {
    			throw new AppException(AppUtil.getMessage("ge.iFields"));
    		}
    		resultado = true;
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
            
