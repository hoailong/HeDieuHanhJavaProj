package hedieuhanh.phantrang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class CacheManagement {
	static List<Integer> chuoiThamChieu = new LinkedList<Integer>();
	static int frame = 0;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		getData();
	    System.out.println("Chuoi tham chieu:");
		for (int i : chuoiThamChieu) {
			System.out.print(i + " ");
		}
		
		System.out.println("\nSo frame: " + frame);
		System.out.println();
		System.out.println("+FIFO:");
		System.out.println("So loi trang: " + FIFO.getFaults(chuoiThamChieu,frame));
		System.out.println();
		System.out.println("+RLU:");
		System.out.println("So loi trang: " + RLU.getFaults(chuoiThamChieu,frame));
		System.out.println();
		System.out.println("+OPT:");
		System.out.println("So loi trang: " + OPT.getFaults(chuoiThamChieu,frame));
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
	}
	
	public static void inputData() {
		
		System.out.print("Frame: ");
		CacheManagement.frame = Integer.parseInt(sc.nextLine());
		System.out.println("Nhap chuoi tham chieu,-1 de ket thuc: ");
		while (true) {
			int giaTriThamChieu = sc.nextInt();
			if (giaTriThamChieu == -1) break;
			CacheManagement.chuoiThamChieu.add(giaTriThamChieu);
		}
	}

	public static void readData(){
		File file = new File("data2.txt");
		
		try {
			Scanner sc = new Scanner(file);
			frame = sc.nextInt();
			while (true) {
				int giaTriThamChieu = sc.nextInt();
				if (giaTriThamChieu == -1) break;
				CacheManagement.chuoiThamChieu.add(giaTriThamChieu);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
