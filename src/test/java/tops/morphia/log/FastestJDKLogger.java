package tops.morphia.log;

import java.util.logging.Level;

public class FastestJDKLogger  implements tops.morphia.log.Logger{
    private final Logger logger;
    private final transient String className;
    
    public FastestJDKLogger(final Class c) {
        className = c.getName();
        logger = java.util.logging.Logger.getLogger(className);
    }
    
	@Override
	public void debug(final String msg) {
		log(Level.FINE,msg);
	}

	@Override
	public void debug(String msg, Object... arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String mag, Object... arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg, Object... arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String mag, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWrningEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trace(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String msg, Object... arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String msg, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warning(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warning(String msg, Object... arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warning(String mag, Throwable t) {
		// TODO Auto-generated method stub
		
	}
	 protected void log(final Level l, final String f, final Object... a) {
	        if (logger.isLoggable(l)) {
	            logger.logp(l, className, null, f, a);
	        }
	    }
}
