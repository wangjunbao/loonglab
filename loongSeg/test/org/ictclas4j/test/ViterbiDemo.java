package org.ictclas4j.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.ictclas4j.bean.MidResult;

public class ViterbiDemo {
	
	
	private String[] states = {"Rainy", "Sunny"};
	 
	private String[] observations = {"walk", "shop", "clean"};
	 
	private double[] start_probability = {0.6,0.4};
	 
	private double[][] transition_probability = {{0.7,0.3},{0.4,0.6}};
	 
	private double[][] emission_probability = {
	   {0.1, 0.4, 0.5},
	   {0.6, 0.3, 0.1},
	};

	
	



	public MidResult forward(String[] obs){
		Map<String, MidResult> T=new HashMap<String, MidResult>();
//		for (String state : states) {
//			T.put(state, new MidResult(start_probability[getStateIndex(state)],state,start_probability[getStateIndex(state)]));
//		}
		
		int i=0;
		for (String output : obs) {
			Map<String, MidResult> U=new HashMap<String, MidResult>();
			for (String nextState : states) {
				double total=0;
				String argmax="";
				double valmax=0;
				if(i==0){
					double p=emission_probability[getStateIndex(nextState)][getObIndex(output)]*start_probability[getStateIndex(nextState)];
					total=p;
					argmax=nextState;
					valmax=p;
				}
				else{
					for (String sourceState : states) {
						MidResult mr=T.get(sourceState);
						double p=emission_probability[getStateIndex(nextState)][getObIndex(output)]
						                                                        *transition_probability[getStateIndex(sourceState)][getStateIndex(nextState)];
						mr.prob *=p;
						mr.vProb *=p;
						total=total+mr.prob;
						
						if(mr.vProb>valmax){
							argmax=mr.vPath+","+nextState;
							valmax=mr.vProb;
						}

					}
				}
				
				
				U.put(nextState, new MidResult(total,argmax,valmax));
			}
						
			T=U;
			
			i++;

		}
		
		   
		double total=0;
		String argmax="";
		double valmax=0;
		for (String state : states) {
			MidResult mr=T.get(state);
			total+=mr.prob;
			if(mr.vProb>valmax){
				argmax=mr.vPath;
				valmax=mr.vProb;
			}
			
			
		}

		return new MidResult(total,argmax,valmax);
    }
	
	class MidResult{
		double prob;
		String vPath;
		double vProb;
		public MidResult(double total, String argmax, double valmax) {
			super();
			this.prob = total;
			this.vPath = argmax;
			this.vProb = valmax;
		}
		
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this);
		}
	};
	
	public int getStateIndex(String state){
		for (int i = 0; i < states.length; i++) {
			if(state.equals(states[i]))
				return i;
		}
		
		return -1;
	}
	
	public int getObIndex(String ob){
		for (int i = 0; i < observations.length; i++) {
			if(ob.equals(observations[i]))
				return i;
		}
		
		return -1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ViterbiDemo demo=new ViterbiDemo();
		String[] obs=new String[]{"walk","shop","clean"};
		System.out.println(demo.forward(obs));

	}

}
