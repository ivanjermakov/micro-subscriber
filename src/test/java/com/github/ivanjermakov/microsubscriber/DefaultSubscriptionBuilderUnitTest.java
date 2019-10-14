package com.github.ivanjermakov.microsubscriber;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import com.github.ivanjermakov.microsubscriber.injector.SubscriptionInjector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		SubscriptionInjector.class
})
public class DefaultSubscriptionBuilderUnitTest {

	@Autowired
	private SubscriptionInjector injector;

	@Test
	public void shouldInitializeWithEmptyBaseUrlAndUri() {

		class ExampleBean {
			@Subscribe(
					baseUrl = "",
					uri = "",
					responseType = Object.class
			)
			public Flux f;
		}

		ExampleBean bean = new ExampleBean();

		injector.inject(bean);

		assertNotNull(bean.f);
	}

}
