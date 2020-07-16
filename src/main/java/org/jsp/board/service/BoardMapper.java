package org.jsp.board.service;

import java.util.List;
import org.jsp.board.vo.SBoard;

public interface BoardMapper {

	// 게시판에서 작성 insert
	public int insertBoard(SBoard s);

	// 게시판의 모든 글을 가져옴 select
	public List<SBoard> selectAll();

	// 게시판에서 하나의 글을 가져옴 select
	public List<SBoard> getBoard(int boardnum);

	// 비밀번호가 맞으면 글을 수정 update
	public int updateBoard(SBoard sb);

	// 비밀번호가 맞으면 글을 삭제 delete
	public int deleteBoard(String pw);

	// 비밀번호 확인 select
	public int checkPw(String pw);

	// 글을 클릭하면 조회수가 증가 update
	public int addHits(int boardnum);

}
