package br.com.nava.coletadashboard;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.nava.coletadashboard.dao.NavaDao;
import br.com.nava.coletadashboard.model.DadosEventos;
import br.com.nava.coletadashboard.model.DadosResult1;
import br.com.nava.coletadashboard.model.DadosResult2;
import br.com.nava.coletadashboard.model.DadosResult3;
import br.com.nava.coletadashboard.model.DadosThresholdOfensores;

public class Coleta {

	public static void main(String[] args) {

		String meuDatabase = "flex4ExternalData";

		try {

			NavaDao navaDao = new NavaDao("com.mysql.jdbc.Driver", "jdbc:mysql://10.202.11.115", "flexvision",
					"f1l2e3x4", meuDatabase);

			navaDao.getTrsid();
			
			navaDao.truncateTable("flex4ExternalData.cache_eventos_producao_top10_thresholds");
			navaDao.truncateTable("flex4ExternalData.cache_top_ofensores_top10_thresholds");
			List<DadosEventos> dados = navaDao.getDados();

			Map<String, Integer> mapa = new HashMap<String, Integer>();

			for (DadosEventos dado : dados) {
				if (!mapa.containsKey(dado.getDescr())) {
					mapa.put(dado.getDescr(), 0);

				}

				mapa.put(dado.getDescr(), mapa.get(dado.getDescr()) + 1);

			}

			for (Map.Entry<String, Integer> each : mapa.entrySet()) {

				System.out.println(each.getKey() + " = " + each.getValue() );		
				navaDao.SalvaEventos(each.getKey(), each.getValue());
			}
			System.out.println("Eventos produção concluido");

			Map<String, Integer> mapaOfensores = new HashMap<String, Integer>();
			Map<String, Integer> mapaTrs = new HashMap<String, Integer>();
			List<DadosThresholdOfensores> dadosOfensores = navaDao.getCurrentThreshold();

			for (DadosThresholdOfensores dado : dadosOfensores) {

				if (!mapaOfensores.containsKey(dado.getDescr())) {
					mapaOfensores.put(dado.getDescr(), 0);
				}

				mapaOfensores.put(dado.getDescr(), mapaOfensores.get(dado.getDescr()) + 1);
				mapaTrs.put(dado.getDescr(), dado.getTrs_id());
			}
			

			for (Entry<String, Integer> each : mapaOfensores.entrySet()) {
				for (Entry<String, Integer> trs : mapaTrs.entrySet()) {

					if (each.getKey().equals(trs.getKey())) {

						navaDao.SalvaThresholdOfensores(trs.getValue(), each.getKey(), each.getValue());
//					System.out.println(trs.getValue()+" -  "+each.getKey()+"  - "+ each.getValue());
					}
				}
			}

			System.out.println("Thresholds ofensores concluido");

			navaDao.truncateTable("flex4ExternalData.cache_top_ofensores_result1");
			List<DadosResult1> dadosResult1 = navaDao.getResult1();

			for (DadosResult1 dado : dadosResult1) {

				navaDao.SalvaResult1(dado.getNod(), dado.getTrs(), dado.getIp(), dado.getDescr(), dado.getHost_name(),
						dado.getRoot_host_name(), dado.getQtd_pis_ent(), dado.getQtd_pis_sai(), dado.getT_grt15(),
						dado.getT_min15(), dado.getT_per1(), dado.getT_per2(), dado.getT_per3(), dado.getAvg_diff(),
						dado.getDt_max_ocurr(), dado.getTotal(), dado.getRow_number(), dado.getThreshold());
			}
			System.out.println("Primeiro result concluido");

			navaDao.truncateTable("flex4ExternalData.cache_top_ofensores_result2");
			List<DadosResult2> dadosResult2 = navaDao.getResult2();

			for (DadosResult2 dado : dadosResult2) {

				navaDao.SalvaResult2(dado.getT_min15(), dado.getT_grt15(), dado.getTrs_id(), dado.getDescr());
			}
			System.out.println("segundo result concluido");

			navaDao.truncateTable("flex4ExternalData.cache_top_ofensores_result3");
			List<DadosResult3> dadosResult3 = navaDao.getResult3();

			for (DadosResult3 dado : dadosResult3) {

				navaDao.SalvaResult3(dado.getT_prim(), dado.getT_sec(), dado.getT_ter(), dado.getTrs_id(),
						dado.getDescr());
			}
			System.out.println("terceiro result concluido");
			System.out.println("Operação finalizada");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
