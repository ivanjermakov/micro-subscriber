package com.github.ivanjermakov.microsubscriber.exception;

public class SubscriptionInjectionException extends RuntimeException {

	public SubscriptionInjectionException() {
	}

	public SubscriptionInjectionException(String s) {
		super(s);
	}

	public SubscriptionInjectionException(Throwable throwable) {
		super(throwable);
	}

	public SubscriptionInjectionException(String s, Throwable throwable) {
		super(s, throwable);
	}

}
