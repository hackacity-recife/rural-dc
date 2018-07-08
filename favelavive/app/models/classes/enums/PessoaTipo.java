package models.classes.enums;

public enum PessoaTipo {
	MORADOR("Morador"), VOLUNTARIO("Voluntário");

    private String label;

    PessoaTipo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
