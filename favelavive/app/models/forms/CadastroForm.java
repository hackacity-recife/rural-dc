package models.forms;

import org.joda.time.DateTime;

import exceptions.AppException;
import play.data.validation.Constraints;

public class CadastroForm implements IForm {

	@Constraints.Required
    public String nome;
	
    @Constraints.Required
    public String email;

    public String senha;
    
    public String genero;
    
    public DateTime dataNascimento;
    
    public String token;
    
    public String urlFoto;
    
    @Override
	public boolean isValido() throws AppException {
		return true;
	}

}
