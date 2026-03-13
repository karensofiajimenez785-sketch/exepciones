package com.example.crud.controller;

import com.example.crud.model.Persona;
import com.example.crud.service.PersonaService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Controlador que maneja la interacción por consola.
 * Ofrece un menú con operaciones CRUD.
 */
public class PersonaController {

    private final PersonaService service;
    private final Scanner scanner;

    public PersonaController(PersonaService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    // Bucle principal del menú
    public void run() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Elige una opción: ");
            switch (opcion) {
                case 1 -> crearPersona();
                case 2 -> listarPersonas();
                case 3 -> verPersonaPorId();
                case 4 -> actualizarPersona();
                case 5 -> eliminarPersona();
                case 0 -> {
                    System.out.println("Saliendo... ¡Hasta luego!");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println(); // línea en blanco entre operaciones
        }
    }

    private void mostrarMenu() {
        System.out.println("===== GESTIÓN DE PERSONAS (CRUD) =====");
        System.out.println("1. Crear persona");
        System.out.println("2. Listar personas");
        System.out.println("3. Ver persona por ID");
        System.out.println("4. Actualizar persona");
        System.out.println("5. Eliminar persona");
        System.out.println("0. Salir");
    }

    private void crearPersona() {
        System.out.println("---- Crear persona ----");
        String nombre = leerTexto("Nombre: ");
        int edad = leerEntero("Edad: ");
        String email = leerTexto("Email: ");
        try {
            Persona creada = service.crear(nombre, edad, email);
            System.out.println("Persona creada: " + creada);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error de validación: " + ex.getMessage());
        }
    }

    private void listarPersonas() {
        System.out.println("---- Lista de personas ----");
        List<Persona> lista = service.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void verPersonaPorId() {
        System.out.println("---- Ver persona por ID ----");
        int id = leerEntero("ID: ");
        Optional<Persona> opt = service.bscarPorId(id);
        if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {
            System.out.println("Persona con ID " + id + " no encontrada.");
        }
    }

    private void actualizarPersona() {
        System.out.println("---- Actualizar persona ----");
        int id = leerEntero("ID de la persona a actualizar: ");
        Optional<Persona> opt = service.bscarPorId(id);
        if (opt.isEmpty()) {
            System.out.println("Persona con ID " + id + " no encontrada.");
            return;
        }
        Persona existente = opt.get();
        System.out.println("Datos actuales: " + existente);

        String nombre = leerTexto("Nuevo nombre (enter para mantener '" + existente.getNombre() + "'): ");
        if (nombre.isEmpty()) nombre = existente.getNombre();

        int edad = leerEnteroConDefault("Nueva edad (enter para mantener " + existente.getEdad() + "): ", existente.getEdad());

        String email = leerTexto("Nuevo email (enter para mantener '" + existente.getEmail() + "'): ");
        if (email.isEmpty()) email = existente.getEmail();

        boolean actualizado = service.actualizar(id, nombre, edad, email);
        if (actualizado) {
            System.out.println("Persona actualizada: " + service.bscarPorId(id).get());
        } else {
            System.out.println("No se pudo actualizar la persona.");
        }
    }

    private void eliminarPersona() {
        System.out.println("---- Eliminar persona ----");
        int id = leerEntero("ID a eliminar: ");
        boolean eliminado = service.eliminar(id);
        if (eliminado) {
            System.out.println("Persona eliminada correctamente.");
        } else {
            System.out.println("No existe persona con ID " + id + ".");
        }
    }

    // ---------- Métodos auxiliares para leer datos desde consola ----------

    private String leerTexto(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    private int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine();
                // si línea vacía, pedimos de nuevo (excepto en casos donde se quiera default)
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }

    // Permite presionar Enter para mantener valor por defecto
    private int leerEnteroConDefault(String prompt, int defaultValue) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) return defaultValue;
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }
    }
