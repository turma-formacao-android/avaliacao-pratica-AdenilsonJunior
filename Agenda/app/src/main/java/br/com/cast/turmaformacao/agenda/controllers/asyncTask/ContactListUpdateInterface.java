package br.com.cast.turmaformacao.agenda.controllers.asyncTask;

import java.util.concurrent.ExecutionException;

public interface ContactListUpdateInterface {

    void updateContactList() throws ExecutionException, InterruptedException;

}
