package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import enumeration.MesesEnum;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transacao {
    @JsonProperty("data")
    private LocalDate data;
    @JsonProperty("descricao")
    private String descricao;
    @JsonProperty("moeda")
    private String moeda;
    @JsonProperty("valor")
    private BigDecimal valor;
    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("data")
    public LocalDate getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        data = data.replaceAll("\\s+", "");
        data = data.replace("-", "/");
        String[] identificaMes = data.split("/");
        Locale locale;

        LocalDate localDate;
        locale = MesesEnum.mesLocale(identificaMes[1].toLowerCase());

        localDate = LocalDate.parse(data.concat("/2018"), new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("dd/MMM/yyyy"))
                .toFormatter().withLocale(locale));
        this.data = localDate;
    }

    @JsonProperty("descricao")
    public String getDescricao() {
        return descricao;
    }

    @JsonProperty("descricao")
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonProperty("moeda")
    public String getMoeda() {
        return moeda;
    }

    @JsonProperty("moeda")
    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    @JsonProperty("valor")
    public BigDecimal getValor() {
        return valor;
    }

    @JsonProperty("valor")
    public void setValor(String valor) {
        valor = valor.replace(",", ".");
        valor = valor.replace(" ", "");
        this.valor = new BigDecimal(valor);
    }

    @JsonProperty("categoria")
    public String getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(String categoria) {
        if(isNullOrEmpty(categoria)){
            this.categoria = "naoinformada";
        }else{
            this.categoria = Normalizer
                    .normalize(categoria.toLowerCase(), Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
        }
    }

    public static Transacao buildPagamento(String data, String descricao, String moeda, BigDecimal valor, String categoria){
        Transacao transacao = new Transacao();
        transacao.setData(data);
        transacao.setDescricao(descricao);
        transacao.setValor(valor.toString());
        transacao.setCategoria(isNullOrEmpty(categoria) ? "NãoInformada" : categoria);
        transacao.setMoeda(moeda);

        return transacao;
    }

    public static boolean isNullOrEmpty(String categoria){
        return categoria==null||categoria=="";
    }

    @Override
    public String toString() {

        return "|Data da Transacao:"+ data +
                " | Descricao='" + descricao + '\'' +
                " | Moeda='" + moeda + '\'' +
                " | Valor=" + valor +
                " | Categoria='" + categoria;
    }
}
