import java.util.Arrays;

public class Population {

    Individual[] individuals;
    int highestFittness;
    int[][] fittnesses;

    //Initialize population
    public void initializePopulation(int size, String[] initialSeq) {
        individuals = new Individual[size];
        fittnesses = new int[size][2];
        System.out.println("init population: \n");
        for (int k = 0; k < individuals.length; k++) {
            int longest = 0;
            String[] newSequence = Arrays.copyOf(initialSeq, initialSeq.length);
//            for(int i = 0; i < newSequence.length; i++)
//            {
//                System.out.println(k + " " + newSequence[i]);
//            }
            for(int i = 0; i < newSequence.length; i++)
            {
                if(newSequence[i].length() > longest)
                    longest = newSequence[i].length();
            }

            for(int i=0; i < newSequence.length; i++){
                String temp = newSequence[i];
//                System.out.println(",,,  " + (longest));
                for(int j = 0; j < (int)(longest * 1.2) - newSequence[i].length(); j++){
//                    System.out.println(longest + " " + newSequence[i].length());
                    int insertBlank = (int)Math.floor(Math.random() * temp.length());
                    temp = temp.substring(0, insertBlank) + "_" + temp.substring(insertBlank);
                }
                newSequence[i] = temp;
            }
            individuals[k] = new Individual(newSequence);

            for(int i = 0; i < newSequence.length; i++)
            {
                System.out.println(k + " " + newSequence[i]);
            }
            System.out.println();
        }
        System.out.println("init done. \n");
    }

    //Get the fittest individual
    public Individual getFittest() {

        highestFittness = individuals[fittnesses[fittnesses.length - 1][1]].fitness;
        return individuals[fittnesses[fittnesses.length - 1][1]];
    }

    //Get the second most fittest individual
    public Individual getSecondFittest() {
        return individuals[fittnesses[fittnesses.length - 2][1]];
    }
    public int[] getTop10()
    {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = fittnesses[fittnesses.length - i - 1][1];
        }
        return arr;
    }
    public int[] getLeast10()
    {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = fittnesses[i][1];
        }
        return arr;
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() {
        return fittnesses[fittnesses.length - 2][1];
    }

    //Calculate fitness of each individual
    public void calculateFitness() {

        for (int i = 0; i < individuals.length; i++) {
            individuals[i].calcFitness();
            fittnesses[i][0] = individuals[i].fitness;
            fittnesses[i][1] = i;
//            System.out.println(fittnesses[i][0] + " " + fittnesses[i][1]);
        }
//        for (int i = 0; i < fittnesses.length; i++) {
//            System.out.println(fittnesses[i][0] + " " + fittnesses[i][1]);
//        }
//        System.out.println();
        Arrays.sort(fittnesses, (a, b) -> Integer.compare(a[0], b[0]));
//        for (int i = 0; i < fittnesses.length; i++) {
//            System.out.println(fittnesses[i][0] + " " + fittnesses[i][1]);
//        }
        getFittest();
    }

}