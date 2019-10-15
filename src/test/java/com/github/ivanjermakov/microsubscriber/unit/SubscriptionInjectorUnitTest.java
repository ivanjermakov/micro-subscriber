package com.github.ivanjermakov.microsubscriber.unit;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import com.github.ivanjermakov.microsubscriber.builder.SubscriptionBuilder;
import com.github.ivanjermakov.microsubscriber.exception.SubscriptionInjectionException;
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
public class SubscriptionInjectorUnitTest {

	private static class FakeBuilder implements SubscriptionBuilder<Object> {
		@Override
		public Flux<Object> build(Subscribe subscribe) {
			return Flux.just(new Object());
		}
	}

	@Autowired
	private SubscriptionInjector injector;

	@Test
	public void shouldAutowireInjector() {
		assertNotNull(injector);
	}

	@Test
	public void shouldInitializePublicField() {

		class ExampleBean {

			@Subscribe(
					baseUrl = "",
					uri = "",
					responseType = Object.class,
					builderClass = FakeBuilder.class
			)
			public Flux f;
		}

		ExampleBean bean = new ExampleBean();

		injector.inject(bean);

		assertNotNull(bean.f);
	}

	@Test
	public void shouldInitializePrivateField() {
		class ExampleBean {
			@Subscribe(
					baseUrl = "",
					uri = "",
					responseType = Object.class,
					builderClass = FakeBuilder.class
			)
			private Flux f;
		}

		ExampleBean bean = new ExampleBean();

		injector.inject(bean);

		assertNotNull(bean.f);
	}

	@Test(expected = SubscriptionInjectionException.class)
	public void shouldThrowException_WithBuilderClassNotAssignable() {
		class ExampleBean {
			@Subscribe(
					baseUrl = "",
					uri = "",
					responseType = Object.class,
					builderClass = SubscriptionBuilder.class
			)
			private Flux f;
		}

		ExampleBean bean = new ExampleBean();

		injector.inject(bean);

		assertNotNull(bean.f);
	}

}
