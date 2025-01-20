package net.bxx2004.pandalib.java.convert;

public class ConvertOption {
    private Model model = Model.VAGUE;

    public Model getModel() {
        return model;
    }

    public ConvertOption setModel(Model model) {
        this.model = model;
        return this;
    }

    public enum Model {
        PRECISE,
        VAGUE
    }
}
