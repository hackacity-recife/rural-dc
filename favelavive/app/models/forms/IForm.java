package models.forms;

import exceptions.AppException;

public interface IForm {
	public boolean isValido() throws AppException;
}
