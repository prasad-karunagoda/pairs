package prasad.game.pairs;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.util.List;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 7, 2010
 */
public class RecordsDialog extends JDialog
{
    public RecordsDialog( Frame owner, List<Record> records )
    {
        super( owner, "Records", true );
        setLayout( new GridBagLayout() );

        JLabel nameLabel = new JLabel( "NAME" );
        nameLabel.setForeground( Color.blue );

        JLabel timeLabel = new JLabel( "TIME (Sec.)" );
        timeLabel.setForeground( Color.blue );

        getContentPane().add( nameLabel, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets( 15, 20, 5, 0 ), 0, 0 ) );
        getContentPane().add( timeLabel, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets( 15, 25, 5, 20 ), 0, 0 ) );

        for( int i = 0; i < records.size(); i++ )
        {
            getContentPane().add( new JLabel( records.get( i ).getName() ), new GridBagConstraints( 0, i+1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 20, ( i == Scheme.NUMBER_OF_RECORDS - 1 ? 15 : 5 ), 0 ), 0, 0 ) );
            getContentPane().add( new JLabel( String.valueOf( records.get( i ).getTime() ) ), new GridBagConstraints( 1, i+1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 25, ( i == Scheme.NUMBER_OF_RECORDS - 1 ? 15 : 5 ), 25 ), 0, 0 ) );
        }
        pack();
        setLocation( owner.getLocation().x + (owner.getSize().width - getSize().width) / 2,
                owner.getLocation().y + (owner.getSize().height - getSize().height) / 2 );
    }
}
