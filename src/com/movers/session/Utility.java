/*
 * Utility.java
 *
 * Created on June 7, 2005, 11:17 AM
 */

package com.movers.session;

import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.Serializable;
import java.util.*;

import com.movers.VO.Open_JobVO;
import com.movers.VO.EstimateVO;
import com.movers.VO.SessionVO;
import com.movers.VO.MoverInformationVO;
import com.movers.VO.EmployeeInformationVO;
import com.movers.VO.OwnerReportVO;
import com.movers.VO.OwnerMonthlyReportVO;

/**
 *
 * @author chris
 */
public class Utility implements Serializable {
    
    /** Creates a new instance of Utility */
    public Utility() {
    }
    
    public Long test( EstimateVO blah ) {
        Long job_id = blah.commit();
        return( job_id );
    }
    
    public Long updateJob( Open_JobVO job ) throws Exception {
        System.out.println( "updating job" );        
        Long job_id = job.update();
        return( job_id );
    }
    
    public Open_JobVO load( Long job_id, Long company_id ) {
        Open_JobVO jobVO = new Open_JobVO();
        jobVO.load( job_id, company_id );
        return( jobVO );
    }
    
    public Boolean updateOwnerReport( OwnerReportVO vo ) throws Exception
    {
        Connection conn = openConnection();
        try {            
            vo.update( conn, vo._job_id.longValue(), vo._company_id.longValue() );
        }
        finally {
            conn.close();
        }        
        return( true );
    }
    
    public Boolean updateOwnerMonthlyDeductions( OwnerMonthlyReportVO vo ) throws Exception
    {
        Connection conn = openConnection();
        try {            
            //vo.print();
            vo.update( conn );
        }
        finally {
            conn.close();
        }        
        return( true );
    }
    
