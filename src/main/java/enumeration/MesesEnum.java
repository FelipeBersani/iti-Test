package enumeration;

import java.util.Arrays;
import java.util.Locale;

public enum MesesEnum {

    JANEIRO("jan", "jan"),
    FEVEREIRO("feb", "fev"),
    MARCO("mar", "mar"),
    ABRIL("apr", "abr"),
    MAIO("may", "mai"),
    JUNHO("jun", "jun"),
    JULHO("jul", "jul"),
    AGOSTO("ago", "ago"),
    SETEMBRO("sep", "set"),
    OUTUBRO("oct", "out"),
    NOVEMBRO("nov", "nov"),
    DEZEMBRO("dec", "dez");

    private String nomeMesAbreviadoUS;
    private String nomeMesAbreviadoPtBR;

    MesesEnum(String nomeMesAbreviadoUS, String nomeMesAbreviadoPtBR) {
        this.nomeMesAbreviadoUS = nomeMesAbreviadoUS;
        this.nomeMesAbreviadoPtBR = nomeMesAbreviadoPtBR;
    }

    public static Locale mesLocale(String mes){
        long countMes = Arrays.stream(MesesEnum.values()).filter(a -> a.getNomeMesAbreviadoUS().equals(mes)).count();
        if(countMes>0){
            return Locale.US;
        }
        return Locale.getDefault();
    }


    public String getNomeMesAbreviadoUS() {
        return nomeMesAbreviadoUS;
    }

    public void setNomeMesAbreviadoUS(String nomeMesAbreviadoUS) {
        this.nomeMesAbreviadoUS = nomeMesAbreviadoUS;
    }

    public String getNomeMesAbreviadoPtBR() {
        return nomeMesAbreviadoPtBR;
    }

    public void setNomeMesAbreviadoPtBR(String nomeMesAbreviadoPtBR) {
        this.nomeMesAbreviadoPtBR = nomeMesAbreviadoPtBR;
    }
}
