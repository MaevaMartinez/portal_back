
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria;
import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenUserCountRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */

@Service
public class ExamenServiceimpl implements ExamenService {
    
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private ExamenUserCountRepository examenUserCountRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examen;
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public List<Examen> obtenerExamenes() {
        return (examenRepository.findAll());

    }

    @Override
    public Examen obtenerExamen(Long exam_id) {
        return examenRepository.findById(exam_id).get();
    }

    @Override
    public void eliminarExamen(Long exam_id) {
        
        Examen examen = new Examen();
        examen.setExamenId(exam_id);
        examenRepository.delete(examen);

    }
    
    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    
}
