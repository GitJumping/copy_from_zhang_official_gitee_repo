package geektime.jdk;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
 
public class InstantToDateTimeExample {
    
    public static void main(String[] args) {

    	addSubstract();
    	datetime();
    	
    }
    
    static void addSubstract() {
        Instant instant = Instant.parse("1984-08-13T10:15:30.345Z");
        System.out.println("Instant             : " + instant);
        

        System.out.println("15 seconds before   : " + instant.minusSeconds(15));
        System.out.println("10 seconds after    : " + instant.plusSeconds(10));
        

        System.out.println("45 minutes before   : " + instant.minus(45, ChronoUnit.MINUTES));

        System.out.println("3 hours before      : " + instant.minus(3, ChronoUnit.HOURS));

    }
    
    static void datetime() {
        Instant instant = Instant.parse("1997-05-07T10:15:30.00Z");
        
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("LocalDateTime : " + localDateTime);
        
        ZonedDateTime zonedDateTime1 = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("ZonedDateTime1: " + zonedDateTime1);
        
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println("ZonedDateTime2: " + zonedDateTime2);
        
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        System.out.println("OffsetDateTime: " + offsetDateTime);
    }
}

