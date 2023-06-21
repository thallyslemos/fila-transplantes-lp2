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
}
