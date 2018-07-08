package controllers;

import javax.inject.Inject;

import play.data.FormFactory;
import play.mvc.Controller;

public class BaseController extends Controller {

    protected FormFactory formFactory;

    @Inject
    public BaseController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }  
}
            
