package yogi.base.io.delayed;

import java.util.ArrayList;
import java.util.List;

public class ComplexModifier<C> implements Modifier<C>{
	private List<Modifier<C>> modifiers = new ArrayList<Modifier<C>>();
	
	public void addModifier(Modifier<C> modifier){
		this.modifiers.add(modifier);
	}
	
	public List<C> modify(List<C> objects) {
		for(Modifier<C> modifier : modifiers){
			objects = modifier.modify(objects);
		}
		return objects;
	}
	
	public List<C> modify(C object) {
		List<C> rtnValue = new ArrayList<C>();
		rtnValue.add(object);
		for(Modifier<C> modifier : modifiers){
			rtnValue = getModifiedCreators(modifier, rtnValue);
		}
		return rtnValue;
	}
	
	private List<C> getModifiedCreators(Modifier<C> modifier, List<C> creators){
		List<C> modifiedCreators = new ArrayList<C>();
		for(C creator : creators){
			modifiedCreators.addAll(modifier.modify(creator));
		}
		return modifiedCreators;
	}
}
