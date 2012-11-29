package org.jsystemtest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <b>Package:</b> org.jsystemtest<br/>
 * <b>Type:</b> RootSUTModule<br/>
 * <b>Description:</b>
 * <br/>
 * @author Itai Agmon
 * <br/>
 */
public class RootSUTModule {
	private SecondLevelSUTModule secondLevel;
	
	public RootSUTModule(SecondLevelSUTModule secondLevel){
		this.secondLevel= secondLevel;
		System.out.println("RootSUTModule: Constructor");
	}
	
	
	/**
	 * @return the secondLevel
	 */
	public SecondLevelSUTModule getSecondLevel() {
		return secondLevel;
	}



	@PostConstruct
	public void postConstuct(){
		System.out.println("RootSUTModule: In post construct");
	}
	
	@PreDestroy
	public void preDestroy(){
		System.out.println("RootSUTModule: In pre destroy");
	}

}
