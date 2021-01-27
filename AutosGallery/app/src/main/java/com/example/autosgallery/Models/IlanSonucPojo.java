package com.example.autosgallery.Models;

public class IlanSonucPojo{
	private boolean tf;
	private int uyeid;
	private int ilanid;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public int getUyeid() {
		return uyeid;
	}

	public void setUyeid(int uyeid) {
		this.uyeid = uyeid;
	}

	public int getIlanid() {
		return ilanid;
	}

	public void setIlanid(int ilanid) {
		this.ilanid = ilanid;
	}

	@Override
	public String toString() {
		return "IlanSonucPojo{" +
				"tf=" + tf +
				", uyeid=" + uyeid +
				", ilanid=" + ilanid +
				'}';
	}
}
