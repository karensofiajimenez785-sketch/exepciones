package com.example.crud.repository;

import com.example.crud.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    List<Persona> findAll();
    Optional<Persona> findByID(int id);

    Persona save(Persona persona);

    boolean update(Persona persona);
    boolean delete(int id);
}
