package prasad.game.pairs;

import javax.swing.Icon;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 6, 2010
 */
public abstract class Scheme
{
    /** Number of best times shown as records */
    public static final int NUMBER_OF_RECORDS = 5;

    public static final String DEFAULT_RECORD_NAME = "Anonymous";

    public static final String IMAGES_FOLDER_NAME = "images";

    private static final String RECORD_FILE_EXTENSION = ".rec";
    private static final String VALIDATE_FILE_EXTENSION = ".v";
    private static final String FILES_FOLDER_NAME = "record_files";

    private static final int DEFAULT_RECORD_TIME = 999;

    private static final int VALIDATION_NUMBER = 50000;

    /**
     * Return the number of rows in the grid in this particular scheme.
     *
     * @return Number of rows in the grid
     */
    public abstract int getRows();

    /**
     * Return the number of columns in the grid in this particular scheme.
     *
     * @return Number of columns in the grid
     */
    public abstract int getColumns();

    /**
     * Retrieve an image icon from the set of image icons in this particular scheme by passing an index.
     *
     * @param index Numerical index
     * @return An image icon
     */
    public abstract Icon getIcon( int index );

    /**
     * When an image pair is matched, those images are marked with a colour around them. That colour can be different
     * for different schemes. This method returns the colour for this scheme.
     *
     * @return A colour
     */
    public abstract Color getCellColor();

    /**
     * Get scheme name of the current scheme.
     *
     * @return Scheme name
     */
    public abstract String getSchemeName();

    /**
     * Validate best time records saved for this scheme. If the records are invalid, they are reset. Records after
     * validation are returned (if reset, those reset records are returned).
     *
     * @return Validated best time records
     */
    public List<Record> validateRecords()
    {
        List<Record> records = getRecords();
        int number = calculateRecordsNumber( records );
        int validationNumber = getValidateNumber();

        if( ( number + validationNumber ) != VALIDATION_NUMBER )
        {
            records.clear();
            for( int i = 0; i < NUMBER_OF_RECORDS; i++ )
            {
                records.add( new Record( DEFAULT_RECORD_NAME, DEFAULT_RECORD_TIME ) );
            }
            writeRecords( records );
        }

        Collections.sort(records);
        return records;
    }

    /**
     * Save the set of best time records for this scheme.
     *
     * @param records Best time records
     */
    public void writeRecords( List<Record> records )
    {
        BufferedWriter writer = null;
        try
        {
            StringBuilder sb = new StringBuilder();
            for( Record r : records )
            {
                sb.append( r.getName() );
                sb.append( ":" );
                sb.append( r.getTime() );
                sb.append( "\n" );
            }

            writer = new BufferedWriter( new FileWriter( FILES_FOLDER_NAME + File.separator + getSchemeName()
                + RECORD_FILE_EXTENSION ) );
            writer.write( sb.toString() );
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }
        finally
        {
            if( writer != null )
            {
                try
                {
                    writer.close();
                }
                catch( IOException ex )
                {
                    ex.printStackTrace();
                }
            }
        }


        int recordsNumber = calculateRecordsNumber( records );

        BufferedWriter validateNumberWriter = null;
        try
        {
            validateNumberWriter = new BufferedWriter( new FileWriter( FILES_FOLDER_NAME + File.separator +
                getSchemeName() + VALIDATE_FILE_EXTENSION ) );
            validateNumberWriter.write( String.valueOf(VALIDATION_NUMBER - recordsNumber) );
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }
        finally
        {
            if( validateNumberWriter != null )
            {
                try
                {
                    validateNumberWriter.close();
                }
                catch( IOException ex )
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads best time records of this scheme and returns them.
     *
     * @return Best time records
     */
    private List<Record> getRecords()
    {
        BufferedReader reader = null;
        try
        {
            List<Record> records = new ArrayList<Record>();
            reader = new BufferedReader( new FileReader( FILES_FOLDER_NAME + File.separator + getSchemeName() +
                RECORD_FILE_EXTENSION ) );
            String line;
            while( (line = reader.readLine()) != null )
            {
                String[] nameAndTime = line.split( ":" );
                Record record = new Record( nameAndTime[0], Integer.valueOf( nameAndTime[1] ) );
                records.add( record );
            }
            return records;
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

        return null;
    }

    private static int calculateRecordsNumber( List<Record> records )
    {
        int number = 0;
        for( Record r : records )
        {
            char[] chars = r.getName().toCharArray();
            for( char c : chars )
            {
                number = number + c;
            }
            number = number + r.getTime();
        }
        return number;
    }

    private int getValidateNumber()
    {
        int number = -1;
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader( new FileReader( FILES_FOLDER_NAME + File.separator + getSchemeName() + VALIDATE_FILE_EXTENSION ) );
            String line = reader.readLine();
            if( line != null )
            {
                number = Integer.valueOf( line );
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
        return number;
    }
}
