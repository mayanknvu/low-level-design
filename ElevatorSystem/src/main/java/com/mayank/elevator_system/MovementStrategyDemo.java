package com.mayank.elevator_system;

import com.mayank.elevator_system.core.enums.ElevatorDirection;
import com.mayank.elevator_system.strategies.movement.*;

import java.util.Arrays;
import java.util.List;

public class MovementStrategyDemo {
    public static void main(String[] args) {
        // Test scenario
        List<Integer> requests = Arrays.asList(3, 8, 2, 7, 1, 9);
        int currentFloor = 5;
        ElevatorDirection direction = ElevatorDirection.UP;

        System.out.println("=== Movement Strategy Comparison ===");
        System.out.println("Current Floor: " + currentFloor);
        System.out.println("Current Direction: " + direction);
        System.out.println("Pending Requests: " + requests);
        System.out.println();

        // Test all strategies
        testStrategy("FCFS", new FCFSMovementStrategy(), requests, currentFloor, direction);
        testStrategy("SSTF", new SSTFMovementStrategy(), requests, currentFloor, direction);
        testStrategy("SCAN", new SCANMovementStrategy(), requests, currentFloor, direction);
        testStrategy("LOOK", new LOOKMovementStrategy(), requests, currentFloor, direction);
    }

    private static void testStrategy(String name, IElevatorMovementStrategy strategy,
                                     List<Integer> requests, int currentFloor, ElevatorDirection direction) {
        List<Integer> serviceOrder = strategy.getNextStops(requests, currentFloor, direction);
        int totalDistance = calculateTotalDistance(serviceOrder, currentFloor);
        int directionChanges = calculateDirectionChanges(serviceOrder, currentFloor);

        System.out.println("--- " + name + " Strategy ---");
        System.out.println("Service Order: " + serviceOrder);
        System.out.println("Total Distance: " + totalDistance + " floors");
        System.out.println("Direction Changes: " + directionChanges);
        System.out.println("Movement Path: " + getMovementPath(serviceOrder, currentFloor));
        System.out.println();
    }

    private static int calculateTotalDistance(List<Integer> serviceOrder, int startFloor) {
        if (serviceOrder.isEmpty()) return 0;

        int totalDistance = 0;
        int currentPos = startFloor;

        for (int floor : serviceOrder) {
            totalDistance += Math.abs(floor - currentPos);
            currentPos = floor;
        }

        return totalDistance;
    }

    private static int calculateDirectionChanges(List<Integer> serviceOrder, int startFloor) {
        if (serviceOrder.size() <= 1) return 0;

        int changes = 0;
        int currentPos = startFloor;
        Boolean lastDirection = null;

        for (int floor : serviceOrder) {
            boolean goingUp = floor > currentPos;
            if (lastDirection != null && lastDirection != goingUp) {
                changes++;
            }
            lastDirection = goingUp;
            currentPos = floor;
        }

        return changes;
    }

    private static String getMovementPath(List<Integer> serviceOrder, int startFloor) {
        if (serviceOrder.isEmpty()) return "No movement";

        StringBuilder path = new StringBuilder();
        path.append(startFloor);

        for (int floor : serviceOrder) {
            path.append(" → ").append(floor);
        }

        return path.toString();
    }
}

