package com.godev.acamparbackend.repositories.custom;

import java.util.List;

import com.godev.acamparbackend.domain.Camping;
import com.godev.acamparbackend.domain.Usuario;

public interface CampingRepositoryCustom {
	
	List<Camping> buscarTodosCampingsQueryDsl();
	
	Camping buscarCampingPorNome(String name);
	
	List<Camping> buscarCampingDoUsuario(Usuario usuario);
	
	List<Camping> buscarCampingsOrdenandoPorPreco();
	
	void deletarCampingPeloNome(String nome);
	
	void atualizarValorDiariaCamping(Double valorDiaria);

}
