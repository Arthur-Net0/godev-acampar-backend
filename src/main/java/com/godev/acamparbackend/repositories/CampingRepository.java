package com.godev.acamparbackend.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.godev.acamparbackend.domain.Camping;
import com.godev.acamparbackend.domain.Regra;
import com.godev.acamparbackend.domain.Usuario;
import com.godev.acamparbackend.repositories.custom.CampingRepositoryCustom;

@Repository
public interface CampingRepository extends CrudRepository<Camping, Integer>, CampingRepositoryCustom {

	@Query("SELECT distinct camping FROM Camping camping JOIN camping.campingRegras campingRegras JOIN campingRegras.id.regra regra WHERE regra IN (:regras)")
	Page<Camping> search(@Param("regras") List<Regra> regras, Pageable pageRequest);
	
	@Transactional
	Page<Camping> findByUsuario(Usuario usuario, Pageable pageRequest);
	
	//Exercicios
	//9
	List<Camping> findByAceitaCartaoTrueAndAceitaDinheiroTrue();
	
	//8
	List<Camping> findByTomadasTrueAndWifiTrueAndAreaParaFogueiraFalse();
	
	//7
	List<Camping> findByValorDiariaLessThan(Double valorDiaria);
	
	//6
//	List<Camping> findByAceitaCartaoTrueAndAceitaDinheiroFalseAndValorDiariaGraterThan(Double valorDiaria);
//	List<Camping> findByValorDiariaGreaterThanAndAceitaCartaoTrueAndAceitaDinheiroFalse(Double valorDiaria);
	
	//6
	@Query("SELECT camping from Camping camping where "
			+ " camping.aceitaCartao = true "
			+ "and camping.aceitaDinheiro = false "
			+ " and camping.valorDiaria > :valorDiaria")
	List<Camping> buscarCamping(@Param("valorDiaria") Double valorDiar);
	
	
	//5
	@Query("SELECT max(camping.valorDiaria) from Camping camping")
	Double maiorValorDiaria();
	
	//5
	@Query("select camping "
            + "from Camping camping"
            + " where camping.valorDiaria=("
            + "select max(valorDiaria) from Camping camping)")
    List<Camping> acharMaxDiaria();
	
	//4
	@Query("SELECT min(camping.valorDiaria) from Camping camping")
	Double menorValorDiaria();
	
	List<Camping> findByValorDiariaGreaterThan(Double valorDiaria);
	
//	//3
	@Query("SELECT camping from Camping camping JOIN camping.proprietario proprietario WHERE proprietario.nome = :nome")
	Camping buscarCampingNomeProprietario(@Param("nome") String nome);
	
	@Query("select camping from Camping camping where camping.proprietario.nome = :nome")
	List<Camping> acharCampingPeloNomePropietario(@Param("nome") String nome);
	
	List<Camping> findByProprietarioNome(String nome);
	
	//2
	@Transactional
	Camping findByEmail(String email);
	
	//1
	@Query("SELECT camping from Camping camping order by camping.nome desc")
	List<Camping> campingPorNomeCrescente();
	
	@Transactional
    List<Camping> findCampingByNomeOrderByNome(String nome);
	
	@Transactional
    List<Camping> findByOrderByNome();
	
	
	
	
	

}
