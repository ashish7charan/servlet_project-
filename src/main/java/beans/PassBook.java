package beans;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class PassBook implements Serializable {
	private int sno;
	private String tType;
	private Date tDate;
	private String tTime;
	private int amount;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	private float rBalance;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String gettType() {
		return tType;
	}
	public void settType(String tType) {
		this.tType = tType;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	public String gettTime() {
		return tTime;
	}
	public void settTime(String tTime) {
		this.tTime = tTime;
	}
	public float getrBalance() {
		return rBalance;
	}
	public void setrBalance(float rBalance) {
		this.rBalance = rBalance;
	}
	
	

}
