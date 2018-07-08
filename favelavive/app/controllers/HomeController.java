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

public class HomeController extends BaseController {

    @Inject
    public HomeController(FormFactory formFactory) {
        super(formFactory);
    }

    public Result main() {
    	return ok(views.html.main.render());
    }
}
            
