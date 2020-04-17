package kr.or.connect.reservation.dto;

public class ReviewAvgCnt {
	private int reviewCnt;
	private double scoreAvg;

	public int getReviewCnt() {
		return reviewCnt;
	}

	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}

	public double getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	@Override
	public String toString() {
		return "ReviewAvgCnt [reviewCnt=" + reviewCnt + ", scoreAvg=" + scoreAvg + "]";
	}
}
