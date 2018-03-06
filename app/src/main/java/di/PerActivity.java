package di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by dakshgargas
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
