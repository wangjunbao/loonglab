package org.loonglab.demo;

public class ST {
	
	double rate;
	
	
	
	public ST(double rate) {
		super();
		this.rate = rate;
	}

	public double calcTotalGain(int year){
		if(year==1)
			return 1*rate;
		
		return calcTotalGain(year-1)+(1+calcTotalGain(year-1))*rate;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 5; i++) {
			double rate=0.1+i*0.05;
			ST st=new ST(rate);
			System.out.println("gain("+rate+")="+st.calcTotalGain(8));
		}
		
//		ST st=new ST(0.25);
//		System.out.println(st.calcTotalGain(8));
	}
}
