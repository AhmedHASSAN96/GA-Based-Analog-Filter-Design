
public class Test {
	
	public static void main(String[] args) {
		try {
			GA ga = new GA(1000);
			
			ga.bestResult.print();
			
			System.out.println();
			
			/*System.out.println("******************************* Mean *****************************");
			System.out.println("w = "+( (ga.mean[1]+ga.mean[0]) / 2 ) );
			System.out.println("Q1 = "+ga.mean[2]);
			System.out.println("Q2 = "+ga.mean[3]);
			
			System.out.println("*******************************Stan Dev******************************");
			System.out.println("w = "+( (ga.StDev[1]+ga.StDev[0]) / 2 ) );
			System.out.println("Q1 = "+ga.StDev[2]);
			System.out.println("Q2 = "+ga.StDev[3]);
			
			System.out.println("******************************* MSE *******************************");
			System.out.println("w = "+( (ga.MES[1]+ga.MES[0]) / 2 ) );
			System.out.println("Q1 = "+ga.MES[2]);
			System.out.println("Q2 = "+ga.MES[3]);*/
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println();
	}
}
