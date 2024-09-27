package com.bdd.automation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RunTimeTestData {
	private Map<String,String> runTimeDataMap=new HashMap<String,String>();
	private  Map<String,Object> runTimeDataMapObject=new HashMap<String,Object>();
	private  List<String>  runTimeDataList=new ArrayList<String>();
	private List<HashMap<String,String>> runTimeDataListMap=new ArrayList<HashMap<String,String>>();
	public Map<String, String> getRunTimeDataMap() {
		return runTimeDataMap;
	}
	public void setRunTimeDataMap(Map<String, String> runTimeDataMap) {
		this.runTimeDataMap = runTimeDataMap;
	}
	public Map<String, Object> getRunTimeDataMapObject() {
		return runTimeDataMapObject;
	}
	public void setRunTimeDataMapObject(Map<String, Object> runTimeDataMapObject) {
		this.runTimeDataMapObject = runTimeDataMapObject;
	}
	public List<String> getRunTimeDataList() {
		return runTimeDataList;
	}
	public void setRunTimeDataList(List<String> runTimeDataList) {
		this.runTimeDataList = runTimeDataList;
	}
	public List<HashMap<String, String>> getRunTimeDataListMap() {
		return runTimeDataListMap;
	}
	public void setRunTimeDataListMap(List<HashMap<String, String>> runTimeDataListMap) {
		this.runTimeDataListMap = runTimeDataListMap;
	}
	
}
