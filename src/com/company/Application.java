package com.company;

import com.company.domain.CommandExecutor;
import com.company.domain.CommandParser;
import com.company.exception.InvalidInputException;
import com.company.model.Command;
import com.company.model.CommandEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author sylvia
 */
public class Application {

    private static Boolean initialized = false;
    private static String canvas = "";

    public static void main(String[] args) {
        Command command = null;
        CommandParser commandParser = new CommandParser();
        Application app = new Application();

        app.printHelp();

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                try {
                    System.out.print("Enter command: ");
                    String input = scanner.nextLine();

                    if(input.isEmpty()){
                        continue;
                    }

                    command = commandParser.preprocessCommand(app.capitalize(input));

                    if(command != null){
                        app.executeCommand(command);
                    }
                } catch (InvalidInputException e) {
                    System.err.println(e.getMessage());
                }
            }
            while (command != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeCommand(Command command) {
        CommandExecutor executor = new CommandExecutor();

        if (!initialized) {
            if (!command.type.equals(CommandEnum.CANVAS.getValue()) && !command.type
                            .equals(CommandEnum.QUIT.getValue())) {
                throw new InvalidInputException("\nPlease create a canvas first. e.g. \'C 10 10\'");
            }
        }
        canvas = executor.executeCommand(command, canvas);
        initialized = !command.type.equals(CommandEnum.QUIT.getValue());
    }

    private void printHelp(){
        try (BufferedReader br = new BufferedReader(new FileReader("help.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private String capitalize(String input){
        return input.length() > 1 ? input.substring(0, 1).toUpperCase() + input.substring(1): input.toUpperCase() ;
    }
}
