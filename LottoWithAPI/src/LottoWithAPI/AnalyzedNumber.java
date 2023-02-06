package LottoWithAPI;

public class AnalyzedNumber implements Comparable<AnalyzedNumber> {

	int num;
	int count;
	double difference;
	double percent;
	
	public AnalyzedNumber(int num, int count, double difference) {
		this.num = num;
		this.count = count;
		this.difference = difference;
		percent = -1;
	}
	
	public int getNum() {
		return num;
	}
	public int getCount() {
		return count;
	}
	
	public double getDiffrence() {
		return difference;
	}
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public double getPercent() {
		return percent;
	}

	@Override
	public int compareTo(AnalyzedNumber o) {
		
		int count = o.getCount();
		if(this.count > count) {
			return 1;
		} else if (this.count < count) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public String toString() {
		return (num + " \t count : " + count + " \t difference : " + difference + " \t percent : " + percent);
	}
	
}
