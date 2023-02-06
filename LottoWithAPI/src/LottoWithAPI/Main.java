package LottoWithAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		DBManager dbManager = new DBManager();
		
		//dbManager.printData();
		Analyzer analyzer = new Analyzer(dbManager.getData());
		analyzer.calc();
		
		List<AnalyzedNumber> list = analyzer.getAnalyzedData();
		int size = list.size();
		
//		for(int i = 0; i < size; i++) {
//			System.out.println(list.get(i));
//		}
		
		for(int i = 0; i < size; i++) {
			int min = i;
			
			for(int j = i + 1; j < size; j++) {
				if(list.get(j).compareTo(list.get(min)) < 0) {
					min = j;
				}
			}
			AnalyzedNumber temp = list.get(min);
			list.set(min, list.get(i));
			list.set(i, temp);
		}
//		System.out.println("=================================");
//		for(int i = 0; i < size; i++) {
//			System.out.println(list.get(i));
//		}
		
		double per = 1 / 1035d;
		for(int i = 1, v = 45; v > 0; i++, v--) {
			AnalyzedNumber n = list.get(v - 1);
			n.setPercent(per * i);
		}
		
		System.out.println("=================================");
		for(int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
		
		
		int sampleCnt = 40000000;
		
		List<int[]> numbers = new ArrayList<>();
		System.out.println("=================================");
		for(int i = 0; i < sampleCnt; i++) {
			int[] num = new int[6];
			for(int j = 0; j < 6; j++) {
				double sum = 0;
				double p = Math.random();
				//System.out.println("p = " + p);
				for(int k = 0; k < 45; k++) {
					sum += list.get(k).getPercent();
					if(sum >= p) {
						num[j] = list.get(k).getNum();
						break;
					}
				}
			}
			Arrays.sort(num);
			numbers.add(num);
			//System.out.println("[" + num[0] + ", " + num[1] + ", " + num[2] + ", " + num[3] + ", " + num[4] + ", " + num[5] + "]");
		}
		
		List<int[]> ranNumbers = new ArrayList<>();
		for(int i = 0; i < sampleCnt; i++) {
			int[] num = new int[6];
			for(int j = 0; j < 6; j++) {
				num[j] = (int) ((Math.random() * 45) + 1);
			}
			Arrays.sort(num);
			ranNumbers.add(num);
		}
		
		
		int[] sam1047 = new int[] {2, 20, 33, 40, 42, 44, 32};
		
		int[] nRank = new int[6];
		int[] rRank = new int[6];
		for(int i = 0; i < sampleCnt; i++) {
			nRank[judge(numbers.get(i), sam1047) - 1]++;
			rRank[judge(ranNumbers.get(i), sam1047) - 1]++;
		}
		
		System.out.println("nRank : " + nRank[0] + ", " +  nRank[1] + ", " +  nRank[2] + ", " +  nRank[3] + ", " +  nRank[4] + ", " +  nRank[5]);
		System.out.println("rRank : " + rRank[0] + ", " +  rRank[1] + ", " +  rRank[2] + ", " +  rRank[3] + ", " +  rRank[4] + ", " +  rRank[5]);
		
//		analyzer.printCount();
//		analyzer.printDiffrence();
		
	}
	
	public static int judge(int[] sample, int[] correct) {
		
		int cnt = 0;
		boolean bnus = false;
		for(int i = 0; i < 6; i++) {
			if(sample[i] == correct[i]) {
				cnt++;
			}
		}
		
		if(cnt == 6) {
			return 1;
		}
		
		for(int i = 0; i < 6; i++) {
			if(sample[i] == correct[6]) {
				bnus = true;
			}
		}
		
		if(cnt == 5 && bnus) {
			return 2;
		} else if (cnt == 5) {
			return 3;
		} else if (cnt== 4) {
			return 4;
		} else if (cnt == 3) {
			return 5;
		} else {
			return 6;
		}
	}

}
	