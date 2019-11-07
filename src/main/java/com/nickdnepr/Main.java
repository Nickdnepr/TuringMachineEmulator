package com.nickdnepr;

import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.TuringMachine;
import com.nickdnepr.turing.builder.TuringMachineBuilder;

import java.util.Scanner;

public class Main {

    Boolean b;

    public static void main(String[] args) {
//        TuringMachineBuilder builder = new TuringMachineBuilder();
//        builder.addState("s1");
//        builder.addState("s2");
//        builder.addTransaction("s1","s2", 'a','a', MovementDirection.RIGHT);
//        builder.addTransaction("s2","s1", 'a','a', MovementDirection.LEFT);
//        TuringMachine machine = builder.build("machine", "aaaa");
//        machine.run();
        Scanner scanner = new Scanner(System.in);
        String command;
        TuringMachineBuilder builder = new TuringMachineBuilder();
        TuringMachine machine = null;
        while (!(command = scanner.nextLine()).equals("exit")) {
            if (command.equals("ads")) {
                System.out.println("Input name of state");
                String name = scanner.nextLine();
                builder.addState(name);
                System.out.println("Ok");
                continue;
            }
            if (command.equals("adt")) {
                System.out.println("Input source state of transaction. Available states:");
                System.out.println(builder.getStates().toString());
                String sourceName = scanner.nextLine();
                if (!builder.getStates().contains(sourceName)) {
                    System.out.println("Invalid source name");
                    continue;
                }

                System.out.println("Input destination state of transaction. Available states:");
                System.out.println(builder.getStates().toString());
                String destinationName = scanner.nextLine();
                if (!builder.getStates().contains(destinationName)) {
                    System.out.println("Invalid destination name");
                    continue;
                }

                System.out.println("Input letter to read from stripe");
                String readLetter = scanner.nextLine();
                if (readLetter.length() > 1) {
                    System.out.println("Input just single letter");
                    continue;
                }

                System.out.println("Input letter to write to stripe");
                String writeLetter = scanner.nextLine();
                if (writeLetter.length() > 1) {
                    System.out.println("Input just single letter");
                    continue;
                }

                System.out.println("Input direction to move [L/R]");
                String direction = scanner.nextLine();
                if (direction.length() == 1) {
                    if (direction.toLowerCase().charAt(0) == 'l' || direction.toLowerCase().charAt(0) == 'r') {
                        System.out.println("Input just [L/R]");
                        continue;
                    }
                } else {
                    System.out.println("Input just single letter");
                    continue;
                }
                builder.addTransaction(sourceName, destinationName, readLetter.charAt(0), writeLetter.charAt(0), direction.toLowerCase().charAt(0) == 'l' ? MovementDirection.LEFT : MovementDirection.RIGHT);
            }
            if (command.equals("build")){
                System.out.println("Input machine name");
                String name = scanner.nextLine();
                System.out.println("Input whe input string");
                String string = scanner.nextLine();
                machine = builder.build(name, string);
            }
            if (command.equals("step")){
                if (machine==null){
                    System.out.println("Build machine first");
                    continue;
                }
                machine.step();
            }
            if (command.equals("run")){
                if (machine==null){
                    System.out.println("Build machine first");
                    continue;
                }
                machine.run();
            }
        }

    }
}
