class com.movers.VO.MaterialsVO
{
  public function MaterialsVO()
  {
    _remoteClass = "com.movers.VO.MaterialsVO";
  }

  public function getTotalPrice()
  {
    var total:Number = 0;
    for( var i=0; i< _materialsList.length; i++ ) {
      var rec = _materialsList.getItemAt(i);
      total += stripFormatting( rec.price ) * rec.qty;
    }
    return( total );
  }
  
  public function stripFormatting( num:String ) : Number
  {
    if( num == null )
    {
      return(0);
    }

    var result:String = "";
    for( var i=0; i<num.length; i++ )
    {
      if( num.charAt(i) != '$' && num.charAt(i) != ',' )
      {
        result += num.charAt(i);
      }
    }
    
    if( Number(result) )
    {
      return( Number(result) );
    }
    else
    {
      return(0);
    }
  }
													

  public var _remoteClass:String;

  public var _job_id        : Number;
  public var _company_id    : Number;
      
  public var _materialsList : Array;
}
