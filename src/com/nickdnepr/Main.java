package com.nickdnepr;

import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.TuringMachine;
import com.nickdnepr.turing.builder.TuringMachineBuilder;

public class Main {

    Boolean b;

    public static void main(String[] args) {
//        TuringMachineBuilder builder = new TuringMachineBuilder();
//        builder.addState("s1");
//        builder.addTransaction("s1", "s1", 'a', 'a', MovementDirection.RIGHT);
//        builder.addTransaction("s1", TuringMachineBuilder.FINAL_STATE_NAME, TuringMachine.nullSymbol, TuringMachine.nullSymbol, MovementDirection.RIGHT);
//        TuringMachine machine = builder.build("machine1", "aabbb");
//        machine.run();
        TuringMachineBuilder builder = new TuringMachineBuilder();
        builder.addState("s1");
        builder.addState("s2");
        builder.addTransaction("s1","s2", 'a','a', MovementDirection.RIGHT);
        builder.addTransaction("s2","s1", 'a','a', MovementDirection.LEFT);
        TuringMachine machine = builder.build("machine", "aaaa");
        machine.run();

    }
}
