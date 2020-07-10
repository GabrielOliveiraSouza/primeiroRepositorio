package br.com.nava.coletadashboard.model;

import java.sql.Date;

public class DadosResult1 {
	
	private Integer nod, trs, qtd_pis_ent, qtd_pis_sai, t_grt15, t_min15, t_per1,
	t_per2, t_per3, total, row_number, threshold ;
	
	private Float avg_diff ;
	
	private String ip, descr, host_name, root_host_name;
	private String dt_max_ocurr ;
	
	public String getDt_max_ocurr() {
		return dt_max_ocurr;
	}
	public void setDt_max_ocurr(String dt_max_ocurr) {
		this.dt_max_ocurr = dt_max_ocurr;
	}
	public Integer getNod() {
		return nod;
	}
	public void setNod(Integer nod) {
		this.nod = nod;
	}
	public Integer getTrs() {
		return trs;
	}
	public void setTrs(Integer trs) {
		this.trs = trs;
	}
	public Integer getQtd_pis_ent() {
		return qtd_pis_ent;
	}
	public void setQtd_pis_ent(Integer qtd_pis_ent) {
		this.qtd_pis_ent = qtd_pis_ent;
	}
	public Integer getQtd_pis_sai() {
		return qtd_pis_sai;
	}
	public void setQtd_pis_sai(Integer qtd_pis_sai) {
		this.qtd_pis_sai = qtd_pis_sai;
	}
	public Integer getT_grt15() {
		return t_grt15;
	}
	public void setT_grt15(Integer t_grt15) {
		this.t_grt15 = t_grt15;
	}
	public Integer getT_min15() {
		return t_min15;
	}
	public void setT_min15(Integer t_min15) {
		this.t_min15 = t_min15;
	}
	public Integer getT_per1() {
		return t_per1;
	}
	public void setT_per1(Integer t_per1) {
		this.t_per1 = t_per1;
	}
	public Integer getT_per2() {
		return t_per2;
	}
	public void setT_per2(Integer t_per2) {
		this.t_per2 = t_per2;
	}
	public Integer getT_per3() {
		return t_per3;
	}
	public void setT_per3(Integer t_per3) {
		this.t_per3 = t_per3;
	}
	public Float getAvg_diff() {
		return avg_diff;
	}
	public void setAvg_diff(Float avg_diff) {
		this.avg_diff = avg_diff;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRow_number() {
		return row_number;
	}
	public void setRow_number(Integer row_number) {
		this.row_number = row_number;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getRoot_host_name() {
		return root_host_name;
	}
	public void setRoot_host_name(String root_host_name) {
		this.root_host_name = root_host_name;
	}
	
	
	

}
