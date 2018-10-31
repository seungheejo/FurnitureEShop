package org.jsp.board.service;

import java.util.List;
import org.jsp.board.vo.SBoard;

public interface BoardMapper {

	// 掲示板で作成 insert
	public int insertBoard(SBoard s);

	// 掲示板の全ての文を降順で持って来る select
	public List<SBoard> selectAll();

	// 掲示板で一つの文をリード select
	public List<SBoard> getBoard(int boardnum);

	// パスワードが合うと文を修正 update
	public int updateBoard(SBoard sb);

	// パスワードが合うと文を削除 delete
	public int deleteBoard(String pw);

	// パスワードを確認 select
	public int checkPw(String pw);

	// 文をクリックすると照会数が増加 update
	public int addHits(int boardnum);

}
