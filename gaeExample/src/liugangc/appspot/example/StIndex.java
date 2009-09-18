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
	private Long Id;
	
	/**
	 * 发布日期
	 */
	private Date pubDate;
	
	/**
	 * 名称
	 */
	private String name;
	
	@Persistent
	private double closing;
	
	private double upDown;
	
	private double upDownPercent;
	
	private double yearUpDown;
	
	private double yearUpDownPercent;
	
	private double turnOverUpDown;
	
	private double turnOverUpDownPercent;	
		
	@Persistent
	private double staticPE;
	
	private double dynaPE;	
	
	@Persistent
	private double PB;
	
	private double lastYearStaticPE;
	private double lastYearDynaPE;
	private double lastYearPB;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	
	
	
	
}
