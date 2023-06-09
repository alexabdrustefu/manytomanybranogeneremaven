package it.prova.manytomanybranogeneremaven.dao.genere;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import it.prova.manytomanybranogeneremaven.dao.IBaseDAO;
import it.prova.manytomanybranogeneremaven.model.Genere;

public interface GenereDAO extends IBaseDAO<Genere> {

	public Genere findByDescrizione(String descrizioneInput) throws Exception;

	// inizio esercizi:
	// mi deve stampare i generi dei brani stabiliti tra due date

	public List<Genere> generiBrano(LocalDate data1, LocalDate data2) throws Exception;

}
