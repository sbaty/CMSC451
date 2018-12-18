import java.util.Random;

public class BenchmarkSorts {
    private final int GEN_MIN = 0;
    private final int GEN_MAX = 100000;
    
    private final int trials;
    private final int cases;
    private final int[] caseSizes;
    private final int [][][] data;
    
    private final int[][] iterativeCounts;    
    private final long[][] iterativeTimes;   
    private final int[][] recursiveCounts;
    private final long[][] recursiveTimes;
    private final double[][] calculations;
    

    public BenchmarkSorts(int[] c, int t){
        trials = t;
        caseSizes = c;
        cases = caseSizes.length;
        
        data = new int[cases][trials][];
        iterativeCounts = new int[cases][trials];
        iterativeTimes = new long[cases][trials];
        recursiveCounts = new int[cases][trials];
        recursiveTimes = new long[cases][trials];
        calculations = new double[cases][8];
        Random rand = new Random();
        
        for(int i = 0; i < cases; i++){
            for(int j = 0; j < trials; j++){
                data[i][j] = new int[caseSizes[i]];
                for(int k = GEN_MIN; k < caseSizes[i]; k++){
                    data[i][j][k] = rand.nextInt(GEN_MAX);
                }
            }
        } 
    }
    
    public void runSorts(){
        for(int i = 0; i < cases; i++){
            for(int j = 0; j < trials; j++){
                
                HeapSort sort = new HeapSort();
                int[] caseData = (int[]) data[i][j].clone();
                sort.iterativeSort(caseData);
                iterativeCounts[i][j] = sort.getCount();
                iterativeTimes[i][j] = sort.getTime();
                try {
                    checkSorted(caseData);
                } catch (UnsortedException ex) {}
               
                sort = new HeapSort();
                caseData = (int[]) data[i][j].clone();
                sort.recursiveSort(caseData);
                recursiveCounts[i][j] = sort.getCount();
                recursiveTimes[i][j] = sort.getTime();
                try{
                    checkSorted(caseData);
                } catch(UnsortedException ex) {}
            }
             System.out.println("Test Case " + (i+1) + " finished.");
        }
    }
    
        private void checkSorted(int[] arr) throws UnsortedException{
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]){
                throw new UnsortedException("SORT FAILED!");
            }
        }
    }

    public void displayReport(){
        for(int i = 0; i < cases; i++){
            calculations[i][0] = average(iterativeCounts[i]);
            calculations[i][1] = standardDev(iterativeCounts[i], calculations[i][0]);
            calculations[i][2] = average(iterativeTimes[i]) / 100000;
            calculations[i][3] = standardDev(iterativeTimes[i], calculations[i][2]);
            
            calculations[i][4] = average(recursiveCounts[i]);
            calculations[i][5] = standardDev(recursiveCounts[i], calculations[i][4]);
            calculations[i][6] = average(recursiveTimes[i]) / 100000;
            calculations[i][7] = standardDev(recursiveTimes[i], calculations[i][6]);
            
            System.out.println("\nTest Case " + (i+1) + ": ");
            System.out.println("\tCase Data Size:   " + caseSizes[i]);
            
            System.out.println("\tIterative Algorithm Results:");
            System.out.printf("\t\tAverage Critical Operation Count: \t%.2f\n", calculations[i][0]);
            System.out.printf("\t\tStandard Deviation of Count: \t\t%.2f\n", calculations[i][1]);
            System.out.printf("\t\tCoefficient of Variance Count: \t\t%.2f\n", (calculations[i][1] / calculations[i][0]) * 100);
            System.out.printf("\t\tAverage Execution Time: \t\t%.3f ns\n", calculations[i][2]);
            System.out.printf("\t\tStandard Deviation of Time: \t\t%.3f ns\n", calculations[i][3]);
            System.out.printf("\t\tCoefficient of Variation of Time: \t%.2f\n", (calculations[i][3] / calculations[i][2]) * 100);
            
            System.out.println("\tRecursive Algorithm Results:");
            System.out.printf("\t\tAverage Critical Operation Count: \t%.2f\n", calculations[i][4]);
            System.out.printf("\t\tStandard Deviation of Count: \t\t%.2f\n", calculations[i][5]);
            System.out.printf("\t\tCoefficient of Variance Count: \t\t%.2f\n", (calculations[i][5] / calculations[i][4]) * 100);
            System.out.printf("\t\tAverage Execution Time: \t\t%.3f ns\n", calculations[i][6]);
            System.out.printf("\t\tStandard Deviation of Time: \t\t%.3f ns\n", calculations[i][7]);
            System.out.printf("\t\tCoefficient of Variation of Time: \t%.2f\n", (calculations[i][7] / calculations[i][6]) * 100);
        }
    }

    private double average(int[] arr){
        double avg = 0.0;
        for(int i = 0; i < arr.length; i++){
            avg += arr[i];
        }
        return avg / arr.length;
    }
    private double average(long[] arr){
        double avg = 0.0;
        for(int i = 0; i < arr.length; i++){
            avg += arr[i];
        }
        return avg / arr.length;
    }

    private double standardDev(int[] arr, double avg){
        double x = 0.0;
        
        for(int i = 0; i < arr.length; i++){
            x += Math.pow((arr.length - avg), 2);
        }
        x /= arr.length;
        return Math.sqrt(x);
    }
    
    private double standardDev(long[] arr, double avg){
        double x = 0.0;
        
        for(int i = 0; i < arr.length; i++){
            x += Math.pow((arr.length - avg), 2);
        }
        x /= arr.length;
        return Math.sqrt(x);
    }
}