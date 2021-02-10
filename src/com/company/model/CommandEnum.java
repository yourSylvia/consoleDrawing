package com.company.model;

/**
 * @author sylvia
 */
public enum CommandEnum {
    /**
     * C: Create new canvas
     * L: Create new line
     * R: Create new rectangle
     * B: Fill the connected area with color
     * Q: Quit
     */
    CANVAS {
        @Override
        public int getLength() {
            return 3;
        }
    },
    LINE {
        @Override
        public int getLength() {
            return 5;
        }
    },
    RECTANGLE {
        @Override
        public int getLength() {
            return 5;
        }
    },
    BUCKETFILL {
        @Override
        public int getLength() {
            return 4;
        }
    },
    QUIT {
        @Override
        public int getLength() {
            return 1;
        }
    };

    public String getValue() {
        return this.toString().substring(0, 1);
    }

    public abstract int getLength();
}
