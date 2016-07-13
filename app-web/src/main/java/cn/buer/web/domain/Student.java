package cn.buer.web.domain;

public class Student {
    /**
     * 
     * 此字段是由MyBatis Generator自动生成，不要修改。
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    private Long id;

    /**
     * 
     * 此字段是由MyBatis Generator自动生成，不要修改。
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    private String name;

    /**
     * 
     * 此字段是由MyBatis Generator自动生成，不要修改。
     *
     * @mbggenerated Tue Mar 22 14:07:54 CST 2016
     */
    private Long classesid;
    
    public Student() {
		super();
	}
    
	public Student(String name, Long classesid) {
		super();
		this.name = name;
		this.classesid = classesid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassesid() {
        return classesid;
    }

    public void setClassesid(Long classesid) {
        this.classesid = classesid;
    }
}