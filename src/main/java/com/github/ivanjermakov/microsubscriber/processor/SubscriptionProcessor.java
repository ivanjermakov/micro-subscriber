package com.github.ivanjermakov.microsubscriber.processor;

import com.github.ivanjermakov.microsubscriber.injector.SubscriptionInjector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionProcessor implements BeanPostProcessor {

	private final SubscriptionInjector subscriptionInjector;

	public SubscriptionProcessor(SubscriptionInjector subscriptionInjector) {
		this.subscriptionInjector = subscriptionInjector;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		subscriptionInjector.inject(bean);
		return bean;
	}

}
