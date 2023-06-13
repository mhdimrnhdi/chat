/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.io.Serializable;


public class Localization implements Serializable{

	private static final long serialVersionUID = 257404015441928212L;
	protected String city, state, nation;
	
	public Localization(String city, String state, String nation) {
		this.city = city;
		this.state = state;
		this.nation = nation;
	}
	
	public String toString() {
    return "Location: " + city + ", " + state + ", " + nation;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getNation() {
		return nation;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

}
