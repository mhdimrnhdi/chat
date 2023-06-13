/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.io.Serializable;

public class Invitation implements Serializable{

	private static final long serialVersionUID = 2911619761941473143L;
	protected Profile source, recipient;

	public Invitation(Profile source, Profile recipient) {
		this.source = source;
		this.recipient = recipient;
	}

	public Profile getSource() {
		return source;
	}

	public Profile getRecipient() {
		return recipient;
	}

	public String toString() {
		return this.getSource().getUsername() + " " + this.getSource().getName()
				+ " has sent an invitation to "
				+ this.getRecipient().getUsername() + " "
				+ this.getRecipient().getName();
	}

}

