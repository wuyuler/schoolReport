package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreOfStudent implements Serializable{
	
	int _id;
	String name;
	String sex;
	int classes;
	int English1;
	int math1;
	int sport1;
	int English2;
	int math2;
	int sport2;
	int English3;
	int math3;
	int sport3;
	
	static public int nowTerms = 2; //从0开始
	//每一学期的科目数
	public static int[] eachTermSubjectNum = {3,3,3,3,3,3,3,3};
	public int[][] scores = new int[10][10];

	
	//所有的科目八个学期
	public static String[][] subject = {{"英语1","数学1","体育1"},{"英语2","数学2","体育2"},{"英语3","数学3","体育3"},
			{"英语4","数学4","体育4"},{"英语5","数学5","体育5"},{"英语6","数学6","体育6"},{"英语7","数学7","体育7"}
			,{"英语8","数学8","体育8"}};
	
	
	
	



	int howtoString = 0;//确定学期
	
	
	
	@Override
	public String toString() {
		String str;
		
//			if(howtoString == 0) str = name+" \n数学1: "+math1+" 英语1: "+English1+" 体育1: "+sport1;
//			else if(howtoString == 1)str = name+" \n数学2: "+math2+" 英语2: "+English2+" 体育2: "+sport2;
//			else  str = name+" \n数学3: "+math3+" 英语3: "+English3+" 体育3: "+sport3;
//		
//		switch(howtoString){
//		case 0:str = name+" \n数学1: "+math1+" 英语1: "+English1+" 体育1: "+sport1;
//			break;
//		case 1:str = name+" \n数学2: "+math2+" 英语2: "+English2+" 体育2: "+sport2;
//			break;
//		case 2:str = name+" \n数学3: "+math3+" 英语3: "+English3+" 体育3: "+sport3;
//			break;
//		default: str = "成绩未输入";
//		}
		
		str =  name +"\n";
		for(int i = 0;i < eachTermSubjectNum[howtoString];i++){
			str = str+subject[howtoString][i]+":"+scores[howtoString][i];
			
		}
			
		return str;
	}
	
	public int getHowtoString() {
		return howtoString;
	}
	public void setHowtoString(int howtoStringbyterm) {
		this.howtoString = howtoStringbyterm;
	}
	
	public ScoreOfStudent(int _id, String name, String sex, int classes) {
		
		this._id = _id;
		this.name = name;
		this.sex = sex;
		this.classes = classes;
		
		
		
	}
public ScoreOfStudent(int _id, String name, String sex, int classes,int[][]scores) {
		
		this._id = _id;
		this.name = name;
		this.sex = sex;
		this.classes = classes;
		this.scores = scores;
		
		
		
	}
	
	
	public int getsum(int whichterm){
		int sum = 0;
		for(int i =0;i < eachTermSubjectNum[whichterm];i ++){
			sum = sum+scores[whichterm][i];
			
		}
		return sum;
	}
	public float getaverage(int whichterm){
		float average;
		int sum = getsum(whichterm);
		average = sum/eachTermSubjectNum[whichterm];
		return average;
	}
	
	
//	public int getsum(int whichterms){
//		
//		
//		
//	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getClasses() {
		return classes;
	}
	public void setClasses(int classes) {
		this.classes = classes;
	}
	public int getEnglish1() {
		return English1;
	}
	public void setEnglish1(int english1) {
		English1 = english1;
	}
	public int getMath1() {
		return math1;
	}
	public void setMath1(int math1) {
		this.math1 = math1;
	}
	public int getSport1() {
		return sport1;
	}
	public void setSport1(int sport1) {
		this.sport1 = sport1;
	}
	public int getEnglish2() {
		return English2;
	}
	public void setEnglish2(int english2) {
		English2 = english2;
	}
	public int getMath2() {
		return math2;
	}
	public void setMath2(int math2) {
		this.math2 = math2;
	}
	public int getSport2() {
		return sport2;
	}
	public void setSport2(int sport2) {
		this.sport2 = sport2;
	}
	public int getEnglish3() {
		return English3;
	}
	public void setEnglish3(int english3) {
		English3 = english3;
	}
	public int getMath3() {
		return math3;
	}
	public void setMath3(int math3) {
		this.math3 = math3;
	}
	public int getSport3() {
		return sport3;
	}
	public void setSport3(int sport3) {
		this.sport3 = sport3;
	}
	

}
