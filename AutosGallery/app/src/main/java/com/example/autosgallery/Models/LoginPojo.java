package com.example.autosgallery.Models;

public class LoginPojo {
	private Object kadi;
	private Object id;

	public void setKadi(Object kadi){
		this.kadi = kadi;
	}

	public Object getKadi(){
		return kadi;
	}

	public void setId(Object id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"LoginPojo{" + 
			"kadi = '" + kadi + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
