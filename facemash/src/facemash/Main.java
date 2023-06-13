/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.io.IOException;
import java.util.*;


public class Main {

	public static void main(String[] args) {

		String emailInsert, passwordInsert;
		int option, option2, option3;
		Scanner scanner = new Scanner(System.in);
		SocialNetwork facemash = new SocialNetwork();
		try {
			facemash = facemash.deserialiser();
		} catch (IOException e1) {
			System.err.println("Loading error.");
		}
		System.out.println("Welcome to Facebook. Come and and connect with " + facemash.getUsersNumbers() +" user(s)");
		do{
			System.out.println("What do you want to do?");
			System.out.println("1 Create account");
			System.out.println("2 Open session");
			System.out.println("3 See the list of session");
			System.out.println("4 Exit");
			try{
				option = Integer.parseInt(scanner.nextLine());
			}catch(Exception e){
				option = -1;
			}
			switch(option) {
			case(1):
				facemash.createAccount();
				break;
			case(2):
				System.out.println("Your email and password:");
				System.out.println("Email: ");
				emailInsert = scanner.nextLine();
				System.out.println("Password: ");
				passwordInsert = scanner.nextLine();
				facemash.openSession(emailInsert, passwordInsert);
				if(facemash.getCurrentUser() != null){
					
					do{
						System.out.println("1 See the list of friends");
						System.out.println("2 See a friend's profile");
						System.out.println("3 Send an invitation");
						System.out.println("4 Cancel an invitation");
						System.out.println("5 View invitation list");
						System.out.println("6 See the friend of yours friends");
						System.out.println("7 See the friend suggestions");
						System.out.println("8 Find the mutual friends between 2 people");
						System.out.println("9 Log out");
						try{
							option2 = Integer.parseInt(scanner.nextLine());
						}catch(Exception e){
							option2 = -1;
						}
						switch(option2) {
						case 1:
							facemash.getCurrentUser().showFriends();
							break;
						case 2:
							facemash.getCurrentUser().showFriendProfile();
							break;
						case 3:
							System.out.println("Enter the email for this invitation");
							String email_input = scanner.nextLine();
							Profile profile = null;
							for (Profile personalInfo : facemash.getRegisteredUsers()) {
								if (personalInfo.getEmail().equals(email_input)) {
									profile = personalInfo;
									break;
								}
							}
							
							if (profile != null) {
								facemash.getCurrentUser().sendInvitation(profile);
							} else {
								System.out.println("No existence of such profile.");
							}
							break;
						case 4:
							facemash.getCurrentUser().cancelInvitation();
							break;
						case 5:
							facemash.getCurrentUser().acceptInvitation();
							break;
						case 6:
							System.out.println("Enter max depth:");
							try{
								option3 = Integer.parseInt(scanner.nextLine());
							}catch(Exception e){
								option3 = -1;
							}
							if (option3 >= 1 && option3 <= 5) {
								List<Profile> alreadySeen = new ArrayList<>();
								facemash.friendOfFriend(alreadySeen, facemash.getCurrentUser(), option3, "");
								break;
							} else {
								System.out.println("Invalid max depth. Please enter a value between 1 and 10.");
								break;
							}
						case 7:
							Profile currentUser = facemash.getCurrentUser();
							List<Profile> friendSuggestions = facemash.suggestFriends(currentUser);
							System.out.println("Friend suggestions:");
							for (int i = 0; i < friendSuggestions.size(); i++) {
								System.out.println((i + 1) + ") " + friendSuggestions.get(i).getName());
							}
							break;
						case 8:
							System.out.println("Enter the name and surname of your friend:");
							System.out.print("Name: ");
							String friendName = scanner.nextLine();
							System.out.print("Surname: ");
							String friendSurname = scanner.nextLine();
						
							// Search for the friend profile in the registeredUsers list
							Profile friendProfile = facemash.search(friendName, friendSurname);
							if (friendProfile == null) {
								System.out.println("Friend profile not found.");
							} else {
								// Find mutual friends between the user profile and the friend profile
								List<Profile> mutualFriends = facemash.findMutualFriends(friendProfile);
						
								if (mutualFriends.isEmpty()) {
									System.out.println("You have no mutual friends with " + friendProfile.getName() + " " + friendProfile.getUsername());
								} else {
									System.out.println("Mutual friends with " + friendProfile.getName() + " " + friendProfile.getUsername() + ":");
									for (Profile mutualFriend : mutualFriends) {
										System.out.println(mutualFriend.getName() + " " + mutualFriend.getUsername());
									}
								}
							}
							break;						
						default:
							break;
						}
					}while(option != 9);
				}
				break;
			case(3):
				facemash.displayRegisteredUsers();
				break;
			default:
				break;
			}
		}
		while(option != 4);
		try {
			facemash.serialiser();
		} catch (IOException e) {
			System.err.println("Can't save!");
		}
	}

}