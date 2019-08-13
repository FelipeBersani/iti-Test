package service;

import model.TransacaoTipo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CalculaOpcaoMenuTest {

    @InjectMocks
    @Spy
    private CalculaOpcaoMenu calculaOpcaoMenu;

    private static final String URL_DA_API = "https://my-json-server.typicode.com/cairano/backend-test/db";

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void chamaOpcao1() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao1(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao1(transacaoTipo);
    }

    @Test
    public void chamaOpcao2() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao2(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao2(transacaoTipo);
    }

    @Test
    public void chamaOpcao3() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao3(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao3(transacaoTipo);
    }

    @Test
    public void chamaOpcao4() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao4(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao4(transacaoTipo);
    }

    @Test
    public void chamaOpcao5() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao5(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao5(transacaoTipo);
    }

    @Test
    public void chamaOpcao6() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao6(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao6(transacaoTipo);
    }

    @Test
    public void chamaOpcao7() {
        TransacaoTipo transacaoTipo = carregaExtrato();

        calculaOpcaoMenu.buscaOpcao7(transacaoTipo);
        verify(calculaOpcaoMenu, times(1)).buscaOpcao7(transacaoTipo);
    }


    private TransacaoTipo carregaExtrato() {
        TransacaoTipo transacaoTipo = BuscaMovimentacaoViaAPI.buscaViaAPI(URL_DA_API);
        transacaoTipo = BuscaMovimentacaoViaArquivoLog.buscaViaLog(transacaoTipo);
        return transacaoTipo;
    }
}