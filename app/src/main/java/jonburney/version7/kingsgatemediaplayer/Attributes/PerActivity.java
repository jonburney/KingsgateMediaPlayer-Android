package jonburney.version7.kingsgatemediaplayer.Attributes;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by jburney on 16/02/2016.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
