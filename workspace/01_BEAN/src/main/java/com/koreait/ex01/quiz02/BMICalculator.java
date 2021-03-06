package com.koreait.ex01.quiz02;

public class BMICalculator {
	//private double lowBase;			// 		 low     < 20
	private double normalBase;		// 20 <= normal  < 25
	private double overBase;		// 25 <= over    < 30
	private double obesityBase; 	// 30 <= obesity < 35
	private Calculator calculator;

	// 생성자
	public BMICalculator(double normalBase, double overBase, double obesityBase,
			Calculator calculator) {
		super();
		//this.lowBase = lowBase;
		this.normalBase = normalBase;
		this.overBase = overBase;
		this.obesityBase = obesityBase;
		this.calculator = calculator;
	}
	
	// getter / setter
	//public double getLowBase() {
	//	return lowBase;
	//}
	//public void setLowBase(double lowBase) {
	//	this.lowBase = lowBase;
	//}
	public double getNormalBase() {
		return normalBase;
	}
	public void setNormalBase(double normalBase) {
		this.normalBase = normalBase;
	}
	public double getOverBase() {
		return overBase;
	}
	public void setOverBase(double overBase) {
		this.overBase = overBase;
	}
	public double getObesityBase() {
		return obesityBase;
	}
	public void setObesityBase(double obesityBase) {
		this.obesityBase = obesityBase;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	public void bmiInfo(double weight, double height) { /* 추가 */ // 몸무게 kg, 키 cm로 받아오기
		// 체질량 지수 = 몸무게(kg) / (키(m) * 키(m))
		height = calculator.div(height, 100); // 전달 받은 Cm를 M로 바꿨음
		double bmi = calculator.div(weight, calculator.mul(height, height));
		String health = null;
		if(bmi >= obesityBase) {	    // bmi >= 30
			health = "비만";
		}else if(bmi >= overBase) { 	// bmi >= 25
			health = "과체중";
		}else if(bmi >= normalBase) {   // bmi >= 20
			health = "정상";
		}else {
			health = "저체중";
		}
		System.out.println("BMI : " + bmi + "(" + health + ")");
	}
}