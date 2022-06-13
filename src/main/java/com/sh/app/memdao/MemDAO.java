package com.sh.app.memdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sh.app.membean.MemDTO;

@Repository
public class MemDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public int memInsert(Map<String, String> map) { // ���� ������ �޾ƿ;� �Ұ�쿡�� map���� ó��
		return sqlSession.insert("com.sh.mybatis.insertMem",map);
	}
	
	public int isExsitId(String id) {
		return sqlSession.selectOne("com.sh.mybatis.selectIdDataMem", id);
	}
	
	public List<MemDTO> memListAll() {
		return sqlSession.selectList("com.sh.mybatis.selectAllMem");
	}
	
	public MemDTO loginInfo(Map<String, String> map) {
		return sqlSession.selectOne("com.sh.mybatis.selectLoginMem",map);
	}
	
	
	
	/*
	@Autowired
	private JdbcTemplate jdbcTmp;
	
	// ��ȣ ���Դ°� ���� �Ф�
	@Value("insert into batismember values (null, ?, ?, ?, ?, ?, ?, now(), default)")
	private String insertMem;
	
	@Value("select count(*) from batismember where id=?")
	private String selectMemIdData;
	
	@Value("select * from batismember where id=? and pw=?")
	private String selectMemLoginData;
	
	@Value("select * from batismember")
	private String selectAllMem;
	
	
	public int memInsert(MemDTO memDto) {
		return jdbcTmp.update(insertMem, memDto.getId(), memDto.getPw(), memDto.getEmail(), memDto.getName(), memDto.getPhone(), memDto.getBirth());
	}
	
	public String isExsitId(String id) {
		String str = "����� �� �ִ� ���̵� �Դϴ�.";
		
		int res = jdbcTmp.queryForObject(selectMemIdData, Integer.class, id);
		if(res>0) {
			str = "�̹� �����ϴ� ���̵� �Դϴ�.";
		}		
		return str;
	}
	
	public MemDTO loginInfo(String id, String pw) {
		// ������ �����ðŶ� queryForObject, ���� �������� Mapper
		
		try {
			return jdbcTmp.queryForObject(selectMemLoginData, new BatisMapper(), id, pw);
		}catch(EmptyResultDataAccessException e) {
			return null; // ���� ���� ����� �ƴ�!
			// �����Ͱ� ���� �ϳ��� 1���� �ƴҼ� �ִٸ�
			// queryForObject ��� query ���
		}
	}
	
	public List<MemDTO> memListAll() {
		return jdbcTmp.query(selectAllMem, new BatisMapper());
	}
	
	
	
	class BatisMapper implements RowMapper<MemDTO> {

		@Override
		public MemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

			MemDTO memDto = new MemDTO();
			memDto.setIdx(rs.getInt(1));
			memDto.setId(rs.getString(2));
			memDto.setPw(rs.getString(3));
			memDto.setEmail(rs.getString(4));
			memDto.setName(rs.getString(5));
			memDto.setPhone(rs.getString(6));
			memDto.setBirth(rs.getString(7));
			memDto.setRegdate(rs.getString(8));
			memDto.setLevel(rs.getInt(9));
			
			return memDto;
		}
		
	}
	*/
}
