package com.test.jfreechart.entity;

import java.util.Date;

/**
 * 学员基本信息实体。
 */
public class Student implements java.io.Serializable {
	private static final long serialVersionUID = 8985499578658875622L;
	// 学员编号
	private Long stuid;
	// 学员姓名
	private String stuName;
	// 籍贯
	private String stuBirthplace;
	// 生日
	private Date stuBirthday;
	// 入学时间
	private Date stuEnrollment;
	// 联系电话
	private String stuPhone;
	// 学历
	private String stuEducation;
	// 联系地址
	private String stuAddress;
	
	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Long stuid) {
		this.stuid = stuid;
	}

	/** full constructor */
	public Student(Long stuid, String stuName, String stuBirthplace,
			Date stuBirthday, Date stuEnrollment, String stuPhone,
			String stuEducation, String stuAddress) {
		this.stuid = stuid;
		this.stuName = stuName;
		this.stuBirthplace = stuBirthplace;
		this.stuBirthday = stuBirthday;
		this.stuEnrollment = stuEnrollment;
		this.stuPhone = stuPhone;
		this.stuEducation = stuEducation;
		this.stuAddress = stuAddress;
	}

	/**
	 * @return the stuid
	 */
	public Long getStuid() {
		return stuid;
	}

	/**
	 * @param stuid the stuid to set
	 */
	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	/**
	 * @return the stuName
	 */
	public String getStuName() {
		return stuName;
	}

	/**
	 * @param stuName the stuName to set
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	/**
	 * @return the stuBirthplace
	 */
	public String getStuBirthplace() {
		return stuBirthplace;
	}

	/**
	 * @param stuBirthplace the stuBirthplace to set
	 */
	public void setStuBirthplace(String stuBirthplace) {
		this.stuBirthplace = stuBirthplace;
	}

	/**
	 * @return the stuBirthday
	 */
	public Date getStuBirthday() {
		return stuBirthday;
	}

	/**
	 * @param stuBirthday the stuBirthday to set
	 */
	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	/**
	 * @return the stuEnrollment
	 */
	public Date getStuEnrollment() {
		return stuEnrollment;
	}

	/**
	 * @param stuEnrollment the stuEnrollment to set
	 */
	public void setStuEnrollment(Date stuEnrollment) {
		this.stuEnrollment = stuEnrollment;
	}

	/**
	 * @return the stuPhone
	 */
	public String getStuPhone() {
		return stuPhone;
	}

	/**
	 * @param stuPhone the stuPhone to set
	 */
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	/**
	 * @return the stuEducation
	 */
	public String getStuEducation() {
		return stuEducation;
	}

	/**
	 * @param stuEducation the stuEducation to set
	 */
	public void setStuEducation(String stuEducation) {
		this.stuEducation = stuEducation;
	}

	/**
	 * @return the stuAddress
	 */
	public String getStuAddress() {
		return stuAddress;
	}

	/**
	 * @param stuAddress the stuAddress to set
	 */
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}