package com.nickdnepr.turing;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class TuringMachine {

    public static final Character nullSymbol = '#';

    private String name;
    private StringBuilder inputString;
    private int readerHeadPosition;
    private int readerHeadPositionOffset;
    private State currentState;
    private LinkedHashMap<String, State> states;
    private MachineResult result;
    private LinkedHashMap<State, Set<Pair<StringBuilder, Integer>>> visitedStates;

    public TuringMachine(String name, StringBuilder inputString, State currentState, LinkedHashMap<String, State> states) {
        this.name = name;
        this.inputString = inputString;
        this.readerHeadPosition = 0;
        this.readerHeadPositionOffset = 0;
        this.currentState = currentState;
        this.states = states;
        this.result = MachineResult.UNDEFINED;
        this.visitedStates = new LinkedHashMap<>();


        states.forEach((s, state) -> {
            visitedStates.put(state, new HashSet<>());
        });
        visitedStates.get(currentState).add(new Pair<>(inputString, getReaderHeadPosition()));

//        Set<Pair<StringBuilder, Integer>> set = new HashSet<>();
//        set.add(new Pair<>(inputString, getReaderHeadPosition()));
//        visitedStates.put(currentState, set);
    }

    public void step() {

        if (result != MachineResult.UNDEFINED) {
            System.out.println("Work is over, result is " + result.toString());
            return;
        }
        Transaction transaction = currentState.getTransaction(inputString.charAt(readerHeadPosition));
        if (transaction == null) {
            result = MachineResult.FALSE;
            step();
            return;
        }
        inputString.setCharAt(readerHeadPosition, transaction.getLetterToWrite());
        State newState = states.get(transaction.getTransactionStateName());
        if (newState == null) {
            result = MachineResult.FALSE;
            step();
            return;
        }
        if (newState.isFinal()) {
            result = MachineResult.TRUE;
            step();
            return;
        }
        if (transaction.getDirectionToMove() == MovementDirection.LEFT) {
            moveHeadLeft();
        } else {
            moveHeadRight();
        }
        currentState = newState;

        if (isLoop()) {
            result = MachineResult.FALSE;
            System.out.println("Infinite Loop");
            return;
        }
        visitedStates.get(currentState).add(new Pair<>(inputString, getReaderHeadPosition()));
//        Set<Pair<StringBuilder, Integer>> stringsOnState = visitedStates.get(currentState);
//        if (stringsOnState == null) {
//            stringsOnState = new HashSet<>();
//            stringsOnState.add(new Pair<>(inputString, getReaderHeadPosition()));
//            visitedStates.put(currentState, stringsOnState);
//        } else {
//            stringsOnState.add(new Pair<>(inputString, getReaderHeadPosition()));
//        }
    }

    public void run() {
        while (result == MachineResult.UNDEFINED) {
            step();
        }
    }

    public String getName() {
        return name;
    }

    private void moveHeadLeft() {
        if (readerHeadPosition == 0) {
            inputString.insert(0, nullSymbol);
            readerHeadPositionOffset++;
        } else {
            readerHeadPosition--;
        }
    }

    private void moveHeadRight() {
        if (readerHeadPosition++ == inputString.length()) {
            inputString.append(nullSymbol);
        }
    }

    private boolean isLoop() {
        if (visitedStates.get(currentState).contains(new Pair<>(inputString, getReaderHeadPosition()))) {
            return true;
        }
        return false;
    }

    private StringBuilder removeEmptiesOnBorders(StringBuilder builder) {
        StringBuilder newBuilder = new StringBuilder(builder);
        while (nullSymbol.equals(newBuilder.charAt(0))) {
            newBuilder.deleteCharAt(0);
        }
        while (nullSymbol.equals(newBuilder.charAt(newBuilder.length() - 1))) {
            newBuilder.deleteCharAt(newBuilder.length() - 1);
        }
        return newBuilder;
    }

    public String getInputString() {
        return inputString.toString();
    }

    public int getReaderHeadPosition() {
        return readerHeadPosition - readerHeadPositionOffset;
    }

    @Override
    public String toString() {
        return "TuringMachine{" +
                "name='" + name + '\'' +
                ", inputString=" + inputString +
                ", readerHeadPosition=" + readerHeadPosition +
                ", readerHeadPositionOffset=" + readerHeadPositionOffset +
                ", currentState=" + currentState +
                ", states=" + states +
                ", result=" + result +
                '}';
    }
}
