/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import models.Libro;

/**
 *
 * @author Adrián
 */
public interface ILibroService {

    public Libro crearLibro(Libro libro);

    Libro buscarPorCorreoYContrasenia(String titulo);

    Libro buscarPorGenero(String genero);
    

    void actualizar(Libro libro);

    void eliminar(int id);
}
