package com.nickdnepr;

import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.TuringMachine;
import com.nickdnepr.turing.builder.TuringMachineBuilder;

public class Main {

    Boolean b;

    public static void main(String[] args) {
        TuringMachineBuilder builder = new TuringMachineBuilder();
        builder.addState("s1");
        builder.addTransaction("s1", TuringMachineBuilder.FINAL_STATE_NAME, 'a', 'b', MovementDirection.LEFT);
        TuringMachine machine = builder.build("machine1", "aaa");
        System.out.println(machine.toString());
        machine.step();
        System.out.println(machine.toString());
    }
}
