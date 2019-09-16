package com.nickdnepr.turing;

public class Transaction {

    private String sourceStateName;
    private String transactionStateName;
    private Character letterToRead;
    private Character letterToWrite;
    private MovementDirection directionToMove;

    public Transaction(String sourceStateName, String transactionStateName, Character letterToRead, Character letterToWrite, MovementDirection directionToMove) {
        this.sourceStateName = sourceStateName;
        this.transactionStateName = transactionStateName;
        this.letterToRead = letterToRead;
        this.letterToWrite = letterToWrite;
        this.directionToMove = directionToMove;
    }

    public String getSourceStateName() {
        return sourceStateName;
    }

    public String getTransactionStateName() {
        return transactionStateName;
    }

    public Character getLetterToRead() {
        return letterToRead;
    }

    public Character getLetterToWrite() {
        return letterToWrite;
    }

    public MovementDirection getDirectionToMove() {
        return directionToMove;
    }
}
