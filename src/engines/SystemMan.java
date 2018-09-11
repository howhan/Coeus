package engines;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import application.Tableau;

public class SystemMan extends Tableau {
	OperatingSystemMXBean mOperatingSystemMXBean;
	
	public SystemMan() {
		mOperatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();	
	};
	
	
	public double getSystemLoadAverage() {
		return mOperatingSystemMXBean.getSystemLoadAverage();
	}
	
	public double getAvailProcessors() {
		return mOperatingSystemMXBean.getAvailableProcessors();
	}
	
	public double getFreeCPU() {
		return getSystemLoadAverage()/getAvailProcessors();		
	}
	
};
