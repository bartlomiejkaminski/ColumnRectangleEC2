package constructions.column_rectangle_ec2.MODEL;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {
	
	private StringProperty 		b 			= new SimpleStringProperty("0.35");
	private StringProperty 		h 			= new SimpleStringProperty("0.5");
	private StringProperty		a 			= new SimpleStringProperty("0.06");
	
	private StringProperty		fck	 		= new SimpleStringProperty("30.0");
	private StringProperty 		fyk 		= new SimpleStringProperty("500.0");
	private StringProperty 		fi 			= new SimpleStringProperty("0.012");
	private StringProperty		eff 		= new SimpleStringProperty("0.163");
	
	private StringProperty		yc 			= new SimpleStringProperty("1.4");
	private StringProperty 		ys			= new SimpleStringProperty("1.15");
	
	private StringProperty		Ec 			= new SimpleStringProperty("32");
	private StringProperty 		Es			= new SimpleStringProperty("200.0");
	
	private StringProperty 		Ned			= new SimpleStringProperty("378");
	private StringProperty 		Med 		= new SimpleStringProperty("125");
	
	private StringProperty		Hd 			= new SimpleStringProperty("7.5");
	private StringProperty		u 			= new SimpleStringProperty("1.0");
	
	private BooleanProperty 	ifSymetric  = new SimpleBooleanProperty();
	
	//GETTERS AND SETTERS
	
	//b
	public double getValue_b()
	{
		return Double.parseDouble(b.getValue());
	}
	
	public void setValue_b(StringProperty value){
		this.b = value;
	}
	
	public StringProperty b_Property(){
		return b;
	}
	
	
	//h
	public double getValue_h()
	{
		return Double.parseDouble(h.getValue());
	}
	
	public void setValue_h(String value){
		h.set(value);
	}
	
	public StringProperty h_Property(){
		return h;
	}
	
	
	//a
	public double getValue_a()
	{
		return Double.parseDouble(a.getValue());
	}
	
	public void setValue_a(String value){
		a.set(value);
	}
	
	public StringProperty a_Property(){
		return a;
	}
	
	
	//fck
	public double getValue_fck()
	{
		return Double.parseDouble(fck.getValue());
	}
	
	public void setValue_fck(String value){
		fck.set(value);
	}
	
	public StringProperty fck_Property(){
		return fck;
	}
	
	
	//fyk
	public double getValue_fyk()
	{
		return Double.parseDouble(fyk.getValue());
	}
	
	public void setValue_fyk(String value){
		fyk.set(value);
	}
	
	public StringProperty fyk_Property(){
		return fyk;
	}
	
	
	//fi
	public double getValue_fi()
	{
		return Double.parseDouble(fi.getValue());
	}
	
	public void setValue_fi(String value){
		fi.set(value);
	}
	
	public StringProperty fi_Property(){
		return fi;
	}
	
	
	//eff
	public double getValue_eff()
	{
		return Double.parseDouble(eff.getValue());
	}
	
	public void setValue_eff(String value){
		eff.set(value);
	}
	
	public StringProperty eff_Property(){
		return eff;
	}
	
	
	//yc
	public double getValue_yc()
	{
		return Double.parseDouble(yc.getValue());
	}
	
	public void setValue_yc(String value){
		yc.set(value);
	}
	
	public StringProperty yc_Property(){
		return yc;
	}
	
	
	//ys
	public double getValue_ys()
	{
		return Double.parseDouble(ys.getValue());
	}
	
	public void setValue_ys(String value){
		ys.set(value);
	}
	
	public StringProperty ys_Property(){
		return ys;
	}
	
	
	//Ned
	public double getValue_Ned()
	{
		return Double.parseDouble(Ned.getValue());
	}
	
	public void setValue_Ned(String value){
		Ned.set(value);
	}
	
	public StringProperty Ned_Property(){
		return Ned;
	}
	
	
	//Med
	public double getValue_Med()
	{
		return Double.parseDouble(Med.getValue());
	}
	
	public void setValue_Med(String value){
		Med.set(value);
	}
	
	public StringProperty Med_Property(){
		return Med;
	}
	
	
	//Ec
	public double getValue_Ec()
	{
		return Double.parseDouble(Ec.getValue());
	}
		
	public void setValue_Ec(String value){
		Ec.set(value);
	}
		
	public StringProperty Ec_Property(){
		return Ec;
	}
	
	
	//Es
	public double getValue_Es()
	{
		return Double.parseDouble(Es.getValue());
	}
			
	public void setValue_Es(String value){
		Es.set(value);
	}
			
	public StringProperty Es_Property(){
		return Es;
	}
	
	
	//H
	public double getValue_Hd()
	{
		return Double.parseDouble(Hd.getValue());
	}
	
	public void setValue_Hd(String value){
		Hd.set(value);
	}
	
	public StringProperty Hd_Property(){
		return Hd;
	}
	
	
	//u
	public double getValue_u()
	{
		return Double.parseDouble(u.getValue());
	}
	
	public void setValue_u(String value){
		u.set(value);
	}
	
	public StringProperty u_Property(){
		return u;
	}
	
	
	//ifSymetric
	public boolean getValue_ifSymetric()
	{
		return ifSymetric.get();
	}
	
	public void setValue_ifSymetric(boolean value){
		ifSymetric.set(value);
	}
	
	public BooleanProperty ifSymetric_Property(){
		return ifSymetric;
	}
	
}
