
public class Filter implements Cloneable, Comparable<Filter> {

	public final double valueUpperLimit = 0.82;
	public final double valueLowerLimit = 0.1;
	public final int decadeUpperLimit = 4;
	public final int decadeLowerLimit = 2;
	final double Q1 = 1/0.7654;
	final double Q2 = 1/1.8478;
	double R1,R2,R3,R4, C1,C2,C3,C4;
	double a,b,c,d;
	double a1,b1,c1,d1;
	double e,f,g,h;
	double e1,f1,g1,h1;
	double wc;
	
	double fitnessValue;

	public Filter(double wc) {
		this.wc = wc;
		
		
		a = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		b = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		c = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		d = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		
		a1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		b1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		c1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		d1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		
		e = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		f = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		g = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		h = Math.random() * (valueUpperLimit - valueLowerLimit) + valueLowerLimit ;
		
		e1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		f1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		g1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;
		h1 = (int)(Math.random() * ( (decadeUpperLimit - decadeLowerLimit) + 1) ) + decadeLowerLimit ;	
		
		calculateResAndCapValues();
		
		fitnessValue = fitness();
	}
	
	public void calculateResAndCapValues() {
		R1 = a*100*Math.pow(10, a1); 
		if(R1 < 1000) R1 = 1000;
		if(R1 > 1000000) R1 = 1000000;
		
		R2 = b*100*Math.pow(10, b1);
		if(R2 < 1000) R2 = 1000;
		if(R2 > 1000000) R2 = 1000000;
		
		R3 = c*100*Math.pow(10, c1);
		if(R3 < 1000) R3 = 1000;
		if(R3 > 1000000) R3 = 1000000;
		
		R4 = d*100*Math.pow(10, d1);
		if(R4 < 1000) R4 = 1000;
		if(R4 > 1000000) R4 = 1000000;
		
		C1 = e*100*Math.pow(10, e1);
		if(C1 < 0) C1 = 1000;
		
		C2 = f*100*Math.pow(10, f1);
		if(C2 < 0) C2 = 1000;
		
		C3 = g*100*Math.pow(10, g1);
		if(C3 < 0) C3 = 1000;
		
		C4 = h*100*Math.pow(10, h1);
		if(C4 < 0) C4 = 1000;
	}
	
	public void updateFilter(double[] newChromosome) {
		a  = newChromosome[0];
		b = newChromosome[1];
		c  = newChromosome[2];
		d = newChromosome[3];
		e  = newChromosome[4];
		f = newChromosome[5] ;
		g = newChromosome[6];
		h = newChromosome[7];
		
		a1  = newChromosome[8];
		b1 = newChromosome[9];
		c1  = newChromosome[10];
		d1 = newChromosome[11];
		e1  = newChromosome[12];
		f1 = newChromosome[13];
		g1  = newChromosome[14] ;	
		h1 = newChromosome[15];
		
		calculateResAndCapValues();
		
		fitnessValue = fitness();
	}
	
	public double[] getChromosome() {
		double[] chromosome = {a, b, c, d, e, f, g, h, a1, b1, c1, d1, e1, f1, g1, h1}; 
		return chromosome;
	}
	
	public double calculateFrequency1() {
		double w = 1 / Math.sqrt(R1*R2*C1*Math.pow(10,-12)*C2*Math.pow(10, -12)) ;
		return w;
	}
	
	public double calculateQ1() {
		double Q = Math.sqrt(R1*R2*C1*Math.pow(10,-12)*C2*Math.pow(10, -12)) / ((R1+R2)*C1*Math.pow(10, -12)) ;
		return Q;
	}
	
	public double calculateFrequency2() {
		double w = 1 / Math.sqrt(R3*R4*C3*Math.pow(10,-12)*C4*Math.pow(10, -12)) ;
		return w;
	}
	
	public double calculateQ2() {
		double Q = Math.sqrt(R3*R4*C3*Math.pow(10,-12)*C4*Math.pow(10, -12)) / ((R3+R4)*C3*Math.pow(10, -12)) ;
		return Q;
	}
	
	public double fitness() {
		double wc1 = 1 / Math.sqrt(R1*R2*C1*Math.pow(10, -12)*C2*Math.pow(10, -12)) ;
		double wc2 = 1 / Math.sqrt(R3*R4*C3*Math.pow(10, -12)*C4*Math.pow(10, -12)) ;
		double deltaWc = ( Math.abs( wc1 - this.wc ) + Math.abs( wc2 - this.wc ) ) / this.wc;
		double deltaQ  = Math.abs(  (1/0.7654) - ( 1 / (wc1*(R1+R2)*C1*Math.pow(10, -12)) ) )+ Math.abs( (1/1.8478) - ( 1 / (wc2*(R3+R4)*C3*Math.pow(10, -12)) ) );
		double error = 0.5*deltaWc + 0.5*deltaQ;
		return error;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int compareTo(Filter f) {
		// TODO Auto-generated method stub
		return Double.compare(fitnessValue, f.fitnessValue);
	}

}
