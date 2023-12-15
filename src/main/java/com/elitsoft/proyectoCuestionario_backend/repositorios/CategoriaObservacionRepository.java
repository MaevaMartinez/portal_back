package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CategoriaObservacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Categoria_Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaObservacionRepository extends JpaRepository<CategoriaObservacion, Long> {


}