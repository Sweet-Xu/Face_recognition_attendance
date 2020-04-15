package nchu.stu.attend.common.domain;

import com.google.common.base.MoreObjects;
import java.io.Serializable;

/**
 * @author XuTian
 * @description
 * @date 2020/2/22
 */
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    private int pageSize;
    private int current;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pageSize", pageSize)
                .add("current", current)
                .toString();
    }

    public void setCurrent(int current) {
        this.current = current;
    }

}
