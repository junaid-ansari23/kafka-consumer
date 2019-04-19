/**
 * 
 */
package kafka.example.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author juansari
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NgfKafkaModel {

	private String userName;
	private String terrName;
	private String terrType;
	private String salesRepNumber;
	private String derivedSegment1;
	private Date fpbDate;
	private String version;
	private String currencyCode;
	private Map<String,CurrencyMapModel[]> currencyMap;
	private String nodeLowestLevelFlag;
	private String lowestLevelFlag;
	private String systemUser;
	private Map<String,String> keyTypes;
	private List<NgfKeyModel> key1;
	private List<NgfKeyModel> key2;
	private List<String> opportunities;
	private List<NgfTeamListModel> teamList;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the terrName
	 */
	public String getTerrName() {
		return terrName;
	}
	/**
	 * @param terrName the terrName to set
	 */
	public void setTerrName(String terrName) {
		this.terrName = terrName;
	}
	/**
	 * @return the terrType
	 */
	public String getTerrType() {
		return terrType;
	}
	/**
	 * @param terrType the terrType to set
	 */
	public void setTerrType(String terrType) {
		this.terrType = terrType;
	}
	/**
	 * @return the salesRepNumber
	 */
	public String getSalesRepNumber() {
		return salesRepNumber;
	}
	/**
	 * @param salesRepNumber the salesRepNumber to set
	 */
	public void setSalesRepNumber(String salesRepNumber) {
		this.salesRepNumber = salesRepNumber;
	}
	/**
	 * @return the derivedSegment1
	 */
	public String getDerivedSegment1() {
		return derivedSegment1;
	}
	/**
	 * @param derivedSegment1 the derivedSegment1 to set
	 */
	public void setDerivedSegment1(String derivedSegment1) {
		this.derivedSegment1 = derivedSegment1;
	}
	/**
	 * @return the fpbDate
	 */
	public Date getFpbDate() {
		return fpbDate;
	}
	/**
	 * @param fpbDate the fpbDate to set
	 */
	public void setFpbDate(Date fpbDate) {
		this.fpbDate = fpbDate;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the nodeLowestLevelFlag
	 */
	public String getNodeLowestLevelFlag() {
		return nodeLowestLevelFlag;
	}
	/**
	 * @param nodeLowestLevelFlag the nodeLowestLevelFlag to set
	 */
	public void setNodeLowestLevelFlag(String nodeLowestLevelFlag) {
		this.nodeLowestLevelFlag = nodeLowestLevelFlag;
	}
	/**
	 * @return the lowestLevelFlag
	 */
	public String getLowestLevelFlag() {
		return lowestLevelFlag;
	}
	/**
	 * @param lowestLevelFlag the lowestLevelFlag to set
	 */
	public void setLowestLevelFlag(String lowestLevelFlag) {
		this.lowestLevelFlag = lowestLevelFlag;
	}
	/**
	 * @return the systemUser
	 */
	public String getSystemUser() {
		return systemUser;
	}
	/**
	 * @param systemUser the systemUser to set
	 */
	public void setSystemUser(String systemUser) {
		this.systemUser = systemUser;
	}	
	
	
	/**
	 * @return the key1
	 */
	public List<NgfKeyModel> getKey1() {
		return key1;
	}
	/**
	 * @param key1 the key1 to set
	 */
	public void setKey1(List<NgfKeyModel> key1) {
		this.key1 = key1;
	}
	/**
	 * @return the key2
	 */
	public List<NgfKeyModel> getKey2() {
		return key2;
	}
	/**
	 * @param key2 the key2 to set
	 */
	public void setKey2(List<NgfKeyModel> key2) {
		this.key2 = key2;
	}
	/**
	 * @return the opportunities
	 */
	public List<String> getOpportunities() {
		return opportunities;
	}
	/**
	 * @param opportunities the opportunities to set
	 */
	public void setOpportunities(List<String> opportunities) {
		this.opportunities = opportunities;
	}
	/**
	 * @return the teamList
	 */
	public List<NgfTeamListModel> getTeamList() {
		return teamList;
	}
	/**
	 * @param teamList the teamList to set
	 */
	public void setTeamList(List<NgfTeamListModel> teamList) {
		this.teamList = teamList;
	}
	/**
	 * @return the currencyMap
	 */
	public Map<String, CurrencyMapModel[]> getCurrencyMap() {
		return currencyMap;
	}
	/**
	 * @param currencyMap the currencyMap to set
	 */
	public void setCurrencyMap(Map<String, CurrencyMapModel[]> currencyMap) {
		this.currencyMap = currencyMap;
	}
	/**
	 * @return the keyTypes
	 */
	public Map<String, String> getKeyTypes() {
		return keyTypes;
	}
	/**
	 * @param keyTypes the keyTypes to set
	 */
	public void setKeyTypes(Map<String, String> keyTypes) {
		this.keyTypes = keyTypes;
	}
	
}
