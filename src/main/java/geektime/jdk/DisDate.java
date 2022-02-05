package geektime.jdk;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class DisDate {
	public static void main (String[] args) {
	    Calendar c = Calendar.getInstance();
	    c.set(1582, 9, 4);

	    SimpleDateFormat s=new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
	    System.out.println(s.format(c.getTime()));
	    c.add(Calendar.DATE, 3); //计算3天后的时间
	    System.out.println("3天后的时间是："+ s.format(c.getTime()));
	  }
}
