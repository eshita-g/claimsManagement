package com.cognizant.cms.model;


public class Claim {
private String claimid;
private String memid;
public Claim(String string, String string2) {
	// TODO Auto-generated constructor stub
	 claimid=string;
	 memid=string2;
}
public String getClaimid() {
	return claimid;
}
public void setClaimid(String claimid) {
	this.claimid = claimid;
}
public String getMemid() {
	return memid;
}
public void setMemid(String memid) {
	this.memid = memid;
}

}
