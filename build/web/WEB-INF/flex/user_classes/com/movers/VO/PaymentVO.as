class com.movers.VO.PaymentVO
{
  public function PaymentVO()
  {
    _remoteClass = "com.movers.VO.PaymentVO";
  }

  public function getTotalPaid()
  {
    var paid:Number = 0;
    for( var i=0; i<_paymentList.length; i++ )
    {
      var rec = _paymentList.getItemAt(i);
      paid += stripFormatting( String(rec.amount) );
    }
    return( paid );
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

  public var _job_id      : Number;
  public var _company_id  : Number;
      
  public var _paymentList : Array;
}
