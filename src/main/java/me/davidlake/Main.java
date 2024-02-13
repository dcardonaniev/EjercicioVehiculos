package me.davidlake;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vehiculo> vehiculos = List.of(
                new Vehiculo("Ford", "Fiesta", 1000),
                new Vehiculo("Ford", "Focus", 1200),
                new Vehiculo("Ford", "Explorer", 2500),
                new Vehiculo("Fiat", "Uno", 500),
                new Vehiculo("Fiat", "Cronos", 1000),
                new Vehiculo("Fiat", "torino", 1250),
                new Vehiculo("Chevrolet", "Aveo", 1250),
                new Vehiculo("Chevrolet", "Spin", 2500),
                new Vehiculo("Toyota", "Corola", 1200),
                new Vehiculo("Toyota", "Fortuner", 3000),
                new Vehiculo("Renault", "Logan", 950)
        );

        Garaje garaje = new Garaje(1, vehiculos);

        List<Vehiculo> vehiculosOrdenadosPrecio = garaje
                .getVehiculos()
                .stream()
                .sorted(Comparator
                        .comparing(Vehiculo::getPrecio))
                .toList();

        List<Vehiculo> vehiculosOrdenadosModeloYPrecio = garaje
                .getVehiculos()
                .stream()
                .sorted(Comparator
                        .comparing(Vehiculo::getModelo)
                        .thenComparing(Vehiculo::getPrecio))
                .toList();

        Double precioPromedio = garaje
                .getVehiculos()
                .stream()
                .mapToInt(Vehiculo::getPrecio)
                .average()
                .orElse(0);

        Double averageFord = garaje
                .getVehiculos()
                .stream()
                .filter(v-> v.getModelo().equals("Fiat"))
                .mapToInt(Vehiculo::getPrecio)
                .average()
                .orElse(0);

        List<Vehiculo> vehiculosBaratos = garaje
                .getVehiculos()
                .stream()
                .filter(v -> v.getPrecio() < 1000)
                .toList();

        List<Vehiculo> vehiculosCaros = garaje
                .getVehiculos()
                .stream()
                .filter(v -> v.getPrecio() >= 1000)
                .toList();

        System.out.println("================== ORDENADOS POR PRECIO ==================");
        vehiculosOrdenadosPrecio.forEach(System.out::println);

        System.out.println("================== ORDENADOS POR PRECIO ==================");
        vehiculosOrdenadosModeloYPrecio.forEach(System.out::println);

        System.out.println("================== PRECIO PROMEDIO ==================");
        System.out.println(precioPromedio);

        System.out.println("================== PRECIO PROMEDIO FIAT ==================");
        System.out.println(averageFord);

        System.out.println("================== PRECIO < 1000 ==================");
        vehiculosBaratos.forEach(System.out::println);

        System.out.println("================== PRECIO >= 1000 ==================");
        vehiculosCaros.forEach(System.out::println);
    }
}