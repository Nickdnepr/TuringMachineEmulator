package com.nickdnepr.turing.builder;

import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.State;
import com.nickdnepr.turing.Transaction;
import com.nickdnepr.turing.TuringMachine;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TuringMachineBuilder {

    public static final String FINAL_STATE_NAME = "end";

    private String startupStateName;
    private ArrayList<String> states;
    private LinkedHashMap<String, LinkedHashMap<Character, Transaction>> transactions;

    public TuringMachineBuilder() {
        this.states = new ArrayList<>();
        this.transactions = new LinkedHashMap<>();
        this.states.add(FINAL_STATE_NAME);
        transactions.put(FINAL_STATE_NAME, new LinkedHashMap<>());
    }

    public TuringMachineBuilder(String startupStateName) {
        this();
        this.startupStateName = startupStateName;
    }

    public void addState(String stateName) {
        if (states.contains(stateName)) {
            System.out.println("Specified state ID already exists");
        } else {
            states.add(stateName);
            if (startupStateName == null) {
                startupStateName = stateName;
            }
            transactions.put(stateName, new LinkedHashMap<>());
        }
    }

    public void addTransaction(String sourceStateName, String transactionStateName, Character letterToRead, Character letterToWrite, MovementDirection directionToMove) {
        Transaction transaction = new Transaction(sourceStateName, transactionStateName, letterToRead, letterToWrite, directionToMove);
        addTransaction(transaction);
    }

    public void addTransaction(Transaction transaction) {
        validateAndAddTransaction(transaction);
    }

    private void validateAndAddTransaction(Transaction transaction) {
        if (!states.contains(transaction.getSourceStateName())) {
            System.out.println("Specified source state does not exists");
            return;
        }
        if (!states.contains(transaction.getTransactionStateName())) {
            System.out.println("Specified transaction state does not exists");
            return;
        }
        transactions.get(transaction.getSourceStateName()).put(transaction.getLetterToRead(), transaction);
    }

    public void deleteTransaction(String sourceStateName, Character letterToRead) {
        transactions.get(sourceStateName).remove(letterToRead);
    }

    public void deleteTransaction(Transaction transaction) {
        deleteTransaction(transaction.getSourceStateName(), transaction.getLetterToRead());
    }

    public void deleteState(String stateName) {
        transactions.remove(stateName);
    }

    public void deleteState(State state) {
        deleteState(state.getName());
    }


    public TuringMachine build(String machineName, String inputString) {
        LinkedHashMap<String, State> states = new LinkedHashMap<>();
        State startupState =  buildState(startupStateName);
        states.put(startupStateName, startupState);
        while (!this.states.isEmpty()) {
            states.put(this.states.get(0), buildState(this.states.get(0)));
        }
        TuringMachine machine = new TuringMachine(machineName, new StringBuilder(inputString),startupState, states);
        return machine;
    }

    private State buildState(String stateName) {
        State state = new State(stateName, transactions.get(stateName), stateName.equals("end"));
        transactions.remove(stateName);
        states.remove(stateName);
        return state;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    @Override
    public String toString() {
        return "TuringMachineBuilder{" +
                "startupStateName='" + startupStateName + '\'' +
                ", states=" + states +
                ", transactions=" + transactions +
                '}';
    }
}
