package service;

import model.Transacao;
import model.TransacaoTipo;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BuscaMovimentacaoViaArquivoLog {

    public static TransacaoTipo buscaViaLog(TransacaoTipo transacaoTipo) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./file.log")));
            String linhaMovimentacao = "";
            int i = 0;

            while ((linhaMovimentacao = bufferedReader.readLine())!=null) {
                if (i!=0) {
                    String[] transacaoDetalhe = linhaMovimentacao.split("\\s{2,}+");
                    BigDecimal valor = Transacao.converteValorToString(transacaoDetalhe[2]);

                    if (valor.longValue() > 0) {
                        Transacao transacao = Transacao.buildTransacao(transacaoDetalhe[0], transacaoDetalhe[1], "R$", valor, transacaoDetalhe.length!=4 ? "NãoInformada":transacaoDetalhe[3]);
                        transacaoTipo.getRecebimentos().add(transacao);
                    } else {
                        Transacao transacao = Transacao.buildTransacao(transacaoDetalhe[0], transacaoDetalhe[1], "R$", valor, transacaoDetalhe.length!=4 ? "NãoInformada":transacaoDetalhe[3]);
                        transacaoTipo.getPagamentos().add(transacao);
                    }
                } else {
                    i++;
                }
            }
            return transacaoTipo;

        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado!");
            return null;
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo!");
            return null;
        }
    }
}