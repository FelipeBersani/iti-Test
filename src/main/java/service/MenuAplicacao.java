package service;

import model.TransacaoTipo;
import java.util.Scanner;

public class MenuAplicacao {

    private TransacaoTipo transacaoTipo;

    public static final String MENSAGEM_INICIAL = "1- Extrato de movimentações\n" +
            "2- Gastos por categoria\n" +
            "3- Categoria com maior gasto\n" +
            "4- Mês com maior gasto\n" +
            "5- Total de dinheiro gasto\n" +
            "6- Total de dinheiro recebido\n" +
            "7- Total de dinheiro movimentado\n" +
            "9- Sair\n";

    public static final String MENSAGEM_ESCOLHA = "Escolha a opção desejada:";
    public static final String MENSAGEM_DESPEDIDA = "Você escolheu a opção de saída, obrigado e volte sempre!";
    public static final String MENSAGEM_OPCAO_ERRADA = "A opção escolhida não existe!\n";

    public void iniciaAplicacao(){
        System.out.println("Buscando dados pela API");
        this.transacaoTipo = BuscaMovimentacaoViaAPI.buscaViaAPI();

        System.out.println("Buscando dados pelo arquivo log\n");
        BuscaMovimentacaoViaArquivoLog.buscaViaLog(transacaoTipo);
        System.out.println("Dados buscados com sucesso!");

        escolhaOpcao();
    }

    public void escolhaOpcao() {
        System.out.println(MENSAGEM_INICIAL);
        System.out.println(MENSAGEM_ESCOLHA);
        int valorEscolhido = capturaEscolha();
        if (valorEscolhido > 0 && valorEscolhido <= 9) {
                switch (valorEscolhido) {
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                    case 7:
                        break;

                    case 8:
                        break;

                    case 9:
                        System.out.println(MENSAGEM_DESPEDIDA);
                        return;

                    default:
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
            capturaEscolha();
        }
        return 0;
    }

}
