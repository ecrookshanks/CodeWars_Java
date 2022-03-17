package com.nokelservices;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HungerGamesTests {

    @Test
    public void shouldCreateListOfZooAnimals(){

        long result =  HungerGames.createZooAnimalList();

        assertEquals(11, result);
        assertTrue(Arrays.stream(HungerGames.getZooAnimals()).anyMatch(z -> z.equals("antelope")));
        assertTrue(Arrays.stream(HungerGames.getZooAnimals()).anyMatch(z -> z.equals("fox")));
        assertTrue(Arrays.stream(HungerGames.getZooAnimals()).anyMatch(z -> z.equals("lion")));
        assertTrue(Arrays.stream(HungerGames.getZooAnimals()).anyMatch(z -> z.equals("sheep")));
    }

    @Test
    public void shouldReturnSingleObjectIfSinglePassedIn(){

        String[] expected = {"fox", "fox"};
        String[] result = HungerGames.whoEatsWho("fox");

        assertArrayEquals(expected, result);

    }

    @Test
    public void shouldReturnFoxAndFoxEatsSheepWhenPassedFoxSheep(){

        String[] expected = {"fox,sheep", "fox eats sheep", "fox"};
        String[] result = HungerGames.whoEatsWho("fox,sheep");

        assertArrayEquals(expected, result);
    }

    @Test
    public void shouldFindProperEdibleItemsForGivenAnimal(){

        Set<String> edibles = HungerGames.findEdibles("fox");

        String[] expected = {"chicken", "sheep"};

        assertArrayEquals(expected, edibles.toArray(String[]::new));
    }

    @Test
    public void shouldProcessEntireListCorrectly(){

        final String input = "fox,bug,chicken,grass,sheep";
        final String[] expected = 	{
                "fox,bug,chicken,grass,sheep",
                "chicken eats bug",
                "fox eats chicken",
                "sheep eats grass",
                "fox eats sheep",
                "fox"};

        assertArrayEquals(expected, HungerGames.whoEatsWho(input));
    }

    @Test
    public void shouldProcessMultipleLeftEntries(){
        final String input = "grass,grass,cow,leaves,bug,big-fish,leaves,bear";

        final String[] expected = {
                "grass,grass,cow,leaves,bug,big-fish,leaves,bear",
                "cow eats grass",
                "cow eats grass",
                "bug eats leaves",
                "bear eats leaves",
                "bear eats big-fish",
                "bear eats bug",
                "bear eats cow",
                "bear"
        };

        assertArrayEquals(expected, HungerGames.whoEatsWho(input));
    }

}
