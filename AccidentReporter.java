package com.prs;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by peter on 5/6/16.
 */
public class AccidentReporter {

    private TreeMap<Long, Accident> accidents_LAT = new TreeMap<>();

    public static void main(String args[]){

        AccidentReporter ar = new AccidentReporter();
        ar.initMap();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.print("Latitude-->");
            Long latitude = scanner.nextLong();

            System.out.print("Longitude-->");
            Long longitude = scanner.nextLong();

            if (ar.logAccident(latitude, longitude)) {
                System.out.println("Thank you for reporting, the incident has been logged");
            } else {
                System.out.println("Thank you for reporting, this incident has already been reported");
            }
            System.out.print("quit: (true/false)");
            quit = Boolean.valueOf(scanner.next());
        }
    }

    public boolean logAccident(Long latitude, Long longitude){

        Long maxLat = latitude.longValue()+5;
        Long minLat = latitude.longValue()-5;

        SortedMap<Long, Accident> latRangeReported = accidents_LAT.subMap(minLat, maxLat);

        if ((latRangeReported.size() == 0)){
            return true;
        }

        boolean reported = latRangeReported.entrySet().stream()
                .anyMatch(es -> es.getValue().isLongInRange(longitude));

        if (!reported) {
            //todo: write to DB

            Accident accident = new Accident(latitude, longitude);
            accidents_LAT.put(latitude, accident);


            return true;
        }

        return false;
    }

    public void initMap(){
        Long lat = Long.valueOf("100");
        Long lng = Long.valueOf("5000");
        Accident accident = new Accident(lat, lng);
        accidents_LAT.put(lat, accident);

        lat = Long.valueOf("125");
        lng = Long.valueOf("5500");
        accident = new Accident(lat, lng);
        accidents_LAT.put(lat, accident);

        lat = Long.valueOf("150");
        lng = Long.valueOf("6000");
        accident = new Accident(lat, lng);
        accidents_LAT.put(lat, accident);

        lat = Long.valueOf("175");
        lng = Long.valueOf("6500");
        accident = new Accident(lat, lng);
        accidents_LAT.put(lat, accident);

        lat = Long.valueOf("200");
        lng = Long.valueOf("7000");
        accident = new Accident(lat, lng);
        accidents_LAT.put(lat, accident);
    }


    public class Accident {
        private Long lat = null;
        private Long lng = null;

        public Accident(Long lat, Long lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public boolean isLongInRange(Long longitude){
            long maxLng = longitude.longValue()+500;
            long minLng = longitude.longValue()-500;
            long accidentLng = lng.longValue();
            return ((accidentLng >= minLng) && (accidentLng <= maxLng));
        }
    }

}
