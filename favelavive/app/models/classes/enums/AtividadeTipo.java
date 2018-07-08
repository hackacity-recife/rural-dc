package models.classes.enums;

public enum AtividadeTipo {
	TAREFA("Tarefa"), EVENTO("Evento");

    private String label;

    AtividadeTipo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
