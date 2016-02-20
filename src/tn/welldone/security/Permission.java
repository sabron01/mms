package tn.welldone.security;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import tn.welldone.helpers.Operation;


@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Permission {
		
	public Class<?> resource();
	public Operation value();

}
