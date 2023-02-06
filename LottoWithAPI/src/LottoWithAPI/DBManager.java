package LottoWithAPI;

import java.io.IOException;
import java.util.List;

public class DBManager {

	List<ND> list;
	JSParser jsParser = new JSParser();
	TXTParser txtParser = new TXTParser();
	
	public DBManager() throws IOException {
		jsParser = new JSParser();
		txtParser = new TXTParser();
		list = txtParser.parse();
	}
	
	public void printData() {
		for(int i = 0; i < list.size(); i++) {
			ND nd = list.get(i);
			int[] nums = nd.getNumbers();
			System.out.print(nd.getDrwNo() + " : " + nd.getDate() + " : ");
			System.out.println(nums[0] + ", " + nums[1] + ", " + nums[2] + ", " + nums[3] + ", " + nums[4] + ", " + nums[5] + ", " + nd.getBnusNo());
		}
	}
	
	public List<ND> getData() {
		return list;
	}
	
	
}
