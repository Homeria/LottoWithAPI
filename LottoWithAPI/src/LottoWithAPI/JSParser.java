package LottoWithAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSParser {
	
	public JSParser() {
		
	}
	
	
	/**W
	 * 
	 * @param dn The Number what you want to parse Data Number
	 * @return drwNo, Date, Numbers
	 * @throws IOException
	 * @throws ParseException
	 */
	public ND parse(int dn) throws IOException, ParseException {
		
		String s = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + dn;
		
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
        	return null;
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
        System.out.println("[SYSTEM] This data is parsed : [" + nd + "]");
		
		return nd;
	}

}
