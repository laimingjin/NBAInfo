package enumerate;

public enum TypeOfSort4MostImprovedPlayer {
//	 場均得分，場均篮板，場均助攻
//	非必要，場均盖帽，場均抢断，三分命中率，投籃命中率，罰球命中率
	
	AVERAGE_PSCORING,//場均得分
	AVERAGE_REBOUND,//場均篮板
	AVERAGE_ASSIST,//場均助攻
	
	
	AVERAGE_BLOCK,//場均盖帽
	AVERAGE_STLS_STEALS,//場均抢断
	AVERAGE_THREEPOINT, //三分命中率
	AVERAGE_SHOTING,//投籃命中率
	AVERAGE_FREETHROW,//罰球命中率
	
    TOTAL,//所有的一起算，预留
	
	NULL,//没有输入
	
	MISTAKE,//错误输入
}
