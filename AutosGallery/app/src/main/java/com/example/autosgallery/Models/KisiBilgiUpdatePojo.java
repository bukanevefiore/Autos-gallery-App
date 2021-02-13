package com.example.autosgallery.Models;

public class KisiBilgiUpdatePojo{
	private boolean tf;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"KisiBilgiUpdatePojo{" + 
			"tf = '" + tf + '\'' + 
			"}";
		}
}
