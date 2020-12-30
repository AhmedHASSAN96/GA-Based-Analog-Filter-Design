
public class Test {
	
	public static void main(String[] args) {
		try {
			GA ga = new GA(1000);
			ga.bestResult.print();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println();
	}
}
