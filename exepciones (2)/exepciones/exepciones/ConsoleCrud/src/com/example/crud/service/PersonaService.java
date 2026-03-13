package com.example.crud.service;


import com.example.crud.model.Persona;
import com.example.crud.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

public class PersonaService {

    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public List<Persona> listar() {
        return repository.findAll();
    }

    public Optional<Persona> bscarPorId(int id) {
        return repository.findByID(id);
    }

    public Persona crear(String nombre, int edad, String email) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (edad < 0) {
        }

        return null;
    }

    public boolean actualizar(int id, String nombre, int edad, String email){
        return false;
    }

    public boolean eliminar(int id) {
        return false;
    }
}