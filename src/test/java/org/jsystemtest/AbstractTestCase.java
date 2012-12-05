/*
 * Organization: RSA
 * Product:      Blackbox
 * Version:      1.0.1
 * Project:		 integration-test
 */
package org.jsystemtest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <b>Package:</b> org.jsystemtest<br/>
 * <b>Type:</b> ITAbstractTestCase<br/>
 * <b>Description:</b>
 * <br/>
 * @author abraho
 * <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/integration-context.xml" })
public abstract class AbstractTestCase {

}
