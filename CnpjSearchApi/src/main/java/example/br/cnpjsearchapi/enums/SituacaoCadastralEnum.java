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

    // Método para encontrar o enum pelo valor do campo 'situacao'
    public static SituacaoCadastralEnum fromSituacao(String situacao) {
        for (SituacaoCadastralEnum value : SituacaoCadastralEnum.values()) {
            if (value.getSituacao().equalsIgnoreCase(situacao)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Situacao Cadastral inválida: " + situacao);
    }
}
