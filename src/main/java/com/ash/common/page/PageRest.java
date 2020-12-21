package com.ash.common.page;

import org.springframework.data.domain.Page;

/**
 * 分页返回集合实体
 * @author nzj
 * @Classname PageDTO
 * @Description
 * @Date 2020/3/6 11:37
 */
public class PageRest<T> {

    /**
     * 集合
     */
    private T list;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总条数
     */
    private Long totalCount;

    public PageRest(Page page, T list) {
        super();
        this.list = list;
        this.totalPage = page.getTotalPages();
        this.pageNum = page.getNumber() + 1;
        this.totalCount = page.getTotalElements();
    }

    public PageRest() {

    }


    public static <T> PageRest<T> createBySuccess(Page page, T data){
        return new PageRest(page, data);
    }



    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
            ", totalPage=" + totalPage +
            ", pageNum=" + pageNum +
            ", totalCount=" + totalCount +
            '}';
    }


}
