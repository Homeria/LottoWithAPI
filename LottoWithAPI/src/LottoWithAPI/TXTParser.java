package LottoWithAPI;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TXTParser {

	public TXTParser() {
		
	}
	
	public List<ND> parse() throws IOException {
		
		List<ND> list = new ArrayList<>();
		
		File file = new File("src/LottoWithAPI/data.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		Scanner scanner = new Scanner(file);
		 
        while (scanner.hasNext()) {
            String s1 = scanner.nextLine();
            String[] s2 = s1.split(" : ");
            
            
            int drwNo = Integer.parseInt(s2[0]);
            String date = s2[1];
            String[] s3 = s2[2].split(", ");
            int n1 = Integer.parseInt(s3[0]);
            int n2 = Integer.parseInt(s3[1]);
            int n3 = Integer.parseInt(s3[2]);
            int n4 = Integer.parseInt(s3[3]);
            int n5 = Integer.parseInt(s3[4]);
            int n6 = Integer.parseInt(s3[5]);
            int bnusNo = Integer.parseInt(s3[6]);
            ND nd = new ND(date, n1, n2, n3, n4, n5, n6, bnusNo, drwNo);
            list.add(nd);
        }
        
        scanner.close();
		
		return list;
	}
	
}
