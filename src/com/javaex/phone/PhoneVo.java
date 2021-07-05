package com.javaex.phone;

public class PhoneVo {
	
	//field
	private String personId;
	private String personName;
	private String personHp;
	private String personCom;
	
	
	//constructor
	
	public PhoneVo(String personId, String personName, String personHp, String personCom) {
		this.personId = personId;
		this.personName = personName;
		this.personHp = personHp;
		this.personCom = personCom;
	}
	
	public PhoneVo(String personName,String personHp, String personCom) {
		this.personName = personName;
		this.personHp = personHp;
		this.personCom = personCom;
	}

	public PhoneVo() {
	
	}

	
	//method g/s
	
	public String getPersonId() {
		return personId;
	}
	
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getPersonHp() {
		return personHp;
	}
	
	public void setPersonHp(String personHp) {
		this.personHp = personHp;
	}
	
	public String getPersonCom() {
		return personCom;
	}
	
	public void setPersonCom(String personCom) {
		this.personCom = personCom;
	}

	
	//method
	
	@Override
	public String toString() {
		return "PhoneVo [personId=" + personId + ", personName=" + personName + ", personHp=" + personHp
				+ ", personCom=" + personCom + "]";
	}
	
	
}
