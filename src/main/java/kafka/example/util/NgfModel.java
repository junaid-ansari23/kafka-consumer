/**
 * 
 */
package kafka.example.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author juansari
 *
 */
public class NgfModel {

	private String userName;
	private String terrName;
	private String terrType;
	private String salesRepNumber;
	private String derivedSegment1;
	private Date fpbDate;
	private String version;
	private String currencyCode;
	private String currencyMap;
	private String nodeLowestLevelFlag;
	private String lowestLevelFlag;
	private String systemUser;
	private String yearName;
	private String quarterName;
	private String monthName;
	private String weekName;
	private String periodId;
	private String periodComments;
	private String keyType;
	private BigDecimal fcstNr;
	private BigDecimal fcstNrPrev;
	private BigDecimal fcstAnnualized;
	private BigDecimal fcstAnnualizedPrev;
	private BigDecimal fcstAnnuals;
	private BigDecimal fcstAnnualsPrev;
	private BigDecimal fcstAnnuity;
	private BigDecimal fcstAnnuityPrev;
	private BigDecimal fcstMultiyear;
	private BigDecimal fcstMultiyearPrev;
	private BigDecimal fcstTcv;
	private BigDecimal fcstTcvPrev;
	private BigDecimal fcstTotalCommit;
	private BigDecimal fcstTotalCommitJdg;
	private BigDecimal fcstTotalCommitPrev;
	private BigDecimal fcstNrAdjJdg;
	private BigDecimal fcstAnnualsADjJdg;
	private BigDecimal fcstMultiyearAdjJdg;
	private BigDecimal fcstTotalCommitLow;
	private BigDecimal fcstTotalCommitHigh;
	private BigDecimal fcstNrCmt;
	private Date fcstNrCmtDt;
	private BigDecimal fcstAnnualsCmt;
	private Date fcstAnnualsCmtDt;
	private BigDecimal fcstMultiyearCmt;
	private Date fcstMultiyearCmtDt;
	private BigDecimal upsideNr ;
	private BigDecimal upsideAnnualized;
	private BigDecimal upsideAnnuals;
	private BigDecimal upsideAnnuity;
	private BigDecimal upsideMultiyear;
	private BigDecimal upsideTcv;
	
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
	 * @return the currencyMap
	 */
	public String getCurrencyMap() {
		return currencyMap;
	}
	/**
	 * @param currencyMap the currencyMap to set
	 */
	public void setCurrencyMap(String currencyMap) {
		this.currencyMap = currencyMap;
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
	 * @return the yearName
	 */
	public String getYearName() {
		return yearName;
	}
	/**
	 * @param yearName the yearName to set
	 */
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	/**
	 * @return the quarterName
	 */
	public String getQuarterName() {
		return quarterName;
	}
	/**
	 * @param quarterName the quarterName to set
	 */
	public void setQuarterName(String quarterName) {
		this.quarterName = quarterName;
	}
	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}
	/**
	 * @param monthName the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	/**
	 * @return the weekName
	 */
	public String getWeekName() {
		return weekName;
	}
	/**
	 * @param weekName the weekName to set
	 */
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	/**
	 * @return the periodId
	 */
	public String getPeriodId() {
		return periodId;
	}
	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	/**
	 * @return the periodComments
	 */
	public String getPeriodComments() {
		return periodComments;
	}
	/**
	 * @param periodComments the periodComments to set
	 */
	public void setPeriodComments(String periodComments) {
		this.periodComments = periodComments;
	}
	/**
	 * @return the keyType
	 */
	public String getKeyType() {
		return keyType;
	}
	/**
	 * @param keyType the keyType to set
	 */
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	/**
	 * @return the fcstNr
	 */
	public BigDecimal getFcstNr() {
		return fcstNr;
	}
	/**
	 * @param fcstNr the fcstNr to set
	 */
	public void setFcstNr(BigDecimal fcstNr) {
		this.fcstNr = fcstNr;
	}
	/**
	 * @return the fcstNrPrev
	 */
	public BigDecimal getFcstNrPrev() {
		return fcstNrPrev;
	}
	/**
	 * @param fcstNrPrev the fcstNrPrev to set
	 */
	public void setFcstNrPrev(BigDecimal fcstNrPrev) {
		this.fcstNrPrev = fcstNrPrev;
	}
	/**
	 * @return the fcstAnnualized
	 */
	public BigDecimal getFcstAnnualized() {
		return fcstAnnualized;
	}
	/**
	 * @param fcstAnnualized the fcstAnnualized to set
	 */
	public void setFcstAnnualized(BigDecimal fcstAnnualized) {
		this.fcstAnnualized = fcstAnnualized;
	}
	/**
	 * @return the fcstAnnualizedPrev
	 */
	public BigDecimal getFcstAnnualizedPrev() {
		return fcstAnnualizedPrev;
	}
	/**
	 * @param fcstAnnualizedPrev the fcstAnnualizedPrev to set
	 */
	public void setFcstAnnualizedPrev(BigDecimal fcstAnnualizedPrev) {
		this.fcstAnnualizedPrev = fcstAnnualizedPrev;
	}
	/**
	 * @return the fcstAnnuals
	 */
	public BigDecimal getFcstAnnuals() {
		return fcstAnnuals;
	}
	/**
	 * @param fcstAnnuals the fcstAnnuals to set
	 */
	public void setFcstAnnuals(BigDecimal fcstAnnuals) {
		this.fcstAnnuals = fcstAnnuals;
	}
	/**
	 * @return the fcstAnnualsPrev
	 */
	public BigDecimal getFcstAnnualsPrev() {
		return fcstAnnualsPrev;
	}
	/**
	 * @param fcstAnnualsPrev the fcstAnnualsPrev to set
	 */
	public void setFcstAnnualsPrev(BigDecimal fcstAnnualsPrev) {
		this.fcstAnnualsPrev = fcstAnnualsPrev;
	}
	/**
	 * @return the fcstAnnuity
	 */
	public BigDecimal getFcstAnnuity() {
		return fcstAnnuity;
	}
	/**
	 * @param fcstAnnuity the fcstAnnuity to set
	 */
	public void setFcstAnnuity(BigDecimal fcstAnnuity) {
		this.fcstAnnuity = fcstAnnuity;
	}
	/**
	 * @return the fcstAnnuityPrev
	 */
	public BigDecimal getFcstAnnuityPrev() {
		return fcstAnnuityPrev;
	}
	/**
	 * @param fcstAnnuityPrev the fcstAnnuityPrev to set
	 */
	public void setFcstAnnuityPrev(BigDecimal fcstAnnuityPrev) {
		this.fcstAnnuityPrev = fcstAnnuityPrev;
	}
	/**
	 * @return the fcstMultiyear
	 */
	public BigDecimal getFcstMultiyear() {
		return fcstMultiyear;
	}
	/**
	 * @param fcstMultiyear the fcstMultiyear to set
	 */
	public void setFcstMultiyear(BigDecimal fcstMultiyear) {
		this.fcstMultiyear = fcstMultiyear;
	}
	/**
	 * @return the fcstMultiyearPrev
	 */
	public BigDecimal getFcstMultiyearPrev() {
		return fcstMultiyearPrev;
	}
	/**
	 * @param fcstMultiyearPrev the fcstMultiyearPrev to set
	 */
	public void setFcstMultiyearPrev(BigDecimal fcstMultiyearPrev) {
		this.fcstMultiyearPrev = fcstMultiyearPrev;
	}
	/**
	 * @return the fcstTcv
	 */
	public BigDecimal getFcstTcv() {
		return fcstTcv;
	}
	/**
	 * @param fcstTcv the fcstTcv to set
	 */
	public void setFcstTcv(BigDecimal fcstTcv) {
		this.fcstTcv = fcstTcv;
	}
	/**
	 * @return the fcstTcvPrev
	 */
	public BigDecimal getFcstTcvPrev() {
		return fcstTcvPrev;
	}
	/**
	 * @param fcstTcvPrev the fcstTcvPrev to set
	 */
	public void setFcstTcvPrev(BigDecimal fcstTcvPrev) {
		this.fcstTcvPrev = fcstTcvPrev;
	}
	/**
	 * @return the fcstTotalCommit
	 */
	public BigDecimal getFcstTotalCommit() {
		return fcstTotalCommit;
	}
	/**
	 * @param fcstTotalCommit the fcstTotalCommit to set
	 */
	public void setFcstTotalCommit(BigDecimal fcstTotalCommit) {
		this.fcstTotalCommit = fcstTotalCommit;
	}
	/**
	 * @return the fcstTotalCommitJdg
	 */
	public BigDecimal getFcstTotalCommitJdg() {
		return fcstTotalCommitJdg;
	}
	/**
	 * @param fcstTotalCommitJdg the fcstTotalCommitJdg to set
	 */
	public void setFcstTotalCommitJdg(BigDecimal fcstTotalCommitJdg) {
		this.fcstTotalCommitJdg = fcstTotalCommitJdg;
	}
	/**
	 * @return the fcstTotalCommitPrev
	 */
	public BigDecimal getFcstTotalCommitPrev() {
		return fcstTotalCommitPrev;
	}
	/**
	 * @param fcstTotalCommitPrev the fcstTotalCommitPrev to set
	 */
	public void setFcstTotalCommitPrev(BigDecimal fcstTotalCommitPrev) {
		this.fcstTotalCommitPrev = fcstTotalCommitPrev;
	}
	/**
	 * @return the fcstNrAdjJdg
	 */
	public BigDecimal getFcstNrAdjJdg() {
		return fcstNrAdjJdg;
	}
	/**
	 * @param fcstNrAdjJdg the fcstNrAdjJdg to set
	 */
	public void setFcstNrAdjJdg(BigDecimal fcstNrAdjJdg) {
		this.fcstNrAdjJdg = fcstNrAdjJdg;
	}
	/**
	 * @return the fcstAnnualsADjJdg
	 */
	public BigDecimal getFcstAnnualsADjJdg() {
		return fcstAnnualsADjJdg;
	}
	/**
	 * @param fcstAnnualsADjJdg the fcstAnnualsADjJdg to set
	 */
	public void setFcstAnnualsADjJdg(BigDecimal fcstAnnualsADjJdg) {
		this.fcstAnnualsADjJdg = fcstAnnualsADjJdg;
	}
	/**
	 * @return the fcstMultiyearAdjJdg
	 */
	public BigDecimal getFcstMultiyearAdjJdg() {
		return fcstMultiyearAdjJdg;
	}
	/**
	 * @param fcstMultiyearAdjJdg the fcstMultiyearAdjJdg to set
	 */
	public void setFcstMultiyearAdjJdg(BigDecimal fcstMultiyearAdjJdg) {
		this.fcstMultiyearAdjJdg = fcstMultiyearAdjJdg;
	}
	/**
	 * @return the fcstTotalCommitLow
	 */
	public BigDecimal getFcstTotalCommitLow() {
		return fcstTotalCommitLow;
	}
	/**
	 * @param fcstTotalCommitLow the fcstTotalCommitLow to set
	 */
	public void setFcstTotalCommitLow(BigDecimal fcstTotalCommitLow) {
		this.fcstTotalCommitLow = fcstTotalCommitLow;
	}
	/**
	 * @return the fcstTotalCommitHigh
	 */
	public BigDecimal getFcstTotalCommitHigh() {
		return fcstTotalCommitHigh;
	}
	/**
	 * @param fcstTotalCommitHigh the fcstTotalCommitHigh to set
	 */
	public void setFcstTotalCommitHigh(BigDecimal fcstTotalCommitHigh) {
		this.fcstTotalCommitHigh = fcstTotalCommitHigh;
	}
	/**
	 * @return the fcstNrCmt
	 */
	public BigDecimal getFcstNrCmt() {
		return fcstNrCmt;
	}
	/**
	 * @param fcstNrCmt the fcstNrCmt to set
	 */
	public void setFcstNrCmt(BigDecimal fcstNrCmt) {
		this.fcstNrCmt = fcstNrCmt;
	}
	/**
	 * @return the fcstNrCmtDt
	 */
	public Date getFcstNrCmtDt() {
		return fcstNrCmtDt;
	}
	/**
	 * @param fcstNrCmtDt the fcstNrCmtDt to set
	 */
	public void setFcstNrCmtDt(Date fcstNrCmtDt) {
		this.fcstNrCmtDt = fcstNrCmtDt;
	}
	/**
	 * @return the fcstAnnualsCmt
	 */
	public BigDecimal getFcstAnnualsCmt() {
		return fcstAnnualsCmt;
	}
	/**
	 * @param fcstAnnualsCmt the fcstAnnualsCmt to set
	 */
	public void setFcstAnnualsCmt(BigDecimal fcstAnnualsCmt) {
		this.fcstAnnualsCmt = fcstAnnualsCmt;
	}
	/**
	 * @return the fcstAnnualsCmtDt
	 */
	public Date getFcstAnnualsCmtDt() {
		return fcstAnnualsCmtDt;
	}
	/**
	 * @param fcstAnnualsCmtDt the fcstAnnualsCmtDt to set
	 */
	public void setFcstAnnualsCmtDt(Date fcstAnnualsCmtDt) {
		this.fcstAnnualsCmtDt = fcstAnnualsCmtDt;
	}
	/**
	 * @return the fcstMultiyearCmt
	 */
	public BigDecimal getFcstMultiyearCmt() {
		return fcstMultiyearCmt;
	}
	/**
	 * @param fcstMultiyearCmt the fcstMultiyearCmt to set
	 */
	public void setFcstMultiyearCmt(BigDecimal fcstMultiyearCmt) {
		this.fcstMultiyearCmt = fcstMultiyearCmt;
	}
	/**
	 * @return the fcstMultiyearCmtDt
	 */
	public Date getFcstMultiyearCmtDt() {
		return fcstMultiyearCmtDt;
	}
	/**
	 * @param fcstMultiyearCmtDt the fcstMultiyearCmtDt to set
	 */
	public void setFcstMultiyearCmtDt(Date fcstMultiyearCmtDt) {
		this.fcstMultiyearCmtDt = fcstMultiyearCmtDt;
	}
	/**
	 * @return the upsideNr
	 */
	public BigDecimal getUpsideNr() {
		return upsideNr;
	}
	/**
	 * @param upsideNr the upsideNr to set
	 */
	public void setUpsideNr(BigDecimal upsideNr) {
		this.upsideNr = upsideNr;
	}
	/**
	 * @return the upsideAnnualized
	 */
	public BigDecimal getUpsideAnnualized() {
		return upsideAnnualized;
	}
	/**
	 * @param upsideAnnualized the upsideAnnualized to set
	 */
	public void setUpsideAnnualized(BigDecimal upsideAnnualized) {
		this.upsideAnnualized = upsideAnnualized;
	}
	/**
	 * @return the upsideAnnuals
	 */
	public BigDecimal getUpsideAnnuals() {
		return upsideAnnuals;
	}
	/**
	 * @param upsideAnnuals the upsideAnnuals to set
	 */
	public void setUpsideAnnuals(BigDecimal upsideAnnuals) {
		this.upsideAnnuals = upsideAnnuals;
	}
	/**
	 * @return the upsideAnnuity
	 */
	public BigDecimal getUpsideAnnuity() {
		return upsideAnnuity;
	}
	/**
	 * @param upsideAnnuity the upsideAnnuity to set
	 */
	public void setUpsideAnnuity(BigDecimal upsideAnnuity) {
		this.upsideAnnuity = upsideAnnuity;
	}
	/**
	 * @return the upsideMultiyear
	 */
	public BigDecimal getUpsideMultiyear() {
		return upsideMultiyear;
	}
	/**
	 * @param upsideMultiyear the upsideMultiyear to set
	 */
	public void setUpsideMultiyear(BigDecimal upsideMultiyear) {
		this.upsideMultiyear = upsideMultiyear;
	}
	/**
	 * @return the upsideTcv
	 */
	public BigDecimal getUpsideTcv() {
		return upsideTcv;
	}
	/**
	 * @param upsideTcv the upsideTcv to set
	 */
	public void setUpsideTcv(BigDecimal upsideTcv) {
		this.upsideTcv = upsideTcv;
	}
	
}

