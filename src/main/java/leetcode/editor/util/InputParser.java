package leetcode.editor.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputParser {

    public int[] parseArray(String input) {
        return parseArray(input, 1);
    }

    public int[] parseArray(String input, int skip) {
        String[] parts = input.split("[\\[\\],]");
        int[] output = new int[parts.length - skip];
        for (int i = skip; i < parts.length; i++) {
            output[i - skip] = Integer.parseInt(parts[i]);
        }
        return output;
    }

    @Test
    public void testArray() {
        String input = "[1,2,3]";
        int[] expected = {1, 2, 3};
        int[] output = parseArray(input);
        Assertions.assertArrayEquals(expected, output);
    }

    public int[][] parseArray2(String input) {
        String[] parts = input.split("(\\[\\[)|(],\\[)|(]])");
        int[][] output = new int[parts.length - 1][];
        for (int i = 1; i < parts.length; i++) {
            output[i - 1] = parseArray(parts[i], 0);
        }
        return output;
    }

    @Test
    public void testArray2() {
        String input = "输入：[[1,2],[3,4]]";
        int[][] expected = {{1, 2}, {3, 4}};
        int[][] output = parseArray2(input);
        Assertions.assertArrayEquals(expected, output);
    }

}
