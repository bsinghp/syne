package com.synechron;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

//Reactive specifications
//Rx Java, Project Reactor(Spring webflux)

//Publisher, Subscriber
class SampleReactiveTest {

	@Test
	void testExample1() {

		List<String> langs = Arrays.asList("Java", "Python", "Ruby", "Go");

		// Pull model
		Iterator<String> itr = langs.iterator();
		while (itr.hasNext()) {
			String lang = itr.next();
			System.out.println(lang);
		}

		// Push model (Reactive style)
		Flux<String> langsFlux = Flux.fromIterable(langs).log();
		langsFlux.subscribe(lang -> System.out.println(lang));

		System.out.println();
		System.out.println();

		langsFlux.subscribe(new BaseSubscriber<String>() {
			@Override
			protected void hookOnNext(String value) {
				super.hookOnNext(value);
				System.out.println(value);
				if ("Ruby".equals(value)) {
					super.cancel();
				}
			}

		});

	}

}