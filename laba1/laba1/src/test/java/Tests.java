import org.example.Main;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;


public class Tests {
    private ArrayList<String> words;

    @BeforeAll
    public static void setUpAll() {
        System.out.println("Running all tests...");
    }


    @ParameterizedTest
    @MethodSource("provideWords")
    public void testSubStringsParametrized(String[] input, String[] expected) {
        String[] actual = Main.cutWord(input);
        Assertions.assertArrayEquals(actual, expected);
    }

    private static Object[][] provideWords() {
        return new Object[][]{

                {new String[]{"supercalifragilisticexpialidocious", "antidisestablishmentarianism"},
                        new String[]{"supercalifragilisticexpialidoc", "antidisestablishmentarianism"}}
        };
    }

    @Test
    public void testMainFileNotFound() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> Main.main(new String[]{}));
        assertEquals("input11.txt (The system cannot find the file specified)", exception.getMessage());
    }

    @BeforeEach
    public void setUp() {
        words = new ArrayList<>();
        words.add("cat");
        words.add("dog");
        words.add("fish");
        words.add("elephant");
        words.add("lion");
        words.add("tiger");
    }

    @Test
    public void testGetWordWithLargestSubstring() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("fish");
        expected.add("elephant");
        assertEquals(expected, Main.getWordWithLargestSubstring(words));
    }

    @Test
    public void testCutWord() {
        String[] words = new String[]{"apple", "banana", "kiwi", "pineapple"};
        String[] expected = new String[]{"apple", "banana", "kiwi", "pineapple"};
        Main.cutWord(words);
        String[] actual = new String[]{"apple", "banana", "kiwi", "pineapple"};
        assertArrayEquals(expected, actual);
    }


    @Test
    public void testGetWordWithLargestSubstringHamcrest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("fish");
        expected.add("elephant");

        MatcherAssert.assertThat(Main.getWordWithLargestSubstring(words), CoreMatchers.equalTo((expected)));
    }

    @Test
    public void HamcrestUsingCutWord() {
        String[] words = new String[]{"dipalmitoylphosphatidylcholineaceton"};
        Main.cutWord(words);
        assertThat(words[0].length(), equalTo(30));
    }

    @Test
    public void testCutWordLongWordsArray() {
        String[] words = new String[]{"dipalmitoylphosphatidylcholineaceton",
                "Anotherdipalmitoylphosphatidylcholine"};
        String[] expected = new String[]{"dipalmitoylphosphatidylcholine", "Anotherdipalmitoylphosphatidyl"};
        Main.cutWord(words);
        assertArrayEquals(expected, words);
    }

    @Test
    public void testGetWordWithLargestSubstringHamcrestConstructions() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "grapefruit"));
        ArrayList<String> result = Main.getWordWithLargestSubstring(words);
        assertThat(result, CoreMatchers.hasItem("apple"));
    }

    @Test
    public void testGetWordWithLargestSubstringAssertTrue() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "grapefruit"));
        ArrayList<String> result = Main.getWordWithLargestSubstring(words);
        assertTrue(result.contains("apple"));
    }
}