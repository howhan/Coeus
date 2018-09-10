package application;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class SystemMan extends Plug {
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
