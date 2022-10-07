package com.ivoair.quarkus;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import lombok.extern.slf4j.Slf4j;

@QuarkusMain
@Slf4j
public class Main {

	public static void main(String... args) {
		Quarkus.run(QuarkusPrototypeApp.class, args);
	}

	public static class QuarkusPrototypeApp implements QuarkusApplication {

		@Override
		public int run(String... args) throws Exception {
			log.info("Do startup logic here");
			Quarkus.waitForExit();
			return 0;
		}
	}

}
