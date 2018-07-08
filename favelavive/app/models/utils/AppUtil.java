package models.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import play.mvc.Call;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;

public final class AppUtil {

  public static final String APP_NAME;
	public static final String APP_VERSION;
	public static final boolean APP_PROD;

    static {
        Config config = ConfigFactory.load();
        APP_NAME = config.getString("play.app.name");
        APP_VERSION = config.getString("play.app.version"); 
        APP_PROD = config.getString("play.app.environment").equalsIgnoreCase("production");
    }
    
    public static String getMessage(String key){
    	Context ctx = Context.current();
    	ctx.changeLang("pt-BR");
    	DebugUtil.i("MessageApi", ctx.lang().code()+": "+key);
    	return ctx.messages().at(key);
    }
    
    public static String absolutPath(RequestHeader request) {
        return String.format("%s%s", request.host(), request.path());
    }
    
    public static String absolutPath(Request request) {
        return String.format("%s%s", request.host(), request.path());
    }

}
