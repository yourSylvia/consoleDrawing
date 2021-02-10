package com.company.domain;

import com.company.model.BucketFill;
import com.company.model.Canvas;
import com.company.model.Command;
import com.company.model.Line;
import com.company.model.Rectangle;

/**
 * @author sylvia
 */
public class CommandExecutor {
    public String executeCommand(Command command, String canvasString){
        switch (command.type){
            case "C":
                Canvas c = new Canvas();
                return c.drawCanvas(command.params);
            case "L":
                Line line = new Line();
                return line.drawLine(command.params, canvasString);
            case "R":
                Rectangle rectangle = new Rectangle();
                return rectangle.drawRectangle(command.params, canvasString);
            case "B":
                BucketFill bucketFill = new BucketFill();
                return bucketFill.drawBucketFill(command.params, canvasString);
            default:
                return "";
        }
    }
}
