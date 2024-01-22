package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Level;
import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.Question;
import com.elitsoft.proyectoCuestionario_backend.repositories.QuestionRepository;
import com.elitsoft.proyectoCuestionario_backend.services.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question agregarPregunta(Question question) {
        System.out.println(question);
        return questionRepository.save(question);
    }

    @Override
    public Question actualizarPregunta(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> obtenerPreguntas() {
        return (Set<Question>) questionRepository.findAll();
    }

    @Override
    public Question actualizarPreguntaId(Long preguntaId, Question question) {
        Question questionExistente = questionRepository.findById(preguntaId).orElseThrow(
                () -> new NoSuchElementException("La pregunta con ID " + preguntaId + " no se encontro.")
        );

        questionExistente.setContent(question.getContent());
        questionExistente.setOption1(question.getOption1());
        questionExistente.setOption2(question.getOption2());
        questionExistente.setOption3(question.getOption3());
        questionExistente.setOption4(question.getOption4());
        questionExistente.setAnswer(question.getAnswer());

        return questionRepository.save(questionExistente);
    }

    @Override
    public Question obtenerPregunta(Long preguntaId) {
        return questionRepository.findById(preguntaId).orElse(null);
    }

    @Override
    public void eliminarPregunta(Long preguntaId) {
        questionRepository.deleteById(preguntaId);
    }

    @Override
    public Question listarPregunta(Long preguntaId) {
        return questionRepository.getOne(preguntaId);
    }

    @Override
    public List<Question> generarExamen(String description, Long productId) {
        Product product = new Product();
        product.setId(productId);
        System.out.printf("description" + description);
        System.out.printf("productoId" + productId);
        int totalQuestions = 10;
        int hardQuestionsCount = 0;
        int mediumQuestionsCount = 0;
        int easyQuestionsCount = 0;
        if ("Alto".equals(description)) {
            hardQuestionsCount = (int) Math.round(totalQuestions * 0.7);
            mediumQuestionsCount = (int) Math.round(totalQuestions * 0.2);
            easyQuestionsCount = totalQuestions - hardQuestionsCount - mediumQuestionsCount;
        } else if ("Medio".equals(description)) {
            mediumQuestionsCount = (int) Math.round(totalQuestions * 0.8);
            easyQuestionsCount = totalQuestions - mediumQuestionsCount;
        } else if ("Bajo".equals(description)) {
            easyQuestionsCount = totalQuestions;
        }

        List<Question> allQuestions = new ArrayList<>(questionRepository.findByLevelDescriptionAndProduct(description, product));
        List<Question> hardQuestions = getRandomQuestions(allQuestions, hardQuestionsCount);
        List<Question> mediumQuestions = getRandomQuestions(allQuestions, mediumQuestionsCount);
        List<Question> easyQuestions = getRandomQuestions(allQuestions, easyQuestionsCount);

        // Filtrar preguntas duplicadas
        Set<Question> uniqueQuestions = new HashSet<>();
        uniqueQuestions.addAll(hardQuestions);
        uniqueQuestions.addAll(mediumQuestions);
        uniqueQuestions.addAll(easyQuestions);

        // Verificar si se alcanzó el número total de preguntas
        while (uniqueQuestions.size() < totalQuestions) {
            // Buscar otra pregunta no duplicada y agregarla
            Question randomQuestion = getRandomQuestion(allQuestions);
            uniqueQuestions.add(randomQuestion);
        }

        List<Question> examQuestions = new ArrayList<>(uniqueQuestions);
        return examQuestions;
    }

    private Question getRandomQuestion(List<Question> questions) {
        Collections.shuffle(questions);
        return questions.get(0);
    }


    private List<Question> getRandomQuestions(List<Question> allQuestions, int count) {
        Collections.shuffle(allQuestions);
        return allQuestions.stream().limit(count).collect(Collectors.toList());
    }
    public List<Question> obtenerPreguntasPorProducto(Long productoId) {
        return questionRepository.findByProductId(productoId);
    }

}
