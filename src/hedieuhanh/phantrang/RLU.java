package hedieuhanh.phantrang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RLU {
	public static int getFaults(List<Integer> chuoiThamChieu, int frame) {
		int faults = 0; //so loi trang
		int frameArr[][] = new int[frame][chuoiThamChieu.size()]; // mang 2 chieu luu gia tri cac trang de in theo hang ngang 
		char checkTrangLoi[] = new char[chuoiThamChieu.size()];  // mang de danh dau trang nao loi 
	   
	    List<Integer> frameList = new LinkedList<Integer>();  // list luu gia tri dang duoc tham chieu
	    for (int i = 0; i < frame; i++) {					// dau tien dang rong nen cho gia tri = -1
	    	frameList.add(-1);
	    }
	    
		List<Integer> listDaThamChieu = new ArrayList<Integer>(); //list luu gia tri da duoc tham chieu, dau tien chua co gia tri nao duoc tham chieu nen list rong
		
		 // vong lap phan trang, so lan lap bang so luong gia tri tham chieu
		for (int i = 0; i < chuoiThamChieu.size(); i++) {
			int giaTriThamChieu = chuoiThamChieu.get(i);
			
			if (listDaThamChieu.indexOf(giaTriThamChieu) != -1) {  					// neu gia tri tham chieu nay da duoc tham chieu truoc do
				listDaThamChieu.remove(listDaThamChieu.indexOf(giaTriThamChieu));  // thi se xoa bo gia tri tham chieu do trong list da dc tham chieu 
			}																	  // de khong bi trung gia tri

			if (!frameList.contains(giaTriThamChieu)) { // neu gia tri tham chieu chua ton tai tren trang thi tien hanh thay the
				if (listDaThamChieu.size() < frame) { // dau tien vao cac trang dang rong, chi can tham chieu cac gia tri vao trang rong do
					frameList.set(listDaThamChieu.size(), giaTriThamChieu);
				}
				else { // con neu trang khong con cho trong nuas
					for (int viTriThamChieu = 0; viTriThamChieu < frame; viTriThamChieu++) { //tim vi tri cua gia tri bi thay the
						if (frameList.get(viTriThamChieu) == listDaThamChieu.get(listDaThamChieu.size()-frame)) { // vi tri gia tri thay the se la vi tri cua gia tri 
							frameList.set(viTriThamChieu, giaTriThamChieu);										 // nam o vi tri hien tai - di so luong trang 
						}																						// trong list da duoc tham chieu
					}
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
			listDaThamChieu.add(giaTriThamChieu); // them gia tri vua duoc tham chieu vao list da duoc tham chieu
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
