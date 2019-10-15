package com.github.ivanjermakov.microsubscriber.integration;

import com.github.ivanjermakov.microsubscriber.injector.SubscriptionInjector;
import com.github.ivanjermakov.microsubscriber.model.TestBean;
import com.github.ivanjermakov.microsubscriber.processor.SubscriptionProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		SubscriptionProcessor.class,
		SubscriptionInjector.class,
		TestBean.class
})
public class SubscriptionProcessorIntegrationTest {

	@Autowired
	private TestBean testBean;

	@Test
	public void shouldAutowireTestBean() {
		assertNotNull(testBean);
	}

}
