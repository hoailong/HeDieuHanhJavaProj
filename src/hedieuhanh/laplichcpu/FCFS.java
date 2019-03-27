package hedieuhanh.laplichcpu;
import java.util.List;

public class FCFS extends Scheduling {
	public static void run(List<Process> list) {
		int timeRunning = list.get(0).getArrivalTime(); // luu tong tgian cac tien trinh da chay,bat dau = tgian tien trinh dau tien xuat hien
		System.out.print(timeRunning);

		for (Process process : list) { 								   // chay tu dau den cuoi danh sach
			process.setWaitTime(timeRunning-process.getArrivalTime());// tgian cho cua tien trinh nay 
																	 // = tong tgian cac tt truoc chay - tgian xuat hien cua tt nay
			
			process.setSaveTime(process.getWaitTime() + process.getBurstTime());// tgian luu = tgian cho + tgian thuc hien
			
			timeRunning+=process.getBurstTime();  
			
			//in so do
			System.out.print("__(P" + process.getId() + ")__");
			System.out.print(timeRunning);
		}
		displayResult(list); // hien thi ket qua thuat toan
	}
}