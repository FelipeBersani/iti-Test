package service;

import enumeration.CategoriaEnum;
import model.Transacao;
import model.TransacaoTipo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class CalculaOpcaoMenu {

    public static final String PULA_LINHA = "\n";
    public static final String CONTORNO_TABELA_OPCAO1 = String.format("%-133s", "-").replaceAll(" ", "-");


    /**
     * Exibir o log de movimentações de forma ordenada
     */
    public void buscaOpcao1(TransacaoTipo listaTransacaoTipo){
        List<Transacao> transacaoList = new ArrayList();
        transacaoList.addAll(listaTransacaoTipo.getPagamentos());
        transacaoList.addAll(listaTransacaoTipo.getRecebimentos());

        System.out.println(CONTORNO_TABELA_OPCAO1);
        transacaoList.stream()
                .sorted(Comparator.comparing(Transacao::getData).reversed())
                .forEach(object -> System.out.println(object.toString()));
        System.out.println(CONTORNO_TABELA_OPCAO1);
        System.out.println(PULA_LINHA);
    }


    /**
     * Informar o total de gastos por categoria
     */
    public void buscaOpcao2(TransacaoTipo listaTransacaoTipo){
        Map<String, BigDecimal> mapGastoPorCategoria = retornaMapCarregadoKeyCategoria(listaTransacaoTipo);

        mapGastoPorCategoria.entrySet()
                .stream()
                .sorted(Comparator.comparing(abc -> abc.getValue().abs()))
                .forEach(objeto -> System.out.println("O total de gastos da categoria "+ CategoriaEnum.retornaCategoria(objeto.getKey()).toUpperCase()+" foi de R$ "+Transacao.formataValor(objeto.getValue().abs())));

        System.out.println(PULA_LINHA);
    }


    /**
     * Informar qual categoria cliente gastou mais
     */
    public void buscaOpcao3(TransacaoTipo listaTransacaoTipo){
        Map<String, BigDecimal> mapGastoPorCategoria = retornaMapCarregadoKeyCategoria(listaTransacaoTipo);

        Map.Entry<String, BigDecimal> map = mapGastoPorCategoria.entrySet()
                .stream()
                .max(Comparator.comparing(ab -> ab.getValue().abs()))
                .get();
        System.out.println("A categoria "+CategoriaEnum.retornaCategoria(map.getKey()).toUpperCase()+" teve o maior custo R$ "+Transacao.formataValor(map.getValue().abs()));
        System.out.println(PULA_LINHA);
    }


    /**
     * Informar qual foi o mês que com mais gasto
     */
    public void buscaOpcao4(TransacaoTipo listaTransacaoTipo){
        Map<String, BigDecimal> mapGastoPorMes = retornaMapCarregadoKeyData(listaTransacaoTipo);

        Map.Entry<String, BigDecimal> map = mapGastoPorMes.entrySet()
                .stream()
                .max(Comparator.comparing(ab -> ab.getValue().abs()))
                .get();
        System.out.println("O mês "+ map.getKey().toUpperCase()+" teve o maior gasto R$ "+Transacao.formataValor(map.getValue().abs()));
        System.out.println(PULA_LINHA);
    }


    /**
     * Quanto de dinheiro o cliente gastou
     */
    public void buscaOpcao5(TransacaoTipo listaTransacaoTipo){
        BigDecimal totalGasto = BigDecimal.ZERO;
        for(Transacao transacao : listaTransacaoTipo.getPagamentos()) {
            totalGasto = totalGasto.add(transacao.getValor().abs());
        }
        System.out.println("O total de dinheiro gasto foi de R$ "+Transacao.formataValor(totalGasto));
        System.out.println(PULA_LINHA);
    }


    /**
     * Quanto de dinheiro o cliente recebeu
     */
    public void buscaOpcao6(TransacaoTipo listaTransacaoTipo){
        BigDecimal totalRecebido = BigDecimal.ZERO;
        for(Transacao transacao : listaTransacaoTipo.getRecebimentos()) {
            totalRecebido = totalRecebido.add(transacao.getValor().abs());
        }
        System.out.println("O total de dinheiro recebido foi de R$ "+Transacao.formataValor(totalRecebido));
        System.out.println(PULA_LINHA);
    }


    /**
     * Saldo total de movimentações do cliente
     */
    public void buscaOpcao7(TransacaoTipo listaTransacaoTipo){
        List<Transacao> transacaoList = new ArrayList();
        transacaoList.addAll(listaTransacaoTipo.getPagamentos());
        transacaoList.addAll(listaTransacaoTipo.getRecebimentos());
        BigDecimal totalMovimentacoes = BigDecimal.ZERO;
        int contador = 0;
        for(Transacao transacao : transacaoList) {
            totalMovimentacoes = totalMovimentacoes.add(transacao.getValor().abs());
            contador++;
        }
        System.out.println("Foram encontradas "+contador+" movimentações, no valor de: R$ "+Transacao.formataValor(totalMovimentacoes));
        System.out.println(PULA_LINHA);
    }



    public static String retornaMesComDisplayDoBrasil(LocalDate data){
        return data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }

    public static Map<String, BigDecimal> retornaMapCarregadoKeyData(TransacaoTipo listaTransacaoTipo){
        Map<String, BigDecimal> mapGastoPorMes = new HashMap<>();

        for(Transacao transacao : listaTransacaoTipo.getPagamentos()) {
            if(!mapGastoPorMes.containsKey(retornaMesComDisplayDoBrasil(transacao.getData()))){
                mapGastoPorMes.put(retornaMesComDisplayDoBrasil(transacao.getData()), transacao.getValor());
            }else{
                BigDecimal valorTotal = mapGastoPorMes.get(retornaMesComDisplayDoBrasil(transacao.getData()));
                mapGastoPorMes.put(retornaMesComDisplayDoBrasil(transacao.getData()), transacao.getValor().add(valorTotal));
            }
        }
        return mapGastoPorMes;
    }

    public static Map<String, BigDecimal> retornaMapCarregadoKeyCategoria(TransacaoTipo listaTransacaoTipo){
        Map<String, BigDecimal> mapGastoPorCategoria = new HashMap<>();

        for(Transacao transacao : listaTransacaoTipo.getPagamentos()) {
            if(!mapGastoPorCategoria.containsKey(transacao.getCategoria())){
                mapGastoPorCategoria.put(transacao.getCategoria(), transacao.getValor());
            }else{
                BigDecimal valorTotal = mapGastoPorCategoria.get(transacao.getCategoria());
                mapGastoPorCategoria.put(transacao.getCategoria(), transacao.getValor().add(valorTotal));
            }
        }
        return mapGastoPorCategoria;
    }

}