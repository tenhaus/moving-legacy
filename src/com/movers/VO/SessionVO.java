/*
 * SessionVO.java
 *
 * Created on July 7, 2005, 7:32 PM
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
public class SessionVO {
     
    public Number _employee_id;
    public String _username;
    public String _employee;
    public String _ext;
    public String _email;
    public String _role;
    
    public Number _company_id;
    
    public String _company_name;
    public String _company_address;
    public String _company_city;
    public String _company_state;
    public String _company_zip;
    public String _company_phone;

    /** Creates a new instance of SessionVO */
    public SessionVO() {
    }
    
        public void load( Connection conn, String username ) throws Exception {
        
        CachedRowSet result = new CachedRowSetImpl();
        
        this._username = username;
        result.setCommand( "SELECT employee_id, name as employee, ext, email, role, company_id  from employee where username = '"+username+"'" );
        result.execute( conn );        
        
        while( result.next() ) {
            _employee_id = result.getLong( "employee_id" );
            _employee    = result.getString( "employee" );
            _ext         = result.getString( "ext" );
            _email       = result.getString( "email" );
            _role        = result.getString( "role" );
            _company_id  = result.getLong( "company_id" );
        }
        
        result.setCommand( "SELECT name as company_name, address as company_address, city as company_city, state as company_state, zip as company_zip, phone as company_phone from company where company_id = '"+_company_id+"'" );
        result.execute( conn );  
        
        while( result.next() ) {
            _company_name = result.getString( "company_name" );
            _company_address = result.getString( "company_address" );
            _company_city = result.getString( "company_city" );
            _company_state = result.getString( "company_state" );
            _company_zip = result.getString( "company_zip" );
            _company_phone = result.getString( "company_phone" );
        }
    }
}
