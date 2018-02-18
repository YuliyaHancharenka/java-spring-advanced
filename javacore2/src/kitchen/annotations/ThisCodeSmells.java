package kitchen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PACKAGE, ElementType.TYPE, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.MODULE, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ThisCodeSmells  {
    String reviewer() default "Petya";
    int vote() default 1;
}
