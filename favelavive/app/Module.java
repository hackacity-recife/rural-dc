import java.time.Clock;

import com.google.inject.AbstractModule;

import models.injects.DateFormattersProvider;
import play.data.format.Formatters;

public class Module extends AbstractModule {

    @Override
    public void configure() {
        // region Singletons.

        // endregion

        // region Providers.
        bind(Formatters.class).toProvider(DateFormattersProvider.class);
        // endregion

        // region Instance.
        bind(Clock.class).toInstance(Clock.systemDefaultZone());
        // endregion
    }
}
