package prasad.game.pairs;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Color;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 6, 2010
 */
public class Fruits extends Scheme
{
    private static final Icon STRAWBERRY = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "strawberry.JPG" );
    private static final Icon ORANGE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "orange.JPG" );
    private static final Icon WATER_MELON = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "water_melon.JPG" );
    private static final Icon PAPAYA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "papaya.JPG" );
    private static final Icon GRAPES = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "grapes.JPG" );
    private static final Icon BANANA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "banana.JPG" );
    private static final Icon PINE_APPLE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "pine_apple.JPG" );
    private static final Icon APPLE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "apple.JPG" );

    @Override
    public int getRows()
    {
        return 4;
    }

    @Override
    public int getColumns()
    {
        return 4;
    }

    @Override
    public Icon getIcon( int index )
    {
        if( index == 1 || index == 2 )
        {
            return STRAWBERRY;
        }
        else if( index == 3 || index == 4 )
        {
            return ORANGE;
        }
        else if( index == 5 || index == 6 )
        {
            return WATER_MELON;
        }
        else if( index == 7 || index == 8 )
        {
            return PAPAYA;
        }
        else if( index == 9 || index == 10 )
        {
            return GRAPES;
        }
        else if( index == 11 || index == 12 )
        {
            return BANANA;
        }
        else if( index == 13 || index == 14 )
        {
            return PINE_APPLE;
        }
        else if( index == 15 || index == 16 )
        {
            return APPLE;
        }
        return null;
    }

    @Override
    public Color getCellColor()
    {
        return Color.yellow;
    }

    @Override
    public String getSchemeName()
    {
      return "Fruits";
    }
}
