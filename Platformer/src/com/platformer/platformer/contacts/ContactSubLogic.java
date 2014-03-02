package com.platformer.platformer.contacts;

import com.badlogic.gdx.physics.box2d.Contact;

/**
 * 
 * This class beaks down the logic that is invoked by the contact listner.
 * 
 *  Each sublogic will be invoked by different
 *  conditions in by an ContactListner Implementation
 * @author jeppe
 *
 */
public abstract class ContactSubLogic {
	/**
	 * invoked by the contact listner implementation.
	 * <br>
	 * 
	 */
	abstract public void endLogic(Contact contact);
	
	abstract public void beginLogic(Contact contact);

}
