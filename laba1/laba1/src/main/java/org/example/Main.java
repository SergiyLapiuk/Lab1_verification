package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        try {
            //Відкриття файлу
            //System.out.println("File name: ");//C:\Users\acer\Desktop\laba1\laba1\src\main\java\org\example\input.txt
            Scanner scanner = new Scanner(System.in);
            //String path = scanner.nextLine();
            String path = "C:\\Users\\acer\\Desktop\\laba1\\laba1\\src\\main\\java\\org\\example\\input.txt";
            //String path = args[0];
            File myFile = new File(path);
            Scanner Reader = new Scanner(myFile);

            //Колекція для зберігання слів із вхідного файлу
            ArrayList<String> words = new ArrayList<>();

            while (Reader.hasNextLine()) {
                String line = Reader.nextLine();

                //Розбиваємо рядок на слова. Роздільником є будь-який
                //символ, окрім букв та цифр
                String[] wordsInLine = line.split("[^a-zA-Z0-9]");//"[^[a-zA-Z]]"
                // Зменшуємо довжину надто великих слів
                cutWord(wordsInLine);
                words.addAll(Arrays.asList(wordsInLine));
            }
            Reader.close();

            // Знаходимо результат та виводимо його
            ArrayList<String> res = getWordWithLargestSubstring(words);
            for (String word : res)
            {
                System.out.print(word + ";\n");

            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    //Перевірка умови на довжину слова

    public static  String[] cutWord(String[] words)
    {
        for (int i = 0; i < words.length; ++i)
        {
            if(words[i].length() > 30) words[i] = words[i].substring(0, 30);

        }
        return words;
    }

    // Функція, що повертає слова з
    // найдовшим підланцюжком приголосних літер
    public static ArrayList<String> getWordWithLargestSubstring(ArrayList<String> words)
    {
        ArrayList<String> arrayListResult = new ArrayList<>();
        int chain_length = 0;
        int chain_lenght_max = 0;

        for (String word : words)
        {
            chain_length = longestConsonant(word);

            if(chain_length == chain_lenght_max && !arrayListResult.contains(word)) {
                arrayListResult.add(word);
            }
            if(chain_length > chain_lenght_max && !arrayListResult.contains(word)) {
                arrayListResult.clear();
                arrayListResult.add(word);
            }
            chain_lenght_max = Math.max(chain_lenght_max, chain_length);
        }
        return  arrayListResult;
    }

    //перевіряєио чи є літреа приголосною
    public static boolean isConsonant(char c)
    {
        return !(c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u' || c == 'y');
    }

    // Знаходимо довжину найдовшого підланцюжка
    // приголосних літер
    public static int longestConsonant(String str)
    {
        int count = 0, res = 0;
        char[] s = str.toCharArray();

        for (int i = 0; i < s.length; i++)
        {
            //  Якщо s [i] приголосна літера збільшуємо лічильник на 1
            if (isConsonant(s[i]))
                count++;
            else
            {
                count = 0;
            }
            // порівнюємо з попереднім результатом та обираємо більший
            res = Math.max(res, count);
        }
        return res;
    }
}