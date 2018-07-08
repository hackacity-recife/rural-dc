package models.classes.enums;

public enum AtividadeCategoria {
	PINTURA("Pintura"), CONSTRUCAO("Construção"), ESPORTES("Esportes"), ELETRICISTA("Eletricista"), OUTRO("Outro");

    private String label;

    AtividadeCategoria(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
