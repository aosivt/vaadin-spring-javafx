package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class DemoApplication extends Application {
	// This is a runnable Spring Boot application
	public static SpringApplication context;
	public static void main(String[] args) {

		Application.launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception{

		SpringApplication.run(DemoApplication.class, "");

		StackPane root = new StackPane();

		WebView view = new WebView();
		WebEngine engine = view.getEngine();
		engine.load("http://localhost:8080");
		root.getChildren().add(view);

		stage.setOnCloseRequest(ev -> {
			stage.close();
		});
		Scene scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.show();

	}
	@Override
	public void stop()
	{


		Platform.exit();
		System.exit(0);

//		for( ScheduledExecutorService sched : activeExecutorServices )
//		{
//			sched.shutdown();
//		}
	}

	/*
	 * Business Service Facade.
     * Annotate your business services with @Service and they
     * will be autodetected by the framework.
     */
	@Service
	public static class MyService {

		public String sayHi() {
			return "Hello Spring Initializr!";
		}


	}

	/* Vaadin UI class.
     * Specify the theme and URI path for the
     * web application.
     */
	@Theme("valo")
	@SpringUI(path = "")
	public static class VaadinUI extends UI {

		// You can easily autowire the services to you
		// Vaadin applications
		@Autowired
		MyService myService;

		@Override
		protected void init(VaadinRequest request) {

			setContent(new Label(myService.sayHi()));
		}



		@Override
		public boolean isClosing() {
			return super.isClosing();
		}
	}
}
