package yogi.paging.test;

import java.io.Serializable;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2648534068989886936L;
	
	String name;
	String color;
	Integer quantity;
	Double amount;
	String priority;
	Boolean transmit;
	
	public Item(String name, String color, int quantity, double amount, String priority, boolean transmit) {
		super();		
		this.name = name; this.color = color; this.quantity = quantity;	this.amount = amount;	this.priority = priority;	this.transmit = transmit;
	}
	
	public Item(String name, String color, String quantity, String amount, String priority, String transmit) {
		if(name.trim().isEmpty())		{	this.name = null;		} 	else {	this.name = name;						}
		if(color.trim().isEmpty())		{	this.color = null;		} 	else {	this.color = color;						}
		if(quantity.trim().isEmpty())	{	this.quantity = null;	} 	else {	this.quantity = new Integer(quantity);	}
		if(amount.trim().isEmpty())		{	this.amount = null;		} 	else {	this.amount = new Double(amount);		}
		if(priority.trim().isEmpty())	{	this.priority = null;	} 	else {	this.priority = priority;				}
		if(transmit.trim().isEmpty())	{	this.transmit = false;	} 	else {	this.transmit = new Boolean(transmit);	}
	}
	
	public String toString() {
		return 	"Name : " + name + "\t" + " # " +
				"Color : " + color + "\t" + " # " + 
				"Qty : " + quantity +"\t" + " # " + 
				"Amount : " + amount +"\t" + " # " + 
				"Priority : " + priority;					
	}
}
