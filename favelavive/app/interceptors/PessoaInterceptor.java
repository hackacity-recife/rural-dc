package interceptors;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import play.cache.CacheApi;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class PessoaInterceptor extends Action.Simple{
	
	@Inject
	CacheApi mCacheApi;
	
	@Override
	public CompletionStage<Result> call(Context ctx) {
		return delegate.call(ctx);
	}
	
}
