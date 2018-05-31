package entity;

import java.io.Serializable;

public class ScoreOfStudent implements Serializable{
	
	int _id;
	String name;
	String sex;
	int classes;
	
	
//	static public int nowTerms = 2; //从0开始
	
	//每一学期的科目数
	public static int[] eachTermSubjectNum = {3,3,3,3,3,3,3,3};
	public int[][] scores = new int[8][10];

	
	//所有的科目八个学期
	public static String[][] subject = {{"英语1","数学1","体育1"},{"英语2","数学2","体育2"},{"英语3","数学3","体育3"},
			{"英语4","数学4","体育4"},{"英语5","数学5","体育5"},{"英语6","数学6","体育6"},{"英语7","数学7","体育7"}
			,{"英语8","数学8","体育8"}};
	
	
	
	



//	int howtoString = 0;//确定学期
	
	
	
	
//	public String toString() {
//		String str;
//		str =  name +"\n";
//		for(int i = 0;i < eachTermSubjectNum[howtoString];i++){
//			str = str+subject[howtoString][i]+":"+scores[howtoString][i];
//			
//		}
//			
//		return str;
//	}
	
//	public int getHowtoString() {
//		return howtoString;
//	}
//	public void setHowtoString(int howtoStringbyterm) {
//		this.howtoString = howtoStringbyterm;
//	}
	
	public ScoreOfStudent(){
		
	}
	public ScoreOfStudent(int _id, String name, String sex, int classes) {
		
		this._id = _id;
		this.name = name;
		this.sex = sex;
		this.classes = classes;
		
		
	}
	
	
	
//	public int getsum(int whichterms){
//		
//		
//		
//	}
	
	
	public int get_id() {
		return _id;
	}
	public int[][] getScores() {
		return scores;
	}
	public void setScores(int[][] scores) {
		this.scores = scores;
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
	

}