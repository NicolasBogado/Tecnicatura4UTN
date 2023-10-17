package utn.tienda_libro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import utn.tienda_libro.vista.LibroForm;

import java.awt.*;

@SpringBootApplication
public class TiendaLibroApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(TiendaLibroApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);
		// ejecutamos el codigo para cargar el formulario
		EventQueue.invokeLater(() ->{ // método lambda
		// obtenemos el objeto from a través del spring
		LibroForm libroForm = contextoSpring.getBean(LibroForm.class);
		libroForm.setVisible(true);
		});
	}
}
