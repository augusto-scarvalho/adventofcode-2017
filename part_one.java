public class part_one {
	
	private static int sum = 0;
	
	static void samecharSum(char x, char y){
		if(x == y)
			sum += Character.getNumericValue(x);
		//System.out.println(x +" | "+ y); // debug
	}
	
	static public void main(String[] args){
		
		String captcha = "3443";  
		int chaptchaLength = captcha.length();
		char charsCaptcha[] = new char[chaptchaLength];
				
		charsCaptcha = captcha.toCharArray();
				
		for(int cont = chaptchaLength-1; cont < chaptchaLength*2-1; cont++)
			samecharSum(charsCaptcha[cont%chaptchaLength], charsCaptcha[(cont+1)%chaptchaLength]);
		
		System.out.println(sum);
	}
}
