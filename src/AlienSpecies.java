import java.util.Random;
//By including this comment, I acknowledge that this submission
//is my work, and my work alone. With no collaboration with (an)other student(s)
//I also acknowledge that I have not used any AI tools and have referenced
//by providing a web link (as a comment) any online sources I have consulted
//22348871 James Finn
class AlienException extends RuntimeException {
    public AlienException(String s) {
        super(s);
    }
}
public abstract class AlienSpecies {
    private String name;
    private int intelligenceLevel;
    private Planet homePlanet;

    public AlienSpecies(String name, int intelligenceLevel, Planet homePlanet) {
        if(intelligenceLevel< 0 ){
            throw new IllegalArgumentException("Intellignes not negative");
        }else if(name.isEmpty()){
            throw new IllegalArgumentException("Name is empty");
        }
        this.name = name;
        this.intelligenceLevel = intelligenceLevel;
        this.homePlanet = homePlanet;
    }

    public abstract String converse(String s);

    public abstract int solve(int[] a, char x);

    public String getName() {
        return name;
    }

    public int getIntelligenceLevel() {
        return intelligenceLevel;
    }

    public Planet getHomePlanet() {
        return homePlanet;
    }
}

class Planet {
    //enums
    enum Climate {
        TROPICAL, TUNDRA, ARID, TEMPERATE;


    }

    private String name;
    private Climate climate;
//creates planet
    public Planet(String name, Climate climate) {
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name is empty");
        }
        this.name = name;
        this.climate = climate;
    }

    public String getName() {
        return name;
    }

    public Climate getClimate() {
        return climate;
    }
}

class Zorgon extends AlienSpecies {
//creates zorgon as a child class
    public Zorgon(String name, int intelligenceLevel, Planet homePlanet) {
        super(name, intelligenceLevel, homePlanet);
    }

//overides abstract method
    @Override
    public String converse(String s) {
        if (s.equalsIgnoreCase("hello")) {
            return "Hello, my name is " + getName();
        } else if (s.toLowerCase().contains("are")) {
            return "Yes";
        } else if (s.toLowerCase().contains(getHomePlanet().getName().toLowerCase())) {
            return getHomePlanet().getName() + " home";
        } else {
            return getName() +" confused";
        }
    }




    @Override
    public int solve(int[] a, char x) {
        Random rand = new Random();
        return rand.nextInt(10) + 1;
    }
}
class Quasarite extends Zorgon{
//child class zorgon
    public Quasarite(String name, int intelligenceLevel, Planet homePlanet) {
        super(name, intelligenceLevel, homePlanet);
    }
//overrides solve method
    @Override
    public int solve(int[] a, char x) {

        if(x != 'x' &&  x != '-' && x != '+' ){
            throw new AlienException("Intellignece not high enough");
        }
        int sum = 0;
        //multiplication
        if(9 < this.getIntelligenceLevel() && x == 'x'){
            sum = 1;
            for(int j : a){
                sum *= j;

            }
            //subtraction
        } else if(6 < this.getIntelligenceLevel() && x == '-') {
            sum = a[0]*2;
           // System.out.println(sum);
            for(int j : a){
                sum -= j;

            }
            //addition
        }else if( 3 < this.getIntelligenceLevel() && x == '+') {


            for(int j : a){
                sum += j;

            }
        }
        // not intelligent enough calls super method
        else{
           sum = super.solve(a, x);
        }
        return sum;
    }


}

class Nebulonian extends Quasarite {

    public Nebulonian(String name, int intelligenceLevel, Planet homePlanet) {
        super(name, intelligenceLevel, homePlanet);
    }

    @Override
    public String converse(String s) {
        String[] array = s.split(" ");
        if(array[0].equalsIgnoreCase("describe") && s.toLowerCase().contains("planet")){
            return this.getHomePlanet().getName() + " has a " + this.getHomePlanet().getClimate().toString().toLowerCase() + " climate";
        }else if(array[0].equalsIgnoreCase("describe") && s.toLowerCase().contains("intelligence")) {
            return getName() + " has intelligence " + getIntelligenceLevel();
        }
        //if doesn't meat criteria of above call paretn class
        else{
            return super.converse(s);
        }

    }
}