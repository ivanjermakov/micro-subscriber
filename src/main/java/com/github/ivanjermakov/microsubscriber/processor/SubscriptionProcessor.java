package com.github.ivanjermakov.microsubscriber.processor;

import com.github.ivanjermakov.microsubscriber.injector.SubscriptionInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionProcessor implements BeanPostProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriptionProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		SubscriptionInjector.inject(bean);
		return bean;
	}

}