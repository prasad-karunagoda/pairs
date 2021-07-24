package prasad.game.pairs;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Color;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 6, 2010
 */
public class Flags extends Scheme
{
    private static final Icon NETHERLANDS = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "netherlands.JPG" );
    private static final Icon BELGIUM = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "belgium.JPG" );
    private static final Icon ITALY = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "italy.JPG" );
    private static final Icon CHINA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "china.JPG" );
    private static final Icon AUSTRALIA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "australia.JPG" );
    private static final Icon SOUTH_AFRICA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "south_africa.JPG" );
    private static final Icon GERMANY = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "germany.JPG" );
    private static final Icon SPAIN = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "spain.JPG" );
    private static final Icon SWITZERLAND = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "switzerland.JPG" );
    private static final Icon FRANCE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "france.JPG" );
    private static final Icon UK = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "uk.JPG" );
    private static final Icon USA = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "usa.JPG" );

    @Override
    public int getRows()
    {
        return 4;
    }

    @Override
    public int getColumns()
    {
        return 6;
    }

    @Override
    public Icon getIcon( int index )
    {
        if( index == 1 || index == 2 )
        {
            return NETHERLANDS;
        }
        else if( index == 3 || index == 4 )
        {
            return BELGIUM;
        }
        else if( index == 5 || index == 6 )
        {
            return ITALY;
        }
        else if( index == 7 || index == 8 )
        {
            return CHINA;
        }
        else if( index == 9 || index == 10 )
        {
            return AUSTRALIA;
        }
        else if( index == 11 || index == 12 )
        {
            return SOUTH_AFRICA;
        }
        else if( index == 13 || index == 14 )
        {
            return GERMANY;
        }
        else if( index == 15 || index == 16 )
        {
            return SPAIN;
        }
        else if( index == 17 || index == 18 )
        {
            return SWITZERLAND;
        }
        else if( index == 19 || index == 20 )
        {
            return FRANCE;
        }
        else if( index == 21 || index == 22 )
        {
            return UK;
        }
        else if( index == 23 || index == 24 )
        {
            return USA;
        }
        return null;
    }

    @Override
    public Color getCellColor()
    {
        return Color.gray;
    }

    @Override
    public String getSchemeName()
    {
      return "Flags";
    }
}
