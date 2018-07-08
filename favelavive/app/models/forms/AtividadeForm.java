package models.forms;

import java.util.Date;

import exceptions.AppException;
import models.classes.enums.AtividadeCategoria;
import models.classes.enums.AtividadeTipo;
import play.data.validation.Constraints;

public class AtividadeForm implements IForm {

	@Constraints.Required
    public String titulo;
	
    @Constraints.Required
    public String descricao;
    
    public Date dataAtividade;
    
    @Constraints.Required
    public AtividadeTipo atividadeTipo;
    
    @Constraints.Required
    public AtividadeCategoria atividadeCategoria;
    
    @Override
	public boolean isValido() throws AppException {
		return true;
	}

}
