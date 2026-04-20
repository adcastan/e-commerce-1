/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import models.Cliente;

/**
 *
 * @author Adrián
 */
public interface IClienteService {

    public Cliente crearCliente(Cliente cliente);

    Long buscarPorId(int id);

    void actualizar(Cliente cliente);

    Long eliminar(int id);
    
    }

