package by.rekuts.tattoosalon.subject;

import java.util.List;
import java.util.Objects;

public class ListPage<T> {

    private int page;

    private List<T> publications;

    private int total;

    private int maxPerPage;

    public ListPage(List<T> publications, int page, int total, int maxPerPage) {
        this.publications = publications;
        this.page = page;
        this.total = total;
        this.maxPerPage = maxPerPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getPublications() {
        return publications;
    }

    public void setPublications(List<T> publications) {
        this.publications = publications;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(int maxPerPage) {
        this.maxPerPage = maxPerPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPage<?> listPage = (ListPage<?>) o;
        return page == listPage.page &&
                total == listPage.total &&
                maxPerPage == listPage.maxPerPage &&
                Objects.equals(publications, listPage.publications);
    }

    @Override
    public int hashCode() {

        return Objects.hash(page, publications, total, maxPerPage);
    }
}
