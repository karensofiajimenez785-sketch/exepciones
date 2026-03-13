package com.example.crud.repository;

import com.example.crud.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonaRepsitoryImpl implements PersonaRepository {

    private final List<Persona> data = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public List<Persona> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Persona> findByID(int id) {
        return data.stream().filter(p -> p.getId() == id).findFirst();
    }


    @Override
    public Persona save(Persona persona) {
        // Asignar id único
        int newId = idCounter.incrementAndGet();
        persona.setId(newId);
        data.add(persona);
        return persona;
    }

    @Override
    public boolean update(Persona persona) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == persona.getId()) {
                data.set(i, persona);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return data.removeIf(p -> p.getId() == id);
    }

}