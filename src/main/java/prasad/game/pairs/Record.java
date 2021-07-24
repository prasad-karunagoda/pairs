package prasad.game.pairs;

/**
 * Author: Prasad Karunagoda
 * Date: Mar 7, 2010
 */
public class Record implements Comparable
{
    private String name;
    private int time;

    public Record( String name, int time )
    {
        this.name = name;
        this.time = time;
    }

    public String getName()
    {
        return name;
    }

    public int getTime()
    {
        return time;
    }

    public int compareTo( Object obj )
    {
        if( this.time < ((Record) obj).time )
        {
            return -1;
        }
        else if( this.time > ((Record) obj).time )
        {
            return 1;
        }
        return 0;
    }
}
