package service;

import model.TransacaoTipo;
import java.util.Scanner;

public class MenuAplicacao {

    private TransacaoTipo transacaoTipo;

    public static final String MENSAGEM_INICIAL = "-------------------------------\n" +
            "OPÇÕES DO MENU: \n"+
            "1- Extrato de movimentações\n" +
            "2- Gastos por categoria\n" +
            "3- Categoria com maior gasto\n" +
            "4- Mês com maior gasto\n" +
            "5- Total de dinheiro gasto\n" +
            "6- Total de dinheiro recebido\n" +
            "7- Total de dinheiro movimentado\n" +
            "9- Sair\n";

    public static final String MENSAGEM_ESCOLHA = "Escolha a opção desejada:";
    public static final String MENSAGEM_DESPEDIDA = "Você escolheu a opção de saída, obrigado e volte sempre!";
    public static final String MENSAGEM_OPCAO_ERRADA = "A opção escolhida não está disponível!\n";
    public static final String MENSAGEM_BUSCANDO_API = "Buscando dados pela API!";
    public static final String MENSAGEM_BUSCANDO_ARQUIVO_LOG = "Buscando dados pelo arquivo log";
    public static final String MENSAGEM_SUCESSO = "Dados buscados com sucesso!\n";

    public void iniciaAplicacao(){
        System.out.println(MENSAGEM_BUSCANDO_API);
        this.transacaoTipo = BuscaMovimentacaoViaAPI.buscaViaAPI();

        System.out.println(MENSAGEM_BUSCANDO_ARQUIVO_LOG);
        BuscaMovimentacaoViaArquivoLog.buscaViaLog(transacaoTipo);
        System.out.println(MENSAGEM_SUCESSO);

        escolhaOpcao();
    }

    public void escolhaOpcao() {
        System.out.println(MENSAGEM_INICIAL);
        System.out.println(MENSAGEM_ESCOLHA);
        int valorEscolhido = capturaEscolha();
        if (valorEscolhido > 0 && valorEscolhido <= 9) {
                switch (valorEscolhido) {
                    case 1:
                        new CalculaOpcaoMenu().buscaOpcao1(this.transacaoTipo);
                        break;

                    case 2:
                        new CalculaOpcaoMenu().buscaOpcao2(this.transacaoTipo);
                        break;

                    case 3:
                        new CalculaOpcaoMenu().buscaOpcao3(this.transacaoTipo);
                        break;

                    case 4:
                        new CalculaOpcaoMenu().buscaOpcao4(this.transacaoTipo);
                        break;

                    case 5:
                        new CalculaOpcaoMenu().buscaOpcao5(this.transacaoTipo);
                        break;

                    case 6:
                        new CalculaOpcaoMenu().buscaOpcao6(this.transacaoTipo);
                        break;

                    case 7:
                        new CalculaOpcaoMenu().buscaOpcao7(this.transacaoTipo);
                        break;

                    case 9:
                        System.out.println(MENSAGEM_DESPEDIDA);
                        return;

                    default:
                        System.out.println(MENSAGEM_OPCAO_ERRADA);
                        break;

                }
                escolhaOpcao();
        } else {
            System.out.println(MENSAGEM_OPCAO_ERRADA);
            escolhaOpcao();
        }
    }

    public int capturaEscolha() {
        Scanner comandosDoUsuario = new Scanner(System.in);
        String comandoUsuario = comandosDoUsuario.next();
        try {
            return Integer.parseInt(comandoUsuario);
        } catch (NumberFormatException ex) {
            System.out.println(MENSAGEM_OPCAO_ERRADA);
            System.out.println(MENSAGEM_INICIAL);
            System.out.println(MENSAGEM_ESCOLHA);
            return 0;
        }
    }

}