package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {

	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return rilevamenti;
		
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		
		List<Rilevamento> rilevamentiLocalitaMese = new ArrayList<Rilevamento>();
		
		final String sql = "SELECT localita, data, umidita " + 
				"FROM situazione " + 
				"WHERE Localita = ? AND MONTH(DATA) = ? ";
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, localita);
			st.setInt(2, mese);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				
				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamentiLocalitaMese.add(r);
			}

			conn.close();
		

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return rilevamentiLocalitaMese;
	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {
		
		double media=0.0;
		
		final String sql = "SELECT AVG(umidita) AS mediacitta " + 
				"FROM situazione " + 
				"WHERE MONTH(DATA)= ? AND Localita = ? ";
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, mese);
			st.setString(2, localita);
			
			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				media=rs.getDouble("mediacitta");
				
			}

			conn.close();
		

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
				
		return media;
	}

}
