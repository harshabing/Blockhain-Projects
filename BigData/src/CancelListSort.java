package utils;

import java.util.Comparator;

import models.CancelInfo;

public class CancelListSort implements Comparator<CancelInfo>{

	@Override
	public int compare(CancelInfo cancelCode1, CancelInfo cancelCode2) {
		// TODO Auto-generated method stub
		int cancelCode1Count = cancelCode1.getCount();
		int cancelCode2Count = cancelCode2.getCount();
		
		if(cancelCode1Count > cancelCode2Count) {
			return 1;
		}
		else if(cancelCode1Count < cancelCode2Count) {
			return -1;
		}
		else
			return 0;
	}
	
	

}
