package com.test.jfreechart.service;

import com.test.jfreechart.entity.Student;

import java.util.List;
import java.util.Map;


/**
 * 学员信息业务处理接口。
 * @author Carry
 */
public interface StudentReportService {
	/**
	 * 查询学员信息
	 * @param condition 查询条件
	 * @return 如果查询条件为null，返回所有学员信息列表，否则按条件查询学员信息
	 */
	public List<Student> find(Student condition);
	/**
	 * 查询饼图统计表。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getPidStatistics();
	/**
	 * 查询柱状图统计表。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Long> getBarStatistics();
}
