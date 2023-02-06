package LottoWithAPI;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	
	int total, diffSum;
	double average;
	
	int[] count;
	double[] difference;
	
	List<AnalyzedNumber> list;
	List<ND> sample;

	public Analyzer(List<ND> sample) {
		this.sample = sample;
		total = 0;
		diffSum = 0;
		list = new ArrayList<AnalyzedNumber>();
		count = new int[45];
		difference = new double[45];
	}
	
	public void calc() {
		counting();
		calcAverage();
		calcDifference();
		
		for(int i = 0; i < 45; i++) {
			list.add(new AnalyzedNumber((i + 1), count[i], difference[i]));
		}
	}
	
	public void counting() {
		
		int size = sample.size();
		this.total = size;
		for(int i = 0; i < size; i++) {
			ND nd = sample.get(i);
			
			int[] nums = nd.getNumbers();
			int bnus = nd.getBnusNo();
			
			for(int j = 0; j < 6; j++) {
				count[nums[j] - 1]++;
			}
			count[bnus - 1]++;
		}
	}
	
	public void calcAverage() {
		int length = count.length;
		int sum = 0;
		for(int i = 0; i < length; i++) {
			sum += count[i];
		}
		average = sum / 45;
	}
	
	public void calcDifference() {
		int length = difference.length;
		for(int i = 0; i < length; i++) {
			difference[i] = Math.abs(count[i] - average);
			diffSum += Math.abs(count[i] - average);
		}
	}
	
	public void printCount() {
		int length = count.length;
		for(int i = 0; i < length; i++) {
			System.out.println((i + 1)  + " : " + count[i]);
		}
		System.out.println("total : " + total);
		System.out.println("average : " + average);
	}
	
	public void printDiffrence() {
		int length = difference.length;
		for(int i = 0; i < length; i++) {
			System.out.println((i + 1)  + " : " + difference[i]);
		}
		System.out.println("diffSum : " + diffSum);
	}
	
	/**
	 * This function analyze 
	 */
	public void analyze1() {
		
	}
	
	public void analyze2() {
		
	}
	
	public List<AnalyzedNumber> getAnalyzedData() {
		return list;
	}
	
	public int getTotal() {
		return total;
	}
	
	public Double getAverage() {
		return average;
	}
	
	public int diffSum( ) {
		return diffSum;
	}
	
	
}
