package com.github.ivanjermakov.microsubscriber.builder;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface SubscriptionBuilder<T> {

	Flux<T> build(Subscribe subscribe);

}
