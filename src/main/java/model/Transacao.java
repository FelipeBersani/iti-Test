package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import enumeration.CategoriaEnum;
import enumeration.MesesEnum;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transacao {

    public static final String NAO_INFORMADA = "naoinformada";

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
        this.data = retornaDataPadronizada(data);
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

    @JsonSetter("moeda:")
    public void setMoedaComFormatoDiferente(String moeda){
        setMoeda(moeda);
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
        this.categoria = retornaCategoriaPadronizada(categoria);
    }

    public static Transacao buildTransacao(String data, String descricao, String moeda, BigDecimal valor, String categoria){
        Transacao transacao = new Transacao();
        transacao.setData(data);
        transacao.setDescricao(descricao);
        transacao.setValor(valor.toString());
        transacao.setCategoria(isNullOrEmpty(categoria) ? NAO_INFORMADA : categoria);
        transacao.setMoeda(moeda);

        return transacao;
    }

    public static boolean isNullOrEmpty(String categoria){
        return categoria==null||categoria.equals("");
    }

    protected LocalDate retornaDataPadronizada(String data){
        data = data.replaceAll("\\s+", "");
        data = data.replace("-", "/");
        String[] identificaMes = data.split("/");
        Locale locale;

        locale = MesesEnum.mesLocale(identificaMes[1].toLowerCase());

        return LocalDate.parse(data.concat("/2018"), new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("dd/MMM/yyyy"))
                .toFormatter().withLocale(locale));
    }

    protected String retornaCategoriaPadronizada(String categoria){
        if(isNullOrEmpty(categoria)){
            return NAO_INFORMADA;
        }else{
            return Normalizer
                    .normalize(categoria.toLowerCase(), Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
        }
    }

    public static BigDecimal converteValorToString(String valorString){
        BigDecimal valor = BigDecimal.ZERO;
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
            Number number = numberFormat.parse(valorString);
            valor = new BigDecimal(number.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return valor;
    }

    public static String formataValor(BigDecimal valor){
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        return numberFormat.format (valor);
    }

    protected String formataCategoria(String categoria){
        String categoriaAjustada = CategoriaEnum.retornaCategoria(categoria);
        categoria = categoriaAjustada.substring(0,1).toUpperCase();
        return categoria.concat(categoriaAjustada.substring(1));
    }


    @Override
    public String toString() {
        return String.format("| Data da Transação: %-10s | Descrição: %-25s | Moeda: %-3s | Valor: %-10s | Categoria: %-14s |",
                data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), descricao, moeda, formataValor(valor), formataCategoria(categoria));
    }
}