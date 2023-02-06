package LottoWithAPI;

public class ND {

	String  date;
	int[] drwt;
	int drwNo, bnusNo;
	
	public ND(String date, int n1, int n2, int n3, int n4, int n5, int n6, int bnus, int drwNo) {
		this.date = date;
		this.drwt = new int[] {n1, n2, n3, n4, n5, n6};
		this.bnusNo = bnus;
		this.drwNo = drwNo;
	}
	
	public int[] getNumbers() {
		return (drwt.clone());
	}
	
	public int getBnusNo() {
		return bnusNo;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getDrwNo() {
		return drwNo;
	}
	
	public String toString() {
		return (drwNo + " : " + date + " : " + drwt[0] + ", " + drwt[1] + ", " +  drwt[2] + ", " + drwt[3] + ", " + drwt[4] + ", " + drwt[5] + ", " + bnusNo);
	}
	
}
