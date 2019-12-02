package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Offer;

// 이름이 offerDao 인 빈을 만들어준다. 패키지 지
@Repository
public class OfferDao {

	private JdbcTemplate jdbcTemplate;

	// 세터 메소드가 불릴때 의존성주입
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from lecture";
		// 위의 스트링을 실행시키 면 결과로써 오브젝트 타입으로 들어온다.
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}

	// 쿼리 하나의 객체를 조회한다.
	public Offer getOffer(String name) {
		
		String sqlStatement = "select * from lecture where year=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { name }, new RowMapper<Offer>() {
			// 레코드를 자바 객체로 매핑시켜준다.rowmapper : 인터페이스를 구현 / 익명클래스 작성
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Offer offer = new Offer();

				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setCode(rs.getString("code")); 
				offer.setName(rs.getString("name"));
				offer.setDivision(rs.getString("division"));
				offer.setPoint(rs.getInt("point"));

				return offer;
			}

		});
	}

// 여러개의 객체를 조회한다. 
	public List<Offer> getOffers() {
		String sqlstatement = "select * from lecture ";
		return jdbcTemplate.query(sqlstatement, new RowMapper<Offer>() {
			// 레코드를 자바 객체로 매핑시켜준다.rowmapper : 인터페이스를 구현 / 익명클래스 작성
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Offer offer = new Offer();

				offer.setYear(rs.getInt("year"));
				offer.setSemester(rs.getInt("semester"));
				offer.setCode(rs.getString("code")); 
				offer.setName(rs.getString("name"));
				offer.setDivision(rs.getString("division"));
				offer.setPoint(rs.getInt("point"));

				return offer;
			}

		});
	}

	// id는 자동으로 증가하도록 설정해놓음.
	public boolean insert(Offer offer) {

		int year = offer.getYear();
		int semester = offer.getSemester();
		String code = offer.getCode();
		String name = offer.getName();
		String division = offer.getDivision();
		int point = offer.getPoint();
		

		String sqlStatement = "insert into lecture (year, semester, code, name, division, point) values (?, ?, ?, ?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { year, semester, code, name, division, point }) == 1);

	}

	public boolean update(Offer offer) {

		int year = offer.getYear();
		int semester = offer.getSemester();
		String code = offer.getCode();
		String name = offer.getName();
		String division = offer.getDivision();
		int point = offer.getPoint();

		String sqlStatement = "update offers set year=?, semester=?, code=?, name=?, division=?, point=? where year=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { year, semester, code, name, division, point}) == 1);

	}

	public boolean delete(int code) {

		String sqlStatement = "delete from offers where year=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { code }) == 1);

	}

}
