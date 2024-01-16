package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez 
 */
@Entity
@Table(name = "tbl_qst")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qst_id")
    private Long id;
    @Column(length = 5000, name = "qst_content")
    private String content;
    @Column(name = "qst_opt1")
    private String option1;
    @Column(name = "qst_opt2")
    private String option2;
    @Column(name = "qst_opt3")
    private String option3;
    @Column(name = "qst_opt4")
    private String option4;
    @Column(name = "qst_ans")
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prd_id")
    @JsonIgnore
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id")
    private Level level;
}