package br.com.cast.turmaformacao.agenda.model.http;


import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.cast.turmaformacao.agenda.model.entities.Address;

public final class AddressService {
    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private AddressService() {
        super();
    }



    public static Address getAddressByZipCode(String zipCode) {
        Address address = null;


        try {
            java.net.URL url = new URL(URL + zipCode);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            Log.i("getAddressByCep", " Return zip code of the request : " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();

                ObjectMapper objectMapper = new ObjectMapper();
                address = objectMapper.readValue(inputStream,Address.class);
            }

        } catch (Exception e) {
            Log.e(AddressService.class.getName(),""+ e.getMessage());
        }

        return address;
    }

}
