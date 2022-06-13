package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Comparator.ChatHistoryComparator;
import beans.ChatHistory;
import beans.UserInfo;
import dao.ChatHistoryDAO;

public class ChatHistorySort {
	
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	public List<ArrayList<ChatHistory>> getChatHistory(UserInfo userInfo) {
		// チャット履歴を取得する
		ChatHistoryDAO chatHistoryDAO = new ChatHistoryDAO();
		List<ChatHistory> tmpChatHistoryList = chatHistoryDAO.getChatHistory(userInfo.getUserId());
		
		// チャット履歴の相手ID列を設定することで、相手ID単位でソートできるようにする			
		for (int i=0; i<tmpChatHistoryList.size(); i++) {
			// Fromのユーザがログインユーザと一致している場合				
			if (userInfo.getUserId().equals(tmpChatHistoryList.get(i).getFromUserId())) {
				tmpChatHistoryList.get(i).setCounterName(tmpChatHistoryList.get(i).getToUserId());
			// Toのユーザがログインユーザと一致している場合
			} else if (userInfo.getUserId().equals(tmpChatHistoryList.get(i).getToUserId())) {
				tmpChatHistoryList.get(i).setCounterName(tmpChatHistoryList.get(i).getFromUserId());
			}
		}
		
		// 相手ID、送信日時順にソートする
		Collections.sort(tmpChatHistoryList, new ChatHistoryComparator());
		//　リストの中にリストを格納する2重リストを作成する
		List<ArrayList<ChatHistory>> chatHistoryList = new ArrayList<ArrayList<ChatHistory>>();

		String counterName = "";
		ArrayList<ChatHistory> tmp = new ArrayList<ChatHistory>();
		for (int i=0; i<tmpChatHistoryList.size(); i++) {
			// ループの1回目は最初に相手IDを退避する				
			if (i==0) {
				counterName = tmpChatHistoryList.get(i).getCounterName();
			}
			
			// 前回のループから相手IDが変わっていない場合、tmpのリストに退避する
			if (counterName.equals(tmpChatHistoryList.get(i).getCounterName())) {
				tmp.add(tmpChatHistoryList.get(i));

			// 前回のループから相手IDが変わった場合、退避していたtmpのリストをchatHistoryListに格納する
			} else {
				chatHistoryList.add(tmp);
				
				tmp = new ArrayList<ChatHistory>();
				tmp.add(tmpChatHistoryList.get(i));
			
				counterName = tmpChatHistoryList.get(i).getCounterName();
			}	
		}
		// チャット履歴がない場合にchatHistoryListのサイズが1にならないようにする		
		if (tmp.size() > 0) {
			chatHistoryList.add(tmp);
		}
		return chatHistoryList;
	}
}