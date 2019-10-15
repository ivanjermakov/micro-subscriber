package com.github.ivanjermakov.microsubscriber.unit;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import com.github.ivanjermakov.microsubscriber.injector.SubscriptionInjector;
import com.github.ivanjermakov.microsubscriber.model.TestObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

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

	@Ignore
	@Test
	public void shouldInitializeBeanWithMapping() {
		TestObject object = new TestObject("str", 1);

		//noinspection unchecked
		Mockito
				.when(mock(WebClient.ResponseSpec.class)
						.bodyToFlux(any(ParameterizedTypeReference.class)))
				.thenReturn(Flux
						.just(object)
						.map(to -> (ServerSentEvent) ServerSentEvent.builder(to).build())
				);


		class ExampleBean {

			@Subscribe(
					baseUrl = "",
					uri = "",
					responseType = TestObject.class
			)
			public Flux<TestObject> f;

		}

		ExampleBean bean = new ExampleBean();

		injector.inject(bean);

		assertNotNull(bean.f);

		StepVerifier
				.create(bean.f)
				.expectNext(object)
				.expectComplete()
				.verify(Duration.ofMillis(100));
	}

}
