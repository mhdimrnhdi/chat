
package facemash;

import java.io.*;
import java.util.*;

public class Profile {
   
    protected String name, username, email, contactNumber, birthday, gender, 
                            password, reTypedPassword, relationshipStatus;
    protected boolean activation;
    protected int age;
    protected Localization address;
    protected List<String> hobbies;
    protected List<String> jobHistories;
    protected List<Profile> friends;
    protected ArrayList<Invitation> invitationList, invitationSourceList;
    protected ArrayList<Profile> friendsList;
    private LinkedList<String> interactionHistory;

    public Profile() {}

    public Profile(String name, String username, String password,
    String birthday, Localization address, String email,
    boolean activation, String contactNumber, String gender, 
    String reTypedPassword, String relationshipStatus, List<String> hobbies,
    List<String> jobHistories) {
        this(name, username, password, birthday, address, email, activation);
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.reTypedPassword = reTypedPassword;
        this.relationshipStatus = relationshipStatus;
        this.hobbies = hobbies;
        this.jobHistories = jobHistories;
    }

	public Profile(String name, String username, String password,
			String birthday, Localization address, String email,
			boolean activation) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.activation = activation;
		this.invitationList = new ArrayList<Invitation>();
		this.invitationSourceList = new ArrayList<Invitation>();
		this.friendsList = new ArrayList<Profile>();
	}

    public String toString() {
		return "name: " + this.name 
                + "\nusername: " + this.username
				+ "\nbirthday: " + this.birthday.toString() 
                + "\naddress: "	+ this.address.toString() 
                + "\nemail: " + this.email 
                + "\ncontactNumber: " + this.contactNumber
                + "\ngender: " + this.gender
                + "\nrelationshipStatus: " + this.relationshipStatus
                + "\nhobbies: " + this.hobbies
                + "\njobHistories: " + this.jobHistories;
	}


	public void showFriends() {
		for (int i = 0; i < this.friendsList.size(); i++)
			System.out.println(i + ") " + this.friendsList.get(i).getUsername()
					+ " " + this.friendsList.get(i).getName());
        //addToInteractionHistory("Viewed friend list");
	}

	public void showFriendProfile() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		if (!this.friendsList.isEmpty()) {
			showFriends();
			System.out.println("Choose a friend: ");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				choice = -1;
			}
			if (choice > -1 && choice < this.friendsList.size()) {
                Profile friend = this.friendsList.get(choice);
                //addToInteractionHistory("Viewed friend profile: " + friend.getUsername());
                System.out.println(friend);
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("You have no friend.");
	}

	public void displayInvitation() {
		if (!this.getInvitationList().isEmpty())
			System.out.println(this.getInvitationList().toString());
		else
			System.out.println("No invitations.");
	}

	public void sendInvitation(Profile profile) {
		if(!this.friendsList.contains(profile)){
			this.invitationSourceList.add(new Invitation(this, profile));
			profile.getInvitationList().add(new Invitation(this, profile));
            //addToInteractionHistory("Sent an invitation to " + profile.getUsername());
			System.out.println("An invitation was sended");
		}else
			System.out.println("It is your friend");
	}

	public void acceptInvitation() {
		Scanner scanner = new Scanner(System.in);
		while (!this.getInvitationList().isEmpty()) {
			Invitation i = this.invitationList.get(0);
			System.out.println(i.getSource().getUsername() + " " + i.getSource().getName() + " wants to add you on Facebook.");
			System.out.println("insert  accept  or  refuse");
			String answer = scanner.nextLine();
			if (answer == "accept") {
				if(!this.friendsList.contains(i.source)){
					this.getFriendsList().add(i.getSource());
					i.getSource().getFriendsList().add(i.getRecipient());
					this.getInvitationList().remove(i);
					i.getSource().getInvitationSourceList().remove(i);
                    //addToInteractionHistory("Accepted invitation from " + i.getSender().getUsername());
					System.out.println(i.source + "was added to your friend list");
				}else
					System.out.println("It is your friend");
			} else if (answer == "refuse") {
				this.getInvitationList().remove(i);
				i.getSource().getInvitationSourceList().remove(i);
                //addToInteractionHistory("Refused invitation from " + i.getSender().getUsername());
			} else {
				System.out.println("Try again. Only words are accept or refuse.");
			}
		}
	}

	public void cancelInvitation() {
		Scanner scanner = new Scanner(System.in);
		int option;
		if (!this.getInvitationSourceList().isEmpty()) {
			int k = 0;
			for (Invitation i : this.invitationSourceList)
				System.out.println(k + ") " + i);
			System.out.println("Choose an invitation: ");
			try {
				option = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				option = -1;
			}
			if (option > -1 && option < this.invitationSourceList.size()) {
				Invitation i = this.invitationSourceList.get(option);
				i.getRecipient().getInvitationList().remove(i);
				this.invitationSourceList.remove(i);
                //addToInteractionHistory("Canceled invitation to " + i.getReceiver().getUsername());
				System.out.println("Remove invitation.");
			} else
				System.out.println("Invalid input.");
		} else
			System.out.println("There are no invitation.");
	}

	public void serialiser(String path) throws IOException {
		String str = path + this.email + ".bin";
		ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(str));
		try {
			object.writeObject(this);
		} catch (IOException ioe) {
			System.err.println("FATAL ERROR " + ioe.toString());
		}
		object.close();
	}

	public Profile deserialiser(String email) throws IOException {
		String nomFic = email + ".bin";
		Profile temporary = null;
		ObjectInputStream object = new ObjectInputStream(new FileInputStream(nomFic));
		try {
			temporary = (Profile) object.readObject();
		} catch (IOException ioe) {
			System.err.println("FATAL ERROR -- " + ioe.toString());
		} catch (ClassNotFoundException cnfe) {
			System.err.println("ERROR 'Unknown class' -- " + cnfe.toString());
		}
		object.close();
		return temporary;
	}

    // Getters and setters for each property

    // Getter for the name property
    public String getName() {
        return name;
    }

    // Setter for the name property
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the username property
    public String getUsername() {
        return username;
    }

    // Setter for the username property
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for the email property
    public String getEmail() {
        return email;
    }

    // Setter for the email property
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for the contactNumber property
    public String getContactNumber() {
        return contactNumber;
    }

    // Setter for the contactNumber property
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Getter for the birthday property
    public String getBirthday() {
        return birthday;
    }

    // Setter for the birthday property
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Getter for the gender property
    public String getGender() {
        return gender;
    }

    // Setter for the gender property
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter for the hobbies property
    public List<String> getHobbies() {
        return hobbies;
    }

    // Setter for the hobbies property
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    // Getter for the jobHistories property
    public List<String> getJobHistories() {
        return jobHistories;
    }

    // Setter for the jobHistories property
    public void setJobHistories(List<String> jobHistories) {
        this.jobHistories = jobHistories;
    }

    // Getter for the friends property
    public List<Profile> getFriends() {
        return friends;
    }

    // Setter for the friends property
    public void setFriends(List<Profile> friends) {
        this.friends = friends;
    }

    // Getter for the password property
    public String getPassword() {
        return password;
    }

    // Setter for the password property
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for the reTypedPassword property
    public String getReTypedPassword() {
        return reTypedPassword;
    }

    // Setter for the reTypedPassword property
    public void setReTypedPassword(String reTypedPassword) {
        this.reTypedPassword = reTypedPassword;
    }

    // Getter for the relationshipStatus property
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    // Setter for the relationshipStatus property
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    // Getter for the age property
    public int getAge() {
        return age;
    }

    // Setter for the age property
    public void setAge(int age) {
        this.age = age;
    }

    public Localization getPlace() {
		return address;
	}

	public void setPlace(Localization address) {
		this.address = address;
	}


	public boolean getActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	public ArrayList<Profile> getFriendsList() {
		return this.friendsList;
	}

	public void setFriendsList(ArrayList<Profile> friendsList) {
		this.friendsList = friendsList;
	}

	public ArrayList<Invitation> getInvitationList() {
		return invitationList;
	}

	public void setInvitationList(ArrayList<Invitation> invitationList) {
		this.invitationList = invitationList;
	}

	public ArrayList<Invitation> getInvitationSourceList() {
		return invitationSourceList;
	}

        
	public void addToInteractionHistory(String interaction) {
        interactionHistory.add(interaction);
    }

    public void clearInteractionHistory() {
        interactionHistory.clear();
    }

    public void printInteractionHistory() {
        for (String interaction : interactionHistory) {
            System.out.println(interaction);
        }
    }
}
