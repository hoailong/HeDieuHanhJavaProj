package hedieuhanh.laplichcpu;
import java.util.List;

public class Scheduling {
	public static void run(List<Process> list) {
		
	}
	
	public static double avgWaitTime(List<Process> list) {
		double sumWaitTime = 0;
		for (int i = 0; i < list.size(); i++) {
			sumWaitTime+=list.get(i).getWaitTime();
		}
		return sumWaitTime/list.size();
	}
	
	public static double avgSaveTime(List<Process> list) {
		double sumSaveTime = 0;		
		for (int i = 0; i < list.size(); i++) {
			sumSaveTime+=list.get(i).getSaveTime();
		}
		return sumSaveTime/list.size();
	}
	
	public static void displayResult(List<Process> list) {
/*		System.out.println("\n\nProcess  |  Arrival Time  |  Burst Time  |  Wait Time  |  Save Time");
		for (Process process : list) {
			System.out.printf("%6c%d  |  %12d  |  %10d  |  %9d  |  %9d\n" ,'P',process.getId(),process.getArrivalTime(),process.getBurstTime(),process.getWaitTime(),process.getSaveTime());
		}*/
		System.out.println("\n\nProcess  |  Wait Time  |  Save Time");
		for (Process process : list) {
			System.out.printf("%6c%d  |  %9d  |  %9d\n" ,'P',process.getId(),process.getWaitTime(),process.getSaveTime());
		}
		System.out.printf("\nThoi gian cho trung binh: %.2f",avgWaitTime(list));
		System.out.printf("\nThoi gian luu trung binh: %.2f\n",avgSaveTime(list));
	}
}
