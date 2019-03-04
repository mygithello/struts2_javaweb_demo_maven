package com.test.jfreechart.dao;

import com.test.jfreechart.entity.Student;

import java.util.List;


/**
 * 学员信息处理DAO。
 * @author Carry
 */
public interface StudentDao {
	/**
	 * 查询学员列表信息。
	 * @param condition 查询条件
	 * @return 学员列表
	 */
	public List<Student> find(Student condition);
	/**
	 * 查询学员列表。
	 * @param hql 查询语句
	 * @return 符合条件的列表
	 */
	@SuppressWarnings("unchecked")
	public List search(String hql);
}
