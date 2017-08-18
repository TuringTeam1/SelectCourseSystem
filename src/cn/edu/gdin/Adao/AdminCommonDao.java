package cn.edu.gdin.Adao;

import java.util.List;
import java.util.Map;

import cn.edu.gdin.demo.TCS;
import cn.edu.gdin.entity.SelectCourse;


public interface AdminCommonDao {
	public <T> void sava(T entity); // ������Ϣ���޷���ֵ��

	public <T> void remove(T entity); // ɾ����Ϣ

	public <T> void update(T entity); // ������Ϣ

	public <T> T findById(Class<T> entityClass, String id); // ͨ��id������ĳһ����Ϣ��
    
	public <T> T findId(Class<T> entityclass,Integer id);
	
	public <T> List<T> findAll(Class<T> entityclass); // ʹ�÷���List<>����ѯ���е���Ϣ
	
	public <T> List<T> getIncidentData(Class<T> entityclass,int page,int rows);//easyui
	
	public <T> Long getTotal(Class<T> entityclass);//�ܼ�¼��
	
	public <T> Long findByParam(Class<T> entityclass,String name,String param);//����ĳ����������ͳ��

	public SelectCourse findByTwoId(String id1,String id2);
	
	public <T> Object[] getColumnName(Class<T> entityclass);//��ȡ���ݿ������
	
	public <T> List<T> getByParamData(Class<T> entityclass,String param0,String param1);//��ϲ�ѯ
	public <T> Long getByParamTotal(Class<T> entityclass,String param0,String param1);//��ϲ�ѯ�е��ܼ�¼��
    
	/**
	 * ��ѯע������Ƿ���ע�������ѯע��Ľ�ɫ�Ƿ������ݿ��С�
	 * @param entityclass
	 * @param param0
	 * @param param1
	 * @return
	 */
	public <T> List<T> Register(Class<T> entityclass,Object param0,Object param1);//��ѯע������Ƿ���ע�������ѯע��Ľ�ɫ�Ƿ������ݿ��С�
	
	public List<TCS> Count(String tenum,int page,int rows);
	
	public Long TotalCount(String tenum);
	
	public List<SelectCourse> SInfo(String tenum,String cno);
	
	
	public <T> List<T> getResultData(Class<T> entityclass,Object param0,Object param1,int page,int rows);
	public <T> Long TotalResultData(Class<T> entityclass,Object param0,Object param1);
	
	public <T> List<T> getGDTwoParam(Class<T> entitycalss,Object param0,Object param1);
	public <T> List<T> getResultTJ(Class<T> entityclass,Object param0,Object param1,Object param2);/**�ɼ�ͳ��*/

	public <T> List<T> getByMoreParams(Class<T> entityclass,int page,int rows,String...strings);
	public <T> Long TotalByMoreParams(Class<T> entityclass,String...strings);
	
	public <T> List<T> SortResultByCourse(String cno);
	public <T> List<T> SortResultByStuclass(String stuclass,String cno);
	
	public <T> List<T> getUserIpByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4,int page,int rows);
	public <T> Long TotalUserIpByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4);
	
	public <T> List<T> getUserLogByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4,Object obj5,Object obj6,int page,int rows);
	public <T> Long TotalUserLogByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4,Object obj5,Object obj6);
	
	public <T> List<T> getEntityByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4,Object obj5,Object obj6,int page,int rows);
	public <T> Long TotalEntityByParam(Class<T> entityclass,Object obj1,Object obj2,Object obj3,Object obj4,Object obj5,Object obj6);
	
	public <T> String getMaxNum(String str,String str1);
}
