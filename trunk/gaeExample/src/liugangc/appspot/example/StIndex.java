package liugangc.appspot.example;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class StIndex {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	/**
	 * 发布日期
	 */
	@Persistent
	private Date pubDate;
	
	/**
	 * 指数时间
	 */
	@Persistent
	private String indexDate;
	
	/**
	 * 名称
	 */
	@Persistent
	private String name;
	
	/**
	 * 收盘价格
	 */
	@Persistent
	private double closing;
	
	/**
	 * 较昨日涨跌
	 */
	@Persistent
	private double upDown;
	
	/**
	 * 较昨日涨跌百分比
	 */
	@Persistent
	private double upDownPercent;
	
	/**
	 * 今年涨跌
	 */
	@Persistent
	private double yearUpDown;
	
	/**
	 * 今年涨跌百分比
	 */
	@Persistent
	private double yearUpDownPercent;
	
	/**
	 * 较昨日成交额涨跌
	 */
	@Persistent
	private double turnOverUpDown;
	
	/**
	 * 较昨日成交额涨跌百分比
	 */
	@Persistent
	private double turnOverUpDownPercent;	
		
	/**
	 * 静态市盈率
	 */
	@Persistent
	private double staticPE;
	
	/**
	 * 滚动市盈率
	 */
	@Persistent
	private double dynaPE;	
	
	/**
	 * 市净率
	 */
	@Persistent
	private double PB;
	
	/**
	 * 去年底市盈率
	 */
	@Persistent
	private double lastYearStaticPE;
	/**
	 * 去年底滚动市盈率
	 */
	@Persistent
	private double lastYearDynaPE;
	/**
	 * 去年底市净率
	 */
	@Persistent
	private double lastYearPB;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getClosing() {
		return closing;
	}
	public void setClosing(double closing) {
		this.closing = closing;
	}
	public double getUpDown() {
		return upDown;
	}
	public void setUpDown(double upDown) {
		this.upDown = upDown;
	}
	public double getUpDownPercent() {
		return upDownPercent;
	}
	public void setUpDownPercent(double upDownPercent) {
		this.upDownPercent = upDownPercent;
	}
	public double getYearUpDown() {
		return yearUpDown;
	}
	public void setYearUpDown(double yearUpDown) {
		this.yearUpDown = yearUpDown;
	}
	public double getYearUpDownPercent() {
		return yearUpDownPercent;
	}
	public void setYearUpDownPercent(double yearUpDownPercent) {
		this.yearUpDownPercent = yearUpDownPercent;
	}
	public double getTurnOverUpDown() {
		return turnOverUpDown;
	}
	public void setTurnOverUpDown(double turnOverUpDown) {
		this.turnOverUpDown = turnOverUpDown;
	}
	public double getTurnOverUpDownPercent() {
		return turnOverUpDownPercent;
	}
	public void setTurnOverUpDownPercent(double turnOverUpDownPercent) {
		this.turnOverUpDownPercent = turnOverUpDownPercent;
	}
	public double getStaticPE() {
		return staticPE;
	}
	public void setStaticPE(double staticPE) {
		this.staticPE = staticPE;
	}
	public double getDynaPE() {
		return dynaPE;
	}
	public void setDynaPE(double dynaPE) {
		this.dynaPE = dynaPE;
	}
	public double getPB() {
		return PB;
	}
	public void setPB(double pb) {
		PB = pb;
	}
	public double getLastYearStaticPE() {
		return lastYearStaticPE;
	}
	public void setLastYearStaticPE(double lastYearStaticPE) {
		this.lastYearStaticPE = lastYearStaticPE;
	}
	public double getLastYearDynaPE() {
		return lastYearDynaPE;
	}
	public void setLastYearDynaPE(double lastYearDynaPE) {
		this.lastYearDynaPE = lastYearDynaPE;
	}
	public double getLastYearPB() {
		return lastYearPB;
	}
	public void setLastYearPB(double lastYearPB) {
		this.lastYearPB = lastYearPB;
	}
	public String getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}
	
	@Override
	public String toString() {
		String result= id+","+indexDate+","+name+","+closing+","+upDown+","+upDownPercent+","+yearUpDown+","+yearUpDownPercent;
		return result;
	}
	
	
}
