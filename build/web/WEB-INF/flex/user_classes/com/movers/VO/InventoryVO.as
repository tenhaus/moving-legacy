class com.movers.VO.InventoryVO
{
  public function InventoryVO()
  {
    _remoteClass = "com.movers.VO.InventoryVO";
  }

  public function getTotalCuft()
  {
    var cuft:Number = 0;

    for( var i=0; i<_inventoryList.length; i++ ) {
      var rec = _inventoryList.getItemAt(i);
      if( Number(rec.cuft) )
      {
        cuft += Number( rec.cuft ) * Number( rec.qty );
      }
    }
    return( cuft );
  }

  public function getTotalWeight()
  {
    var weight:Number = 0;

    for( var i=0; i<_inventoryList.length; i++ ) {
      var rec = _inventoryList.getItemAt(i);
      if( Number(rec.lbs) )
      {
        weight += Number( rec.lbs ) * Number( rec.qty );
      }
    }
    return( weight );
  }

  public var _remoteClass:String;

  public var _job_id          : Number;
  public var _company_id      : Number;
      
  public var _inventoryList   :  Array;
  public var _test            :  String;
}
