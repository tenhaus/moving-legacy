/*
 * MaterialsVO.java
 *
 * Created on June 23, 2005, 4:06 PM
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
public class MaterialsVO {
    public Long   _job_id;
    public Long   _company_id;
    
    public Object[] _materialsList;
    
    /** Creates a new instance of MaterialsVO */
    public MaterialsVO() {
        _materialsList = new Object[0];
    }
    
    public void print() {
        for( int i=0; i< _materialsList.length; i++ ) {
            System.out.println( _materialsList[i].toString() );
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
        stmt.execute( "delete from materials where company_id = '"+company_id+"' and job_id = '"+job_id+"'");
        commit( conn, job_id, company_id );
    }
    
    public Boolean commit( Connection conn, Long job_id, Long company_id ) throws Exception {
        /*
        String sql = "";;
        Statement stmt = null;
         */
        
        for( int i=0; i< _materialsList.length; i++ ) {
            
            Map m = (Map)_materialsList[i];
            
            String item = m.get( "item" ).toString();
            String pack = m.get( "pack" ).toString();
            String packing = m.get( "packing" ).toString();
            String unpack = m.get( "unpack" ).toString();
            String unpacking = m.get( "unpacking" ).toString();
            
            String price = m.get( "price" ).toString();
            String material = m.get( "material" ).toString();
            String extra = m.get( "extra" ).toString();
            
            String qty = m.get( "qty" ).toString();
            
            packing = stripFormatting( packing );
            unpacking = stripFormatting( unpacking );
            price = stripFormatting( price );
            material = stripFormatting( material );
            extra = stripFormatting( extra );
            
            PreparedStatement stmt = conn.prepareStatement( "insert into materials values ( ?,?,?,?,?,?,?,?,?,?,? )" );
            stmt.setLong  ( 1,  job_id );
            stmt.setString( 2,  item );
            stmt.setDouble( 3,  Double.valueOf(qty) );
            stmt.setDouble( 4,  Double.valueOf(price) );
            stmt.setString( 5,  pack );
            stmt.setDouble( 6,  Double.valueOf(packing) );
            stmt.setString( 7,  unpack );
            stmt.setDouble( 8,  Double.valueOf(unpacking) );
            stmt.setDouble( 9,  Double.valueOf(material) );
            stmt.setDouble( 10, Double.valueOf(extra) );
            stmt.setLong  ( 11, company_id );
            
            stmt.executeUpdate();
            /*
            sql = "insert into materials values ( ";
            sql += "'"+job_id+"'";
            sql += ",";
            sql += "'"+item+"'";
            sql += ",";
            sql += "'"+qty+"'";
            sql += ",";
            sql += "'"+price+"'";
            sql += ",";
            sql += "'"+pack+"'";
            sql += ",";
            sql += "'"+packing+"'";
            sql += ",";
            sql += "'"+unpack+"'";
            sql += ",";
            sql += "'"+unpacking+"'";
            sql += ",";
            sql += "'"+material+"'";
            sql += ",";
            sql += "'"+extra+"'";
            sql += ",";
            sql += "'"+company_id+"'";
            sql += " )";
            
            stmt = conn.createStatement();
            stmt.executeUpdate( sql );
             */
        }
        
        /*
        stmt = conn.createStatement();
        sql = "";
         
        stmt.executeUpdate( sql );
         */
        
        return( true );
    }
    
    public void load( Connection conn, Long job_id, Long company_id ) throws Exception {
                
        this._job_id = job_id;
        this._company_id = company_id;
        
        String sql = "";
        CachedRowSet result = null;
    
        sql += "select item as item, quantity as qty, ";
        sql += "price as price, pack as pack, unpack as unpack, ";
        sql += "packing_fee as packing, unpacking_fee as unpacking, ";
        sql += "material_fee as material, extra as extra ";
        sql += "from materials where job_id = '"+job_id+"' and company_id = '"+company_id+"'";
        
        result = new CachedRowSetImpl();
        result.setCommand( sql );
        result.execute( conn );
        
        Vector eh = new Vector();
        
        while( result.next() ) {
            Map tmp = new HashMap();
            tmp.put( "item", result.getString("item") );
            tmp.put( "qty" , result.getDouble("qty") );
            tmp.put( "price" , result.getDouble("price") );
            tmp.put( "pack" , result.getString("pack") );
            tmp.put( "unpack" , result.getString("unpack") );
            tmp.put( "packing" , result.getDouble("packing") );
            tmp.put( "unpacking" , result.getDouble("unpacking") );
            tmp.put( "material" , result.getDouble("material") );
            tmp.put( "extra" , result.getDouble("extra") );
            eh.add( tmp );
        }
        _materialsList = eh.toArray();
    }
}
