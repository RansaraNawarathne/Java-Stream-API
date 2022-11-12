import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List <Aircraft> aircraftFleet = new ArrayList<>();

        aircraftFleet.add(new Aircraft(ManufacturerEnum.AIRBUS, "A330-200F", 61000f, 260, 7400f));
        aircraftFleet.add(new Aircraft(ManufacturerEnum.AIRBUS, "A350F", 109000f, 350, 8700f));
        aircraftFleet.add(new Aircraft(ManufacturerEnum.AIRBUS, "A321P2F", 27000f, 180, 3800f));
        aircraftFleet.add(new Aircraft(ManufacturerEnum.BOEING, "777-8F", 118200f, 396, 8167.32f));
        aircraftFleet.add(new Aircraft(ManufacturerEnum.BOEING, "767-300F", 56600f, 350, 6028.26f));
        aircraftFleet.add(new Aircraft(ManufacturerEnum.BOEING, "747-8F", 139500f, 467, 8009.9f));

        aircraftFleetFilter( aircraftFleet );
    }

    private static void aircraftFleetFilter ( List <Aircraft> fleet ) {
        //Filter
        List<Aircraft> airbusAircraftList = fleet.stream()
                .filter(aircraft -> aircraft.getManufacturer().equals(ManufacturerEnum.AIRBUS))
                .collect(Collectors.toList());
        System.out.println("Airbus Aircraft Fleet");
        airbusAircraftList.forEach(System.out::println);

        //Sort
        List<Aircraft> sortByPayload = fleet.stream()
                .sorted(Comparator.comparing(Aircraft::getMaxPayLoadCapacity))
                .collect(Collectors.toList());
        System.out.println("Aircraft sorted according payload : min -> max");
        sortByPayload.forEach(System.out::println);

        //Reverse Order Sort
        List<Aircraft> revSortByRange = fleet.stream()
                .sorted(Comparator.comparing(Aircraft::getRange).reversed())
                .collect(Collectors.toList());
        System.out.println("Aircraft sorted according to range : max -> min");
        revSortByRange.forEach(System.out::println);
        
        //Sort, Then Comparing
        List<Aircraft> sortBySeatingCapacityAndRange = fleet.stream()
                .sorted(Comparator.comparing(Aircraft::getSeatingCapacity).thenComparing(Aircraft::getRange))
                .collect(Collectors.toList());
        System.out.println("Aircraft sorted according to seating capacity and then range : min -> max");
        sortBySeatingCapacityAndRange.forEach(System.out::println);

        //Reverse Sort, Then Comparing
        List<Aircraft> revSortBySeatingCapacityAndRange = fleet.stream()
                .sorted(Comparator.comparing(Aircraft::getSeatingCapacity).thenComparing(Aircraft::getRange).reversed())
                .collect(Collectors.toList());
        System.out.println("Aircraft sorted according to seating capacity and then range : max -> min");
        revSortBySeatingCapacityAndRange.forEach(System.out::println);

        //All match
        boolean allMatch = fleet.stream()
                .allMatch(aircraft -> aircraft.getRange() > 2000.0f);
        System.out.println("All aircraft can fly more than 2000km range : " + allMatch);

        //Any match
        boolean anyMatch = fleet.stream()
                .anyMatch(aircraft -> aircraft.getSeatingCapacity() > 400);
        System.out.println("There are aircraft which have more than 400 seating capacity : " + anyMatch);

        //None match
        boolean noneMatch = fleet.stream()
                .noneMatch(aircraft -> aircraft.getMaxPayLoadCapacity() == 118200.0f);
        System.out.println("None of the aircraft carry more than 200000kg payload : " + noneMatch);

        //Max
        Optional<Aircraft> maxPayloadCapacityAircraft = fleet.stream()
                .max(Comparator.comparing(Aircraft::getMaxPayLoadCapacity));
        System.out.println("Aircraft which has highest payload : "
                + maxPayloadCapacityAircraft.get().getManufacturer() + " " + maxPayloadCapacityAircraft.get().getModel());

        //Min
        fleet.stream()
                .min(Comparator.comparing(Aircraft::getRange))
                .ifPresent(aircraft -> System.out.print("Aircraft which has the shortest range : "
                        + aircraft.getManufacturer() + " " + aircraft.getModel() + "\n\n"));

        //Group
        System.out.println("Group by manufacturer");
        Map<ManufacturerEnum, List<Aircraft>> groupByManufacturer = fleet.stream()
                .collect(Collectors.groupingBy(Aircraft::getManufacturer));

        groupByManufacturer.forEach((manufacturerEnum, aircrafts) ->
                aircrafts.forEach(System.out::println));

        //Chaining
        Optional<String> airbusAircraftWithMaxPayload = fleet.stream()
                .filter(aircraft -> aircraft.getManufacturer().equals(ManufacturerEnum.AIRBUS))
                .max(Comparator.comparing(Aircraft::getMaxPayLoadCapacity))
                .map(Aircraft::toString);

        System.out.println("Airbus aircraft which has highest payload capacity :");
        System.out.println(airbusAircraftWithMaxPayload);
    }
}