import java.util.Random;

public class Individual {

    int fitness;
    String[] sequence;
    int sequenceLength;

    public Individual(String[] seq) {
        sequence = seq;
        fitness = 0;
        sequenceLength = sequence[0].length();
    }

    //Calculate fitness
    public void calcFitness() {
        fitness = 0;
        for(int i = 0; i < sequence[0].length(); i++)
        {
            for(int j = 0; j < sequence.length; j++)
            {
                if((sequence[j].charAt(i) == sequence[(j + 1) % sequence.length].charAt(i)) && (sequence[j].charAt(i)!= '_' || sequence[j].charAt(i)!= '.')){
                    fitness++;
                }
            }
        }
//        System.out.println("fitness  " + fitness);
    }
}
