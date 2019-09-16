package com.nickdnepr.turing;

public enum MachineResult {
    UNDEFINED("Undefined"), TRUE("True"), FALSE("False");

    private String name;

    MachineResult(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
