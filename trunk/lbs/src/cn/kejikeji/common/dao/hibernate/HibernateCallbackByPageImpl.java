package cn.kejikeji.common.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;



public class HibernateCallbackByPageImpl implements HibernateCallback {
//	private Log log = LogFactory.getLog(HibernateCallbackByPageImpl.class); 
	private String hql;
	private PageBean pageObject;
	private Object[] param;
	private String[] nameForSetVar;
	
	/**
	 * 
	 * @param hql
	 * @param pageObject
	 */
	public HibernateCallbackByPageImpl(String hql, PageBean pageObject) {
		this.hql = hql;
		this.pageObject = pageObject;		
	}

	public HibernateCallbackByPageImpl(String hql, PageBean pageObject,Object[] param) {	
		this.hql = hql;
		this.pageObject = pageObject;
		this.param = param;		
	}
	

	

	public HibernateCallbackByPageImpl(String hql, PageBean pageObject,Object[] param,String[] nameForSetVar) {	
		this.hql = hql;
		this.pageObject = pageObject;
		this.param = param;		
		this.nameForSetVar = nameForSetVar;
	}	
	
	

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate.HibernateCallback#doInHibernate(net.sf.hibernate.Session)
	 */
	public Object doInHibernate(Session session) throws HibernateException, SQLException {
		Query selectQuery = session.createQuery(hql);
		if(param!=null){
			for (int i = 0; i < param.length; i++) {

				if(param[i] instanceof List){
					selectQuery.setParameterList(nameForSetVar[i],(List)param[i]);
				}else{
					if(nameForSetVar==null){
						selectQuery.setParameter(i,param[i]);
					}else{
						selectQuery.setParameter(nameForSetVar[i],param[i]);
					}
				}
			}
		}
		if(this.pageObject!=null){
			if(hql.toLowerCase().indexOf("select")>=0){
				int pos = hql.toLowerCase().indexOf("from");
				hql = hql.substring(pos);
			}
			
			if(hql.toLowerCase().indexOf("order by")>=0){
				int pos=hql.toLowerCase().indexOf("order by");
				hql=hql.substring(0, pos);
			}
			
			String getTotolCountHql = "select count(*) "+hql;			
			Query countQuery = session.createQuery(getTotolCountHql);		
			if(param!=null){
				for (int i = 0; i < param.length; i++) {
					if(param[i] instanceof List){
						countQuery.setParameterList(nameForSetVar[i],(List)param[i]);
					}else{
						if(nameForSetVar==null){
							countQuery.setParameter(i,param[i]);
						}else{
							countQuery.setParameter(nameForSetVar[i],param[i]);
						}
					}
				}
			}
			pageObject.setTotalRecords(((Long)countQuery.list().get(0)).intValue());
			selectQuery.setFirstResult(pageObject.getRsFirstNumber()-1);
			selectQuery.setMaxResults(pageObject.getLength());
		}
		
		List result=selectQuery.list();
		
		pageObject.setResults(result);
		return result;
	}
}
