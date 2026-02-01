
package com.example.rickstarter.model;

import java.util.List;

public class PageResponse<T> {
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;
    private List<T> items;

    public void setPage(int p) { this.page = p; }
    public void setSize(int s) { this.size = s; }
    public void setTotalItems(long t) { this.totalItems = t; }
    public void setTotalPages(int t) { this.totalPages = t; }
    public void setItems(List<T> i) { this.items = i; }
}
