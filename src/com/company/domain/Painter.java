package com.company.domain;

import com.company.exception.InvalidInputException;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author sylvia
 */
public class Painter {

    public String drawCanvas(int w, int h) {
        String[][] canvas = new String[h + 2][w + 2];

        for (int row = 0; row < canvas.length; row++) {
            for (int col = 0; col < canvas[0].length; col++) {
                if (row == 0 || row == canvas.length - 1) {
                    canvas[row][col] = "-";
                } else if (col == 0 || col == canvas[0].length - 1) {
                    canvas[row][col] = "|";
                } else {
                    canvas[row][col] = " ";
                }
            }
        }
        return canvasToString(canvas);
    }

    public String[][] drawLine(int x1, int y1, int x2, int y2, String[][] canvas) {
        if (x1 == x2 && x1 != 0 && x1 != canvas[0].length - 1) {
            for (int row = y1; row <= y2; row++) {
                canvas[row][x1] = "x";
            }
        } else if (y1 == y2 && y1 != 0 && y1 != canvas.length - 1) {
            for (int col = x1; col <= x2; col++) {
                canvas[y1][col] = "x";
            }
        }
        return canvas;
    }

    public String[][] drawRectangle(int x1, int y1, int x2, int y2, String[][] canvas) {
        Painter painter = new Painter();
        canvas = painter.drawLine(x1, y1, x2, y1, canvas);
        canvas = painter.drawLine(x1, y1, x1, y2, canvas);
        canvas = painter.drawLine(x2, y1, x2, y2, canvas);
        canvas = painter.drawLine(x1, y2, x2, y2, canvas);

        return canvas;
    }

    public String[][] fillBucket(int x, int y, String c, String[][] canvas) {
        String prevC = canvas[y][x];
        bucketFillHelper(x, y, prevC, c, canvas);

        return canvas;
    }

    public void printCanvas(String canvas) {
        System.out.println(canvas);
    }

    public String canvasToString(String[][] canvas) {
        StringBuilder builder = new StringBuilder();

        for (String[] can : canvas) {
            for (String s : can) {
                builder.append(s);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String[][] canvasToArray(String canvasString) {
        String[] lines = canvasString.split("\n");
        String[][] canvas = new String[lines.length][lines[0].length()];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length(); j++) {
                canvas[i][j] = Character.toString(lines[i].charAt(j));
            }
        }
        return canvas;
    }

    private void bucketFillHelper(int x, int y, String prevC, String newC, String[][] canvas) {
        if (x < 0 || x >= canvas[0].length || y < 0 || y >= canvas.length) {
            return;
        }
        if (!canvas[y][x].equals(prevC)) {
            return;
        }

        canvas[y][x] = newC;

        bucketFillHelper(x + 1, y, prevC, newC, canvas);
        bucketFillHelper(x - 1, y, prevC, newC, canvas);
        bucketFillHelper(x, y + 1, prevC, newC, canvas);
        bucketFillHelper(x, y - 1, prevC, newC, canvas);
    }
}
