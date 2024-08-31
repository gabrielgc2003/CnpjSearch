package example.br.cnpjsearchapi.enums;

public enum SituacaoCadastralEnum {
    ATIVA("Ativa"),
    BAIXADA("Baixada"),
    SUSPENSA("Suspensa"),
    INAPTA("Inapta"),
    NULA("Nula");

    private String situacao;
    SituacaoCadastralEnum(String situacao) {
        this.situacao = situacao;
    }
    public String getSituacao() {
        return situacao;
    }
}
