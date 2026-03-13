package com.example.crud;

import com.example.crud.controller.PersonaController;
import com.example.crud.repository.PersonaRepository;
import com.example.crud.repository.PersonaRepsitoryImpl;
import com.example.crud.service.PersonaService;

import java.util.Scanner;

/**
 * Clase Main: monta las dependencias e inicia el controlador.
 */
public class Main {
    public static void main(String[] args) {
        // Crear el repositorio (implementación en memoria)
        PersonaRepository repository = new PersonaRepsitoryImpl();
        // Crear el servicio con el repositorio
        PersonaService service = new PersonaService(repository);
        // Compartir un único Scanner para la aplicación (buena práctica)
        Scanner scanner = new Scanner(System.in);
        // Crear el controlador con servicio y scanner
        PersonaController controller = new PersonaController(service, scanner);

        // Iniciar el menú
        controller.run();

        // Cerrar scanner al finalizar
        scanner.close();
    }
}