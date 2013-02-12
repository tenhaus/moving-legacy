/*
 * CancelledJobs.java
 *
 * Created on June 16, 2005, 12:51 PM
 */

package com.movers.reports;

import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author chris
 */
public class CancelledJobs {
    
    /** Creates a new instance of CancelledJobs */
    public CancelledJobs() {
    }
    
    public Connection openConnection() throws Exception {
        
        System.out.println( "opening connection" );
        
        Context initContext = null;
        Context envContext = null;
        DataSource ds = null;
        Connection conn = null;
        
        initContext = new InitialContext();
        envContext  = (Context)initContext.lookup("java:/comp/env");
        ds = (DataSource)envContext.lookup("jdbc/postgres");
        conn = ds.getConnection();
        return( conn );
    }
    
    public CachedRowSet getCancelledJobs( String beg, String end, Long company_id ) throws Exception {
        Connection conn = openConnection();
        try {
            String sql = "";
            if( company_id == 657676 )
            {
               sql = "SELECT job.company_id, job.job_id as job, taken_by as salesman, name, taken as booked, fromdate as date,cancel_dates.cancel as cancelled, total from estimate_dates,cancel_dates,job,fromcontact,summary_info where job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = cancel_dates.job_id and job.job_id = estimate_dates.job_id and status = 'Cancelled' and estimate_dates.taken between date '"+beg+"' and date '"+end+"' order by job.company_id desc;"; 
            }
            else 
            {
                sql = "SELECT job.company_id, job.job_id as job, taken_by as salesman, name, taken as booked, fromdate as date,cancel_dates.cancel as cancelled,total from estimate_dates,cancel_dates,job,fromcontact,summary_info where job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.job_id = cancel_dates.job_id and job.job_id = estimate_dates.job_id and status = 'Cancelled' and job.company_id = '"+company_id+"' and estimate_dates.taken between date '"+beg+"' and date '"+end+"' order by job.job_id desc;";
            }
            CachedRowSet result = new CachedRowSetImpl();
            System.out.println( sql );
            result.setCommand(sql);
            result.execute( conn );
            return( result );
        }
        finally {
            try{conn.close();} catch(Exception e) {}
        }
    }    
}
