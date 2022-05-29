package com.lagu.shop.core.pagination;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageWrapper {
    public static final int MAX_PAGE_ITEM_DISPLAY = 7;

    private final Metadata metadata;
    private final String url;
    private final Map<String, String> params;

    public PageWrapper(Metadata metadata, String url, Map<String, String> params) {
        this.metadata = metadata;
        this.url = url;
        this.params = params;
    }

    public List<PageItem> getPageWrapper() {
        List<PageItem> pageItems = new ArrayList<>();
        setCurrentPage();
        int start;
        int stop;
        int center = MAX_PAGE_ITEM_DISPLAY - (int) Math.ceil((MAX_PAGE_ITEM_DISPLAY + 1) / 2.0);
        PageItem clean = new PageItem("...", "disabled", "#");
        if (metadata.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            start = 0;
            stop = metadata.getTotalPages();
        } else {
            pageItems.add(setPageSetup(0));
            if (metadata.getPage() <= center) {
                start = 1;
                stop = MAX_PAGE_ITEM_DISPLAY - 2;
            } else if (metadata.getTotalPages() - center <= metadata.getPage() + 1) {
                pageItems.add(clean);
                start = metadata.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 2;
                stop = metadata.getTotalPages() - 1;
            } else {
                pageItems.add(clean);
                start = metadata.getPage() - MAX_PAGE_ITEM_DISPLAY / 2 + 2;
                stop = start + MAX_PAGE_ITEM_DISPLAY - 4;
            }
        }
        for (int i = start; i < stop; i++) {
            pageItems.add(setPageSetup(i));
        }
        if (pageItems.size() < Math.min(MAX_PAGE_ITEM_DISPLAY - 1, metadata.getTotalPages())) {
            pageItems.add(clean);
        }
        if (pageItems.size() < Math.min(MAX_PAGE_ITEM_DISPLAY, metadata.getTotalPages())) {
            pageItems.add(setPageSetup(metadata.getTotalPages() - 1));
        }
        return pageItems;
    }

    public String getCurrentPageUri() {
        setCurrentPage();
        return getPageUri(metadata.getPage());
    }

    private PageItem setPageSetup(int i) {
        return new PageItem(
                String.valueOf(i + 1),
                (i == metadata.getPage()) ? "active disabled" : "",
                getPageUri(i));
    }

    private String getPageUri(int i) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        for (Map.Entry param : params.entrySet()) {
            uriComponentsBuilder.queryParam(param.getKey().toString(), param.getValue());
        }
        return uriComponentsBuilder.queryParam("page", String.valueOf(i)).build().toUriString();
    }

    private void setCurrentPage() {
        if (metadata.getPage() < 0) {
            metadata.setPage(0);
        } else if (metadata.getPage() >= metadata.getTotalPages()) {
            metadata.setPage(metadata.getTotalPages() - 1);
        }
    }

    public static class PageItem {
        private final String textValue;
        private final String className;
        private final String url;

        public PageItem(String textValue, String className, String url) {
            this.textValue = textValue;
            this.className = className;
            this.url = url;
        }

        public String getTextValue() {
            return this.textValue;
        }

        public String getClassName() {
            return this.className;
        }

        public String getUrl() {
            return this.url;
        }
    }
}
