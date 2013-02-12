/*
 * MoversVO.java
 *
 * Created on July 6, 2005, 4:45 PM
 */

package com.movers.VO;

import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.io.Serializable;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.naming.*;
import com.sun.rowset.CachedRowSetImpl;

/**
 *
 * @author chris
 */
public class MoversVO {
    public Long   _job_id;
    public Long   _company_id;    
    
    public ArrayList  _movers;
    
    public Object[] _moversList;
     
    /** Creates a new instance of MoversVO */
    public MoversVO() {
        _moversList = new Object[0];
    }
    
    public void print() {
        for( int i=0; i< _moversList.length; i++ ) {
            System.out.println( _moversList[i].toString() );
        }
    }
    
    public String stripFormatting( String item ) {
        if( item == null )
            return( "0.00" );
        
        String newitem = "";
        for( int i=0; i< item.length(); i++ ) {
            if( item.charAt(i) != '$' && item.charAt(i) != ',' )
                newitem += item.charAt(i);
        }
        return( newitem );
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from movers where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }


    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        /*
        String sql = "";
        Statement stmt = null;
         */
        
        for( int i=0; i< _moversList.length; i++ ) {
            
            Map m = (Map)_moversList[i];
   
            String employee = m.get( "employee" ).toString();
            String type = m.get( "type" ).toString();
            String date = m.get( "date" ).toString();
            String hours = m.get( "hours" ).toString();
            
            String rate = m.get( "rate" ).toString();            
            String comm = m.get( "comm" ).toString();
            String other = m.get( "other" ).toString();
            String tip = m.get( "tip" ).toString();
            String deduct = m.get( "deduct" ).toString();            
            String total = m.get( "total" ).toString();
            
            String paid = null;
            Number mover_job_id = null;
            
            if( m.get("paid") != null )
            {
                paid = m.get( "paid" ).toString();
            }
            if( m.get("mover_job_id") != null )
            {
                mover_job_id = (Number)m.get( "mover_job_id" );
            }
            
            rate = stripFormatting(rate);
            comm = stripFormatting(comm);
            other = stripFormatting(other);
            tip = stripFormatting(tip);
            deduct = stripFormatting(deduct);            
            total = stripFormatting(total);          
            
            PreparedStatement eh = conn.prepareStatement( "select mover_id from movers_list where name = ?" );
            eh.setString( 1, employee   );
            //eh.setLong  ( 2, company_id );
            
            ResultSet rs = eh.executeQuery();
            Long mover_id = null;
            while( rs.next() ) {
                mover_id = rs.getLong( "mover_id" );
            }
            
            String[] parts = date.split( "-" );            
            date = parts[2] + "-" + parts[0] + "-" + parts[1];
            
            PreparedStatement stmt = null;
            
            // new mover
            if( mover_job_id == null ) {
                
                stmt = conn.prepareStatement( "insert into movers values ( ?,?,?,?,?,?,?,?,?,?,?,? )" );
                stmt.setLong( 1, mover_id );
                stmt.setLong( 2, company_id );
                stmt.setLong( 3, job_id );
                stmt.setDate( 4, java.sql.Date.valueOf(date) );
                stmt.setString( 5, employee );
                stmt.setString( 6, type );
                stmt.setDouble( 7, Double.valueOf(hours) );
                stmt.setDouble( 8, Double.valueOf(rate) );
                stmt.setDouble( 9, Double.valueOf(comm) );
                stmt.setDouble( 10, Double.valueOf(other) );
                stmt.setDouble( 11, Double.valueOf(tip) );
                stmt.setDouble( 12, Double.valueOf(deduct) );
            }
            // old mover
            else {
                
                System.out.println( "paid is " + m.get( "paid" ) );
                System.out.println( "mover_job_id is " + m.get( "mover_job_id" ) );
                
                stmt = conn.prepareStatement( "insert into movers values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
                stmt.setLong( 1, mover_id );
                stmt.setLong( 2, company_id );
                stmt.setLong( 3, job_id );
                stmt.setDate( 4, java.sql.Date.valueOf(date) );
                stmt.setString( 5, employee );
                stmt.setString( 6, type );
                stmt.setDouble( 7, Double.valueOf(hours) );
                stmt.setDouble( 8, Double.valueOf(rate) );
                stmt.setDouble( 9, Double.valueOf(comm) );
                stmt.setDouble( 10, Double.valueOf(other) );
                stmt.setDouble( 11, Double.valueOf(tip) );
                stmt.setDouble( 12, Double.valueOf(deduct) );
                stmt.setString( 13, paid );                
                stmt.setLong( 14, Long.valueOf(mover_job_id.longValue()) ); 
            }       
            
            stmt.executeUpdate();
            
            /*
            sql = "insert into movers values ( ";
            sql += "(select mover_id from movers_list where name = '"+employee+"' and company_id = '"+company_id+"' )";
            sql += ",";
            sql += "'"+company_id+"'";
            sql += ",";
            sql += "'"+job_id+"'";
            sql += ",";
            sql += "'"+date+"'";
            sql += ",";
            sql += "'"+employee+"'";
            sql += ",";
            sql += "'"+type+"'";
            sql += ",";
            sql += "'"+hours+"'";
            sql += ",";
            sql += "'"+rate+"'";
            sql += ",";
            sql += "'"+comm+"'";
            sql += ",";
            sql += "'"+other+"'";
            sql += ",";            
            sql += "'"+tip+"'";
            sql += ",";            
            sql += "'"+deduct+"'";
            sql += " )";
            
            stmt = conn.createStatement();
            stmt.executeUpdate( sql );
             */
        }
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
        
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        String sql = "";
        CachedRowSet result = null;
 
        sql += "select job_date as date, name as employee, position as type, hours as hours, ";
        sql += "rate as rate, comm as comm, other as other, tip as tip, deduct as deduct, paid as paid, ";
        sql += "mover_job_id as mover_job_id, ";
        sql += "( hours * rate ) + comm + other + tip - deduct as total ";
        sql += "from movers where job_id = '"+job_id+"' and company_id = '"+company_id+"' ";
        
        result = new CachedRowSetImpl();
        result.setCommand( sql );
        result.execute( conn );
        
        Vector eh = new Vector();
        
        while( result.next() ) {
            Map tmp = new HashMap();
            
            tmp.put( "employee", result.getString("employee") );
            tmp.put( "type", result.getString("type") );
            tmp.put( "date", result.getDate("date").toString() );
            tmp.put( "hours", result.getDouble("hours") );
            tmp.put( "rate", result.getDouble("rate") );
            tmp.put( "comm", result.getDouble("comm") );
            tmp.put( "other", result.getDouble("other") );
            tmp.put( "tip", result.getDouble("tip") );
            tmp.put( "deduct", result.getDouble("deduct") );
            tmp.put( "total", result.getDouble("total") );
            tmp.put( "paid" , result.getString("paid") );
            tmp.put( "mover_job_id", result.getLong("mover_job_id") );
            
            eh.add( tmp );
        }
        _moversList = eh.toArray();
        
        result = new CachedRowSetImpl();
        result.setCommand( "select name from movers_list order by name asc" );
        result.execute( conn );
        
        _movers = new ArrayList();
        
        while( result.next() ) {
            _movers.add( result.getString("name") );
        }
        
    }
}
