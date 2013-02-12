/*
 * ContactVO.java
 *
 * Created on June 14, 2005, 2:53 PM
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
public class ContactVO {
    
    public Long   _job_id;
    public Long   _company_id;
    
    public ArrayList _employees;    
    public ArrayList _adv;
    
    public String _from_name;
    public String _from_address_1;
    public String _from_address_2;
    public String _from_county;
    public String _from_city;
    public String _from_state;
    public String _from_zip;
    public String _from_cross_street;
    public String _from_email;
    public String _from_home_phone;
    public String _from_fax;
    public String _from_work_phone;
    public String _from_cell_phone;
    
    public String _to_name;
    public String _to_address_1;
    public String _to_address_2;
    public String _to_county;
    public String _to_city;
    public String _to_state;
    public String _to_zip;
    public String _to_cross_street;
    public String _to_email;
    public String _to_home_phone;
    public String _to_fax;
    public String _to_work_phone;
    public String _to_cell_phone;
    
    /** Creates a new instance of ContactVO */
    public ContactVO() {
        _from_name = "";
        _from_address_1 = "";
        _from_address_2 = "";
        _from_county = "";
        _from_city = "";
        _from_state = "";
        _from_zip = "";
        _from_cross_street = "";
        _from_email = "";
        _from_home_phone = "";
        _from_fax = "";
        _from_work_phone = "";
        _from_cell_phone = "";
        
        _to_name = "";
        _to_address_1 = "";
        _to_address_2 = "";
        _to_county = "";
        _to_city = "";
        _to_state = "";
        _to_zip = "";
        _to_cross_street = "";
        _to_email = "";
        _to_home_phone = "";
        _to_fax = "";
        _to_work_phone = "";
        _to_cell_phone = "";        
    }
    
    public void print() {
        System.out.println( "_from_name " + _from_name );
        System.out.println( "_from_address_1 " + _from_address_1 );
        System.out.println( "_from_address_2 " + _from_address_2 );
        System.out.println( "_from_county " + _from_county );
        System.out.println( "_from_city " + _from_city );
        System.out.println( "_from_state " + _from_state );
        System.out.println( "_from_zip " + _from_zip );
        System.out.println( "_from_cross_street " + _from_cross_street );
        System.out.println( "_from_email " + _from_email );
        System.out.println( "_from_home_phone " + _from_home_phone );
        System.out.println( "_from_fax " + _from_fax );
        System.out.println( "_from_work_phone " + _from_work_phone );
        System.out.println( "_from_cell_phone " + _from_cell_phone );
        
        System.out.println( "_to_name " + _to_name );
        System.out.println( "_to_address_1 " + _to_address_1 );
        System.out.println( "_to_address_2 " + _to_address_2 );
        System.out.println( "_to_county " + _to_county );
        System.out.println( "_to_city " + _to_city );
        System.out.println( "_to_state " + _to_state );
        System.out.println( "_to_zip " + _to_zip );
        System.out.println( "_to_cross_street " + _to_cross_street );
        System.out.println( "_to_email " + _to_email );
        System.out.println( "_to_home_phone " + _to_home_phone );
        System.out.println( "_to_fax " + _to_fax );
        System.out.println( "_to_work_phone " + _to_work_phone );
        System.out.println( "_to_cell_phone " + _to_cell_phone );
    }    
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from fromcontact where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        stmt.execute( "delete from tocontact where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
        
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        
        //String sql;
        //Statement stmt;
        
        PreparedStatement eh = conn.prepareStatement( "insert into fromcontact values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );
        eh.setLong  ( 1, job_id );
        eh.setString( 2, _from_name );
        eh.setString( 3, _from_address_1 );
        eh.setString( 4, _from_address_2 );
        eh.setString( 5, _from_city );
        eh.setString( 6, _from_state );
        eh.setString( 7, _from_zip );
        eh.setString( 8, _from_cross_street );
        eh.setString( 9, _from_email );
        eh.setString( 10, _from_home_phone );
        eh.setString( 11, _from_fax );
        eh.setString( 12, _from_work_phone );
        eh.setString( 13, _from_cell_phone );
        eh.setString( 14, _from_county );
        eh.setLong  ( 15, company_id );
        eh.executeUpdate();
        
        eh = conn.prepareStatement( "insert into tocontact values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )" );        
        eh.setLong  ( 1, job_id );
        eh.setString( 2, _to_name );
        eh.setString( 3, _to_address_1 );
        eh.setString( 4, _to_address_2 );
        eh.setString( 5, _to_city );
        eh.setString( 6, _to_state );
        eh.setString( 7, _to_zip );
        eh.setString( 8, _to_cross_street );
        eh.setString( 9, _to_email );
        eh.setString( 10, _to_home_phone );
        eh.setString( 11, _to_fax );
        eh.setString( 12, _to_work_phone );
        eh.setString( 13, _to_cell_phone );
        eh.setString( 14, _to_county );
        eh.setLong  ( 15, company_id );
        eh.executeUpdate();
        
        /*
        stmt = conn.createStatement();
        sql = "";
        
        sql = "insert into fromcontact values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_from_name+"'";
        sql += ",";
        sql += "'"+_from_address_1+"'";
        sql += ",";
        sql += "'"+_from_address_2+"'";
        sql += ",";
        sql += "'"+_from_city+"'";
        sql += ",";
        sql += "'"+_from_state+"'";
        sql += ",";
        sql += "'"+_from_zip+"'";
        sql += ",";
        sql += "'"+_from_cross_street+"'";
        sql += ",";
        sql += "'"+_from_email+"'";
        sql += ",";
        sql += "'"+_from_home_phone+"'";
        sql += ",";
        sql += "'"+_from_fax+"'";
        sql += ",";
        sql += "'"+_from_work_phone+"'";
        sql += ",";
        sql += "'"+_from_cell_phone+"'";
        sql += ",";
        sql += "'"+_from_county+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );
         */
        /*
        sql = "insert into tocontact values ( ";
        sql += "'"+job_id+"'";
        sql += ",";
        sql += "'"+_to_name+"'";
        sql += ",";
        sql += "'"+_to_address_1+"'";
        sql += ",";
        sql += "'"+_to_address_2+"'";
        sql += ",";
        sql += "'"+_to_city+"'";
        sql += ",";
        sql += "'"+_to_state+"'";
        sql += ",";
        sql += "'"+_to_zip+"'";
        sql += ",";
        sql += "'"+_to_cross_street+"'";
        sql += ",";
        sql += "'"+_to_email+"'";
        sql += ",";
        sql += "'"+_to_home_phone+"'";
        sql += ",";
        sql += "'"+_to_fax+"'";
        sql += ",";
        sql += "'"+_to_work_phone+"'";
        sql += ",";
        sql += "'"+_to_cell_phone+"'";
        sql += ",";
        sql += "'"+_to_county+"'";
        sql += ",";
        sql += "'"+company_id+"'";
        sql += " )";
        
        stmt.executeUpdate( sql );        
         */
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {        
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        CachedRowSet result = new CachedRowSetImpl();
        result.setCommand( "select * from fromcontact where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() ) {            
            _from_name = result.getString( "name" );
            _from_address_1 = result.getString( "address1" );
            _from_address_2 = result.getString( "address2" );
            _from_county = result.getString( "county" );
            _from_city = result.getString( "city" );
            _from_state = result.getString( "state" );
            _from_zip = result.getString( "zip" );
            _from_cross_street = result.getString( "cross_street" );
            _from_email = result.getString( "email" );
            _from_home_phone = result.getString( "homephone" );
            _from_fax = result.getString( "homefax" );
            _from_work_phone = result.getString( "workphone" );
            _from_cell_phone = result.getString( "workfax" );
        }
        result = new CachedRowSetImpl();
        result.setCommand( "select * from tocontact where job_id = '"+job_id+"' and company_id = '"+company_id+"';" );
        result.execute( conn );
        while( result.next() ) {            
            _to_name = result.getString( "name" );
            _to_address_1 = result.getString( "address1" );
            _to_address_2 = result.getString( "address2" );
            _to_city = result.getString( "city" );
            _to_county = result.getString( "county" );
            _to_state = result.getString( "state" );
            _to_zip = result.getString( "zip" );
            _to_cross_street = result.getString( "cross_street" );
            _to_email = result.getString( "email" );
            _to_home_phone = result.getString( "homephone" );
            _to_fax = result.getString( "homefax" );
            _to_work_phone = result.getString( "workphone" );
            _to_cell_phone = result.getString( "workfax" );
        }
        
        result = new CachedRowSetImpl();
        result.setCommand( "select username from employee where company_id = '"+company_id+"' and role != 'Mover' and role != '' order by username asc" );
        result.execute( conn );
        
        _employees = new ArrayList();
        
        while( result.next() ) {
            _employees.add( result.getString( "username" ) );
        }        
        
        result = new CachedRowSetImpl();
        result.setCommand( "select adv from adv order by lower(adv) asc" );
        result.execute( conn );
        
        _adv = new ArrayList();
        
        while( result.next() ) {
            _adv.add( result.getString( "adv" ) );
        }      
    }
}
