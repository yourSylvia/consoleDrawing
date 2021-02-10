package com.company.model;

import com.company.domain.Painter;
import com.company.exception.InvalidInputException;

import java.util.List;

/**
 * @author sylvia
 */
public class Canvas {
    public String drawCanvas(List<String> params){
        int width = Integer.parseInt(params.get(0));
        int height = Integer.parseInt(params.get(1));

        if(!validateCanvas(width, height)){
            throw new InvalidInputException("You should enter positive integer for width and height.");
        }

        Painter painter = new Painter();
        String canvasString = painter.drawCanvas(width, height);
        painter.printCanvas(canvasString);
        return canvasString;
    }

    private Boolean validateCanvas(int w, int h){
        return w >= 0 && h >= 0;
    }
}
