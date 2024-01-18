
package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_JOB_USR")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserJob {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_usr_id")
    private Long id;
    @Column(name = "job_usr_sal")
    private String salary;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "job_app_date")
    private Date applicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_pos_id")
    private JobPosition jobPosition;


    @OneToMany(mappedBy = "id.userJob", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserJobApproval> approvals;



}
