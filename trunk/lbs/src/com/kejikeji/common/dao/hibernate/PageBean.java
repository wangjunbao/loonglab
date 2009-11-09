package com.kejikeji.common.dao.hibernate;

import java.util.List;

/**
 * 处理翻页后，产生的翻页bean，记录了所有翻页和查询结果的信息。
 * 
 * @author lizhaoyang
 */
public class PageBean {
    /**
     * 当前页号
     */
    private int currentPage;
    /**
     * 每页记录数
     */
    private int length;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private int totalRecords;
    /**
     * 结果
     */
    private List results;
    
    
    
    
    public PageBean() {
		super();
	}
    
    public PageBean(int currentPage,int length){
    	this.currentPage=currentPage;
    	this.length=length;
    }
    
	public List getResults() {
        return results;
    }
    public void setResults(List results) {
        this.results = results;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public int getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(int count) {
        totalRecords = count;
        totalPages = totalRecords / length;
        if (totalRecords % length != 0) {
            totalPages++;
        }

        if (totalPages != 0) {
            if (currentPage < 1) {
                currentPage = 1;
            }
        } else {
            currentPage = 0;
        }
    }
    
    /**
     * @return
     */
    public boolean canToFirst() {
        return currentPage > 1;
    }

    /**
     * @return
     */
    public boolean canToLast() {
        return currentPage < totalPages;
    }

    /**
     * @return
     */
    public boolean canToNext() {
        return currentPage < totalPages;
    }

    /**
     * @return
     */
    public boolean canToPre() {
        return currentPage > 1;
    }

    /**
     * 起始记录号。
     * @return
     */
    public int getRsFirstNumber() {
        return (currentPage - 1) * length + 1;
    }

    public int getRsLastNumber() {
        return currentPage * length + 1;
    }
}
