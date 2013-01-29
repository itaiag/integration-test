package org.jsystemtest.infra.bdd;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Step {
	public String description();
}
