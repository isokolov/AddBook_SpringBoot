/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.javabegin.training.javafx.springboot;

import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Main extends JavaFxSpringIntegrator{


	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);
	}

	@Override
	public void init() throws Exception {
		super.init();
	}

	public static void main(String[] args) {
		// старт приложения
		launchSpringJavaFXApp(Main.class, args);
	}


	@Override
	public void stop() throws Exception {
		System.exit(0);
	}

}
