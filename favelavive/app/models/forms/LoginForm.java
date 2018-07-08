package models.forms;

import exceptions.AppException;
import play.data.validation.Constraints;

public class LoginForm implements IForm {
	
    @Constraints.Required
    public String email;

    @Constraints.Required
    public String senha;

	@Override
	public boolean isValido() throws AppException {
		return true;
	}

}
