
package com.sh.app.boarddao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sh.app.boardbean.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> boardListAll() {
		return sqlSession.selectList("com.sh.mybatis.selectAllBoard");
	}
	
	public int boardAllCnt() {
		return sqlSession.selectOne("com.sh.mybatis.selectAllCntBoard");
	}
	
	public List<BoardDTO> boardListPaging(Map<String, Integer> map) {
		return sqlSession.selectList("com.sh.mybatis.selectAllBoardPaging", map);
	}
	
	public int boardSearchCnt(String title) {
		return sqlSession.selectOne("com.sh.mybatis.selectSearchCntBoard", title);
	}
	
	public List<BoardDTO> boardSearchList(Map<String, Object> map) {
		return sqlSession.selectList("com.sh.mybatis.selectSearchBoard",map);
	}
	
	public int boardinsertProc(Map<String, String> map) {
		return sqlSession.insert("com.sh.mybatis.insertBoardData",map);
	}
	
	public List<BoardDTO> boardListOne(int idx) {
		return sqlSession.selectList("com.sh.mybatis.selectOneBoard",idx);
	}
	
	public int boarddelete(int idx) {
		return sqlSession.delete("com.sh.mybatis.deleteBoard", idx);
	}
	
	public int boardupdate(Map<String, Object> map) {
		return sqlSession.update("com.sh.mybatis.updateBoard", map);
	}
	
	/*
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	
	@Value("insert into batisboard values (null, ?, ?, ?, ?, ?, now(), default)")
	private String insertBoardData;
	
	@Value("select * from batisboard")
	private String selectAllBoard;
	
	@Value("select * from batisboard where idx=?")
	private String selectOneBoard;
	
	@Value("delete from batisboard where idx=?")
	private String deleteBoard;
	
	@Value("update batisboard set title=?, content=?, img=? where idx=?")
	private String updateBoard;
	
	@Value("select count(idx) from batisboard")
	private String selectAllCntBoard;
	
	@Value("select count(idx) from batisboard where name like ?")
	private String selectSearchCntBoard;
	
	@Value("select * from batisboard limit ?, ?")
	private String selectAllBoardPaging;
	
	@Value("select * from batisboard where title like ? limit ?, ?")
	private String selectSearchBoard;
	
	public int boardinsertProc(String id, String name, String title, String content, String img) {
		// MultipartRequest로 받으므로 DTO로 받지 않는다.
		// 위의 각 id,name,title,content,img는 고전적 방법인 Multipart에 있는 getParameter 메서드를 사용한다.
				
		return jdbcTmp.update(insertBoardData, id, name, title, content, img);
	}
	
	public List<BoardDTO> boardListAll() {
		return jdbcTmp.query(selectAllBoard, new BatisMapper());
	}
	
	public List<BoardDTO> boardListPaging(int start, int cnt) {
		return jdbcTmp.query(selectAllBoardPaging, new BatisMapper(), start, cnt);
	}
	
	public BoardDTO boardListOne(int idx) {
		BoardDTO boardDto = jdbcTmp.queryForObject(selectOneBoard, new BatisMapper(), idx);
		
		return boardDto;
	}
	
	public int boarddelete(int idx) {
		return jdbcTmp.update(deleteBoard, idx);
	}
	
	public int boardupdate(String title, String content, String img, int idx) {
		return jdbcTmp.update(updateBoard, title, content, img, idx);
	}
	
	public int boardAllCnt() {
		return jdbcTmp.queryForObject(selectAllCntBoard, Integer.class);
	}
	
	public int boardSearchCnt(String title) {
		return jdbcTmp.queryForObject(selectSearchCntBoard, Integer.class, "%"+title+"%");
	}
	
	public List<BoardDTO> boardSearchList(String title, int start, int cnt) {
		return jdbcTmp.query(selectSearchBoard, new BatisMapper(), "%"+title+"%", start, cnt);
	}
	
	
	class BatisMapper implements RowMapper<BoardDTO>{

		@Override
		public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			BoardDTO boardDto = new BoardDTO();
			boardDto.setIdx(rs.getInt(1));
			boardDto.setId(rs.getString(2));
			boardDto.setName(rs.getString(3));
			boardDto.setTitle(rs.getString(4));
			boardDto.setContent(rs.getString(5));
			boardDto.setImg(rs.getString(6));
			boardDto.setRegdate(rs.getString(7));
			boardDto.setHit(rs.getInt(8));
			
			return boardDto;
		}
		
	}
	*/
}
