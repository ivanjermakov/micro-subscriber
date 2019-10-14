package com.github.ivanjermakov.microsubscriber.builder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
public class DefaultSubscriptionBuilder implements SubscriptionBuilder<Object> {

	@Override
	public Flux<Object> build(Subscribe subscribe) {
		return WebClient
				.create(subscribe.baseUrl())
				.get()
				.uri(subscribe.uri())
				.retrieve()
				.bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<Object>>() {})
				.map(ServerSentEvent::data)
				.map(o -> (Object) new ObjectMapper()
						.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
						.convertValue(o, subscribe.responseType()))
				.retryBackoff(Long.MAX_VALUE, Duration.ofSeconds(1))
				.repeat()
				.log();
	}

}
