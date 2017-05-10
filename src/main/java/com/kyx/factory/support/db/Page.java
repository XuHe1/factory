package com.kyx.factory.support.db;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public class Page<F> {
    private Integer totalCount;
    private Integer totalPages;
    private Integer page;
    private Integer pageSize;
    List<F> list;

    public Page(Integer totalCount, Integer page, Integer pageSize, List<F> list) {
        this.totalCount = totalCount;
        this.totalPages = (totalCount - 1) / pageSize + 1;

        this.page = page;
        this.pageSize = pageSize;
        this.list = list;
    }

    public <T> Page<T> map(Function<? super F, ? extends T> mapFunction) {
        Page<T> pageMapped = new Page<>(this.totalCount, this.page, this.pageSize, null);
        pageMapped.list = this.list.stream().map(mapFunction).collect(Collectors.toList());
        return pageMapped;
    }

    public static <T> Page<T> newPage(org.springframework.data.domain.Page<T> page) {
        return new Page<>((int)page.getTotalElements(), page.getNumber(), page.getSize(), page.getContent());
    }

    public static <T> Page newPage(Integer totalCount, Integer page, Integer pageSize, List<T> list) {
        return new Page<>(totalCount, page, pageSize, list);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<F> getList() {
        return list;
    }

    public void setList(List<F> list) {
        this.list = list;
    }
}
