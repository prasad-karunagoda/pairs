package prasad.game.pairs;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 7, 2010
 */
public class MainFrame extends JFrame
{
    private List<Scheme> schemes;

    private JMenu menuGame;
    private JMenu menuRecords;

    public static void main( String[] args )
    {
        MainFrame f = new MainFrame();
        f.setLocation( (Toolkit.getDefaultToolkit().getScreenSize().width - f.getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - f.getSize().height) / 2 );
        f.setVisible( true );
    }

    public MainFrame()
    {
        super( "Pairs" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );

        JMenuBar menuBar = new JMenuBar();

        menuGame = new JMenu( "Game" );
        menuBar.add( menuGame );

        menuRecords = new JMenu( "Records" );
        menuBar.add( menuRecords );

        JMenu menuHelp = new JMenu( "Help" );
        menuBar.add( menuHelp );

        registerSchemes();

        menuGame.addSeparator();

        JMenuItem menuItemExit = new JMenuItem( "Exit" );
        menuItemExit.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                System.exit( 0 );
            }
        } );
        menuGame.add( menuItemExit );

        JMenuItem menuItemHelpObjective = new JMenuItem( "Objective" );
        menuItemHelpObjective.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuItemHelpObjectiveClicked();
            }
        } );
        menuHelp.add( menuItemHelpObjective );

        JMenuItem menuItemHelpAbout = new JMenuItem( "About" );
        menuItemHelpAbout.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                menuItemHelpAboutClicked();
            }
        } );
        menuHelp.add( menuItemHelpAbout );

        setJMenuBar( menuBar );

        getContentPane().add( new Board( schemes.get( 0 ) ) );
        pack();
    }

    private void registerSchemes()
    {
        BufferedReader reader = null;
        try
        {
            schemes = new ArrayList<Scheme>();
            reader = new BufferedReader( new FileReader( "Schemes.txt" ) );
            String line;
            while( (line = reader.readLine()) != null )
            {
                if( line.trim().length() > 0 )
                {
                    try
                    {
                        Scheme scheme = (Scheme) Class.forName( line.trim() ).newInstance();
                        schemes.add( scheme );
                    }
                    catch( Exception ex )
                    {
                        ex.printStackTrace();
                    }
                }
            }

            if( schemes.isEmpty() )
            {
              JOptionPane.showMessageDialog( this, "No schemes. There must be atleast one scheme to play the game.",
                  "Error", JOptionPane.ERROR_MESSAGE );
              System.exit( 0 );
            }

            for( final Scheme scheme : schemes )
            {
                JMenuItem menuItemGame = new JMenuItem( scheme.getSchemeName() );
                menuItemGame.addActionListener( new ActionListener()
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        getContentPane().remove( 0 );
                        getContentPane().add( new Board( scheme ) );
                        pack();
                        setLocation( (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
                            (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2 );
                    }
                });
                menuGame.add( menuItemGame );

                JMenuItem menuItemRecords = new JMenuItem( scheme.getSchemeName() );
                menuItemRecords.addActionListener( new ActionListener()
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        List<Record> records = scheme.validateRecords();
                        RecordsDialog dialog = new RecordsDialog( MainFrame.this, records );
                        dialog.setVisible( true );
                    }
                } );
                menuRecords.add( menuItemRecords );
            }
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }
        finally
        {
            if( reader != null )
            {
                try
                {
                    reader.close();
                }
                catch( IOException ex )
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void menuItemHelpObjectiveClicked()
    {
        JDialog dialog = new JDialog( this, "Game Objective" );
        JTextArea textArea = new JTextArea( "All the items are in pairs. Objective is to match the pairs " +
                "by clicking on them one after the other. Matched pairs will be marked with a colour and will be " +
                "kept visible.", 5, 20 );
        textArea.setFont( new Font( textArea.getFont().getName(), Font.BOLD, 13 ) );
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        dialog.getContentPane().setLayout( new GridBagLayout() );
        dialog.getContentPane().add( textArea, new GridBagConstraints( 0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
            GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 ) );

        dialog.setSize(250, 150);

        dialog.setLocation( this.getLocation().x + (this.getSize().width - dialog.getSize().width) / 2,
                this.getLocation().y + (this.getSize().height - dialog.getSize().height) / 2 );

        dialog.setVisible(true);
    }

    private void menuItemHelpAboutClicked()
    {
        JDialog aboutDialog = new JDialog( this, "About", true );
        aboutDialog.setLayout( new GridBagLayout() );

        JLabel gameName = new JLabel( "Pairs" );
        gameName.setFont( new Font( gameName.getFont().getName(), Font.BOLD, 20 ) );

        aboutDialog.getContentPane().add( gameName, new GridBagConstraints( 0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 10, 20, 5, 30 ), 0, 0 ) );

        aboutDialog.getContentPane().add( new JLabel( " v1.0" ), new GridBagConstraints( 0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 20, 10, 30 ), 0, 0 ) );

        aboutDialog.getContentPane().add( new JLabel( "Prasad Karunagoda" ), new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 10, 20, 5, 30 ), 0, 0 ) );

        aboutDialog.getContentPane().add( new JLabel( "March 2010" ), new GridBagConstraints( 0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 20, 10, 30 ), 0, 0 ) );

        aboutDialog.pack();

        aboutDialog.setLocation( this.getLocation().x + (this.getSize().width - aboutDialog.getSize().width) / 2,
                this.getLocation().y + (this.getSize().height - aboutDialog.getSize().height) / 2 );

        aboutDialog.setVisible( true );
    }
}
