package yogi.paging.test;

import java.io.Serializable;

public class Recom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3263399260894115689L;
		
	String act;
	String orig;
	String dest;
	String fareBasis;
	Integer owrt;
	String rtg;
	String fn;
	String cur;
	Float amount;
	Float prevAmount;
	Float fareDiff;
	Double slaYld;
	String hCxr;
	Float hShr;
	String cxr;
	Float shr;
	String recFbc;
	String recFn;
	String market;
	String chgRtg;
	String chgFn;
		
	
	public Recom(String act, String orig, String dest, String fareBasis, int owrt, String rtg, String fn, String cur, float amount) {
		super();
		this.act = act; this.orig = orig; this.dest = dest;	this.fareBasis = fareBasis;	this.owrt = owrt; this.rtg = rtg; this.fn = fn; this.cur = cur; this.amount = amount;
	}
	
	public Recom(String act, String orig, String dest, String fareBasis, int owrt, String rtg, String fn, String cur, float amount, float prevAmount, float fareDiff, double slaYld, String hCxr, float hShr, String cxr, float shr, String recFbc, String recFn, String market, String chgRtg, String chgFn) {
		super();
		this.act = act; this.orig = orig; this.dest = dest;	this.fareBasis = fareBasis;	this.owrt = owrt; this.rtg = rtg; this.fn = fn; this.cur = cur; this.amount = amount; this.prevAmount = prevAmount; this.fareDiff = fareDiff; this.slaYld = slaYld; this.hCxr = hCxr; this.hShr = hShr; this.cxr = cxr; this.shr = shr; this.recFbc = recFbc; this.recFn = recFn; this.market = market; this.chgRtg = chgRtg; this.chgFn = chgFn;
	}
	
	public Recom(String act, String orig, String dest, String fareBasis, String owrt, String rtg, String fn, String cur, String amount, String prevAmount, String fareDiff, String slaYld, String hCxr, String hShr, String cxr, String shr, String recFbc, String recFn, String market, String chgRtg, String chgFn) {
		super();
		if(act.trim().isEmpty())		{	this.act = null;		} 	else {	this.act = act;										}
		if(orig.trim().isEmpty())		{	this.orig = null;		} 	else {	this.orig = orig;									}
		if(dest.trim().isEmpty())		{	this.dest = null;		} 	else {	this.dest = dest;									}
		if(fareBasis.trim().isEmpty())	{	this.fareBasis = null;	} 	else {	this.fareBasis = fareBasis;							}	
		if(owrt.trim().isEmpty())		{	this.owrt = -1;			} 	else {	this.owrt = Integer.parseInt(owrt);					}
		if(rtg.trim().isEmpty())		{	this.rtg = null;		} 	else {	this.rtg = rtg;										}
		if(fn.trim().isEmpty())			{	this.fn = null;			}	else {	this.fn = fn; 										}
		if(cur.trim().isEmpty())		{	this.cur = null;		}	else {	this.cur = cur; 									}
		if(amount.trim().isEmpty()) 	{ 	this.amount = null; 	}	else {	this.amount = Float.parseFloat(amount);			}
		if(prevAmount.trim().isEmpty()) { 	this.prevAmount = null; }	else {	this.prevAmount = Float.parseFloat(prevAmount);	}
		if(fareDiff.trim().isEmpty()) 	{ 	this.fareDiff = null; 	}	else {	this.fareDiff = Float.parseFloat(fareDiff);		}
		if(slaYld.trim().isEmpty()) 	{ 	this.slaYld = null; 	}	else {	this.slaYld = Double.parseDouble(slaYld);			}
		if(hCxr.trim().isEmpty())		{	this.hCxr = null;		}	else {	this.hCxr = hCxr;									} 
		if(hShr.trim().isEmpty()) 		{ 	this.hShr = null; 		}	else {	this.hShr = Float.parseFloat(hShr);				}
		if(cxr.trim().isEmpty())		{	this.cxr = null;		}	else {	this.cxr = cxr;										} 
		if(shr.trim().isEmpty()) 		{ 	this.shr = null; 		}	else {	this.shr = Float.parseFloat(shr);					}
		if(recFbc.trim().isEmpty())		{	this.recFbc = null;		}	else {	this.recFbc = recFbc;								} 
		if(recFn.trim().isEmpty())		{	this.recFn = null;		}	else {	this.recFn = recFn;									} 
		if(market.trim().isEmpty())		{	this.market = null;		}	else {	this.market = market;								} 
		if(chgRtg.trim().isEmpty())		{	this.chgRtg = null;		}	else {	this.chgRtg = chgRtg;								} 
		if(chgFn.trim().isEmpty())		{	this.chgFn = null;		}	else {	this.chgFn = chgFn;									}
	}
	
	public String toString() {
		return 	"Act : " + act + "\t" + " # " + 
				"Orig : " + orig + "\t" + " # " +
				"Dest : " + dest + "\t" + " # " +
				"Fare Basis : " + fareBasis + "\t" + " # " +
				"Owrt : " + owrt + "\t" + " # " +
				"Rtg : " + rtg + "\t" + " # " +
				"FN : " + fn + "\t" + " # " +
				"Cur : " + cur + "\t" + " # " +
				"Amount : " + amount + "\t" + " # " +
				"Prev Amount : " + prevAmount  + "\t" + " # " +
				"Fare Diff : " + fareDiff  + "\t" + " # " +
				"Sla Yld : " + slaYld  + "\t" + " # " +
				"HCxr : " + hCxr  + "\t" + " # " +
				"HShr : " + hShr + "\t" + " # " +
				"Cxr : " + cxr + "\t" + " # " +
				"Shr : " + shr + "\t" + " # " +
				"Rec FBC : " + recFbc + "\t" + " # " +
				"Rec FN : " + recFn + "\t" + " # " +
				"Market : " + market + "\t" + " # " +
				"Chg Rtg : " + chgRtg + "\t" + " # " +
				"Chg FN : " + chgFn;
	}
}
