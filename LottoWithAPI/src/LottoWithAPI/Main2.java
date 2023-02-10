package LottoWithAPI;

import java.io.IOException;
import java.util.List;

public class Main2 {

	public static void main(String[] args) throws IOException {

		DBManager dbManager = new DBManager();
		
		//dbManager.printData();
		Analyzer analyzer = new Analyzer(dbManager.getData());
		analyzer.calc();
		
		List<AnalyzedNumber> list = analyzer.getAnalyzedData();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			System.out.println(list.get(i));
		}
		
	}

}
