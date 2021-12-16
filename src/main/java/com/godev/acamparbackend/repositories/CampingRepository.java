package com.godev.acamparbackend.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.godev.acamparbackend.domain.Camping;
import com.godev.acamparbackend.domain.Regra;
import com.godev.acamparbackend.domain.Usuario;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Integer> {

	@Transactional
	Camping findByEmail(String email);

	@Query("SELECT distinct camping FROM Camping camping JOIN camping.campingRegras campingRegras JOIN campingRegras.id.regra regra WHERE regra IN (:regras)")
	Page<Camping> search(@Param("regras") List<Regra> regras, Pageable pageRequest);
	
	@Transactional
	Page<Camping> findByUsuario(Usuario usuario, Pageable pageRequest);

}
