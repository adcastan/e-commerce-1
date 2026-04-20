/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import models.Resenia;

/**
 *
 * @author Adrián
 */
public interface IReseniaService {

    public Resenia crearCliente(Resenia resenia);

    Long buscarPorId(int id);

    void actualizar(Resenia resenia);

    Long eliminar(int id);
}
