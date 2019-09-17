package com.nickdnepr.turing;

import java.util.LinkedHashMap;

public class TuringMachine {

    public static final Character nullSymbol = '#';

    private String name;
    private StringBuilder inputString;
    private int readerHeadPosition;
    private int readerHeadPositionOffset;
    private State currentState;
    private LinkedHashMap<String, State> states;
    private MachineResult result;

    public TuringMachine(String name, StringBuilder inputString, State currentState, LinkedHashMap<String, State> states) {
        this.name = name;
        this.inputString = inputString;
        this.readerHeadPosition = 0;
        this.readerHeadPositionOffset = 0;
        this.currentState = currentState;
        this.states = states;
        this.result = MachineResult.UNDEFINED;
    }

    public void step() {
        if (result != MachineResult.UNDEFINED) {
            System.out.println("Work is over, result is " + result.toString());
            return;
        }
        Transaction transaction = currentState.getTransaction(inputString.charAt(readerHeadPosition));
        if (transaction==null){
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
