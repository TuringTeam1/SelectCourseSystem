package cn.edu.gdin.test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.gdin.entity.Course;
/**
 * ͨ��Hibernate���û�ȡ���ݿ��ľ�����Ϣ
 * @author ��繤���� www.soservers.com
 *
 */
public class HibernatePluginsUtil extends HibernateDaoSupport {
 
    // public static String getTablenameByObject(Class c){
    // SingleTableEntityPersister
    // entityPersister=(SingleTableEntityPersister).getSessionFactory().getClassMetadata(POJO.class).
    //
    // //���pojo��Ӧ������
    // String tableName = entityPersister.getTableName();
    // //entityPersister�л�����pojo�����ԡ�����ֵ���Լ����Զ�Ӧ�����ݿ���ֶε�������Ϣ��
    //
    // }
    private static Configuration hibernateConf = new Configuration();
 
    /**
     * ���Hibernate�־û���
     * @param clazz
     * @return PersistentClass
     */
    @SuppressWarnings("unchecked")
    private static PersistentClass getPersistentClass(Class clazz) {
        synchronized (HibernatePluginsUtil.class) {
            PersistentClass pc = hibernateConf.getClassMapping(clazz.getName());
            if (pc == null) {
                hibernateConf = hibernateConf.addClass(clazz);
                pc = hibernateConf.getClassMapping(clazz.getName());
            }
            return pc;
        }
         
    }
 
    /**
     * ��ñ���
     * 
     * @param clazz ӳ�䵽���ݿ��po��
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getTableName(Class clazz) {
        return getPersistentClass(clazz).getTable().getName();
    }
 
    /**
     * �������
     * 
     * @param clazz ӳ�䵽���ݿ��po��
     * @param icol �ڼ���
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getColumnName(Class clazz, int icol) {
        // return getPersistentClass( clazz
        // ).getTable().getPrimaryKey().getColumn( 0 ).getName(); //�@ȡ���I��
        return getPersistentClass(clazz).getTable().getColumn(icol).getName();
    }
 
    /**
     * �����������
     * 
     * @param clazz ӳ�䵽���ݿ��po��
     * @return List<String> ����
     */
    @SuppressWarnings("unchecked")
    public static List<String> getColumnNames(Class clazz) {
        Iterator<Column> itr = getPersistentClass(clazz).getTable().getColumnIterator();
        List<String> columns = new ArrayList<String>();
        while (itr.hasNext()) {
            Column tmp = itr.next();
            columns.add(tmp.getName());
            
        }
        return columns;
    }
 
     public static void main(String[] args) {
		List list = getColumnNames(Course.class);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
 
}