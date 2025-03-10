package com.narvasoft.apirest.repository;

import com.narvasoft.apirest.models.Mascotas;
import com.narvasoft.apirest.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotasRepository extends JpaRepository<Mascotas, Long> {
}
