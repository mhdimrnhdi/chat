/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.io.*;
import java.util.*;

public class SocialNetwork implements Serializable {

    private static final long serialVersionUID = 902665912334739241L;
    private List<String> registeredUserKeys; // like identifiers but using email
    private List<Profile> registeredUsers; 
    private Profile currentUser; // currently logged-in user in the social network

    public SocialNetwork() { //initialisation
        this.registeredUserKeys = new ArrayList<>(); // can be serializable and deserializable
        this.registeredUsers = new ArrayList<>(); // can be serializable and deserializable
        this.currentUser = null;
    }

    public SocialNetwork(List<String> keys, List<Profile> profiles) {
        this.registeredUserKeys = keys;
        this.registeredUsers = profiles;
    }

    // display all registered users on social network
    public void displayRegisteredUsers() {
        if (!registeredUsers.isEmpty()) {
            int count = 0;
            for (Profile profile : registeredUsers) {
                String key = registeredUserKeys.get(count);
                System.out.println("User ~" + count++ + " (Key: " + key + ")"); // show email and update at the same time
                System.out.println(profile); // call toString() method in class Profile
            }
        } else {
            System.out.println("No such session.");
        }
    }

    // display friends pf a user's friends 
    // depth limit prevent overflow 
    // tab for indentation in the console output to represent the depth level
    public void friendOfFriend(List<Profile> adySeen, Profile profile, int maxdepth, String whitespace) {
        if (maxdepth < 0) {
            return;
        } else {
            int count = 0;
            for (Profile friend : profile.getFriendsList()) {
                System.out.println(whitespace + count++ + ") " + friend.getName() + " " + friend.getUsername());
                if (!adySeen.contains(friend)) {
                    adySeen.add(friend);
                    friendOfFriend(adySeen, friend, maxdepth - 1, whitespace + "\t");
                }
            }
        }
    }

    // return number of registered users
    public int getUsersNumbers() {
        return this.registeredUsers.size();
    }

    public void createAccount() {
        // ask the user for all the informations
		Scanner scanner = new Scanner(System.in);
		System.out.println("To create a new account, pls enter the informations of ");
		System.out.println("name: ");
		String name = scanner.nextLine();
		System.out.println("username: ");
		String username = scanner.nextLine();
		System.out.println("password: ");
		String password = scanner.nextLine();
        System.out.println("retype password:");
        String reTypedPassword = scanner.nextLine();
		System.out.println("date of bith (mm-dd-yyyy): ");
		String birthday = scanner.nextLine();
		System.out.println("city: ");
		String city = scanner.nextLine();
		System.out.println("state: ");
		String state = scanner.nextLine();
		System.out.println("nation: ");
		String nation = scanner.nextLine();
		System.out.println("email: ");
		String email = scanner.nextLine();
        System.out.println("contact number:");
        String contact = scanner.nextLine();
        System.out.println("gender:");
        String gender = scanner.nextLine();
        System.out.println("relationship status:");
        String relationshipStatus = scanner.nextLine();
        HobbyList hobbyList = new HobbyList();
        hobbyList.initializeHobbies();
        promptForHobbySelections(hobbyList);
        System.out.println("hobbies?");
        List<String> hobbies = Arrays.asList(scanner.nextLine().split(","));
        JobList jobList = new JobList();
        jobList.initializeJobs();
        promptForJobSelections(jobList);
        System.out.println("jobs?");
        List<String> jobHistories = Arrays.asList(scanner.nextLine().split(","));
		Localization address = new Localization(city, state, nation);
		Profile newProfile = new Profile(name, username, password, birthday, address, 
                                email, true, contact, gender, reTypedPassword, 
                                relationshipStatus, hobbies, jobHistories);

        // check if the person is already on the network
        int index = registeredUserKeys.indexOf(email);
        if (index == -1) {
            registeredUserKeys.add(email);
            registeredUsers.add(newProfile);
            System.out.println("You successfully create your account!");
        } else {
            System.out.println("This account was registered. Pls try another information.");
        }
    }

