package com.company.model;

import com.company.domain.Painter;
import com.company.exception.InvalidInputException;

import java.util.List;

/**
 * @author sylvia
 */
public class Rectangle {
    public String drawRectangle(List<String> params, String canvasString){
        Painter painter = new Painter();
        int x1 = Integer.parseInt(params.get(0));
        int y1 = Integer.parseInt(params.get(1));
        int x2 = Integer.parseInt(params.get(2));
        int y2 = Integer.parseInt(params.get(3));

        String[][] canvas = painter.canvasToArray(canvasString);
        validateRectangleParams(x1, y1, x2, y2, canvas);
        canvas = painter.drawRectangle(x1, y1, x2, y2, canvas);
        canvasString = painter.canvasToString(canvas);
        painter.printCanvas(canvasString);

        return canvasString;
    }

    private void validateRectangleParams(int x1, int y1, int x2, int y2, String[][] canvas){
        if(x1 <= 0 || x2 > canvas[0].length - 2 || y1 <= 0 || y2 > canvas.length - 2){
            throw new InvalidInputException(String.format("Coordinate out of or on the canvas boundary: %d x %d", canvas[0].length-2, canvas.length-2));
        }
    }
}
