/*
 * PaymentVO.java
 *
 * Created on July 6, 2005, 10:23 AM
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
public class PaymentVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Object[] _paymentList;
    
    /** Creates a new instance of PaymentVO */
    public PaymentVO() {
        _paymentList = new Object[0];
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
    
    public void print() {
        for( int i=0; i< _paymentList.length; i++ ) {
            System.out.println( _paymentList[i].toString() );
        }
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from payment where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        /*
        String sql = "";
        Statement stmt = null;
         */
        
        for( int i=0; i< _paymentList.length; i++ ) {
            
            Map m = (Map)_paymentList[i];
            
            String date = m.get( "date" ).toString();
            
            String type = m.get( "type" ).toString();
            String amount = stripFormatting( m.get( "amount" ).toString() );
            String description = m.get( "description" ).toString();
            String payment_id = m.get( "payment_id" ).toString();
            
            /*
            sql = "insert into payment values ( ";
            sql += "'"+job_id+"'";
            sql += ",";
            sql += "'"+company_id+"'";
            sql += ",";
            sql += "'"+date+"'";
            sql += ",";
            sql += "'"+type+"'";
            sql += ",";
            sql += "'"+amount+"'";
            sql += ",";
            sql += "'"+description+"'";
            sql += ",";
            if( payment_id.equals("") || payment_id == null )
            {                
                sql += "default";            
            }
            else {
                Number pay = (Number)m.get( "payment_id" );                
                sql += "'"+pay.longValue()+"'";            
            }            
            sql += " )";
            
            stmt = conn.createStatement();
            stmt.executeUpdate( sql );            
             */
            
            String[] parts = date.split( "-" );        
            date = parts[2] + "-" + parts[0] + "-" + parts[1];
            
            // new payment
            if( payment_id.equals("") || payment_id == null )
            {
                PreparedStatement stmt = conn.prepareStatement( "insert into payment values ( ?,?,?,?,?,? )" );            
                stmt.setLong( 1, job_id );
                stmt.setLong( 2, company_id );
                stmt.setDate( 3, java.sql.Date.valueOf(date) );
                stmt.setString( 4, type );
                stmt.setDouble( 5,  Double.valueOf(amount) );
                stmt.setString( 6, description );                
                stmt.executeUpdate();
            }
            // old payment
            else {
                PreparedStatement stmt = conn.prepareStatement( "insert into payment values ( ?,?,?,?,?,?,? )" );            
                stmt.setLong( 1, job_id );
                stmt.setLong( 2, company_id );
                stmt.setDate( 3, java.sql.Date.valueOf(date) );
                stmt.setString( 4, type );
                stmt.setDouble( 5,  Double.valueOf(amount) );
                stmt.setString( 6, description );
                Number pay = (Number)m.get( "payment_id" );                
                stmt.setLong( 7, pay.longValue() );
                stmt.executeUpdate();
            }
        }
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        String sql = "";
        CachedRowSet result = null;
    
        sql += "select payment_date as date, type as type, ";
        sql += "payment_id as payment_id, ";
        sql += "amount as amount, description as description ";        
        sql += "from payment where job_id = '"+job_id+"' and company_id = '"+company_id+"'";
        
        result = new CachedRowSetImpl();
        result.setCommand( sql );
        result.execute( conn );
        
        Vector eh = new Vector();
        
        while( result.next() ) {
            Map tmp = new HashMap();           
            tmp.put( "date" , result.getDate("date").toString() );
            tmp.put( "type", result.getString("type") );
            tmp.put( "amount", result.getDouble("amount") );
            tmp.put( "description", result.getString("description") );
            tmp.put( "payment_id", result.getLong("payment_id") );
            eh.add( tmp );
        }
        _paymentList = eh.toArray();
    }
}
