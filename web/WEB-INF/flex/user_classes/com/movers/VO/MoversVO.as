class com.movers.VO.MoversVO
{
  public function MoversVO()
  {
    _remoteClass = "com.movers.VO.MoversVO";
  }

  public var _remoteClass:String;

  public var _job_id     : Number;
  public var _company_id : Number;

  // list of movers
  public var _movers     : Array;
 
  // the selected movers
  public var _moversList : Array;
}
