package mytest;

public class mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String range 	= "SG0000.0.5.7.260_01_POOQ_SD_1000.mp4"; 
		String filegb[]	= range.split("\\.");
		
		System.out.println(filegb[filegb.length-1]);
	}

}
