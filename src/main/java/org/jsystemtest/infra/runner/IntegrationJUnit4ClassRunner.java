package org.jsystemtest.infra.runner;

import org.jsystemtest.infra.junit.configuration.Configurator;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test junit4 class runner. Exposes different services like the ability to add runlisteners using the
 * RunListenerManager
 * 
 * @author Itai_a
 * 
 */
public class IntegrationJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	public IntegrationJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	public void run(RunNotifier notifier) {
		//Make sure that the system is configured
		Configurator.getInstance();
		
		// Adding listener to the notifier
		if (RunListenerManager.getInstance().getListeners() != null){
			for (RunListener listener : RunListenerManager.getInstance().getListeners()) {
				notifier.addListener(listener);
			}
		}
		super.run(notifier);
	}

}
