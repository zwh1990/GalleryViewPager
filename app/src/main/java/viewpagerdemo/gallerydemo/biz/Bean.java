package viewpagerdemo.gallerydemo.biz;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Bean {

    private String title;
    private int color;

    public Bean(String title, int color) {
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "title='" + title + '\'' +
                ", color=" + color +
                '}';
    }
}
