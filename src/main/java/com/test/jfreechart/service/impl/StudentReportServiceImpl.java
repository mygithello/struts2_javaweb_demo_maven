package com.test.jfreechart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.jfreechart.dao.StudentDao;
import com.test.jfreechart.entity.Student;
import com.test.jfreechart.service.StudentReportService;

/**
 * 学员信息业务处理接口实现类。
 */
public class StudentReportServiceImpl implements StudentReportService {
	// 学员信息处理DAO
	private StudentDao studentDao = null;

	/* (non-Javadoc)
	 * @see cn.jbit.jfreechart.service.StudentReportService#find(cn.jbit.jfreechart.entity.Student)
	 */
	public List<Student> find(Student condition) {
		List<Student> ret = this.studentDao.find(condition);
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see cn.jbit.jfreechart.service.StudentReportService#getBarStatistics()
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Long> getBarStatistics() {
		Map<String,Long> ret = new HashMap<String,Long>();
		String hql = "select s.stuEducation,count(s.stuEducation) from Student s group by s.stuEducation";
		List<Object[]> list = this.studentDao.search(hql);
		if (list!=null && !list.isEmpty()){
			for(Object[] arr : list){
				if (arr!=null && arr.length==2){
					System.out.println(arr[0]+"  "+arr[1]);
					ret.put((String)arr[0], (Long)arr[1]);
				}
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see cn.jbit.jfreechart.service.StudentReportService#getPidStatistics()
	 */
	@SuppressWarnings("unchecked")
	public List getPidStatistics() {
		String hql = "select s.stuEducation,count(s.stuEducation) from Student s group by s.stuEducation";
		List ret = this.studentDao.search(hql);
		return ret;
	}

	/**
	 * @param studentDao the studentDao to set
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
}
