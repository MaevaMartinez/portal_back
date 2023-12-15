package com.elitsoft.proyectoCuestionario_backend.servicios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CargoUsuario;
import com.elitsoft.proyectoCuestionario_backend.entidades.User;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
public interface CargoUsuarioService {

    Boolean guardarCargo(CargoUsuario cargo, String jwt, Date fechaPostulacion) throws Exception;
    List<CargoUsuario> obtenerCargosPorUsuario(User user);
    
    List<CargoUsuario> obtenerListaCargos();

    CargoUsuario obtenerCargoUsuario(String jwt) throws Exception;

    void eliminarCargoPorUsuario(Long usuarioId);

    Boolean actualizarDisponibilidadLaboral(String disponibilidadLaboral, String jwt) throws  Exception;
    
}
