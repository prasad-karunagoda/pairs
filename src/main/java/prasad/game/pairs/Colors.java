package prasad.game.pairs;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.io.File;

/**
 * TODO
 * Author: Prasad Karunagoda
 * Date: 11/3/13
 */
public class Colors extends Scheme
{
  private static final Icon RED = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "red.JPG" );
  private static final Icon GREEN = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "green.JPG" );
  private static final Icon YELLOW = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "yellow.JPG" );
  private static final Icon BLUE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "blue.JPG" );
  private static final Icon PURPLE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "purple.JPG" );
  private static final Icon PINK = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "pink.JPG" );
  private static final Icon BROWN = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "brown.JPG" );
  private static final Icon ORANGE = new ImageIcon( IMAGES_FOLDER_NAME + File.separator + "color_orange.JPG" );

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
  public Icon getIcon(int index)
  {
    if (index == 1 || index == 2)
    {
      return RED;
    }
    else if (index == 3 || index == 4)
    {
      return GREEN;
    }
    else if (index == 5 || index == 6)
    {
      return YELLOW;
    }
    else if (index == 7 || index == 8)
    {
      return BLUE;
    }
    else if (index == 9 || index == 10)
    {
      return PURPLE;
    }
    else if (index == 11 || index == 12)
    {
      return PINK;
    }
    else if (index == 13 || index == 14)
    {
      return BROWN;
    }
    else if (index == 15 || index == 16)
    {
      return ORANGE;
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
    return "Colors";
  }
}
