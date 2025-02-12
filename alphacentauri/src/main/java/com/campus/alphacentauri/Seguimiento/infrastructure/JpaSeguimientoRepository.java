package com.campus.alphacentauri.Seguimiento.infrastructure;

import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSeguimientoRepository extends JpaRepository<Seguimiento, Long>, SeguimientoRepository {

}
