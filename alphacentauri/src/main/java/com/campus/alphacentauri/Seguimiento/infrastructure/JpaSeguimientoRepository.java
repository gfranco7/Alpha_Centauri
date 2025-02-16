package com.campus.alphacentauri.Seguimiento.infrastructure;

import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaSeguimientoRepository extends JpaRepository<Seguimiento, Long>, SeguimientoRepository {

}
