package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetWorking;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;
import br.com.cast.turmaformacao.agenda.model.persistence.SocialNetworkingRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.TelephoneRepository;

public class SocialNetWorkingBusinessService {
    private SocialNetWorkingBusinessService() {
        super();
    }


    public static List<SocialNetWorking> findAll() {
        List<SocialNetWorking> socialNetWorkings = SocialNetworkingRepository.getAll();

        return socialNetWorkings;
    }

    public static  void save(SocialNetWorking socialNetworking) {
        SocialNetworkingRepository.save(socialNetworking);

    }

    public static void delete(Contact selectedContact) {
        SocialNetworkingRepository.delete(selectedContact.getId());
    }
}
