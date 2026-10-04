package dev.shubham.problems.atlassian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RobotFactory {

    public static void main(String[] args){
        System.out.println("hello world!");


        String[] all_parts = { "Rosie_claw",
                "Rosie_sensors",
                "Dustie_case",
                "Optimus_sensors",
                "Rust_sensors",
                "Rosie_case",
                "Rust_case",
                "Optimus_speaker",
                "Rosie_wheels",
                "Rosie_speaker",
                "Dustie_case",
                "Dustie_arms",
                "Rust_claw",
                "Dustie_case",
                "Dustie_speaker",
                "Optimus_case",
                "Optimus_wheels",
                "Rust_legs",
                "Optimus_sensors" };

        String required_parts_1 = "sensors,case,speaker,wheels";
        String[] result1  = getRobots(all_parts, required_parts_1);
        System.out.println(Arrays.toString(result1));


        String required_parts_2 = "sensors,case,speaker,wheels,claw";
        String required_parts_3 = "sensors,case,screws";


        String[] result2  = getRobots(all_parts, required_parts_2);
        System.out.println(Arrays.toString(result2));


        String[] result3  = getRobots(all_parts, required_parts_3);
        System.out.println(Arrays.toString(result3));
    }

    /**
     *
     * Sample Input:
     * String[] all_parts = { "Rosie_claw",
     * "Rosie_sensors",
     * "Dustie_case",
     * "Optimus_sensors",
     * "Rust_sensors",
     * "Rosie_case",
     * "Rust_case",
     * "Optimus_speaker",
     * "Rosie_wheels",
     * "Rosie_speaker",
     * "Dustie_case",
     * "Dustie_arms",
     * "Rust_claw",
     * "Dustie_case",
     * "Dustie_speaker",
     * "Optimus_case",
     * "Optimus_wheels",
     * "Rust_legs",
     * "Optimus_sensors" };
     *
     * String required_parts_1 = "sensors,case,speaker,wheels";
     * String required_parts_2 = "sensors,case,speaker,wheels,claw";
     * String required_parts_3 = "sensors,case,screws";
     *
     * Expected Output:
     * get_robots(all_parts, required_parts_1) => ["Optimus", "Rosie"]
     * get_robots(all_parts, required_parts_2) => ["Rosie"]
     * get_robots(all_parts, required_parts_3) => []
     *
     * @param all_parts
     * @param required_parts
     * @return
     */
    public static String[] getRobots(String[] all_parts, String required_parts){
        Map<String, Map<String, Integer>> robotToParts = new LinkedHashMap<>();

        for(String part : all_parts){
            String[] namePart = part.split("_");
            String name = namePart[0];
            String robotPart = namePart[1];
            robotToParts.putIfAbsent(name, new HashMap<>());

            if(robotToParts.get(name).containsKey(robotPart)){
                robotToParts.get(name).put(robotPart, robotToParts.get(name).get(robotPart) + 1);
            }
            else {
                robotToParts.get(name).put(robotPart, 1);
            }
        }

        Map<String, Integer> requiredPartsCount = new HashMap<>();
        String[] parts = required_parts.split(",");

        for(String part : parts){
            requiredPartsCount.put(part, requiredPartsCount.getOrDefault(part, 0) + 1);
        }


        List<String> robots = new ArrayList<>();

        for(Map.Entry<String, Map<String, Integer>> entry : robotToParts.entrySet()){

            String robotName = entry.getKey();
            Map<String, Integer> availableParts = entry.getValue();
            boolean allAvailable = true;

            for(Map.Entry<String, Integer> requiredPartCount : requiredPartsCount.entrySet()){
                String partName = requiredPartCount.getKey();
                Integer requiredCount = requiredPartCount.getValue();
                if( requiredCount > availableParts.getOrDefault(partName, 0) ){
                    allAvailable = false; break;
                }
            }
            if(allAvailable){
                robots.add(robotName);
            }
        }
        Collections.sort(robots);
        return robots.toArray(new String[0]);
    }

}
