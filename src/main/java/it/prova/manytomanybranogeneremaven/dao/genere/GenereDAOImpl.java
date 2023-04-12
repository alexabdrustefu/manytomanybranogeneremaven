package it.prova.manytomanybranogeneremaven.dao.genere;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.manytomanybranogeneremaven.model.Genere;

public class GenereDAOImpl implements GenereDAO {

	private EntityManager entityManager;

	@Override
	public List<Genere> list() throws Exception {
		return entityManager.createQuery("from Genere", Genere.class).getResultList();
	}

	@Override
	public Genere get(Long id) throws Exception {
		return entityManager.find(Genere.class, id);
	}

	@Override
	public void update(Genere input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Genere input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Genere input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public Genere findByDescrizione(String descrizioneInput) throws Exception {
		TypedQuery<Genere> query = entityManager
				.createQuery("select g from Genere g where g.descrizione=?1", Genere.class)
				.setParameter(1, descrizioneInput);

		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// inizio eserscizi
	// 1)
	@Override
	public List<Genere> generiBrano(LocalDate primaData, LocalDate seondaDate) throws Exception {
		if (primaData == null || seondaDate == null)
			throw new Exception("le date sono nulle valore input non valido ");
		TypedQuery<Genere> query = entityManager.createQuery(
				"select g from Genere g join g.brani where datapubblicazione between ?1 and ?2", Genere.class);
		query.setParameter(1, primaData);
		query.setParameter(2, seondaDate);
		return query.getResultList();
	}

}
