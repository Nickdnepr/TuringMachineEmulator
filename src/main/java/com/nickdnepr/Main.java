package com.nickdnepr;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.nickdnepr.turing.MovementDirection;
import com.nickdnepr.turing.TuringMachine;
import com.nickdnepr.turing.builder.TuringMachineBuilder;

import java.io.*;
import java.util.Scanner;

public class Main {

    Boolean b;

    public static void main(String[] args) throws IOException {

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
                    if (direction.toLowerCase().charAt(0) != 'l' && direction.toLowerCase().charAt(0) != 'r') {
                        System.out.println("Input just [L/R]");
                        continue;
                    }
                } else {
                    System.out.println("Input just single letter");
                    continue;
                }
                builder.addTransaction(sourceName, destinationName, readLetter.charAt(0), writeLetter.charAt(0), direction.toLowerCase().charAt(0) == 'l' ? MovementDirection.LEFT : MovementDirection.RIGHT);
                System.out.println("Ok");
            }
            if (command.equals("build")) {
                System.out.println("Input machine name");
                String name = scanner.nextLine();
                System.out.println("Input whe input string");
                String string = scanner.nextLine();
                machine = builder.build(name, string);
                System.out.println("Ok");
            }
            if (command.equals("step")) {
                if (machine == null) {
                    System.out.println("Build machine first");
                    continue;
                }
                machine.step();
            }
            if (command.equals("run")) {
                if (machine == null) {
                    System.out.println("Build machine first");
                    continue;
                }
                machine.run();
            }
            if (command.equals("rms")) {
                System.out.println("Input state of transaction to remove. Available states:");
                System.out.println(builder.getStates().toString());
                String sourceName = scanner.nextLine();
                if (!builder.getStates().contains(sourceName)) {
                    System.out.println("Invalid state name");
                    continue;
                }
                builder.deleteState(sourceName);
            }
            if (command.equals("rmt")) {
                System.out.println("Input source state of transaction to remove. Available states:");
                System.out.println(builder.getStates().toString());
                String sourceName = scanner.nextLine();
                if (!builder.getStates().contains(sourceName)) {
                    System.out.println("Source not found");
                    continue;
                }

                System.out.println("Input transaction letter");
                String readLetter = scanner.nextLine();
                if (readLetter.length() > 1) {
                    System.out.println("Input just single letter");
                    continue;
                }
                builder.deleteTransaction(sourceName, readLetter.charAt(0));
                System.out.println("Removed transaction from " + sourceName + " by " + readLetter);
            }
            if (command.equals("ls")) {
                System.out.println("List of states:");
                for (String name : builder.getStates()) {
                    System.out.println(name);
                }
            }
            if (command.equals("lst")) {

            }
            if (command.equals("wr")) {
                if (machine == null) {
                    System.out.println("Build machine first");
                    continue;
                }

                File file = new File(machine.getName() + ".json");
                FileWriter writer = new FileWriter(file);
                new Gson().toJson(machine, writer);
                writer.flush();
                writer.close();
            }
            if (command.equals("rd")) {
                System.out.println("Please input file name");
                String fileName = scanner.nextLine();
                File file = new File(fileName);
                if (!file.exists()) {
                    System.out.println("File does not exits");
                    continue;
                }
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new FileReader(file));
                machine = gson.fromJson(reader, TuringMachine.class);
            }
        }
    }
}
