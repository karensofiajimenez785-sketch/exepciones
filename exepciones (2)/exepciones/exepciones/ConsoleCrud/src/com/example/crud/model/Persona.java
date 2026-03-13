package com.example.crud.model;

public class Persona {
    private int id;
    private  String nombre;
    private int edad;
    private String email;

    public Persona() {}

    public Persona(int id, String nombre, int edad, String email){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public Persona(String nombre, int edad, String email){
        this(0, nombre, edad, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "edad=" + edad +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
