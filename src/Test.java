import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Question2Tests {

    @Test
    void testPlanet() {
        Planet p = new Planet("name", Planet.Climate.ARID);
        assertEquals("name", p.getName());
        assertEquals("ARID", p.getClimate().toString());
    }

    @Test
    void testZorgon() {
        Zorgon z = new Zorgon("Zoidberg", 5, new Planet("Zorgonia", Planet.Climate.TEMPERATE));
        assertEquals("Zoidberg", z.getName());
        assertEquals(5, z.getIntelligenceLevel());

        int i = z.solve(new int [] {1,2,3,4,5}, '+');
        assertTrue(i >= 0 && i <= 10);

        assertTrue(z instanceof AlienSpecies);
    }

    @Test
    void testZorgonConverse() {
        Zorgon z = new Zorgon("Zoidberg", 5, new Planet("Zorgonia", Planet.Climate.TEMPERATE));

        String response;
        //Zorgons can respond to the following key points (case insensitive):
        //if the input text contains hello
        response = z.converse("hello");
        assertEquals("Hello, my name is Zoidberg", response);

        //if the text contains a ? they always answer Yes
        response = z.converse("are you my friend?");
        assertEquals("Yes", response);

        //if the text contains the name of their home planet, they mention their home planet:
        response = z.converse("blah blah zorgonia blah 123");
        assertEquals("Zorgonia home", response);

        //for all other input they get confused
        response = z.converse("this is a java exam");
        assertEquals("Zoidberg confused", response);
    }

    @Test
    void testQuaserite() {
        AlienSpecies q = new Quasarite("Q", 10, new Planet("Quaser5", Planet.Climate.TUNDRA));

        int response;
        //Quaserites can do 3 kinds of maths
        //+ add all numbers (if intelligence > 3)
        response = q.solve(new int[] {1,2,3,4,5}, '+');
        assertEquals(15, response);

        //- subtract all numbers from the first element in the array
        //if intelligence > 6
        response = q.solve(new int[] {100, 10, 20, 30}, '-');
        assertEquals(40, response);

        //x multiply all numbers together (if intelligence > 9)
        response = q.solve(new int[] {1,2,3,4,5}, 'x');
        assertEquals(120, response);

        //in all other cases throw an AlienException
        assertThrows(AlienException.class, () -> q.solve(new int[] {1,2,3,4,5}, '/'));

        //if they lack the intelligence to solve the problem, they revert back to Zorgon behaviour (return a number between 0 and 10)
        Quasarite q1 = new Quasarite("Q", 5, new Planet("Quaser5", Planet.Climate.TUNDRA));
        response = q1.solve(new int[] {100, 10, 20, 30}, '-');
        assertTrue(response >= 0 && response <= 10);

        //they also converse like Zorgons
        assertEquals("Hello, my name is Q", q.converse("hello"));
    }

    @Test
    void testNebulonian() {
        AlienSpecies n = new Nebulonian("Neptulon", 5, new Planet("Neblulon", Planet.Climate.TROPICAL));

        //Nebulonians have slightly more conversation ability
        String response;

        response = n.converse("hello");
        assertEquals("Hello, my name is Neptulon", response);
        response = n.converse("are you my friend?");
        assertEquals("Yes", response);
        response = n.converse("blah blah nEbLuLoN blah 123");
        assertEquals("Neblulon home", response);

        //in situations where Zorgon's and Quaserites get confused, they can describe their intelligence and home planet
        response = n.converse("describe intelligence");
        assertEquals("Neptulon has intelligence 5", response);

        response = n.converse("describe planet");
        assertEquals("Neblulon has a tropical climate", response);
        response = n.converse("DESCRIBE 9876543 planet");
        assertEquals("Neblulon has a tropical climate", response);

        //they get confused if "describe" is not the first word though
        response = n.converse("java describe planet");
        assertEquals("Neptulon confused", response);
        response = n.converse("java describe intelligence");
        assertEquals("Neptulon confused", response);

        //they can still solve like Quaserites
        int i  = n.solve(new int[] {100, 10, 20, 30}, '+');
        assertEquals(160, i);
    }

}
