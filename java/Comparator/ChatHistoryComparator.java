package Comparator;

import java.util.Comparator;

import beans.ChatHistory;

public class ChatHistoryComparator implements Comparator<ChatHistory> {

	@Override
	public int compare(ChatHistory o1, ChatHistory o2) {
		
		//　チャット相手のIDでソート		
		if (o1.getCounterName().compareTo(o2.getCounterName()) > 0) {
			return -1;
		} else if (o1.getCounterName().compareTo(o2.getCounterName()) < 0) {
			return 1;
		//　送信日時でソート
		} else if (o1.getSendDateTime().compareTo(o2.getSendDateTime()) > 0) {
			return -1;
		} else if (o1.getSendDateTime().compareTo(o2.getSendDateTime()) < 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
}