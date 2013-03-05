import javax.swing.JScrollBar;


@SuppressWarnings("serial")
public class MyScrollBar extends JScrollBar {

    MyScrollBar() {
        super();
        setUI(new MyScrollBarUi());
    }
}

