public enum Orgaos {
    CORACAO("coracao"),
    RIM("rim"),
    PULMAO("pulmao"),
    FIGADO("figado");

    private String orgaos;

    Orgaos(String orgaos) {
        this.orgaos = orgaos;
    }

    public String getOrgaos() {
        return orgaos;
    }

    public static Orgaos fromString(String value) {
        for (Orgaos orgao : Orgaos.values()) {
            if (orgao.orgaos.equalsIgnoreCase(value)) {
                return orgao;
            }
        }
        throw new IllegalArgumentException("Opção inválida: " + value);
    }
}
