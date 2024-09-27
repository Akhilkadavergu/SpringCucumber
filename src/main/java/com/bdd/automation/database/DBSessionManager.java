package com.bdd.automation.database;

import org.springframework.stereotype.Component;

import com.bdd.automation.scenarioTest.AbstractBeans;

//@Component
public class DBSessionManager extends AbstractBeans {

//	private JdbcTemplate jdbcTemplate;
//
//	public Integer executeQueryForInt(String query) throws SQLException {
//		Integer result = jdbcTemplate.queryForObject(query, Integer.class);
//		return result;
//	}
//
//	public String executeQueryForString(String query) throws SQLException {
//		String result = jdbcTemplate.queryForObject(query, String.class);
//		return result;
//	}
//
//	public Boolean executeQueryForBoolean(String query) throws SQLException {
//		Boolean result = jdbcTemplate.queryForObject(query, Boolean.class);
//		return result;
//	}
//
//	public <T> List<?> executeQueryForListForSingleColumn(String query, Class T) throws SQLException {
//		List<?> listType = jdbcTemplate.queryForList(query, T);
//		return listType;
//	}
//
//	public Map<String, Object> executeQueryForMapForSingleRow(String query) throws SQLException {
//		Map<String, Object> mapType = jdbcTemplate.queryForMap(query);
//		return mapType;
//
//	}
//
//	public List<HashMap<String, String>> executeQueryForMapForMultipleRow (String query) throws SQLException {
//		List<HashMap<String, String>> mapData = jdbcTemplate.query(query, new ResultSet Extractor<List<HashMap<String, String>>>(){
//
//	@Override
//		public List<HashMap<String, String>> extractData(ResultSet rs) throws SQLException, DataAccess Exception {
//		ResultSetMetaData metadata = rs.getMetaData();
//		int clmCnt= metadata.getColumnCount();
//		List<String> columnHeader = new ArrayList<String>();
//		List<HashMap<String, String>> queryData = new ArrayList<HashMap<String, String>>();
//		for (int i = 1; i <= clmCnt; i++) {
//		columnHeader.add(metadata.getColumnName(i));
//		}
//		}
//		});return mapData;}while(rs.next()){
//
//	HashMap<String, String> eachRowMap = new HashMap<>();for(
//	int i = 1;i<=clmCnt;i++)
//	{
//	}eachRowMap.put(columnHeader.get(i-1),rs.getString(i));queryData.add(eachRowMap);
//}return queryData;}
}