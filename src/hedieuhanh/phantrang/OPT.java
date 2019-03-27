package hedieuhanh.phantrang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OPT {
	public static int getFaults(List<Integer> chuoiThamChieu, int frame) {
		int faults = 0; //so loi trang
		int frameArr[][] = new int[frame][chuoiThamChieu.size()];  // mang 2 chieu luu gia tri cac trang de in theo hang ngang 
		char checkTrangLoi[] = new char[chuoiThamChieu.size()];   // mang de danh dau trang nao loi 
	    
	    List<Integer> frameList = new LinkedList<Integer>();  // list luu gia tri dang duoc tham chieu
	    for (int i = 0; i < frame; i++) {					// dau tien dang rong nen cho gia tri = -1
	    	frameList.add(-1);
	    }
	    
	    List<Integer> listChuaThamChieu = new ArrayList<Integer>(); //list luu tat ca cac gia tri tham chieu chua duoc tham chieu
	    for (int i : chuoiThamChieu) {	// ban dau chua co gia tri tham chieu nao dc tham chieu nen list nay = list dau vao
	    	listChuaThamChieu.add(i);
	    }
	    
	 // vong lap phan trang, so lan lap bang so luong gia tri tham chieu
	    for (int i = 0; i < chuoiThamChieu.size(); i++) {
	    	int giaTriThamChieu = chuoiThamChieu.get(i); // lay gia tri tham chieu ra
			
			if (!frameList.contains(giaTriThamChieu)) { // neu gia tri tham chieu chua ton tai tren trang thi tien hanh thay the
				if (listChuaThamChieu.size() > chuoiThamChieu.size()-frame) { // dau tien vao cac trang dang rong, chi can tham chieu cac gia tri vao trang rong do
					frameList.set(chuoiThamChieu.size()-listChuaThamChieu.size(), giaTriThamChieu);
				}
				else { // con neu trang khong con cho trong nua
					int viTriThamChieu = 0;
					for (int j = 0; j < frameList.size(); j++) {// vong lap tim vi tri gia tri bi thay the
						if(listChuaThamChieu.indexOf(frameList.get(j)) == -1) {//gia tri dang tham chieu ma tuong lai khong
							viTriThamChieu = j;								  //tham chieu nua se bi thay the
							break;
						}
						else if (listChuaThamChieu.indexOf(frameList.get(j)) > listChuaThamChieu.indexOf(frameList.get(viTriThamChieu))) {//
							viTriThamChieu = j;		//hoac gia tri dang tham chieu ma tuong lai duoc tham chieu muon nhat	 															
						}							//se bi thay the
					}
					frameList.set(viTriThamChieu, giaTriThamChieu);
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
			listChuaThamChieu.remove(0);//xoa gia tri vua duoc tham chieu ra khoi list
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
