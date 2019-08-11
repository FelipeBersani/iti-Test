package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransacaoTipo {

    @JsonProperty("pagamentos")
    private List<Transacao> pagamentos = null;

    @JsonProperty("recebimentos")
    private List<Transacao> recebimentos = null;


    @JsonProperty("pagamentos")
    public List<Transacao> getPagamentos() {
        return pagamentos;
    }

    @JsonProperty("pagamentos")
    public void setPagamentos(List<Transacao> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @JsonProperty("recebimentos")
    public List<Transacao> getRecebimentos() {
        return recebimentos;
    }

    @JsonProperty("recebimentos")
    public void setRecebimentos(List<Transacao> recebimentos) {
        this.recebimentos = recebimentos;
    }

    @Override
    public String toString() {
        return "TransacaoTipo{" +
                "pagamentos=" + pagamentos +
                ", recebimentos=" + recebimentos +
                '}';
    }
}