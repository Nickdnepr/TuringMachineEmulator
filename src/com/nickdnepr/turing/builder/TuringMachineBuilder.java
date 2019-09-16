package com.nickdnepr.turing.builder;

import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.State;
import com.nickdnepr.turing.Transaction;
import com.nickdnepr.turing.TuringMachine;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TuringMachineBuilder {

    private String startupStateName;
    private ArrayList<String> states;
    private LinkedHashMap<String, LinkedHashMap<Character, Transaction>> transactions;

    public TuringMachineBuilder() {
        this.states = new ArrayList<>();
        this.states.add("end");
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


    //TODO complete builder
//    public TuringMachine build(String machineName, String inputString){
//
//        TuringMachine machine = new TuringMachine(machineName, inputString);
//    }
//
//    private State buildState(String stateName){
//
//    }


}
