package tops.morphia.log;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.txw2.IllegalSignatureException;


public final class MorphiaLoggerFactory {
	private static final List<String> FACTORIES=new ArrayList<String>();
	private static LoggerFactory loggerFactory;
	static{
		FACTORIES.add("");
		FACTORIES.add("");
	}
	 private MorphiaLoggerFactory() {
	    }
	public static Logger get(final Class<?> c){
		init();
		return loggerFactory.get(c);
	}
	
	private static synchronized void init(){
		if(loggerFactory==null){
			chooseLoggerFactory();
		}
	}
	
	private static void chooseLoggerFactory(){
		for(final String f :FACTORIES){
			loggerFactory=newInstance(f);
			if(loggerFactory!=null){
				loggerFactory.get(MorphiaLoggerFactory.class).info("LoggerImplFactory set to " + loggerFactory.getClass().getName());
				return ;
			}
		}
		throw new IllegalStateException("Cannot instantiate any MorphiaLoggerFactory");
	}
	
	private static LoggerFactory newInstance(final String f){
		try{
			return (LoggerFactory)Class.forName(f).newInstance();
		}catch (Exception e) {
			return null;
		}
	}
	
	public static void registerLogger(final Class<? extends LoggerFactory> factoryClass){
		if(loggerFactory==null){
			FACTORIES.add(0,factoryClass.getName());
		}else{
			throw new IllegalSignatureException("LoggerImplFactory must be registered before logging is initialized.");
		}
	}
	public static void  reset(){
		loggerFactory=null;
	}
}
