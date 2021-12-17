package com.godev.acamparbackend.repositories.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.godev.acamparbackend.domain.Camping;
import com.godev.acamparbackend.domain.QCamping;
import com.godev.acamparbackend.domain.QUsuario;
import com.godev.acamparbackend.domain.Usuario;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CampingRepositoryCustomImpl implements CampingRepositoryCustom {

	public static final QUsuario Q_USUARIO = QUsuario.usuario;
	public static final QCamping Q_CAMPING = QCamping.camping;

	private JPAQueryFactory _buildQueryFactory() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.godev.acamparbackend");
		EntityManager em = emf.createEntityManager();
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory;
	}

	@Override
	public List<Camping> buscarTodosCampingsQueryDsl() {
		List<Camping> campings = _buildQueryFactory().selectFrom(Q_CAMPING).fetch();

		return campings;
	}

	@Override
	public Camping buscarCampingPorNome(String name) {
		QCamping camping = QCamping.camping;

		Camping c = _buildQueryFactory().selectFrom(camping).where(camping.nome.eq("Teste")).fetchOne();
		return c;
	}

	@Override
	public List<Camping> buscarCampingDoUsuario(Usuario usuario) {
		QCamping qCamping = QCamping.camping;
		QUsuario qUsuario = QUsuario.usuario;

		List<Camping> campings = _buildQueryFactory().selectFrom(qCamping).innerJoin(qCamping.proprietario, qUsuario)
				.on(qUsuario.nome.eq("Marcio")).fetch();

		return campings;
	}

	@Override
	public List<Camping> buscarCampingsOrdenandoPorPreco() {
		QCamping camping = QCamping.camping;

		List<Camping> c = _buildQueryFactory().selectFrom(camping).orderBy(camping.valorDiaria.asc()).fetch();
		return c;
	}

	@Override
	public void deletarCampingPeloNome(String nome) {
		_buildQueryFactory().delete(Q_CAMPING).where(Q_CAMPING.nome.eq(nome)).execute();
	}

	@Override
	public void atualizarValorDiariaCamping(Double valorDiaria) {
		_buildQueryFactory().update(Q_CAMPING)
		  .where(Q_CAMPING.nome.eq("NomeCamping"))
		  .set(Q_CAMPING.valorDiaria, valorDiaria)
		  .execute();
		
	}

}
