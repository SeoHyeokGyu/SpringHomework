package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.LectureDao;
import kr.ac.hansung.model.Lecture;

@Service
public class LectureService {

	@Autowired
	private LectureDao lectureDao;
	
	public List<Lecture> getLecture(int year, int semester) {
		List<Lecture> specifier = lectureDao.getLecture(year, semester);
		return specifier;
	}
	public List<Lecture> getCurrent(){
		return lectureDao.getLectures();
	}

	public void insert(Lecture lecture) {
		// TODO Auto-generated method stub
		lectureDao.insert(lecture);
	}
}
