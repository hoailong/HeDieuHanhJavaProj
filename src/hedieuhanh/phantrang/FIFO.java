package hedieuhanh.phantrang;

import java.util.LinkedList;
import java.util.List;


public class FIFO {
	public static int getFaults(List<Integer> chuoiThamChieu, int frame) {
		int faults = 0; 
		int viTriThamChieu = 0;
		int frameArr[][] = new int[frame][chuoiThamChieu.size()]; // mang 2 chieu luu gia tri cac trang de in theo hang ngang 
		char checkTrangLoi[] = new char[chuoiThamChieu.size()];   // mang de danh dau trang nao loi 
		
	    List<Integer> frameList = new LinkedList<Integer>(); // list luu gia tri dang duoc tham chieu
	    for (int i = 0; i < frame; i++) {					// dau tien dang rong nen cho gia tri = -1
	    	frameList.add(-1);
	    }
	   
	    // vong lap phan trang, so lan lap bang so luong gia tri tham chieu
		for (int i = 0; i < chuoiThamChieu.size(); i++) {
			int giaTriThamChieu = chuoiThamChieu.get(i); // lay gia tri tham chieu ra
			if (!frameList.contains(giaTriThamChieu)) { 		// neu gia tri tham chieu chua ton tai tren trang thi tien hanh thay the
				frameList.set(viTriThamChieu, giaTriThamChieu);// thay gia tri dang co tren trang = gia tri tham chieu vua lay ra
				viTriThamChieu++;						      // tang vi tri tham chieu 
				if (viTriThamChieu == frame) {	// neu vi tri tham chieu la vi tri cuoi cung trong trang thi cho vi tri tham chieu ve lai ban dau
					viTriThamChieu = 0;
				}
				
				// them cac gia tri trang vua thay the vao mang 2 chieu 
				for (int j = 0; j < frameList.size(); j++) {  
					frameArr[j][i] = frameList.get(j);
				}
				
				faults++; // tang so loi
				checkTrangLoi[i] = '*'; // danh dau la trang day loi
			}
			else { // con neu gia tri tham chieu da ton tai tren trang thi giu nguyen trang
				for (int j = 0; j < frameList.size(); j++) {  
					frameArr[j][i] = frameArr[j][i-1];
				}
				checkTrangLoi[i] = ' '; // danh dau trang khong bi loi
			}
		}
		
		//in ket qua
		for (int i = 0; i < chuoiThamChieu.size(); i++) System.out.print(chuoiThamChieu.get(i) + "   ");
		System.out.println();
		for (int i = 0; i < chuoiThamChieu.size(); i++) System.out.print("----");
		System.out.println();
		for (int i = 0; i < frame; i++) {
			for (int j = 0; j < chuoiThamChieu.size(); j++) {
				if (frameArr[i][j] == -1) System.out.print("    ");
				else System.out.print(frameArr[i][j] + "   " );
			}
			System.out.println();
		}
		for (int i = 0; i < chuoiThamChieu.size(); i++) System.out.print(checkTrangLoi[i] + "   ");
		System.out.println();
		return faults;
	}
}

