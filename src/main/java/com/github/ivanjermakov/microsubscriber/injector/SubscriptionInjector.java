package com.github.ivanjermakov.microsubscriber.injector;

import com.github.ivanjermakov.microsubscriber.annotation.Subscribe;
import com.github.ivanjermakov.microsubscriber.builder.SubscriptionBuilder;
import com.github.ivanjermakov.microsubscriber.exception.SubscriptionInjectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Component
public class SubscriptionInjector {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriptionInjector.class);

	public void inject(Object instance) {
		Arrays
				.stream(instance.getClass().getDeclaredFields())
				.parallel()
				.filter(field -> field.isAnnotationPresent(Subscribe.class))
				.forEach(field -> {
					LOG.debug("found annotation on: {}", instance.getClass().getSimpleName());
					Subscribe subscribeAnnotation = field.getAnnotation(Subscribe.class);
					field.setAccessible(true);

					Class<? extends SubscriptionBuilder> builderClass = subscribeAnnotation.builderClass();
					try {
						Constructor<? extends SubscriptionBuilder> constructor = builderClass.getDeclaredConstructor();
						constructor.setAccessible(true);
						SubscriptionBuilder builder = constructor.newInstance();
						field.set(instance, builder.build(subscribeAnnotation));
					} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//						TODO: handle exceptions separately
						throw new SubscriptionInjectionException("unable to inject subscription", e);
					}
				});
	}

}