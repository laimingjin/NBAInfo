package vo;

/**
 * @author ysh123
 *
 *球队数据的顶尖人物，==，数据王的集合
 *用于球队详情界面的子模块——数据王的VO
 *
 *一共有三人：分别称之为 得分王，篮板王，助攻王，==（注：是对当前球队来讲的）
 *
 *0.0这个类真恶俗……
 *
 */
public class DataKingVO {
	//String pic_score ;//得分王的头像地址
	//String pic_rebound;//篮板王的头像地址
	//String pic_assist;//助攻王的头像地址
	
	
	String playerName_score;//得分王大名
	String playerName_rebound;//篮板王大名
	String playerName_assist;//助攻王大名
	
	Double score;//得分王赛季场均得分
	Double rebound;//篮板王的赛季场均篮板
	Double assist;//助攻王的赛季场均助攻
	public DataKingVO(
			String playerName_score, String playerName_rebound,
			String playerName_assist, Double score, Double rebound,
			Double assist) {
		super();
		this.playerName_score = playerName_score;
		this.playerName_rebound = playerName_rebound;
		this.playerName_assist = playerName_assist;
		this.score = score;
		this.rebound = rebound;
		this.assist = assist;
	}
	/**public String getPic_score() {
		return pic_score;
	}
	public String getPic_rebound() {
		return pic_rebound;
	}
	public String getPic_assist() {
		return pic_assist;
	}
	*/
	public String getPlayerName_score() {
		return playerName_score;
	}
	public String getPlayerName_rebound() {
		return playerName_rebound;
	}
	public String getPlayerName_assist() {
		return playerName_assist;
	}
	public Double getScore() {
		return score;
	}
	public Double getRebound() {
		return rebound;
	}
	public Double getAssist() {
		return assist;
	}
	
	
	

}
