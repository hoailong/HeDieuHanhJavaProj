package hedieuhanh.laplichcpu;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RRv2 extends Scheduling {
	public static void run(List<Process> list, int quantumTime) {
		
		int burstTimeArr[] = new int[list.size()];  // Mang thoi gian thuc hien con lai cua tien trinh
		for (int i = 0; i < list.size(); i++) {
			burstTimeArr[i] = list.get(i).getBurstTime();
		}
		
		Queue<Integer> qe = new LinkedList<Integer>(); // Hang doi cho thuc hien (luu id tien trinh cho thuc hien)
		
		boolean checkArrivaled[] = new boolean[list.size()]; //Mang kiem tra tien trinh da xuat hien chua	
		for (int i = 0; i < list.size(); i++) {
			checkArrivaled[i] = false;
		}
		
		int timeRunning = list.get(0).getArrivalTime();
		qe.add(0);	//dua tien trinh dau tien vao hang doi
		checkArrivaled[0] = true;	//danh dau tien trinh dau tien da den
		
		System.out.print(timeRunning);
		
		while (!qe.isEmpty()) {	//chay den khi hang doi thuc hien trong
			int index = qe.poll(); 	//lay tien trinh dau tien trong hang doi ra chay
			
			if (burstTimeArr[index] >= quantumTime) { //tgian thuc hien con lai > quantum
				timeRunning+=quantumTime;
				burstTimeArr[index]-=quantumTime;
			}
			else {
				timeRunning+=burstTimeArr[index];  //chay lan cuoi
				burstTimeArr[index] = 0;
			}

			System.out.print("__(P" + list.get(index).getId() + ")__");
			System.out.print(timeRunning);
			
			for (int i = 0; i < list.size(); i++) {  	//tim cac tien trinh vua moi den de them vao hang doi
				if (!checkArrivaled[i] && list.get(i).getArrivalTime() <= timeRunning) {
					qe.add(i);
					checkArrivaled[i] = true;
				}
				
			}

			if (burstTimeArr[index] != 0) {	//neu chay chua xong thi them vao hang doi cho chay tiep
				qe.add(index);
			}
			else {
				list.get(index).setSaveTime(timeRunning - list.get(index).getArrivalTime());//tgian luu = tgian ket thuc - tgian xuat hien
				list.get(index).setWaitTime(list.get(index).getSaveTime()-list.get(index).getBurstTime()); //tgian cho = tgian luu - tgian chay
			}
		}
		displayResult(list);
	}
	
}
