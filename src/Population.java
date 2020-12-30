
public class Population {
	Filter[] filterPopulation;
	
	//double[] populationFitnesses;

	public Population(int populationSize) {
		filterPopulation = new Filter[populationSize];

		for(int i= 0; i < populationSize; i++) {
			filterPopulation[i] =  new Filter(10000);
		}
	}
	
	public void newGeneration(int best) {
		for (int i = best+1; i < filterPopulation.length; i++ ) {
			filterPopulation[i] = new Filter(10000);
		}
	}
}
