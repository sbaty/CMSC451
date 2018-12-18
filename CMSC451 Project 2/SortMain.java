public class SortMain {
    
    public static void main(String[] args){
        int[] cases = {50, 100, 250, 500, 750, 1000, 2000, 3000, 4000, 5000};
        int trials = 50;
        
        //Code from http://www.baeldung.com/java-jvm-warmup 
        
        System.out.println("Performing JVM Warmup to ensure the test's accuracy.");
        long start = System.nanoTime();
        ManualClassLoader.load();
        long end = System.nanoTime();
        System.out.println("Warm Up time : " + (end - start) + " ns\n");
     
        BenchmarkSorts benchmarkDriver = new BenchmarkSorts(cases, trials);
        benchmarkDriver.runSorts();
        benchmarkDriver.displayReport();
    }
}