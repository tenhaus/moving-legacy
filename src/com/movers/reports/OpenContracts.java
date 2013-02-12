/*
 * OpenContracts.java
 *
 * Created on June 16, 2005, 1:31 PM
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
public class OpenContracts {
    
    /** Creates a new instance of OpenContracts */
    public OpenContracts() {
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
    
    public CachedRowSet getOpenContracts( Long company_id ) throws Exception {
        Connection conn = null;
        try {
            String sql = "";
            if( company_id == 657676 )
            {
                sql = "SELECT job.company_id, job.job_id as job,name,fromdate as date,(select sum(amount)from payment where payment.job_id = job.job_id) as paid,total, total -(select sum(amount) from payment where payment.job_id = job.job_id) as balance from job,fromcontact,summary_info where status = 'Booked' and jobtype = 'Long Distance' and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id order by job.company_id asc;";
            }
            else
            {
                sql = "SELECT job.company_id, job.job_id as job,name,fromdate as date,(select sum(amount)from payment where payment.job_id = job.job_id) as paid,total, total -(select sum(amount) from payment where payment.job_id = job.job_id) as balance from job,fromcontact,summary_info where status = 'Booked' and jobtype = 'Long Distance' and job.job_id = fromcontact.job_id and job.job_id = summary_info.job_id and job.company_id = '"+company_id+"' order by fromdate asc;";
            }
            conn = openConnection();
            CachedRowSet result = new CachedRowSetImpl();
            
            System.out.println( sql );
            
            result.setCommand(sql);
            result.execute( conn );
            return( result );
        } 
        finally {
            System.out.println( "in finally for getOpenContracts" );
            try{conn.close();} catch(Exception e) {}
        }
    }    
}
