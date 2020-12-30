
public class Population {
	Filter[] filterPopulation;
	
	//double[] populationFitnesses;

	public Population(int populationSize) {
		filterPopulation = new Filter[populationSize];

		for(int i= 0; i < populationSize; i++) {
			filterPopulation[i] =  new Filter(10000);
		}
	}
}
