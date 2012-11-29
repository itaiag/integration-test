package org.jsystemtest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <b>Package:</b> org.jsystemtest<br/>
 * <b>Type:</b> SecondLevelSUTModule<br/>
 * <b>Description:</b>
 * <br/>
 * @author Itai Agmon
 * <br/>
 */
public class SecondLevelSUTModule {
	public SecondLevelSUTModule(){
		System.out.println("SecondLevelSUTModule: Constructor");
	}
	
	@PostConstruct
	public void postConstuct(){
		System.out.println("SecondLevelSUTModule: In post construct");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("SecondLevelSUTModule: In pre destroy");
	}
}
