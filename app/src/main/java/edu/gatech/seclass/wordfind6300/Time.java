package edu.gatech.seclass.wordfind6300;

/* vim: set tabstop=4 softtabstop=4 shiftwidth=4 noexpandtab : */
import java.util.Date;
import java.util.Calendar;

public class Time
{
	private int duration; //minutes
    private Date date;
    private Calendar cal;
	private long end; //ms
	private long now; //ms
	//constructor

	public Time()
	{
		duration = 3;
        this.date = new Date();
		this.now = this.date.getTime();
System.out.println("time.now:"+this.now);
        this.cal = Calendar.getInstance();
		//this.now = this.cal.getTimeInMillis();
long ms = System.currentTimeMillis();
System.out.println("ms:"+ms);
//this.now = System.currentTimeMillis();
		this.end = this.now + (this.duration * 60 * 1000);
	}
	public Time(int duration)
	{
        this.date = new Date();
		this.now = this.date.getTime();
System.out.println("time.now:"+this.now);
        this.cal = Calendar.getInstance();
		//this.now = this.cal.getTimeInMillis();
long ms = System.currentTimeMillis();
System.out.println("ms:"+ms);
//this.now = System.currentTimeMillis();
		this.end = this.now + (this.duration * 60 * 1000);
	}

    public void setTime(int duration) {
	    this.duration = duration;
	    this.end = this.now + (this.duration * 60 * 1000);
	    return;
    }

    public void Change() {
	    this.now = this.date.getTime();
	    //this.cal = this.cal.getTimeInMillis();
	    //this.now = System.currentTimeMillis();
	    return;
    }

/////Unit Test needed
    //public int getTime() { return (int) (this.now/1000); }
    public int getTime() { return (int) (this.now); }
    public int add(int val) { this.now += val*1000; return this.getTime(); }
    public int sub(int val) { this.now -= val*1000; return this.getTime(); }
    public int Remain() { int remain = (int) ((this.end - this.now)/1000); return remain; }

}

