package prasad.game.pairs;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 6, 2010
 */
public class Board extends JPanel implements MouseListener
{
    private int clickCount;
    private List<Cell> cells = new ArrayList<Cell>();
    private List<Cell> matchedCells = new ArrayList<Cell>();
    private Cell firstOpenedCell;
    private Cell secondOpenedCell;
    private Scheme scheme;
    private long startingTime;

    public Board( Scheme scheme )
    {
        super( new GridBagLayout() );

        this.scheme = scheme;

        List<Integer> givenNumbers = new ArrayList<Integer>();
        for( int i = 0; i < this.scheme.getColumns(); i++ )
        {
            for( int j = 0; j < this.scheme.getRows(); j++ )
            {
                int index;
                do
                {
                    index = (int) ( Math.random() * this.scheme.getRows() * this.scheme.getColumns() ) + 1;
                }
                while( givenNumbers.contains( index ) );

                givenNumbers.add( index );

                Cell cell = new Cell( this.scheme.getIcon( index ), this );
                this.cells.add( cell );
                add( cell, new GridBagConstraints( i, j, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
            }
        }
    }

    public void mouseClicked( MouseEvent e )
    {
        // User has clicked on an already matched cell. Hence nothing will happen.
        if( this.matchedCells.contains( e.getSource() ) )
        {
            return;
        }

        if( this.clickCount == 0 ) // Very first click
        {
            // Start recording time
            this.startingTime = System.currentTimeMillis();
            
            this.firstOpenedCell = (Cell) e.getSource();

            this.clickCount++;
            ((Cell) e.getSource()).setImage();
        }
        if( this.clickCount == 1 ) // Clicking the 2nd
        {
            this.secondOpenedCell = (Cell) e.getSource();

            // this.firstOpenedCell == this.secondOpenedCell means user has clicked on the same cell again. Then
            // nothing will happen.
            if( this.firstOpenedCell != this.secondOpenedCell )
            {
                // this.firstOpenedCell.getImage() == this.secondOpenedCell.getImage() means user has clicked on the
                // matching cell.
                if( this.firstOpenedCell.getImage() == this.secondOpenedCell.getImage() )
                {
                    matchedCells.add( this.firstOpenedCell );
                    matchedCells.add( this.secondOpenedCell );

                    this.firstOpenedCell.setBackground( this.scheme.getCellColor() );
                    this.secondOpenedCell.setBackground( this.scheme.getCellColor() );
                }

                this.clickCount++;
                ((Cell) e.getSource()).setImage();

                // this.cells.size() == this.matchedCells.size() means all the cells are matched.
                if( this.cells.size() == this.matchedCells.size() )
                {
                    long timeTaken = System.currentTimeMillis() - this.startingTime;
                    int timeTakenInSeconds = (int) ( timeTaken / 1000 );
                    JOptionPane.showMessageDialog( this, "Time taken: " + timeTakenInSeconds + " seconds",
                            "Congratulations", JOptionPane.INFORMATION_MESSAGE );

                    List<Record> records = this.scheme.validateRecords();
                    if( records != null && ! records.isEmpty() )
                    {
                        Record lastRecord = records.get( Scheme.NUMBER_OF_RECORDS - 1 );
                        if( lastRecord.getTime() > timeTakenInSeconds )
                        {
                            String response = JOptionPane.showInputDialog( this,
                                    "You have recorded a best time. Please enter your name:", "Congratulations",
                                    JOptionPane.INFORMATION_MESSAGE );
                            if( response != null )
                            {
                                records.remove( lastRecord );
                                records.add( new Record( response.trim().length() > 0 ? response.trim() : Scheme.DEFAULT_RECORD_NAME,
                                    timeTakenInSeconds ) );
                                Collections.sort( records );
                                this.scheme.writeRecords( records );
                            }
                        }

                        RecordsDialog dialog = new RecordsDialog( (MainFrame) this.getTopLevelAncestor() , records );
                        dialog.setVisible( true );
                    }
                }
            }
        }
        else if( this.clickCount == 2 ) // Clicking the 3rd
        {
            if( this.firstOpenedCell != e.getSource() && this.secondOpenedCell != e.getSource() )
            {
                this.clickCount = 0;
                for( Cell cell : this.cells )
                {
                    if( ! matchedCells.contains( cell ) )
                    {
                        cell.setIcon( null );
                    }
                }

                this.firstOpenedCell = (Cell) e.getSource();

                this.clickCount++;
                ((Cell) e.getSource()).setImage();
            }
        }
    }

    public void mousePressed( MouseEvent e )
    {
    }

    public void mouseReleased( MouseEvent e )
    {
    }

    public void mouseEntered( MouseEvent e )
    {
    }

    public void mouseExited( MouseEvent e )
    {
    }
}
