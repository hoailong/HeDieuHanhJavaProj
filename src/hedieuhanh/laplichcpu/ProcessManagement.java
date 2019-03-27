package hedieuhanh.laplichcpu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProcessManagement {
	static List<Process> listProcess = new ArrayList<Process>();
	static int numberProcess = 0;
	static int quantum = 2;
	static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {
		getData();
		display();
		System.out.println("\n+ FCFS:");
		FCFS.run(listProcess);
		System.out.println("\n+ SJF:");
		SJF.run(listProcess);
		System.out.println("\n+ SRN:");
		SRN.run(listProcess);
		System.out.println("\n+ RR:");
		RR.run(listProcess,quantum);
		System.out.println("\n+ RR v2:");
		RRv2.run(listProcess,quantum);
	}
	
	public static void getData() {
		int choice = 0;		
		
		System.out.println("1.Nhap du lieu");
		System.out.println("2.Lay du lieu tu file data");
		do {
			System.out.println("Ban chon ? ");
			choice = Integer.parseInt(sc.nextLine());
		} while (choice != 1 && choice != 2);
		
		
		switch(choice) {
			case 1:inputData();
				break;
			case 2:readData();
				break;
			default:;
		}
		
		Collections.sort(listProcess, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) {
				if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
				if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
				else {
					if (o1.getBurstTime() < o2.getBurstTime()) return -1;
					if (o1.getBurstTime() > o2.getBurstTime()) return 1;
				}
				return 0;
			}
			
		
		});
	}
	public static void inputData() {

		System.out.print("So tien trinh: ");
		ProcessManagement.numberProcess = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < ProcessManagement.numberProcess; i++) {
			int id = i+1;
			int arrivalTime;
			int burstTime;
			
			System.out.print("Process " + (i+1) + ":\n\tArrival Time: ");
			arrivalTime = Integer.parseInt(sc.nextLine());
			System.out.print("\tBurst Time: ");
			burstTime = Integer.parseInt(sc.nextLine());
			
			listProcess.add(new Process(id,arrivalTime,burstTime));
		}

		System.out.print("Quantum: ");
		ProcessManagement.quantum = Integer.parseInt(sc.nextLine());
		
	}

	public static void readData(){
		File file = new File("data1.txt");
		
		try {
			Scanner sc = new Scanner(file);
			numberProcess = sc.nextInt();
			
			for (int i = 0; i < numberProcess; i++) {
				int id = i+1;
				int arrivalTime = sc.nextInt();
				int burstTime = sc.nextInt();
				listProcess.add(new Process(id,arrivalTime,burstTime));
			}
			
			quantum = sc.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void display() {
		System.out.println("-----=CHƯƠNG TRÌNH LẬP LỊCH CPU=-----");
		System.out.println("\nProcess  |  Arrival Time  |  Burst Time  |  Quantum = " + quantum);
		for (Process process : listProcess) {
			System.out.printf("%6c%d  |  %12d  |  %10d\n" ,'P',process.getId(),process.getArrivalTime(),process.getBurstTime());
		}
		System.out.println("\n------------------------------------------------------------");
	}
}
