package prasad.game.pairs;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 6, 2010
 */
public class Cell extends JLabel
{
    private static final int CELL_BORDER_LINE_THICKNESS = 1;
    private static final int PADDING_AROUND_ICON = 15;

    private Icon image;

    public Cell( Icon image, MouseListener mouseListener )
    {
        this.image = image;

        setPreferredSize( new Dimension( this.image.getIconWidth() + (CELL_BORDER_LINE_THICKNESS * 2) + PADDING_AROUND_ICON,
                this.image.getIconHeight() + (CELL_BORDER_LINE_THICKNESS * 2) + PADDING_AROUND_ICON ) );
        setHorizontalAlignment( SwingConstants.CENTER );
        setOpaque( true );
        setBorder( BorderFactory.createLineBorder( Color.black, CELL_BORDER_LINE_THICKNESS ) );
        addMouseListener( mouseListener );
    }

    public void setImage()
    {
        setIcon( this.image );
    }

    public Icon getImage()
    {
        return this.image;
    }
}
