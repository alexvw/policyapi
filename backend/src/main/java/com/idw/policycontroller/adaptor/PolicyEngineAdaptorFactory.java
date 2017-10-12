package com.idw.policycontroller.adaptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PolicyEngineAdaptorFactory {

	public static IPolicyEngineAdaptor getPolicyEngineAdaptor(String adaptorClassName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//clazz it up
		Class<?> clazz;
		//reflect on that clazz for a moment
		clazz = Class.forName(adaptorClassName);
		//show some clazz
		Constructor<?> ctor = clazz.getConstructor();
		IPolicyEngineAdaptor theAdaptor = (IPolicyEngineAdaptor) ctor.newInstance(new Object[] {  });		
		//return if found, if not this will throw exception. Exception handled within service
		return theAdaptor;
	}	
}
