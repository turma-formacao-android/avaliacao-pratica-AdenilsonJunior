package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;
import br.com.cast.turmaformacao.agenda.model.persistence.TelephoneRepository;


public class TelephoneBusinessService {

    private TelephoneBusinessService() {
        super();
    }


    public static List<Telephone> findAll() {
        List<Telephone> telephones = TelephoneRepository.getAll();

        return telephones;
    }

    public static  void save(Telephone telephone) {
        TelephoneRepository.save(telephone);

    }

    public static void delete(Contact selectContact) {
        TelephoneRepository.delete(selectContact.getId());
    }
}
