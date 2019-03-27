package hedieuhanh.laplichcpu;

public class Process {
	int id;
	int arrivalTime;
	int burstTime;
	int waitTime;
	int saveTime;
	
	
	public Process() {
		super();
		this.waitTime = 0;
		this.saveTime = 0;
	}

	public Process(int id, int arrivalTime, int burstTime) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.waitTime = 0;
		this.saveTime = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getBurstTime() {
		return burstTime;
	}
	
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(int saveTime) {
		this.saveTime = saveTime;
	}
	
	
}
