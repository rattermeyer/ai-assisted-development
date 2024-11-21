package com.opitzconsulting.demo.copilot.javademo;

public class ConditionalCode {

    /**
     * Entweder:
     * rewrite the condition to use a switch and use Java 21 syntax with null case, but also add documentation and provide a better function name.
     *
     * Oder:
     * simplify this method.
     */
    public String getSound(String animal) {
        if (animal == null) {
            System.out.println("Oops! A null animal?");
        } else if (animal.equalsIgnoreCase("Dog")) {
            return "Bark";
        } else if ( animal.equalsIgnoreCase("Cat")) {
            return "Meow";
        } else if ( animal.equalsIgnoreCase("Bird")) {
            return "Tweet";
        }
        return "Unknown";
    }
}
