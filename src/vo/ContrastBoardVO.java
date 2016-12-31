package vo;

/**
 * @author ysh123
 *用于球员界面球员详情中的子模块——对比板的VO
 *
 *包含三个方面：联盟平均、联盟第一、player本身的赛季场均得分，篮板，助攻
 */
public class ContrastBoardVO {
	 double alliance_score;//联盟平均赛季场均得分
	 double alliance_rebound;//联盟平均赛季场均篮板
	double alliance_assist;//联盟平均赛季场均助攻
	double first_score;//联盟第一赛季场均得分
	double first_rebound;//联盟第一赛季场均篮板
	double first_assist;//联盟第一赛季场均助攻
	double player_score;//player的赛季场均得分
	double player_rebound;//player的赛季场均篮板
	double player_assist;//player的赛季场均助攻
	public ContrastBoardVO(double alliance_score, double alliance_rebound,
			double alliance_assist, double first_score, double first_rebound,
			double first_assist, double player_score, double player_rebound,
			double player_assist) {
		super();
		this.alliance_score = alliance_score;
		this.alliance_rebound = alliance_rebound;
		this.alliance_assist = alliance_assist;
		this.first_score = first_score;
		this.first_rebound = first_rebound;
		this.first_assist = first_assist;
		this.player_score = player_score;
		this.player_rebound = player_rebound;
		this.player_assist = player_assist;
	}
	public double getAlliance_score() {
		return alliance_score;
	}
	public double getAlliance_rebound() {
		return alliance_rebound;
	}
	public double getAlliance_assist() {
		return alliance_assist;
	}
	public double getFirst_score() {
		return first_score;
	}
	public double getFirst_rebound() {
		return first_rebound;
	}
	public double getFirst_assist() {
		return first_assist;
	}
	public double getPlayer_score() {
		return player_score;
	}
	public double getPlayer_rebound() {
		return player_rebound;
	}
	public double getPlayer_assist() {
		return player_assist;
	}
	
	
	
}
