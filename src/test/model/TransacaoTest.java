package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TransacaoTest {

    @InjectMocks
    @Spy
    private Transacao transacaoService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retornaCategoriaPadronizadaShouldReturnARealCategory() {
        String categoria = "Alimentação";

        String retorno = transacaoService.retornaCategoriaPadronizada(categoria);

        verify(transacaoService, times(1)).retornaCategoriaPadronizada(eq(categoria));
        assertEquals(retorno, "alimentacao");
    }

    @Test
    public void retornaCategoriaPadronizadaShouldNaoInformada() {
        String categoria = "";

        String retorno = transacaoService.retornaCategoriaPadronizada(categoria);

        verify(transacaoService, times(1)).retornaCategoriaPadronizada(eq(categoria));
        assertEquals(retorno, "naoinformada");
    }

    @Test
    public void converteValorToStringShouldBeOk() {
        String valor = "3.123,55";

        BigDecimal retorno = transacaoService.converteValorToString(valor);

        verify(transacaoService, times(1)).converteValorToString(valor);
        assertEquals(retorno, new BigDecimal("3123.55"));

    }

    @Test
    public void converteValorToStringShouldThrowAnException() {
        String valor = "valor";

        try{
            BigDecimal retorno = transacaoService.converteValorToString(valor);
        }catch (NumberFormatException ex){
            verify(transacaoService, times(1)).converteValorToString(valor);
            assertEquals(ex.getMessage(), "Erro ao converter valor!");
        }

    }

}
