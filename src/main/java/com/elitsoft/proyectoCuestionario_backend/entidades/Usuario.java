
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


/**
 *
 * @author Elitsoft83
 */
@Entity
@Table(name = "TBL_USR")
@Data
public class Usuario  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usr_id;
    private String usr_rut;
    private String usr_nom;
    private String usr_ap_pat;
    private String usr_ap_mat;
    @Column(unique = true)
    private String usr_email;
    private String usr_pass;
    private String usr_tel;
    private String usr_url_link;
    private String usr_rol;
    private String usr_ver_code;
    // Relación muchos a uno con la entidad Pais
    private Boolean usr_is_ver;
    private String usr_rec_tkn;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id") // Nombre de la columna que será clave foránea
    private Pais pais;

    @OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resultados> resultados;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Herramienta> herramientas;


}