    // it is login in another word
    // login by key in email and password
    public void openSession(String email, String password) {
        int index = registeredUserKeys.indexOf(email);
        if (index == -1) {
            System.out.println("Wrong email insertion.");
        } else {
            Profile objPersonLogin = registeredUsers.get(index);
            /*three cases:  1. wrong password
                            2. haven't activated account means haven't registered account
                            3. correct otherwise 
            */
            if (!objPersonLogin.getPassword().equals(password)) {
                System.out.println("Incorrect password.");
            } else if (!objPersonLogin.getActivation()) {
                System.out.println("The account has not yet been activated.");
            } else {
                System.out.println("User identified.");
                System.out.println("Welcome, " + objPersonLogin.getUsername() + "!");
                currentUser = objPersonLogin;
            }
        }
    }

    /*  currentUser can search for someone using name and username
        assume two people cannot have the same names and username 
    */
    public Profile search(String name, String username) {
        Profile found = null;
        for (Profile profile : registeredUsers) {
            if (profile.getName().equals(name) && profile.getUsername().equals(username)) {
                found = profile;
                break;
            }
        }
        return found;
    }

    public Profile getCurrentUser() {
        return currentUser;
    }

    public List<Profile> getRegisteredUsers() {
        return registeredUsers;
    }

    public List<Profile> findMutualFriends(Profile user) {
        List<Profile> mutualFriends = new ArrayList<>();
    
        List<Profile> friendsProfile1 = currentUser.getFriendsList();
        List<Profile> friendsProfile2 = user.getFriendsList();
    
        for (Profile friendProfile1 : friendsProfile1) {
            if (friendsProfile2.contains(friendProfile1)) {
                mutualFriends.add(friendProfile1);
            }
        }
    
        return mutualFriends;
    }

    public List<Profile> suggestFriends(Profile user) {
        List<Profile> friendSuggestions = new ArrayList<>();
    
        // Retrieve the user's existing friends
        List<Profile> userFriends = user.getFriendsList();
    
        // Iterate through all registered users
        for (Profile profile : registeredUsers) {
            // Skip the user and their existing friends
            if (profile.equals(user) || userFriends.contains(profile)) {
                continue;
            }
    
            // Check if the profile has any common friends with the user
            List<Profile> commonFriends = new ArrayList<>(userFriends);
            commonFriends.retainAll(profile.getFriendsList());
    
            // Check if the common friends are already friends of the user
            boolean alreadyFriends = false;
            for (Profile commonFriend : commonFriends) {
                if (userFriends.contains(commonFriend)) {
                    alreadyFriends = true;
                    break;
                }
            }
    
            // If the profile has at least one common friend who is not already a friend of the user, add it to the suggestions
            if (!alreadyFriends && !commonFriends.isEmpty()) {
                friendSuggestions.add(profile);
            }
        }
    
        return friendSuggestions;
    }

    public void promptForJobSelections(JobList jobList) {
        List<String> jobs = jobList.getJobs();

        System.out.println("Job Options:");
        for (int i = 0; i < jobs.size(); i++) {
            System.out.println((i + 1) + ". " + jobs.get(i));
        }
        System.out.println((jobs.size() + 1) + ". Other");
    }

    public static void promptForHobbySelections(HobbyList hobbyList) {
        List<String> hobbies = hobbyList.getHobbies();

        System.out.println("List of hobbies:");
        for (int i = 0; i < hobbies.size(); i++) {
            System.out.println((i + 1) + ". " + hobbies.get(i));
        }
        System.out.println(hobbies.size() + ". Other");
    }
    
    public void serialiser() throws IOException {
        ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream("facemash.bin"));
        try {
            object.writeObject(this);
        } catch (IOException ioe) {
            System.err.println("Error from recovering the data information " + ioe.toString());
        }
        object.close();
    }

    public SocialNetwork deserialiser() throws IOException {
        SocialNetwork temporary = null;
        ObjectInputStream object = new ObjectInputStream(new FileInputStream("facebook.bin"));
        try {
            temporary = (SocialNetwork) object.readObject();
        } catch (IOException ioe) {
            System.err.println("ERROR " + ioe.toString());
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR from inserting the data information " + e.toString());
        }
        object.close();
        return temporary;
    }

}
