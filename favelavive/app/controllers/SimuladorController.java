package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.inject.Inject;

import org.joda.time.DateTime;

import interceptors.PessoaInterceptor;
import models.classes.Atividade;
import models.classes.Contribuicao;
import models.classes.Favela;
import models.classes.Pessoa;
import models.classes.enums.AtividadeCategoria;
import models.classes.enums.AtividadeTipo;
import models.classes.enums.PessoaTipo;
import models.classes.enums.UF;
import models.utils.DateUtil;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.With;
import services.ContribuicaoService;

public class SimuladorController extends BaseController {
	
    @Inject
    public SimuladorController(FormFactory formFactory) {
        super(formFactory);
    }
    
    public void contribuir(Pessoa pessoa, Atividade atividade){
    	if(atividade.getPessoa().getId() != pessoa.getId()) {
	    	Contribuicao contribuicao = ContribuicaoService.findByPessoaEAtividade(pessoa.getId(), atividade.getId());
			if(contribuicao == null){
				contribuicao = new Contribuicao(atividade, pessoa);
				contribuicao.save();
			}
    	}
    }

    @With({PessoaInterceptor.class})
    public Result simular() {
    	HashMap<String, Object> resposta = new HashMap<>();
    	boolean resultado = false;
    	try {
    		Random random = new Random();
    		UF uf = UF.PE;
    		String cidade = "Recife";
    		Pessoa pessoa;
    		Favela favela;
    		Atividade atividade;
    		String nome, sobrenome, senha="6f5c4ff0824111e8adc0fa7ae01bbebc";
    		DateTime nascimento;
    		ArrayList<String> nomes = new ArrayList<>();
    		ArrayList<String> sobrenomes = new ArrayList<>();
    		ArrayList<DateTime> nascimentos = new ArrayList<>();
    		ArrayList<Pessoa> pessoas = new ArrayList<>();
    		
    		nomes.add("Enzo");
    		nomes.add("Heleno");
    		nomes.add("Andre");
    		nomes.add("Bruno");
    		nomes.add("Eduardo");
    		nomes.add("Rodrigo");
    		nomes.add("Pedro");
    		nomes.add("Joao");
    		nomes.add("Marcos");
    		nomes.add("Diego");
    		nomes.add("Ana");
    		nomes.add("Francisca");
    		nomes.add("Antonia");
    		nomes.add("Adriana");
    		nomes.add("Juliana");
    		nomes.add("Marcia");
    		nomes.add("Fernanda");
    		nomes.add("Herminia");
    		nomes.add("Maria");
    		nomes.add("Vera");
    		    		
    		sobrenomes.add("Marques");
    		sobrenomes.add("Pereira");
    		sobrenomes.add("Nascimento");
    		sobrenomes.add("Henrique");
    		sobrenomes.add("Costa");
    		sobrenomes.add("Elimar");
    		sobrenomes.add("Silva");
    		sobrenomes.add("Santos");
    		sobrenomes.add("Oliveira");
    		sobrenomes.add("Souza");
    		sobrenomes.add("Lima");
    		sobrenomes.add("Ferreira");
    		sobrenomes.add("Rodrigues");
    		sobrenomes.add("Almeida");
    		sobrenomes.add("Alves");
    		sobrenomes.add("Carvalho");
    		sobrenomes.add("Araujo");
    		sobrenomes.add("Ribeiro");
    		sobrenomes.add("Conceicao");
    		sobrenomes.add("Amorim");
    		
    		nascimentos.add(DateUtil.parse("1950-05-21"));
    		nascimentos.add(DateUtil.parse("1963-08-01"));
    		nascimentos.add(DateUtil.parse("1988-06-21"));
    		nascimentos.add(DateUtil.parse("1955-12-07"));
    		nascimentos.add(DateUtil.parse("1971-04-17"));
    		nascimentos.add(DateUtil.parse("1990-01-18"));
    		nascimentos.add(DateUtil.parse("1957-02-19"));
    		nascimentos.add(DateUtil.parse("1964-03-20"));
    		nascimentos.add(DateUtil.parse("1981-07-22"));
    		nascimentos.add(DateUtil.parse("1983-09-23"));
    		nascimentos.add(DateUtil.parse("1979-10-24"));
    		nascimentos.add(DateUtil.parse("1968-11-25"));
    		
    		favela = new Favela("Morro do Calango", uf, cidade);
    		favela.save();
    		favela.refresh();
    		
    		for(int i=0; i<100; i++) {
    			nome = nomes.get(random.nextInt(nomes.size()));
    			sobrenome = sobrenomes.get(random.nextInt(sobrenomes.size()));
    			nascimento = nascimentos.get(random.nextInt(nascimentos.size()));
    			pessoa = new Pessoa(nome+" "+sobrenome, (nome+sobrenome+"@email.com").toLowerCase(), senha, nome.charAt(nome.length()-1)=='a'?"F":"M", 
    					"", "", nascimento, PessoaTipo.MORADOR, favela);
    			pessoa.save();
    			pessoas.add(pessoa);
    		}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Lampadas fracas", "Preciso de alguem eletricista para ajeitar uma area de casa em que as lampadas estao fracas", AtividadeTipo.TAREFA, AtividadeCategoria.ELETRICISTA, null, pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 4; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Manutencao da quadra", "Anuncio um multirao para fazer a manutencao da quadra da igrejinha", AtividadeTipo.TAREFA, AtividadeCategoria.CONSTRUCAO, DateUtil.parse("2018-01-06"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 6; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		atividade = new Atividade("Campeonato de futebol", "Convido 5 pessoas para me ajudar a coordenar um campeonato de futebol na quadra da igrejinha", AtividadeTipo.EVENTO, AtividadeCategoria.ESPORTES, DateUtil.parse("2018-01-15"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 5; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Natal Solidario", "Gente, neste natal preciso de 10 pessoas para me ajudar a organizar o Natal Solidario da comunidade", AtividadeTipo.EVENTO, AtividadeCategoria.OUTRO, DateUtil.parse("2018-01-25"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 8; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Pintura", "Preciso de 2 pessoas para ajudar a pintar a casa da minha avo", AtividadeTipo.TAREFA, AtividadeCategoria.PINTURA, DateUtil.parse("2017-12-06"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 1; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Pintar casa", "A minha casa precisa de uma pintura nova", AtividadeTipo.TAREFA, AtividadeCategoria.PINTURA, DateUtil.parse("2017-12-10"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 3; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Pintura", "Precisamos pintar a mureta da pista de entrada", AtividadeTipo.TAREFA, AtividadeCategoria.PINTURA, DateUtil.parse("2017-12-20"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 2; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
			}
    		
    		pessoa = pessoas.get(random.nextInt(pessoas.size()));
    		atividade = new Atividade("Pintura da Igreja", "A fachada da Igrejinha precisa ser pintada para este natal", AtividadeTipo.TAREFA, AtividadeCategoria.PINTURA, DateUtil.parse("2017-12-15"), pessoa, favela);
    		atividade.save();
    		for (int i = 0; i < 6; i++) {
    			contribuir(pessoas.get(random.nextInt(pessoas.size())), atividade);
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
            
