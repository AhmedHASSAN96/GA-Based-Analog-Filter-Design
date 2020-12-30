import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GA {
	
	Population population;
	Filter bestResult = null;
	int numOfBest;
	int populationSize;
	
	double wc = 10000;
	double q1 = 1/0.7654;
	double q2 = 1/1.8478;
	
	ArrayList<Filter> BestFilters = new ArrayList<>();
	double[] mean  = new double[4];
	double[] StDev = new double[4];
	double[] MES = new double[4];
	
	int iteration = 0;
	
	public GA(int populationSize, int numOfBest,int iteration) throws CloneNotSupportedException {
		this.populationSize = populationSize;
		this.numOfBest = numOfBest;
		this.iteration = iteration;
		
		//initialization & evaluation
		population = new Population(populationSize);
		
		for (int j = 0;  j < iteration /* bestResult==null ||bestResult.fitness() > 0.001*/; j++) {
					
			//selection
			Arrays.sort(population.filterPopulation);

			if(bestResult == null || bestResult.fitness() > population.filterPopulation[0].fitness()) {
				bestResult = (Filter) population.filterPopulation[0].clone();
			}

			//crossover
			for(int i = 0; i < numOfBest - 1; i += 2) {
				crossOver(population.filterPopulation[i], population.filterPopulation[i + 1]);
			}

			//mutation
			for(int i = 0; i < numOfBest; i++) {
				mutation(population.filterPopulation[i]);
			}
			
			//fitness
			Arrays.sort(population.filterPopulation);
			BestFilters.add(population.filterPopulation[0]);

			//getbest
			if(bestResult.fitness() > population.filterPopulation[0].fitness()) {
				bestResult = (Filter) population.filterPopulation[0].clone();
			}
			
			population.newGeneration(numOfBest);
		}
		
	}
	
	public void crossOver(Filter f1, Filter f2) {
		double[] ch1 = f1.getChromosome();
		double[] ch2 = f2.getChromosome();

		int rand1 = getRandomNumberUsingInts(0,8);
		int rand2 = getRandomNumberUsingInts(8,16);
		
		double[] newch1 = new double[ch1.length];
		double[] newch2 = new double[ch1.length];

		for(int i = 0; i <= rand1; i++) {
			newch1[i] = ch1[i];
			newch2[i] = ch2[i];
		}

		for(int i = rand1 + 1; i <= 7; i++) {
			newch1[i] = ch2[i];
			newch2[i] = ch1[i];
		}
		
		for(int i = 8; i <= rand2; i++) {
			newch1[i] = ch1[i];
			newch2[i] = ch2[i];
		}

		for(int i = rand2 + 1; i <= 15; i++) {
			newch1[i] = ch2[i];
			newch2[i] = ch1[i];
		}
		
		if(fitness(newch1) < f1.fitnessValue)
		f1.updateFilter(newch1);
		
		if(fitness(newch2) < f2.fitnessValue)
		f2.updateFilter(newch2);
	}
	
	public void mutation(Filter f) {
		double[] ch = f.getChromosome();

		int rand1 = getRandomNumberUsingInts(0,8);
		int rand2 = getRandomNumberUsingInts(8,16);

		ch[rand1] = Math.random() * (0.82 - 0.1) + 0.1;
		ch[rand2] = getRandomNumberUsingInts(2,5);

		if(fitness(ch) < f.fitnessValue)
		f.updateFilter(ch);
	}

	public int getRandomNumberUsingInts(int min, int max) {
	    Random random = new Random();
	    return random.ints(min, max).findFirst().getAsInt();
	}

	public double fitness(double[] ch) {
		double a  = ch[0];
		double b = ch[1];
		double c  = ch[2];
		double d = ch[3];
		double e  = ch[4];
		double f = ch[5] ;
		double g = ch[6];
		double h = ch[7];
		
		int a1  = (int) ch[8];
		int b1 = (int) ch[9];
		int c1  = (int) ch[10];
		int d1 = (int) ch[11];
		int e1  = (int) ch[12];
		int f1 = (int) ch[13];
		int g1  = (int) ch[14] ;	
		int h1 = (int) ch[15];
		
		double R1 = a*100*Math.pow(10, a1); 
		if(R1 < 1000) R1 = 1000;
		if(R1 > 1000000) R1 = 1000000;
		
		double R2 = b*100*Math.pow(10, b1);
		if(R2 < 1000) R2 = 1000;
		if(R2 > 1000000) R2 = 1000000;
		
		double R3 = c*100*Math.pow(10, c1);
		if(R3 < 1000) R3 = 1000;
		if(R3 > 1000000) R3 = 1000000;
		
		double R4 = d*100*Math.pow(10, d1);
		if(R4 < 1000) R4 = 1000;
		if(R4 > 1000000) R4 = 1000000;
		
		double C1 = e*100*Math.pow(10, e1);
		if(C1 < 0) C1 = 1000;
		
		double C2 = f*100*Math.pow(10, f1);
		if(C2 < 0) C2 = 1000;
		
		double C3 = g*100*Math.pow(10, g1);
		if(C3 < 0) C3 = 1000;
		
		double C4 = h*100*Math.pow(10, h1);
		if(C4 < 0) C4 = 1000;
		
		double wc1 = 1 / Math.sqrt(R1*R2*C1*Math.pow(10, -12)*C2*Math.pow(10, -12)) ;
		double wc2 = 1 / Math.sqrt(R3*R4*C3*Math.pow(10, -12)*C4*Math.pow(10, -12)) ;
		double deltaWc = ( Math.abs( wc1 - this.wc ) + Math.abs( wc2 - this.wc ) ) / this.wc;
		double deltaQ  = Math.abs(  (1/0.7654) - ( 1 / (wc1*(R1+R2)*C1*Math.pow(10, -12)) ) )+ Math.abs( (1/1.8478) - ( 1 / (wc2*(R3+R4)*C3*Math.pow(10, -12)) ) );
		double error = 0.5*deltaWc + 0.5*deltaQ;
		return error;
	}
	
}
