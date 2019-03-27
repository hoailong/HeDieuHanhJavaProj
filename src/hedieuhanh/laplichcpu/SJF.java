package hedieuhanh.laplichcpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends Scheduling {
	public static void run(List<Process> list) {
		
		List<Process> listTemp = new ArrayList<Process>(); // Tao 1 list tien trinh moi tu list goc 
		for (Process process : list) {					  // de list goc khong bi bien doi trong qua trinh 
			listTemp.add(process);						 // thuc hien thuat toan nay
		}
		
		int timeRunning = listTemp.get(0).getArrivalTime();  // tong tgian dang chay,khoi dau = tgian dau tien xuat hien chay
		System.out.print(timeRunning);

		Collections.sort(listTemp, new Comparator<Process>() { // Sap xep list tien trinh theo thu tu tien trinh theo tgian chay

			@Override
			public int compare(Process o1, Process o2) {
				if (o1.getBurstTime() < o2.getBurstTime()) return -1;
				if (o1.getBurstTime() > o2.getBurstTime()) return 1;
				else {			// neu tgian chay bang nhau thi sap xep theo tgian den
					if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
					if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
				}
				return 0;
			}
		});
		
		boolean checkRunned[] = new boolean[listTemp.size()]; // mang kiem tra tien trinh da chay chua
		for (int i = 0; i < listTemp.size(); i++) { 		 // ban dau tat ca tien trinh deu chua chay
			checkRunned[i] = false;
		}

		for (int i = 0; i < listTemp.size(); i++) { //so lan lap bang so tien trinh, moi lan lap se chay xong 1 tien trinh
			
			// vong lap while duoi day tim tien trinh da xuat hien (ArrivalTime > timeRunning) va tien trinh do chua duoc chay (checkRunned = true)
			// bien index se luu chi so cua tien trinh vua tim duoc
			int index = 0; 
			while ((listTemp.get(index).getArrivalTime() > timeRunning || checkRunned[index]) && index < listTemp.size()) { 
				// neu tien trinh nay chua den hoac tien trinh nay da duoc chay roi thi bo qua
				index++; 
			}
			
			// neu tim duoc tien trinh du dieu kien da xuat hien va chua tung chay thi se cho chay luon 
			timeRunning += listTemp.get(index).getBurstTime(); // tang tong tgian da chay (+ them tgian chay cua tien trinh vua chay xong)
			checkRunned[index] = true; // tien trinh vua chay xong se duoc danh dau la da chay 
			// in so do
			System.out.print("__(P" + listTemp.get(index).getId() + ")__");
			System.out.print(timeRunning);
			
			// tinh tgian cho va tgian luu cua tien trinh vua chay xong
			listTemp.get(index).setSaveTime(timeRunning - listTemp.get(index).getArrivalTime());// tgian luu = tgian ket thuc - tgian xuat hien
			listTemp.get(index).setWaitTime(listTemp.get(index).getSaveTime() - listTemp.get(index).getBurstTime()); // tgian cho = tgian luu - tgian chay
		}
		displayResult(listTemp); // hien thi ket qua thuat toan
	}
}