    public OwnerMonthlyReportVO getMonthlyOwnerDeductions( Number agent_id, Number month, Number year ) throws Exception
    {
        Connection conn = null;
        OwnerMonthlyReportVO vo = null;
        try {
            conn = openConnection();
            vo = new OwnerMonthlyReportVO();
            vo.load( conn, agent_id, month, year );
        }
        finally {
            conn.close();
        }
        return( vo );
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
    
    public CachedRowSet fillByZip( String zipcode ) throws Exception {        
        Connection conn = null;
        CachedRowSet result = null;
        try {
            conn = openConnection();
            result = new CachedRowSetImpl();
            result.setCommand( "select county,city,state from zipcode where zip = '"+zipcode+"';" );
            result.execute( conn );
        }
        finally {
            conn.close();
        }
        
        return( result );
    }
    
    
    public CachedRowSet jobSearch( String text, Long company_id ) throws Exception {
        CachedRowSet result = null;
        Connection conn = openConnection();        
        try {
            text = text.toLowerCase();
            result = new CachedRowSetImpl();
            String sql = "";
            System.out.println( "Searching for " + text );
            if( company_id == 657676 )
            {
                sql += "select job.job_id, job.company_id, fromcontact.name,comment.comment from job,fromcontact,comment where job.job_id = fromcontact.job_id and job.job_id = comment.job_id and job.job_id in (";
                sql += "select job.job_id from job where job.job_id::text like '%"+text+"%' union ";
                // sddd
                sql += "select job.job_id from job where lower(job.agent) like '%"+text+"%' union ";
                sql += "select job.job_id from job where lower(job.status) like '%"+text+"%' union ";
                sql += "select job.job_id from job where lower(job.adv) like '%"+text+"%' union ";
                sql += "select job.job_id from job where lower(job.trip_num) like '%"+text+"%' union ";
                sql += "select job.job_id from job where lower(job.truck_num) like '%"+text+"%' union ";
                //
                sql += "select job.job_id from job where lower(job.fromdate) like '%"+text+"%' union ";
                //sdf
                sql += "select job.job_id from job where lower(job.storage_location) like '%"+text+"%' union ";
                //
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.name) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.address1) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.address2) like '%"+text+"%' union ";
                //sdfsdf
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.county) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.state) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.zip) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.city) like '%"+text+"%' union ";
                //
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.homephone) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.homefax) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.workphone) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.workfax) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where lower(fromcontact.email) like '%"+text+"%' union ";
                
                //sdf
                sql += "select tocontact.job_id from tocontact where lower(tocontact.name) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.address1) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.address2) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.county) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.state) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.zip) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.city) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.homephone) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.homefax) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.workphone) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.workfax) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where lower(tocontact.email) like '%"+text+"%' union ";
                //
                
                //sdfsdf
                sql += "select additional_contact.job_id from additional_contact where lower(additional_contact.name) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.address1) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.address2) like '%"+text+"%' union ";
                //sql += "select additional_contact.job_id from additional_contact where additional_contact.county like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.state) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.zip) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.city) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.homephone) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.homefax) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.workphone) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.workfax) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where  lower(additional_contact.email) like '%"+text+"%' union ";
                //
                
                //sdfsdf
                sql += "select comment.job_id from comment where lower(comment.comment) like '%"+text+"%' union ";
                sql += "select comment.job_id from comment where lower(comment.internal) like '%"+text+"%' union ";
                sql += "select comment.job_id from comment where lower(comment.delivery) like '%"+text+"%' ";
                
                sql += " )";
            }
            else {
                sql += "select job.job_id,job.company_id,fromcontact.name,comment.comment from job,fromcontact,comment where job.company_id = '"+company_id+"' and job.job_id = fromcontact.job_id and job.job_id = comment.job_id and job.job_id in (";
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and job.job_id::text like '%"+text+"%' union ";
                // sddd
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.agent) like '%"+text+"%' union ";
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.status) like '%"+text+"%' union ";
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.adv) like '%"+text+"%' union ";
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.trip_num) like '%"+text+"%' union ";
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.truck_num) like '%"+text+"%' union ";
                //
                
                //sdf
                sql += "select job.job_id from job where job.company_id = '"+company_id+"' and lower(job.storage_location) like '%"+text+"%' union ";
                //
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.name) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.address1) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.address2) like '%"+text+"%' union ";
                //sdfsdf
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.county) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.state) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.zip) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.city) like '%"+text+"%' union ";
                //
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.homephone) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.homefax) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.workphone) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.workfax) like '%"+text+"%' union ";
                sql += "select fromcontact.job_id from fromcontact where fromcontact.company_id = '"+company_id+"' and lower(fromcontact.email) like '%"+text+"%' union ";
                
                //sdf
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.name) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.address1) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.address2) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.county) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.state) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.zip) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.city) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.homephone) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.homefax) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.workphone) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.workfax) like '%"+text+"%' union ";
                sql += "select tocontact.job_id from tocontact where tocontact.company_id = '"+company_id+"' and lower(tocontact.email) like '%"+text+"%' union ";
                //
                
                //sdfsdf
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.name) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.address1) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.address2) like '%"+text+"%' union ";
                //sql += "select additional_contact.job_id from additional_contact where additional_contact.county like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.state) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.zip) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.city) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.homephone) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.homefax) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.workphone) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.workfax) like '%"+text+"%' union ";
                sql += "select additional_contact.job_id from additional_contact where additional_contact.company_id = '"+company_id+"' and lower(additional_contact.email) like '%"+text+"%' union ";
                //
                
                //sdfsdf
                sql += "select comment.job_id from comment where comment.company_id = '"+company_id+"' and lower(comment.comment) like '%"+text+"%' union ";
                sql += "select comment.job_id from comment where comment.company_id = '"+company_id+"' and lower(comment.internal) like '%"+text+"%' union ";
                sql += "select comment.job_id from comment where comment.company_id = '"+company_id+"' and lower(comment.delivery) like '%"+text+"%' ";
                
                sql += " )";                
            }
            result.setCommand( sql );
            result.execute( conn );
        } 
        catch( Exception e ) {
            System.out.println( "Find Job " + e );
        } 
        finally {
            conn.close();
            System.out.println( "closing connection" );
        }
        return( result );
    }
    
    
    public String addMover( MoverInformationVO moverInformationVO ) throws Exception {
        Connection conn = null;
        try {            
            conn = openConnection();            
            moverInformationVO.print();
            moverInformationVO.commit( conn );                        
            return( "true" );
        }        
        finally {
            conn.close();
        }
    }
    
    public String addEmployee( EmployeeInformationVO employeeInformationVO ) throws Exception {
        Connection conn = null;
        try {            
            conn = openConnection();            
            employeeInformationVO.print();
            employeeInformationVO.commit( conn );         
            System.out.println( "returning true" );
            return( "true" );
        }
        finally {
            conn.close();
        }
    }
    
    public CachedRowSet getMoversPayroll( String name, String from, String to, Long company_id ) throws Exception {
        
        CachedRowSet movers;
        Connection conn = null;        
        try {
            
            conn = openConnection();
            movers = new CachedRowSetImpl();
    
            String sql = "SELECT job_date as date,movers.job_id as job,movers.company_id as company_id,position as type,hours,rate,comm, ";
            sql += "other,deduct,tip,mover_job_id,((rate*hours)+comm+other+tip-deduct)::varchar(10) as total,paid, ";
            sql += "(select jobtype from job where job.job_id = movers.job_id )::varchar(30) as job_type from movers ";
            sql += "where name = '"+name+"' and job_date between '"+from+"' and '"+to+"' order by job_date asc ";
            
            movers.setCommand(sql);
            movers.execute(conn);
            
            return( movers );
        }
        finally {
            conn.close();
        }
    }
    
    public CachedRowSet getOwnerReport( String agent, String from, String to, Long company_id ) throws Exception
    {        
        CachedRowSet report;
        Connection conn = null;        
        try {
            
            conn = openConnection();
            report = new CachedRowSetImpl();
    
            String sql = "SELECT job.fromdate as date,job.job_id as job,job.company_id as company_id,summary_info.total as total,summary_info.extramaterials as materials, ";
            sql += "estimate_totals.total as estimated, labor as labor_charge, other_1,other_2,other_3, ";
            sql += "(labor + other_1 + other_2 + other_3) as deductions ";
            sql += "from job,summary_info,estimate_totals,owner_report ";
            sql += "where job.job_id = owner_report.job_id and owner_report.job_id = summary_info.job_id and job.job_id = summary_info.job_id and job.job_id = estimate_totals.job_id and job.company_id = summary_info.company_id ";            
            sql += "and job.company_id = estimate_totals.company_id and job.company_id = owner_report.company_id and summary_info.company_id = owner_report.company_id ";
            sql += "and job.agent = '"+agent+"' and fromdate between '"+from+"' and '"+to+"' order by job.fromdate asc  ";
            
            //System.out.println( sql );
            
            report.setCommand(sql);
            report.execute(conn);
            
            return( report );
        }
        finally {
            conn.close();
        }
    }
    
    public String updateMoversPay( Object[] data ) throws Exception {
        
        Connection conn = null;        
        Statement stmt  = null;
        try {
            conn = openConnection();
            conn.setAutoCommit( false );
            stmt = conn.createStatement();                                
            
            String sql = null;
            
            for( int i=0; i< data.length; i++ ) {
                Map m = (Map)data[i];
                Number id = (Number)m.get( "mover_job_id" );
                sql = "update movers set paid = '"+m.get( "paid" )+"' where mover_job_id = '"+id.longValue()+"' ";
                //System.out.println( sql );
                stmt.execute( sql );
            }
            conn.commit();
            
            return( "true" );
        }
        finally {
            conn.close();
        }
    }
    
    public String updateMover( MoverInformationVO moverInformationVO ) throws Exception {
        Connection conn = null;
        try {            
            conn = openConnection();            
            moverInformationVO.print();
            moverInformationVO.update( conn );            
            return( "true" );
        }
        finally {
            conn.close();
        }
    }
     
    public String savePrint( String name, String from_date, String to_date, Long company_id )  throws Exception {
        
        Connection conn = null;
        Statement  stmt = null;
        try {
            conn = openConnection();
            stmt = conn.createStatement();
            
            String sql = "";
            ResultSet rs = null;
            sql = "select nextval( 'prints' )";
            rs = stmt.executeQuery( sql );
            rs.next();
            Long print_id = rs.getLong( "nextval" );
            
            sql = "";
            sql += "insert into print_key values ( ";
            sql += "'"+name+"', '"+company_id+"', '0', '"+from_date+"', '"+to_date+"' , '"+print_id+"' )";
            stmt.execute( sql );        
            return( String.valueOf(print_id) );
        }
        finally {
            stmt.close();
            conn.close();
        }
    }
    
    public CachedRowSet getAdv() throws Exception
    {
        CachedRowSet adv;
        Connection conn = null;        
        try {            
            conn = openConnection();
            adv = new CachedRowSetImpl();
    
            String sql = "select adv from adv order by lower(adv) asc";
            
            adv.setCommand(sql);
            adv.execute(conn);
            return( adv );
        }
        finally {
            conn.close();
        }
    }
    
    
    public String addAdv( String adv ) throws Exception
    {        
        Connection conn = null;
        try
        {
            conn = openConnection();
            
            CachedRowSet res = new CachedRowSetImpl();
            res.setCommand( "select 1 from adv where adv = '"+adv+"'" );
            res.execute(conn);
            
            System.out.println( "res.size is " + res.size() );
            if( res.size() != 0 )
            {                          
                throw new Exception( "That advertising source already exists." );
            }
            
            System.out.println( "Adding adv " + adv );            
            PreparedStatement eh = conn.prepareStatement( "insert into adv values( ? )" );
            eh.setString( 1, adv );
            eh.executeUpdate();
            return( "true" );
        }
        finally {
            System.out.println( "Closing connection for add adv " + adv );
            conn.close();
        }
        
    }
        
    public CachedRowSet getMoverInfo( String name, Long company_id ) throws Exception {
                
        Connection conn     = null;
        CachedRowSet movers = null;
        
        try {
            System.out.println( "Getting mover info for " + name + " " + company_id );
            conn = openConnection();
            movers = new CachedRowSetImpl();
            movers.setCommand( "select * from movers_list where name = '"+name+"'" );
            movers.execute(conn);            
            return( movers );
        } 
        finally
        {
            System.out.println( "getMoverInfo(): closing connection" );
            conn.close();
        }                
    }
    
    public String changePassword( String username, String old, String new_password ) throws Exception {
        Connection conn = null;
        Statement stmt = null;       
        try {
            conn = openConnection();
            stmt = conn.createStatement(); 
            String sql = "update employee set password = '"+new_password+"' where username = '"+username+"' and password = '"+old+"'";
            System.out.println( "changing password for " + username );
            int ret = stmt.executeUpdate(sql);
            stmt.close();            
            if( ret == 1 ) {
                return( "true" );
            }
            else {
                return( "Wrong current password.  Please try again" );
            }
        }
        finally {
            conn.close();
        }
    }
    
    public CachedRowSet getJobStatus( Long job_id, Long company_id ) throws Exception {
        Connection conn = null;
        CachedRowSet result = null;
        try {
            System.out.println( "getting status for job " + job_id );
            conn = openConnection();
            result = new CachedRowSetImpl();
            String sql = "SELECT job.job_id,name,jobtype,status,booked_by,taken_by from job,fromcontact where job.job_id = '"+job_id+"' and job.company_id = '"+company_id+"' and job.job_id = fromcontact.job_id;";                    
            result.setCommand(sql);
            result.execute( conn );
            return( result );
        }
        finally {
            System.out.println( "closing connection for getJobStatus()" );
            conn.close();
        }
    }
    
    public Boolean setJobStatus( Long job_id, Long company_id, String jobtype, String status, String taken_by, String booked_by ) throws Exception {
        Connection conn = null;
        try {            
            conn = openConnection(); 
            
            String sql = "update job set jobtype = '"+jobtype+"', status = '"+status+"', taken_by = '"+taken_by+"', booked_by = '"+booked_by+"' where job_id = '"+job_id+"' and company_id = '"+company_id+"'";                    
            Statement stmt = conn.createStatement();
            int ret = stmt.executeUpdate( sql );                    
            conn.commit();
            return( true );
        }
        finally {
            conn.close();
        }
    }
    
    // gets only active employees
    public CachedRowSet getEmployees( Long company_id ) {
        Connection conn = null;
        System.out.println( "getEmployees" );
        try {
            String sql = "";
            if( company_id == 657676 ) {
                sql = "select username from employee where role != 'Mover' and role != '' and username != 'admin' and username != 'dispatch' and active = 'y' order by username asc";
            } 
            else {
                sql = "select username from employee where company_id = '"+company_id+"' and role != 'Mover' and role != '' and username != 'admin' and username != 'dispatch' and active = 'y' order by username asc";
            }
            conn = openConnection();
            CachedRowSet employees = new CachedRowSetImpl();
            employees.setCommand( sql );
            employees.execute(conn);
            return( employees );
        } catch( Exception e ) {
            System.out.println( e );
            return( null );
        } finally {
            System.out.println( "in finally for getEmployees" );
            try {conn.close();} catch(Exception e) {};
        }
    }
    
    // gets employees that are inactive also
    public CachedRowSet getAllEmployees( Long company_id ) {
        Connection conn = null;
        System.out.println( "getEmployees" );
        try {
            String sql = "";
            if( company_id == 657676 ) {
                sql = "select username from employee where role != 'Mover' and role != '' and username != 'admin' and username != 'dispatch' order by username asc";
            } 
            else {
                sql = "select username from employee where company_id = '"+company_id+"' and role != 'Mover' and role != '' and username != 'admin' and username != 'dispatch' order by username asc";
            }
            conn = openConnection();
            CachedRowSet employees = new CachedRowSetImpl();
            employees.setCommand( sql );
            employees.execute(conn);
            return( employees );
        } catch( Exception e ) {
            System.out.println( e );
            return( null );
        } finally {
            System.out.println( "in finally for getEmployees" );
            try {conn.close();} catch(Exception e) {};
        }
    }
    
    public CachedRowSet getMovers( Long company_id ) throws Exception {
        System.out.println( "getMovers" );
        Connection conn = null;
        try {
            CachedRowSet movers;
            conn = openConnection();
            movers = new CachedRowSetImpl();
            movers.setCommand( "select * from movers_list order by name asc" );
            movers.execute(conn);
            return( movers );
        } catch( Exception e ) {
            System.out.println( e );
            return( null );
        } finally {
            conn.close();
        }
    }
    
    public CachedRowSet getAgents( Long company_id ) throws Exception {
        Connection conn = null;
        CachedRowSet result = null;
        
        try {
            result = new CachedRowSetImpl();
            
            conn = openConnection();
            result.setCommand( "select name,agent_id from agent order by name asc" );
            result.execute( conn );
        } finally {
            conn.close();
        }
        return( result );
    }
    
    public CachedRowSet getCarriers( Long company_id ) throws Exception {
        Connection conn = null;
        CachedRowSet result = null;
        
        try {
            result = new CachedRowSetImpl();
            
            conn = openConnection();
            result.setCommand( "select name,carrier_id from carrier order by name asc" );
            result.execute( conn );
        } finally {
            conn.close();
        }
        return( result );
    }
    
    public CachedRowSet getDispatchSchedule( Long company_id, String type, String from, String to ) throws Exception {
        
        System.out.println( "getDispatchSchedule " + from + " " + to + " " + type  );
        Connection conn = openConnection();
        CachedRowSet result = new CachedRowSetImpl();
        String sql = "";
        try {
            if( type.equals( "All" ) ) {
                if( company_id == 657676 ) {
                    result.setCommand( "SELECT job.company_id, job.job_id as job,  job.jobtype as type, job.status as status, job.fromtime as time, (to_char( job.fromdate, 'MM-DD-YYYY' ))::varchar(20) as date, fromcontact.name as name, fromcontact.address1 as address, fromcontact.city as from_city, tocontact.city as to_city, fromcontact.state as from_state, tocontact.state as to_state, confirmed_by from job,fromcontact,tocontact where job.job_id = tocontact.job_id and job.job_id = fromcontact.job_id and fromdate between date '"+from+"' and date '"+to+"' and job.status != 'Cancelled' order by job.company_id asc" );
                    //result.setCommand( "SELECT job.company_id, job.job_id as job,  job.jobtype as type, job.status as status, (to_char( job.fromdate, 'MM-DD-YYYY' ))::varchar(20) as date, fromcontact.name as name, fromcontact.address1 as address, confirmed_by from job,fromcontact where job.job_id = fromcontact.job_id and fromdate between date '"+from+"' and date '"+to+"' and job.status != 'Cancelled' order by job.company_id asc" );
                    result.execute( conn );                
                }
                else {
                    result.setCommand( "SELECT job.company_id, job.job_id as job,  job.jobtype as type, job.status as status, job.fromtime as time, (to_char( job.fromdate, 'MM-DD-YYYY' ))::varchar(20) as date, fromcontact.name as name, fromcontact.address1 as address, fromcontact.city as from_city, tocontact.city as to_city, fromcontact.state as from_state, tocontact.state as to_state, confirmed_by from job,fromcontact,tocontact where job.job_id = tocontact.job_id and job.job_id = fromcontact.job_id and fromdate between date '"+from+"' and date '"+to+"' and job.status != 'Cancelled' and job.company_id = '"+company_id+"' order by job.company_id asc" );
                    //result.setCommand( "SELECT job.company_id, job.job_id as job,  job.jobtype as type, job.status as status, (to_char( job.fromdate, 'MM-DD-YYYY' ))::varchar(20) as date, fromcontact.name as name, fromcontact.address1 as address, confirmed_by from job,fromcontact where job.job_id = fromcontact.job_id and fromdate between date '"+from+"' and date '"+to+"' and job.status != 'Cancelled' and job.company_id = '"+company_id+"'" );
                    result.execute( conn );                
                }
                
                return( result );
            }
            else if( type.equals( "Local Moving") ) {
                if( company_id == 657676 ) {
                    sql += "SELECT job.company_id, job.job_id as job,fromcontact.name,fromtime as time,fromcontact.city as From,tocontact.city as To,movers as men,fromcontact.homephone as phone,confirmed_by, truck_num as truck,agent,summary_info.total ";
                    sql += "from job,fromcontact,tocontact,local_summary,summary_info ";
                    sql += "where job.job_id = fromcontact.job_id and job.job_id = tocontact.job_id and job.job_id = local_summary.job_id and job.job_id = summary_info.job_id ";
                    sql += "and fromdate between date '"+from+"' and date '"+to+"' and jobtype = 'Local Moving' and job.status != 'Cancelled' order by job.company_id asc";
                    result.setCommand( sql );
                    result.execute( conn );        
                }
                else {
                    sql += "SELECT job.company_id, job.job_id as job,fromcontact.name,fromtime as time,fromcontact.city as From,tocontact.city as To,movers as men,fromcontact.homephone as phone,confirmed_by, truck_num as truck,agent,summary_info.total ";
                    sql += "from job,fromcontact,tocontact,local_summary,summary_info ";
                    sql += "where job.job_id = fromcontact.job_id and job.job_id = tocontact.job_id and job.job_id = local_summary.job_id and job.job_id = summary_info.job_id ";
                    sql += "and fromdate between date '"+from+"' and date '"+to+"' and jobtype = 'Local Moving' and job.status != 'Cancelled' and job.company_id = '"+company_id+"'";
                    result.setCommand( sql );
                    result.execute( conn );
                }
                
                return( result );
            }
            else if( type.equals( "Long Distance" ) ) {
                if( company_id == 657676 ) {
                    sql += "SELECT job.company_id, job.job_id as job,job.status as status,fromcontact.name,fromtime as time,fromcontact.state as From,tocontact.state as To,fromcontact.homephone as phone,confirmed_by,truck_num as truck,agent,summary_info.total ";
                    sql += "from job,fromcontact,tocontact,local_summary,summary_info ";
                    sql += "where job.job_id = fromcontact.job_id and job.job_id = tocontact.job_id and job.job_id = local_summary.job_id and job.job_id = summary_info.job_id ";
                    sql += "and fromdate between date '"+from+"' and date '"+to+"' and jobtype = 'Long Distance' and job.status != 'Cancelled' order by job.company_id asc";
                    result.setCommand( sql );
                    result.execute( conn );  
                }
                else {
                    sql += "SELECT job.company_id, job.job_id as job,job.status as status,fromcontact.name,fromtime as time,fromcontact.state as From,tocontact.state as To,fromcontact.homephone as phone,confirmed_by,truck_num as truck,agent,summary_info.total ";
                    sql += "from job,fromcontact,tocontact,local_summary,summary_info ";
                    sql += "where job.job_id = fromcontact.job_id and job.job_id = tocontact.job_id and job.job_id = local_summary.job_id and job.job_id = summary_info.job_id ";
                    sql += "and fromdate between date '"+from+"' and date '"+to+"' and jobtype = 'Long Distance' and job.status != 'Cancelled' and job.company_id = '"+company_id+"'";
                    result.setCommand( sql );
                    result.execute( conn );  
                }
                
              
                return( result );
            } 
            else {
                return( null );
            }
        } 
        finally {
            System.out.println( "closing dispatch conn" );
            conn.close();
        }
        
    }
    
    public SessionVO getEmployeeInfo( String username ) throws Exception {
        
        SessionVO session = null;
        Connection conn   = null;        
        try {
            session = new SessionVO();    
            conn = openConnection();
            session.load( conn, username );
        }
        finally {
            conn.close();
        }        
        return( session );
    }
    
    public CachedRowSet login( String username, String password ) throws Exception {
        
        if( username.equals("") || password.equals("") ) {
            return(null);
        }
        
        Long company_id = new Long(0);
        Connection conn = null;
        CachedRowSet rs = null;
        
        try {
            conn = openConnection();
            rs = new CachedRowSetImpl();
            rs.setCommand( "select employee_id,company_id,name,username,password,role from employee where username = '"+username+"' and active = 'y'" );
            rs.execute( conn );
        } catch( Exception e ) {
            e.printStackTrace();
        }
        
        try {
            rs.next();
            String storedpass = rs.getString( "password" );
            if( password.equals(storedpass) ) {
                conn.close();
                company_id = rs.getLong( "company_id" );
                System.out.println( "company_id for login is " + company_id );
                return(rs);
            } else {
                conn.close();
                return( null );
            }
        } catch( Exception e ) {
            try {
                conn.close();
            } catch( Exception ee ) {
                System.out.println( "couldn't close connection for login() " + ee );
            }
            return(null);
        }
    }
}
