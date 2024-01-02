
package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Question;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Maeva Martínez
 */
public interface QuestionService {
    
    Question agregarPregunta(Question question);

    Question actualizarPregunta(Question question);

    Question actualizarPreguntaId (Long preguntaId, Question question);

    Set<Question> obtenerPreguntas();

    Question obtenerPregunta(Long preguntaId);

    List<Question> obtenerPreguntasDelExamen(Exam exam);

    void eliminarPregunta(Long preguntaId);

    Question listarPregunta(Long preguntaId);

}
