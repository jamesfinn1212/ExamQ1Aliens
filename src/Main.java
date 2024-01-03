// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        AlienSpecies q = new Quasarite("Q", 10, new Planet("Quaser5", Planet.Climate.TUNDRA));
        int response;
        //Quaserites can do 3 kinds of maths
        //+ add all numbers (if intelligence > 3)
        response = q.solve(new int[] {100, 10, 20, 30}, '-');
        System.out.println(response);
    }
}