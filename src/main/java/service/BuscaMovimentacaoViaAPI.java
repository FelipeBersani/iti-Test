package service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.TransacaoTipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class BuscaMovimentacaoViaAPI {

    private static final String URL_DA_API = "https://my-json-server.typicode.com/cairano/backend-test/db";

    public static TransacaoTipo buscaViaAPI(){

        try {
            URL urlRequest = new URL(URL_DA_API);
            InputStream reader = urlRequest.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reader));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            TransacaoTipo transacaoTipo = objectMapper.readValue(stringBuilder.toString(), TransacaoTipo.class);
            transacaoTipo.getRecebimentos().forEach(transacao -> transacao.setCategoria("naoInformada"));

            return transacaoTipo;

        } catch (IOException e) {
            System.out.println("Não foi possível consumir a API!");
            return null;
        }
    }
}
