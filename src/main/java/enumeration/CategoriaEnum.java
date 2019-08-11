package enumeration;

import java.util.Arrays;

public enum  CategoriaEnum {

    HIGIENE("higiene","higiene"),
    DIVERSAO("diversão","diversao"),
    VESTUARIO("vestuário","vestuario"),
    VIAGEM("viagem","viagem"),
    ALIMENTACAO("alimentação","alimentacao"),
    TRANSPORTE("transporte","transporte"),
    HOSPEDAGEM("hospedagem", "hospedagem"),
    NAOINFORMADA("não Informada","naoinformada");


    private String nomeCategoriaComCaracteresEspeciais;
    private String nomeCategoria;

    CategoriaEnum(String nomeCategoriaComCaracteresEspeciais, String nomeCategoria) {
        this.nomeCategoriaComCaracteresEspeciais = nomeCategoriaComCaracteresEspeciais;
        this.nomeCategoria = nomeCategoria;
    }

    public static String retornaCategoria(String categoria){
        return Arrays.stream(CategoriaEnum.values())
                .filter(a -> a.getNomeCategoria().equals(categoria))
                .findFirst()
                .get().getNomeCategoriaComCaracteresEspeciais();
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoriaComCaracteresEspeciais() {
        return nomeCategoriaComCaracteresEspeciais;
    }

    public void setNomeCategoriaComCaracteresEspeciais(String nomeCategoriaComCaracteresEspeciais) {
        this.nomeCategoriaComCaracteresEspeciais = nomeCategoriaComCaracteresEspeciais;
    }
}
