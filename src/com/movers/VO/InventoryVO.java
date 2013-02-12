/*
 * InventoryVO.java
 *
 * Created on June 23, 2005, 4:04 PM
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
public class InventoryVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Object[] _inventoryList;
    public String   _test;
    
    /** Creates a new instance of InventoryVO */
    public InventoryVO() {
        _inventoryList = new Object[0];
        _test          = "";
    }
    
    public void print() {
        for( int i=0; i< _inventoryList.length; i++ ) {
            System.out.println( _inventoryList[i].toString() );
        }
    }
    
    public void update( Connection conn, Long job_id, Long company_id ) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute( "delete from inventory where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        /*
        System.out.println( "test is " + _test );
        String sql = "";
        Statement stmt = null;
        */
        for( int i=0; i< _inventoryList.length; i++ ) {
            
            Map m = (Map)_inventoryList[i];
            
 
            String item = m.get( "item" ).toString();
            String lbs = m.get( "lbs" ).toString();
            String cuft = m.get( "cuft" ).toString();
            
            String pack = m.get( "pack" ).toString();
            String unpack = m.get( "unpack" ).toString();
            
            String qty = m.get( "qty" ).toString();
            String pack_qty = m.get( "pack_qty" ).toString();
            String unpack_qty = m.get( "unpack_qty" ).toString();
            
            
            // this is fine as long as we make sure 
            // nothing but numbers get through from the 
            // flex end
            PreparedStatement stmt = conn.prepareStatement( "insert into inventory values( ?,?,?,?,?,?,?,?,?,? )" );
      
            stmt.setLong  ( 1 , job_id );
            stmt.setString( 2 , item );
            stmt.setDouble( 3 , Double.valueOf(qty) );
            stmt.setDouble( 4 , Double.valueOf(cuft) );
            stmt.setString( 5 , pack );
            stmt.setDouble( 6 , Double.valueOf(pack_qty) );
            stmt.setString( 7 , unpack );
            stmt.setDouble( 8 , Double.valueOf(unpack_qty) );
            stmt.setDouble( 9 , Double.valueOf(lbs) );
            stmt.setLong  ( 10, company_id );
  
            stmt.executeUpdate();
            
            /*
            sql = "insert into inventory values ( ";
            sql += "'"+job_id+"'";
            sql += ",";
            sql += "'"+item+"'";
            sql += ",";
            sql += "'"+qty+"'";
            sql += ",";
            sql += "'"+cuft+"'";
            sql += ",";
            sql += "'"+pack+"'";
            sql += ",";
            sql += "'"+pack_qty+"'";
            sql += ",";
            sql += "'"+unpack+"'";
            sql += ",";
            sql += "'"+unpack_qty+"'";
            sql += ",";
            sql += "'"+lbs+"'";
            sql += ",";
            sql += "'"+company_id+"'";
            sql += " )";
            
            stmt = conn.createStatement();
            stmt.executeUpdate( sql );
             */
        }
        return( true );
    }
    
    // retarded type converstions from java -> actionscript
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {        
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        String sql = "";
        CachedRowSet result = null;
        
        sql += "select article as item, quantity as qty, ";
        sql += "cubic_feet as cuft, pack as pack, ";
        sql += "qty_pack as pack_qty, unpack as unpack, ";
        sql += "qty_unpack as unpack_qty, lbs as lbs ";
        sql += " from inventory where job_id = '"+job_id+"' and company_id = '"+company_id+"'";
        
        result = new CachedRowSetImpl();
        result.setCommand( sql );
        result.execute( conn );
        
        Vector v = new Vector();
        while( result.next() ) {
            Map m = new HashMap();
            m.put( "item", result.getString("item") );
            m.put( "qty" , result.getDouble("qty") );            
            m.put( "cuft" , result.getDouble("cuft") );
            m.put( "pack" , result.getString("pack") );
            m.put( "unpack" , result.getString("unpack") );
            m.put( "pack_qty" , result.getDouble("pack_qty") );
            m.put( "unpack_qty" , result.getDouble("unpack_qty") );
            m.put( "lbs" , result.getDouble("lbs") );
            v.add( m );
        }
        _inventoryList = v.toArray();      
    }
}
