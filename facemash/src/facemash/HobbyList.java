/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.util.ArrayList;
import java.util.List;

public class HobbyList {
    private List<String> hobbies;

    public void initializeHobbies() {
        hobbies = new ArrayList<>();

        // Add the hobbies to the ArrayList
        hobbies.add("Painting or drawing");
        hobbies.add("Photography");
        hobbies.add("Gardening");
        hobbies.add("Cooking or baking");
        hobbies.add("Playing a musical instrument");
        hobbies.add("Writing");
        hobbies.add("Reading books or starting a book club");
        hobbies.add("Hiking or nature walks");
        hobbies.add("Yoga or meditation");
        hobbies.add("DIY crafts or upcycling");
        hobbies.add("Learning a new language");
        hobbies.add("Playing board games or puzzles");
        hobbies.add("Knitting or crocheting");
        hobbies.add("Playing sports");
        hobbies.add("Woodworking or carpentry");
        hobbies.add("Volunteering");
        hobbies.add("Collecting");
        hobbies.add("Learning to code or programming");
        hobbies.add("Astronomy or stargazing");
        hobbies.add("Dancing");
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
