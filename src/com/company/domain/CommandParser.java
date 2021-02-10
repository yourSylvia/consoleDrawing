package com.company.domain;

import com.company.exception.InvalidInputException;
import com.company.model.Command;
import com.company.model.CommandEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sylvia
 */
public class CommandParser {

    private final String[] COMMAND_TYPE = { "C", "L", "R", "B", "Q" };

    public Command preprocessCommand(String input) {
        if (!isValidCommand(input)) {
            throw new InvalidInputException(
                            "Please input correct command type: \n" + "C: Create new canvas\n" + "L: Create new line\n"
                                            + "R: Create new rectangle\n" + "B: Fill the connected area with color\n"
                                            + "Q: Quit\n");
        }
        return generateCommand(input);
    }

    private Boolean isValidCommand(String input) {
        if (input.trim().isEmpty()) {
            return false;
        }
        String[] items = input.split("\\s+");
        return isValidType(items[0]) && isValidFormat(items);
    }

    private Boolean isValidType(String type) {
        return Arrays.asList(COMMAND_TYPE).contains(type);
    }

    private Boolean isValidFormat(String[] items) {
        String type = items[0];
        switch (type) {
            case "C":
                if (items.length != CommandEnum.CANVAS.getLength()) {
                    System.out.println("Please input correct number of variable: width w and height h.");
                    return false;
                }
                return true;
            case "L":
                if (items.length != CommandEnum.LINE.getLength()) {
                    System.out.println("Please input correct number of variable: (x1,y1) to (x2,y2).");
                    return false;
                }
                return true;
            case "R":
                if (items.length != CommandEnum.RECTANGLE.getLength()) {
                    System.out.println("Please input correct number of variable: (x1,y1) to (x2,y2).");
                    return false;
                }
                return true;
            case "B":
                if (items.length != CommandEnum.BUCKETFILL.getLength()) {
                    System.out.println("Please input correct number of variable: (x,y) with color.");
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    private Command generateCommand(String input) {
        String[] items = input.split("\\s+");
        String type = items[0];

        if (type.equals(CommandEnum.QUIT.getValue())) {
            return null;
        }

        List<String> params = Arrays.stream(items).skip(1).collect(Collectors.toList());
        return new Command(type, params);
    }
}
