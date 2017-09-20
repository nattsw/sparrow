package io.natalietay.sparrow;

import dagger.Component;
import io.natalietay.sparrow_service.SparrowServiceModule;

/**
 * Created by Natalie on 19/9/17.
 */

// in this component, we indicate the modules that this component contains
// take time to read the @Component documentation
@Component(modules = {
        SparrowApplicationModule.class,
        SparrowServiceModule.class,
})
interface AppComponent {
    // over here we need to define every injection entry point,
    // for now, we only have MainActivity, but usually they should be presenters and views
    void inject(MainActivity mainActivity);
}
