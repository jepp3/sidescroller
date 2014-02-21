package com.platformer.platformer;

import com.badlogic.gdx.utils.Array;



public class InputLogicWraper {
	private Array<InputSublogic> sublogics;
	private int activeInput;
	
	public InputLogicWraper(Array<InputSublogic> sublogicImp) {
		sublogics = sublogicImp;
		activeInput = 0;
	}
	public InputLogicWraper() {
		activeInput = 0;
		sublogics = new Array<InputSublogic>();
	}
	public void process()
	{
		sublogics.get(activeInput).process();
	}
	
	public void setActiveInput(int index)
	{
		activeInput = index;
	}
	
	public void addSubLogic( InputSublogic subLogic)
	{
		sublogics.add(subLogic);
	}
}
