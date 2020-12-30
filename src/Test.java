import java.util.ArrayList;

public class Test {

	static double wc = 10000;
	static double q1 = 1 / 0.7654;
	static double q2 = 1 / 1.8478;

	static ArrayList<Filter> BestFilters = new ArrayList<>();
	static Filter thebest = null;
	static double[] mean = new double[4];
	static double[] StDev = new double[4];
	static double[] MES = new double[4];

	static int iteration = 0;
	static int maxIteration = 150;
	
	static double maxError = 0.001;
	
	public static void main(String[] args) {

		
		
		try {
			for (int i = 0; i < maxIteration  /*thebest == null || thebest.fitness() > maxError*/ ; i++) {
				iteration = i;
				GA ga = new GA(1000,500,100);
				System.out.println("epoch " + i + "--> best: " + ga.bestResult.fitness());
				BestFilters.add(ga.bestResult);

				if (i == 0 || ga.bestResult.fitness() < thebest.fitness())
					thebest = ga.bestResult;
			}

			System.out.println("******************************* Best *****************************");
			System.out.println("best error = "+ thebest.fitnessValue);
			thebest.print();
			
			updateMean();
			updateStDev();
			updateMES();

			System.out.println("******************************* Mean *****************************");
			System.out.println("w = " + ((mean[1] + mean[0]) / 2));
			System.out.println("Q1 = " + mean[2]);
			System.out.println("Q2 = " + mean[3]);

			System.out.println("*******************************Stan Dev******************************");
			System.out.println("w = " + ((StDev[1] + StDev[0]) / 2));
			System.out.println("Q1 = " + StDev[2]);
			System.out.println("Q2 = " + StDev[3]);

			//System.out.println("******************************* MSE *******************************");
			//System.out.println("w = " + ((MES[1] + MES[0]) / 2));
			//System.out.println("Q1 = " + MES[2]);
			//System.out.println("Q2 = " + MES[3]);

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println();
	}

	public static void updateMean() {
		for (Filter f : BestFilters) {
			mean[0] += f.calculateFrequency1();
			mean[1] += f.calculateFrequency2();
			mean[2] += f.calculateQ1();
			mean[3] += f.calculateQ2();
		}
		mean[0] /= iteration;
		mean[1] /= iteration;
		mean[2] /= iteration;
		mean[3] /= iteration;
	}

	public static void updateStDev() {
		for (Filter f : BestFilters) {
			StDev[0] += Math.pow((f.calculateFrequency1() - mean[0]), 2);
			StDev[1] += Math.pow((f.calculateFrequency2() - mean[1]), 2);
			StDev[2] += Math.pow((f.calculateQ1() - mean[2]), 2);
			StDev[3] += Math.pow((f.calculateQ2() - mean[3]), 2);
		}
		StDev[0] /= iteration;
		StDev[1] /= iteration;
		StDev[2] /= iteration;
		StDev[3] /= iteration;

		StDev[0] = Math.sqrt(StDev[0]);
		StDev[1] = Math.sqrt(StDev[1]);
		StDev[2] = Math.sqrt(StDev[2]);
		StDev[3] = Math.sqrt(StDev[3]);
	}

	public static void updateMES() {
		for (Filter f : BestFilters) {
			MES[0] += Math.pow((f.calculateFrequency1() - wc), 2);
			MES[1] += Math.pow((f.calculateFrequency2() - wc), 2);
			MES[2] += Math.pow((f.calculateQ1() - q1), 2);
			MES[3] += Math.pow((f.calculateQ2() - q2), 2);
		}
		MES[0] /= iteration;
		MES[1] /= iteration;
		MES[2] /= iteration;
		MES[3] /= iteration;
	}
}
