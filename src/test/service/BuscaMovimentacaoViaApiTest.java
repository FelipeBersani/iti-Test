package service;

import model.TransacaoTipo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class BuscaMovimentacaoViaApiTest {

    @InjectMocks
    @Spy
    private BuscaMovimentacaoViaAPI buscaMovimentacaoViaAPI;

    private static final String URL_DA_API = "https://my-json-server.typicode.com/cairano/backend-test/db";

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buscaViaAPIShouldBeOk(){

        TransacaoTipo transacaoTipo = buscaMovimentacaoViaAPI.buscaViaAPI(URL_DA_API);

        verify(buscaMovimentacaoViaAPI, times(1)).buscaViaAPI(URL_DA_API);
        assertThat(transacaoTipo.getPagamentos(), is(not(Collections.EMPTY_LIST)));
        assertThat(transacaoTipo.getRecebimentos(), is(not(Collections.EMPTY_LIST)));
    }

    @Test
    public void buscaViaAPIShouldThrowIOException(){
        String url = "http://nãoexiste.com.br";

        TransacaoTipo transacaoTipo = buscaMovimentacaoViaAPI.buscaViaAPI(url);
        verify(buscaMovimentacaoViaAPI, times(1)).buscaViaAPI(url);
        assertEquals(transacaoTipo, null);
    }
}
