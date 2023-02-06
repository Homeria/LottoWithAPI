package LottoWithAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LottoAPITest {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, ParseException {
		
		List<ND> list = new ArrayList<ND>();
		
		File file = new File("src/LottoWithAPI/data.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		PrintWriter writer = new PrintWriter(fw);
		
		for(int i = 1; i <= 1050; i++) {
			
			String s = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + i;
			
			
			URL url = new URL(s);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuffer sb = new StringBuffer();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	            sb.append("\n");
	        }
	        rd.close();
	        conn.disconnect();
	        
	        String data = sb.toString();
	        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObj = (JSONObject) jsonParser.parse(data);
	        
	        String returnValue = (String) jsonObj.get("returnValue");
	        if(returnValue.equals("fail")) {
	        	break;
	        }
	        String date = (String) jsonObj.get("drwNoDate");
	        int drwNo = Integer.parseInt(String.valueOf(jsonObj.get("drwNo")));
	        int bnusNo = Integer.parseInt(String.valueOf(jsonObj.get("bnusNo")));
	        int n1 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo1")));
	        int n2 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo2")));
	        int n3 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo3")));
	        int n4 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo4")));
	        int n5 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo5")));
	        int n6 = Integer.parseInt(String.valueOf(jsonObj.get("drwtNo6")));
	        ND nd = new ND(date, n1, n2, n3, n4, n5, n6, bnusNo, drwNo);
	        list.add(nd);
	        System.out.println(nd);
	        writer.append(nd.toString() + "\n");
	        
	        
	        
		}
		writer.close();
		
        
        
        
		
	}
	
}
