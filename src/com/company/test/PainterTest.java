package com.company.test;

import com.company.exception.InvalidInputException;
import com.company.model.BucketFill;
import com.company.model.Canvas;
import com.company.model.Line;
import com.company.model.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sylvia
 */
class PainterTest {

    TestUtil testUtil = new TestUtil();

    @Test
    void drawCanvas() {
        List<String> params = new ArrayList<>(Arrays.asList("20", "4"));
        Canvas canvas = new Canvas();

        String actual = canvas.drawCanvas(params);
        assertEquals(actual, testUtil.testCanvas);
    }

    @Test
    void testDrawLineWhenPointWithinEdgeExpectedLineExistInCanvas() {
        List<String> params = new ArrayList<>(Arrays.asList("1", "2", "6", "2"));
        Line line = new Line();

        String actual = line.drawLine(params, testUtil.testCanvas);
        assertEquals(actual, testUtil.testLine);
    }

    @Test
    void testDrawLineWhenInputIsSlopeExpectedException(){
        List<String> params = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        Line line = new Line();

        Exception exception = assertThrows(InvalidInputException.class, () -> {
            line.drawLine(params, testUtil.testCanvas);
        });
        String expectedMessage = "Please make sure x1 = x2 or y1 = y2 to draw a line";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testDrawLineWhenPointOnTheEdgeExpectedException() {
        List<String> params = new ArrayList<>(Arrays.asList("1", "0", "6", "0"));
        Line line = new Line();

        Exception exception = assertThrows(InvalidInputException.class, () -> {
            line.drawLine(params, testUtil.testCanvas);
        });
        String expectedMessage = "Coordinate out of or on the canvas boundary: 20 x 4";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testDrawRectangleWhenPointWithinEdgeExpectedLineExistInCanvas() {
        List<String> params = new ArrayList<>(Arrays.asList("14", "1", "18", "3"));
        Rectangle rectangle = new Rectangle();

        String actual = rectangle.drawRectangle(params, testUtil.testCanvas);
        assertEquals(actual, testUtil.testRectangle);
    }

    @Test
    void testDrawRectangleWhenPointOutOfTheEdgeExpectedException(){
        List<String> params = new ArrayList<>(Arrays.asList("14", "1", "18", "5"));
        Rectangle rectangle = new Rectangle();

        Exception exception = assertThrows(InvalidInputException.class, () -> {
            rectangle.drawRectangle(params, testUtil.testCanvas);
        });
        String expectedMessage = "Coordinate out of or on the canvas boundary: 20 x 4";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testFillBucketWhenPointInsideOfTheMainAreaExpectedAreaFilled() {
        List<String> params = new ArrayList<>(Arrays.asList("10", "3", "o"));
        BucketFill bucketFill = new BucketFill();

        String actual = bucketFill.drawBucketFill(params, testUtil.bucketFillCanvas);
        assertEquals(actual, testUtil.testFillBucket1);
    }

    @Test
    void testFillBucketWhenPointInsideOfTheLinesAndEdgeAreaExpectedAreaFilled() {
        List<String> params = new ArrayList<>(Arrays.asList("2", "4", "s"));
        BucketFill bucketFill = new BucketFill();

        String actual = bucketFill.drawBucketFill(params, testUtil.bucketFillCanvas);
        assertEquals(actual, testUtil.testFillBucket2);
    }

    @Test
    void testFillBucketWhenPointInsideOfTheRectangleExpectedRectangleFilled() {
        List<String> params = new ArrayList<>(Arrays.asList("15", "2", "r"));
        BucketFill bucketFill = new BucketFill();

        String actual = bucketFill.drawBucketFill(params, testUtil.bucketFillCanvas);
        assertEquals(actual, testUtil.testFillBucket3);
    }

    @Test
    void testFillBucketWhenPointOnDrewPointExpectedPointChanges(){
        List<String> params = new ArrayList<>(Arrays.asList("2", "2", "Q"));
        BucketFill bucketFill = new BucketFill();

        String actual = bucketFill.drawBucketFill(params, testUtil.bucketFillCanvas);
        assertEquals(actual, testUtil.testFillBucket4);
    }
}
