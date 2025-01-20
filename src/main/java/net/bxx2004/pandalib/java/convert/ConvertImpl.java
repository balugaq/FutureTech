package net.bxx2004.pandalib.java.convert;

public abstract class ConvertImpl {
    private ConvertOption option;

    public ConvertImpl(ConvertOption option) {
        this.option = option;
    }

    public ConvertOption getOption() {
        return option;
    }
}
