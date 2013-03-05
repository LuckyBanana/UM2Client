import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class MyScrollBarUi extends BasicScrollBarUI {
	@Override
    protected void paintThumb(final Graphics g, final JComponent c, final Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !this.scrollbar.isEnabled()) {
            return;
        }
        g.translate(thumbBounds.x, thumbBounds.y);
        g.setColor(new Color(2, 75, 78));
        g.drawOval(-1, 0, 14, 14);
        g.setColor(new Color(2, 75, 78));
        g.fillOval(-1, 0, 14, 14);
        g.setColor(new Color(2, 75, 78));
        g.setColor(new Color(2, 75, 78));
        g.translate(-thumbBounds.x, -thumbBounds.y);
    }

    @Override
   protected void paintTrack(final Graphics g, final JComponent c, final Rectangle trackBounds) {
        g.setColor(new Color(6, 122, 127));
        g.fillRect(trackBounds.width / 2, trackBounds.y, 3, trackBounds.height);
        if (this.trackHighlight == BasicScrollBarUI.NO_HIGHLIGHT) {
           // this.paintDecreaseHighlight(g);
        } else if (this.trackHighlight == BasicScrollBarUI.NO_HIGHLIGHT) {
            //this.paintIncreaseHighlight(g);
        }
    }
    
    protected void setThumbBounds(int x, int y,int width,int height)
    {
        super.setThumbBounds(x, y, 14, 14);
    }
    protected Rectangle getThumbBounds()
    {
        return new Rectangle(super.getThumbBounds().x,super.getThumbBounds().y,14,14);
    }  protected Dimension getMinimumThumbSize()
    {
        return new Dimension(14,14);
    }
    protected Dimension getMaximumThumbSize()
    {
       return new Dimension(14,14);
    }
    
    protected void installComponents(){
        switch (scrollbar.getOrientation()) {
        case JScrollBar.VERTICAL:
            incrButton = createIncreaseButton(SOUTH);
            decrButton = createDecreaseButton(NORTH);
            break;

        case JScrollBar.HORIZONTAL:
            if (scrollbar.getComponentOrientation().isLeftToRight()) {    
                incrButton = createIncreaseButton(EAST);
                decrButton = createDecreaseButton(WEST);
            } else {
                incrButton = createIncreaseButton(WEST);
                decrButton = createDecreaseButton(EAST);
            }
            break;
        }
       // scrollbar.add(incrButton); // Comment out this line to hide arrow
       // scrollbar.add(decrButton); // Comment out this line to hide arrow
        // Force the children's enabled state to be updated.
    scrollbar.setEnabled(scrollbar.isEnabled());
    }

}
