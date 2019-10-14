package com.github.ivanjermakov.microsubscriber.annotation;

import com.github.ivanjermakov.microsubscriber.builder.DefaultSubscriptionBuilder;
import com.github.ivanjermakov.microsubscriber.builder.SubscriptionBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Subscribe {

	String baseUrl();

	String uri();

	Class<?> responseType();

	Class<? extends SubscriptionBuilder> builderClass() default DefaultSubscriptionBuilder.class;

}
