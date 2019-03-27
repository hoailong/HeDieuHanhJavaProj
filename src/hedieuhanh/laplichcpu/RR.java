package hedieuhanh.laplichcpu;

import java.util.LinkedList;
import java.util.List;

public class RR extends Scheduling {
	public static void run(List<Process> list, int quantumTime) {
		
		int burstTimeArr[] = new int[list.size()];          // mang thoi gian thuc hien con lai cua tung tien trinh
		for (int i = 0; i < list.size(); i++) {	   		   // moi phan tu cua mang
			burstTimeArr[i] = list.get(i).getBurstTime(); // =thoi gian chay cua moi tien trinh(burstTime) 
		}
		
		List<Integer> runList = new LinkedList<Integer>(); // danh sach luu chi so cua cac tien trinh dang cho thuc hien
		
		boolean checkArrivaled[] = new boolean[list.size()]; // mang kiem tra tien trinh da xuat hien chua	
		for (int i = 0; i < list.size(); i++) {				// ban dau tat ca ca tien trinh deu chua xuat hien 
			checkArrivaled[i] = false;
		}
		
		int timeRunning = list.get(0).getArrivalTime();   // tong tgian dang chay,khoi dau = tgian dau tien xuat hien chay
		runList.add(0);             //dua tien trinh dau tien vao danh sach thuc hien
		checkArrivaled[0] = true;	//danh dau tien trinh dau tien da den
		System.out.print(timeRunning);
		
		int i = 0;
		while (!runList.isEmpty()) {	//chay den khi danh sach thuc hien trong
			
			// vong lap while duoi day se cho chay 1 luot cac tien trinh co trong danh sach cho thuc hien
			while (i < runList.size()) { //chay het tien trinh trong danh sach cho thuc hien
				int index = runList.get(i); //lay chi so cua tien trinh chay
				if (burstTimeArr[index] >= quantumTime) { //tgian thuc hien con lai > quantum thi chi tien trinh do chay trong tgian quantum
					timeRunning+=quantumTime;  			 // tong tgian chay tang theo quantum
					burstTimeArr[index]-=quantumTime;	// tgian thuc hien con lai cua tien trinh do giam di quantum
				}
				else {  // con neu tgian thuc hien con lai cua tien trinh do < quantum thi cho chay het tgian con lai
					timeRunning+=burstTimeArr[index];  // tong tgian chay tang them tgian chay cua tien trinh vua chay
					burstTimeArr[index] = 0;		   // tgian thuc hien con lai cua tien trinh vua chay xong = 0
				}
				
				// in so do
				System.out.print("__(P" + list.get(index).getId() + ")__");
				System.out.print(timeRunning);
					
				if (burstTimeArr[index] == 0) { // neu tien trinh vua chay xong (tuc la tgian thuc hien con lai = 0) thi xoa tien trinh do khoi danh sach cho thuc hien
					// tinh tgian cho va tgian luu cua tien trinh vua chay xong 
					list.get(index).setSaveTime(timeRunning - list.get(index).getArrivalTime());//tgian luu = tgian ket thuc - tgian xuat hien
					list.get(index).setWaitTime(list.get(index).getSaveTime()-list.get(index).getBurstTime()); //tgian cho = tgian luu - tgian chay
					runList.remove(i); // xoa khoi danh sach cho thuc hien
				}
				else { // con neu tien trinh vua chay nhung chua chay xong (tgian thuc hien con lai > 0) thi phai tam dung
					  // de cho tien trinh dang cho thuc hien phia sau no chay
					i++;
				}
			}

			for (int j = 0; j < list.size(); j++) {  // vong lap tim cac tien trinh moi xuat hien (checkArrivaled = false va tgian den <= tong tgian chay) ]
													// va cho vao danh sach cho thuc hien
				if (!checkArrivaled[j] && list.get(j).getArrivalTime() <= timeRunning) {
					runList.add(j);
					checkArrivaled[j] = true;	// danh dau tien trinh vua cho va danh sach cho thuc hien da den
				}
			}
			
			if (i == runList.size()) i = 0; // neu da chay het 1 luot tat ca tien trinh trong danh sach cho thuc hien thi quay lai chay luot moi
		}
		displayResult(list); // hien thi ket qua thuat toan
	}
	
}
