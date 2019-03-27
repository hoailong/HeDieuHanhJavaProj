package hedieuhanh.laplichcpu;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SRN extends Scheduling {
	public static void run(List<Process> list) {
		
		int burstTimeArr[] = new int[list.size()]; // mang thoi gian thuc hien con lai cua tung tien trinh
		for (int i = 0; i < list.size(); i++) {				// moi phan tu cua mang
			burstTimeArr[i] = list.get(i).getBurstTime();  // =thoi gian chay cua moi tien trinh(burstTime) 
		}
		
		TreeSet<Integer> waitRun = new TreeSet<Integer>(new Comparator<Integer>() {  // Hang cho thuc hien (luu id tien trinh cho thuc hien)
			@Override																// luon duoc sap xep theo thoi gian thuc hien con lai
			public int compare(Integer o1, Integer o2) {						   // tu be den lon sau moi lan add them gia tri vao 
				if (burstTimeArr[o1] > burstTimeArr[o2]) return 1;
				if (burstTimeArr[o1] < burstTimeArr[o2]) return -1;
				return 0;
			}
		}); 
		
		waitRun.add(0); // dua tien trinh dau tien vao hang cho
		
		int timeRunning = list.get(0).getArrivalTime(); // tong tgian dang chay,khoi dau = tgian dau tien xuat hien chay
		System.out.print(timeRunning);
	
		while (!waitRun.isEmpty()) { 		  // chay den khi hang cho thuc hien rong
			int index = waitRun.pollFirst(); // lay id tien trinh dau tien trong hang doi ra de chay (pollFirst = lay ra va xoa khoi hang doi) 
			burstTimeArr[index]--; 			// giam tgian thuc hien con lai cua tien trinh do di 1
			timeRunning++;  			   // tong tgian chay tang len 1
			for (int i = 0; i < list.size(); i++) {  // vong lap tim cac tien trinh du dieu kien de chay cho vao hang doi
				if (list.get(i).getArrivalTime() <= timeRunning && burstTimeArr[i] != 0) { // neu tgian xuat hien nay da den (tgian xuat hien <= tong tgian dang chay)
																						  // hoac tien trinh da den nhung chua chay xong
					waitRun.add(i);														 // thi them tien trinh do vao hang doi
				}
			}
			if (burstTimeArr[index] == 0) {   //neu tien trinh chay het thoi gian thuc hien
				System.out.print("__(P" + list.get(index).getId() + ")__");  // in so do
				System.out.print(timeRunning);
				//tinh tgian cho va tgian luu cua tien trinh vua chay xong
				list.get(index).setSaveTime(timeRunning - list.get(index).getArrivalTime());// tgian luu = tgian ket thuc - tgian xuat hien
				list.get(index).setWaitTime(list.get(index).getSaveTime() - list.get(index).getBurstTime()); // tgian cho = tgian luu - tgian chay
			}
			
			// sau khi chay vong lap de tim cac tien trinh du dieu kien chay tren,neu add them 1 gia tri moi vao
			// danh sach hang doi se tu dong sap xep lai theo thu tu tu be den lon theo thoi gian thuc hien con lai
			else if (waitRun.first() != index) {  // neu tien trinh vua chay chua thuc hien xong thi
												 // kiem tra neu id cua tien trinh dau tien trong danh sach hang doi vua sap xep khong phai tien trinh dang chay  
												// thi co nghia la tien trinh dang chay do phai nhuong cho tien trinh khac chay
				//in so do
				System.out.print("__(P" + list.get(index).getId() + ")__");
				System.out.print(timeRunning);
			}
		}
		displayResult(list); //hien thi ket qua thuat toan
	}
}
