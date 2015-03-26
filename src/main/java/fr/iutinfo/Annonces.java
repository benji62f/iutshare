package fr.iutinfo;

import java.util.Date;



public class Annonces {
	private int ano;
	private String loginProprio;
	private String msg;
	private Date date;
	private String titre;
	private String lieu;

	public Annonces(int ano,String loginProprio,String msg,Date date,String titre,String lieu){
		this.ano=ano;
		this.loginProprio=loginProprio;
		this.msg=msg;
		this.date=date;
		this.titre=titre;
		this.lieu=lieu;
	}

	public Annonces() {
		
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getLoginProprio() {
		return loginProprio;
	}

	public void setLoginProprio(String loginProprio) {
		this.loginProprio = loginProprio;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
}
	