package br.com.nava.coletadashboard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import br.com.nava.coletadashboard.model.DadosEventos;
import br.com.nava.coletadashboard.model.DadosResult1;
import br.com.nava.coletadashboard.model.DadosResult2;
import br.com.nava.coletadashboard.model.DadosResult3;
import br.com.nava.coletadashboard.model.DadosThresholdOfensores;

public class NavaDao {

	private Connection connection;
	public String database;

	public NavaDao(String driver, String url, String user, String password, String database)
			throws ClassNotFoundException, SQLException {

		Class.forName(driver);
		connection = DriverManager.getConnection(url + "/" + database + "?useSSL=false", user, password);
		this.database = database;
	}

	/* @method Coleta os dados da Proc de thresholds do mes de eventos */
	public List<DadosEventos> getDados() {
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) + 1;
		List<DadosEventos> dados = new ArrayList<DadosEventos>();

		String sql = "call flex4ExternalData.pr_dashboard_eventos_producao('alarms_month', null, '" + ano + "-" + mes
				+ "-01 03:00', now());";
		try {

			PreparedStatement pst = this.connection.prepareStatement(sql);

			ResultSet result = pst.executeQuery();

			if (result != null) {
				while (result.next()) {

					DadosEventos dado = new DadosEventos();

					dado.setDescr(result.getString("descr"));

					dados.add(dado);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dados;
	}

	/*
	 * @method Coleta os 3 primeiros trs_ids dos thresholds para servir de parametro
	 * na proc flex4ExternalData.pr_dashboard_top_ofensores_info2
	 */

	Integer[] trsId = new Integer[3];

	public List<DadosThresholdOfensores> getTrsid() {

		String sql = "SELECT * FROM flex4ExternalData.cache_top_ofensores_top10_thresholds order by valor desc limit 3;";
		List<DadosThresholdOfensores> trsIds = new ArrayList();
		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);
			ResultSet result = pst.executeQuery();
			int i = 0;
			while (result.next()) {

				DadosThresholdOfensores dado = new DadosThresholdOfensores();
				dado.setTrs_id(result.getInt("trs_id"));
				trsIds.add(dado);
				trsId[i] = dado.getTrs_id();
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trsIds;
	}

	

	public List<DadosResult1> getResult1() {

		String sql = "call flex4ExternalData.pr_dashboard_top_ofensores_info2('" + trsId[0] + " , " + trsId[1] + " , "
				+ trsId[2]
				+ " ', date_add(date_add(date_add(now(), interval -1 month), interval 1 day), interval -3 hour), date_add(now(), interval 3 hour));";

		List<DadosResult1> listResult1 = new ArrayList();
		try {
			java.sql.CallableStatement pst = this.connection.prepareCall(sql);
			
			ResultSet result = pst.executeQuery();

			result = pst.getResultSet();

			if (result != null) {
				while (result.next()) {

					DadosResult1 dado = new DadosResult1();

					dado.setNod(result.getInt("nod"));
					dado.setTrs(result.getInt("trs"));
					dado.setIp(result.getString("ip"));
					dado.setDescr(result.getString("descr"));
					dado.setHost_name(result.getString("host_name"));
					dado.setRoot_host_name(result.getString("root_host_name"));
					dado.setQtd_pis_ent(result.getInt("qtd_pis_ent"));
					dado.setQtd_pis_sai(result.getInt("qtd_pis_sai"));
					dado.setT_grt15(result.getInt("t_grt15"));
					dado.setT_min15(result.getInt("t_min15"));
					dado.setT_per1(result.getInt("t_per1"));
					dado.setT_per2(result.getInt("t_per2"));
					dado.setT_per3(result.getInt("t_per3"));
					dado.setAvg_diff(result.getFloat("avg_diff"));
					dado.setDt_max_ocurr(result.getString("dt_max_ocurr"));
					dado.setTotal(result.getInt("total"));
					dado.setRow_number(result.getInt("row_number"));
					dado.setThreshold(result.getInt("threshold"));

					listResult1.add(dado);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listResult1;
	}

	public List<DadosResult2> getResult2() {

		String sql = "call flex4ExternalData.pr_dashboard_top_ofensores_info2('" + trsId[0] + " , " + trsId[1] + " , "
				+ trsId[2]
				+ " ', date_add(date_add(date_add(now(), interval -1 month), interval 1 day), interval -3 hour), date_add(now(), interval -3 hour));";

		List<DadosResult2> listResult2 = new ArrayList();

		try {
			java.sql.CallableStatement pst = this.connection.prepareCall(sql);

			ResultSet result = pst.executeQuery();
			pst.getMoreResults();
			result = pst.getResultSet();

			if (result != null) {
				while (result.next()) {

					DadosResult2 dado = new DadosResult2();

					dado.setT_min15(result.getInt("t_min_15"));
					dado.setT_grt15(result.getInt("t_grt_15"));
					dado.setTrs_id(result.getInt("trs_id"));
					dado.setDescr(result.getString("descr"));

					listResult2.add(dado);

				}
				result.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listResult2;
	}

	public List<DadosResult3> getResult3() {

		String sql = "call flex4ExternalData.pr_dashboard_top_ofensores_info2('" + trsId[0] + " , " + trsId[1] + " , "
				+ trsId[2]
				+ " ', date_add(date_add(date_add(now(), interval -1 month), interval 1 day), interval -3 hour), date_add(now(), interval -3 hour));";

		List<DadosResult3> listResult3 = new ArrayList();

		try {
			java.sql.CallableStatement pst = this.connection.prepareCall(sql);

			ResultSet result = pst.executeQuery();

			pst.getMoreResults();
			pst.getMoreResults();
			result = pst.getResultSet();

			if (result != null) {
				while (result.next()) {

					DadosResult3 dado = new DadosResult3();
					dado.setT_prim(result.getInt("t_prim"));
					dado.setT_sec(result.getInt("t_sec"));
					dado.setT_ter(result.getInt("t_ter"));
					dado.setTrs_id(result.getInt("trs_id"));
					dado.setDescr(result.getString("descr"));
					listResult3.add(dado);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listResult3;
	}

	public List<DadosThresholdOfensores> getCurrentThreshold() {

		List<DadosThresholdOfensores> listThreshold = new ArrayList();

		String sql = "call flex4ExternalData.pr_dashboard_top_ofensores('alarms_month', date_add(now(), interval -1 month), now());";
		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);

			ResultSet result = pst.executeQuery();
			if (result != null) {
				while (result.next()) {

					DadosThresholdOfensores dado = new DadosThresholdOfensores();

					dado.setDescr(result.getString("descr"));
					dado.setTrs_id(result.getInt("trs_id"));

					listThreshold.add(dado);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listThreshold;
	}

	public void SalvaEventos(String descr, Integer valor) {

		String sql = "replace into cache_eventos_producao_top10_thresholds values (?,?);";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);
			pst.setString(1, descr);
			pst.setInt(2, valor);

			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SalvaResult1(Integer nod, Integer trs, String ip, String descr, String host_name, String root_host_name,
			Integer qtd_pis_ent, Integer qtd_pis_sai, Integer t_grt15, Integer t_min15, Integer t_per1, Integer t_per2,
			Integer t_per3, Float avg_diff, String dt_max_ocurr, Integer total, Integer row_number, Integer threshold) {

		String sql = "replace into  cache_top_ofensores_result1 values (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);
			pst.setInt(1, nod);
			pst.setInt(2, trs);
			pst.setString(3, ip);
			pst.setString(4, descr);
			pst.setString(5, host_name);
			pst.setString(6, root_host_name);
			pst.setInt(7, qtd_pis_ent);
			pst.setInt(8, qtd_pis_sai);
			pst.setInt(9, t_grt15);
			pst.setInt(10, t_min15);
			pst.setInt(11, t_per1);
			pst.setInt(12, t_per2);
			pst.setInt(13, t_per3);
			pst.setFloat(14, avg_diff);
			pst.setString(15, dt_max_ocurr);
			pst.setInt(16, total);
			pst.setInt(17, row_number);
			pst.setInt(18, threshold);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SalvaResult2(Integer t_min15, Integer t_grt15, Integer trs_id, String descr) {
		String sql = "replace into cache_top_ofensores_result2 values (?, ?, ?, ?)";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);

			pst.setInt(1, t_min15);
			pst.setInt(2, t_grt15);
			pst.setInt(3, trs_id);
			pst.setString(4, descr);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SalvaResult3(Integer t_prim, Integer t_sec, Integer t_ter, Integer trs_id, String descr) {
		String sql = "replace into cache_top_ofensores_result3 values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);

			pst.setInt(1, t_prim);
			pst.setInt(2, t_sec);
			pst.setInt(3, t_ter);
			pst.setInt(4, trs_id);
			pst.setString(5, descr);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SalvaThresholdOfensores(Integer trs, String descr, Integer valor) {

		String sql = "replace into cache_top_ofensores_top10_thresholds values (?,?,?);";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);
			pst.setInt(1, trs);
			pst.setString(2, descr);
			pst.setInt(3, valor);

			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void truncateTable(String table) {

		String sql = "truncate table " + table + " ;";

		try {
			PreparedStatement pst = this.connection.prepareStatement(sql);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
