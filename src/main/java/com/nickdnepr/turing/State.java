package com.nickdnepr.turing;

import java.util.LinkedHashMap;
import java.util.Objects;

public class State {

    private String name;
    private LinkedHashMap<Character, Transaction> transactions;
    private boolean isFinal;

    public State(String name, LinkedHashMap<Character, Transaction> transactions) {
        this(name, transactions, false);
    }

    public State(String name, LinkedHashMap<Character, Transaction> transactions, boolean isFinal) {
        this.name = name;
        this.transactions = transactions;
        this.isFinal = isFinal;
        if (this.transactions==null){
            this.transactions=new LinkedHashMap<>();
        }
    }

    public Transaction getTransaction(Character input) {
        return transactions.get(input);
    }

    public String getName() {
        return name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", transactions=" + transactions +
                ", isFinal=" + isFinal +
                '}';
    }
}
