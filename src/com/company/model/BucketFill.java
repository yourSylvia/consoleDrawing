package com.company.model;

import com.company.domain.Painter;
import com.company.exception.InvalidInputException;

import java.util.List;

/**
 * @author sylvia
 */
public class BucketFill {
    public String drawBucketFill(List<String> params, String canvasString){
        Painter painter = new Painter();
        int x = Integer.parseInt(params.get(0));
        int y = Integer.parseInt(params.get(1));
        String c = params.get(2);

        String[][] canvas = painter.canvasToArray(canvasString);
        validateBucketFillParams(x, y, canvas);
        canvas = painter.fillBucket(x, y, c, canvas);
        canvasString = painter.canvasToString(canvas);
        painter.printCanvas(canvasString);

        return canvasString;
    }

    private void validateBucketFillParams(int x, int y, String[][] canvas){
        if(x <= 0 || x > canvas[0].length - 2 || y <= 0 || y > canvas.length - 2){
            throw new InvalidInputException(String.format("Coordinate out of or on the canvas boundary: %d x %d", canvas[0].length-2, canvas.length-2));
        }
    }
}
